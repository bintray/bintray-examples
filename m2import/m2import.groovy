//uncomment the following line for shell integration. It drives github's language detection crazy
//#!/usr/bin/env groovy

@GrabResolver(name = 'jcenter', root = 'http://jcenter.bintray.com/') @Grab(group = 'org.codehaus.groovy.modules.http-builder', module = 'http-builder', version = '0.6')
import groovy.json.JsonSlurper
import groovyx.net.http.HTTPBuilder
import org.apache.http.entity.FileEntity
@Grab(group = 'apache-log4j', module = 'log4j', version = '1.2.15')
import org.apache.log4j.xml.DOMConfigurator

import static groovy.io.FileType.FILES
import static groovyx.net.http.ContentType.JSON
import static groovyx.net.http.Method.*
import static java.io.File.separatorChar
import static java.lang.System.err
import static java.lang.System.exit
import static org.apache.commons.cli.Option.UNLIMITED_VALUES

/**
 *
 * @author jbaruch
 * @since 08/04/13
 */

def excludes = [~/_maven.repositories/, ~/maven-metadata.*/, ~/.*\.sha1/]

//uncomment for HTTP trace
//DOMConfigurator.configure('log4j.xml')

CliBuilder cli = new CliBuilder(usage: "${this.class.name}.groovy -u -i", header: 'Available options:',
        footer: '''The sample of an input file:
{
    "bintray": {
        "repo": "jbaruch-maven",
        "package": "org.apache.wicket"
    },
    "maven": {
        "localRepoPath": "/data/maven/repository", //optional, ~/.m2/repository will be used
        "group": {
            "groupId": "org.apache.wicket",
            "artifactIds": ["wicket-core","wicket-utils"], //optional, everything will be imported
            "versions": ["1.5.2","1.5.3"] //optional, all the versions will be imported
        }
    }
}
''').with {
    u longOpt: 'user', 'Specify the user name and password to use for server authentication',
            args: 2, valueSeparator: ':', argName: 'username:password', required: true
    i longOpt: 'input', 'One or more comma separated input file names with import settings. ',
            args: UNLIMITED_VALUES, valueSeparator: ',', argName: 'a.json,b.json,...', required: true
    it
}
def options = cli.parse(args)
if (!options) {
    exit 87
}
String user = options.us[0]
String password = options.us[1]
List<String> inputs = options.is

inputs.each { input ->
    println "Parsing $input"
    final FileReader reader = new FileReader(input)
    if (!reader) {
        err.println("$input not found. Please verify file path")
        exit 2
    }
    def json = new JsonSlurper().parse(reader)
    String repository
    String packge
    String m2repo
    String group
    List<String> artifacts
    List<String> versions

    json.bintray.with {
        repository = repo
        packge = delegate.'package'
    }
    json.maven.with {
        m2repo = localRepoPath ?: "${System.getProperty('user.home')}/.m2/repository"
    }

    json.maven.group.with {
        group = groupId
        artifacts = artifactIds ?: []
        versions = delegate.versions ?: []
    }

    println "Bintray user to use is $user"
    println "Bintray repository to upload to is $repository"
    println "Bintray package to upload to is $packge"
    println "Using m2 repository at $m2repo"
    println "Upload is limited to $group groupId"
    println artifacts ? "Upload is limited to artifactIds from the following list $artifacts" : 'All found artifacts will be uploaded.'
    println versions ? "Upload is limited to versions from the following list $versions" : 'All found versions will be uploaded.'

    File root = new File(m2repo, group.replace('.', '/'))
    if (!root.directory) {
        err.println("$root.absolutePath does not exist, please check the 'localRepoPath' and 'groupId' values")
        exit 3
    }

    def createPackage = false
    HTTPBuilder bintray = new HTTPBuilder('https://api.bintray.com', JSON)
    bintray.parser.'application/json' = { '' } //we don't need to parse any response and this crap doesn't know that 201 might not return any response
    bintray.auth.basic user, password
    bintray.request(GET) {
        uri.path = "/packages/$user/$repository/$packge"
        response.'404' = {
            println "Package $packge wasn't found, creating."
            createPackage = true
        }
        response.failure = {
            err.println "Failed to check for package $packge: $it.message"
            exit it.status
        }
    }
    if (createPackage) {
        bintray.request(POST, JSON) {
            uri.path = "/packages/$user/$repository"
            body = [name: packge]
            response.failure = {
                err.println "Failed to create package $packge: $it.statusLine.reasonPhrase."
                exit it.status
            }
        }
    }

    def counter = 0
    root.eachFileRecurse(FILES) { File file ->
        switch (file.name) {
            case 'pom.xml':
                err.println("Looks like $root is Maven project directory, when it should be Maven local repository.")
                exit 87
                break; //stupid intelliJ
            case {
                it.endsWith('pom') && (versions ? versions.contains(file.parentFile.name) : true) && (artifacts ? artifacts.contains(file.parentFile.parentFile.name) : true)
            }:
                File dir = file.parentFile
                Set<File> files = dir.listFiles({ File directory, String name ->
                    !excludes.any { name.matches it }
                } as FilenameFilter)
                files << file
                files.each { uploadedFile ->
                    print "uploading $uploadedFile.name..."
                    bintray.request(PUT, JSON) { req ->
                        uri.path = "/maven/$user/$repository/$packge${(uploadedFile.absolutePath - new File(m2repo).absolutePath).replace(separatorChar, '/' as char)}"
                        req.entity = new FileEntity(uploadedFile)
                        response.success = {
                            counter ++
                            println 'Done.'
                        }
                        response.'409' = {
                            err.println "WARNING: Conflict - file $uploadedFile.name was already uploaded."
                        }
                        response.failure = {
                            err.println "Failed to upload file $file: $it.statusLine.reasonPhrase."
                            exit it.status
                        }
                    }
                }
        }
    }
    println "$counter artifacts from $input were sucessfully uploaded to https://bintray.com/pkg/show/general/$user/$repository/$packge. Don't forget to publish!"
}