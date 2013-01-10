import groovyx.net.http.RESTClient
import org.apache.ivy.util.cli.CommandLineParser

import static groovyx.net.http.Method.POST
import static groovyx.net.http.ContentType.JSON
def http = new RESTClient('https://api.bintray.net/')
http.auth.basic 'shayy', '79427e0e162b89b2d81b210ce8d4e4375f5a2d91653f4abf0eb03a24b7212725'
try {
    // Expect an exception from a 404 response:
    http.get(path: '/packages/shayy/repo/' + 'shshsh') {
        println 'blabla'
    }
} catch (ex) {
    if(ex.response.status == 404) {
        println 'Package ${project.group} does not exist in Bintray, trying to create it...'
        http.request(POST, JSON) {
            uri.path = '/packages/shayy/repo'
            body = [name: 'shshsh', desc: 'auto', desc_url: 'auto', labels: 'gradle']
            //println "Creating Bintray package response status: ${postResp.statusLine}"
        }
    }
}