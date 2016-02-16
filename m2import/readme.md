This script allows you to upload artifacts from your local m2 repository to your Bintray packages.
It might be useful for uploading your old builds or some 3rd-party libraries which can't be found elsewhere.
It accepts two arguments, <code>-u</code> for username:apiKey and <code>-i</code> for comma separated list of json files with package descriptions.

The sample script invocation is the following:
<code>m2import.groovy -u jbaruch:34sfgsf343 -i sample.json,anotherSample.json<code>

The sample input file is the following:
<pre><code>{
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
</code></pre>

You can find number of sample files in this directory.

*Note: organizational repostories aren't currently supported.*
