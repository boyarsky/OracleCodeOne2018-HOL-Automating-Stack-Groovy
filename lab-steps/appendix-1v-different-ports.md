## Appendix IV: Running with Different Ports

# Step 1.2 - Tell Nexus it is running with a different port

Nexus has the port embedded in it. So you can't just change the port on your machine; you need to use that port in both.  Remember which port you chose as you'll need to change it later. (Yes, we are changing the port inside Docker too)
**docker run -d -p 8089:8089 --name nexus --network hol-network sonatype/nexus3:3.13.0**

````
docker exec -it nexus bash
cd nexus-data
cd etc
vi nexus.properties
````
Uncomment the line about application port and hchange the port you used
application-port=8089

Then exit vi and the container.

Finally,
````
docker restart nexus
````

If you forgot to do step 1.2, your sea lion and osprey builds will fail with:
````
09:22:23 * What went wrong:
09:22:23 Execution failed for task ':publishMavenPublicationToSnapshotsRepository'.
09:22:23 > Failed to publish publication 'maven' to repository 'snapshots'
09:22:23    > Could not write to resource 'http://nexus:8081/repository/custom-snapshots/net/selikoff/oraclecodeone/groovy/osprey/osprey-project/0.0.1-SNAPSHOT/osprey-project-0.0.1-20181028.132223-1.jar'.
09:22:23       > Could not PUT 'http://nexus:8081/repository/custom-snapshots/net/selikoff/oraclecodeone/groovy/osprey/osprey-project/0.0.1-SNAPSHOT/osprey-project-0.0.1-20181028.132223-1.jar'. Received status code 401 from server: Unauthorized
````

# Step 6.2 - Tell Jenkins the new port for Nexus

Before running the job-generator script, change 8081 to the port you are using in the textarea. This change needs to made in two places

Note: If you forget to make this change, just delete the osprey-project and sea-lion-project jobs and recreate them.

If you forgot to do step 6.2, your sea lion and osprey builds will fail with:
````
09:20:34 * What went wrong:
09:20:34 Could not resolve all files for configuration ':compileClasspath'.
09:20:34 > Could not resolve commons-io:commons-io:2.6.
09:20:34   Required by:
09:20:34       project :
09:20:34    > Could not resolve commons-io:commons-io:2.6.
09:20:34       > Could not get resource 'http://nexus:8089/repository/maven-central/commons-io/commons-io/2.6/commons-io-2.6.pom'.
09:20:34          > Could not HEAD 'http://nexus:8089/repository/maven-central/commons-io/commons-io/2.6/commons-io-2.6.pom'.
09:20:34             > Connect to nexus:8089 [nexus/172.18.0.2] failed: Connection refused (Connection refused)
````

