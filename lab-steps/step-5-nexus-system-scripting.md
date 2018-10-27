## Step 5: Nexus System Scripting

### 5.1 - Learn what objects are available

Nexus exposes some variables to Groovy for ease in scripting:

- core
- repository
- blobStore
- security
- container

For this to be useful, you need to know the class name of each. This allows you to use JavaDoc (or autocomplete) to determine what methods are available.

Write your own Groovy task to output the classes in use here. (See the solution guide if you get stuck)

Note: Looking at the source code for these classes in the IDE, it is possible to find the interfaces as well. The technique of using a Groovy task to learn more about a class remains useful.

### 5.2 - Create global access and Jenkins access

Jenkins needs access to Nexus. While it can download open source jars without credentials, it does need to provide credentials to upload the artifacts from Osprey and Sea Lion projects. We don&#39;t want to use the admin id for this! We also need to create an upload role and the repositories.

We used a combination of IDE autocomplete and examples online to create the Groovy script.

1. Copy the code from

[https://github.com/boyarsky/OracleCodeOne2018-HOL-Automating-Stack-Groovy/blob/master/nexus-scripts/src/main/groovy/nexusBasicSetup.groovy](https://github.com/boyarsky/OracleCodeOne2018-HOL-Automating-Stack-Groovy/blob/master/nexus-scripts/src/main/groovy/nexusBasicSetup.groovy)

2. Run it in a Groovy task in Nexus.

1. In the left navigation, click on Roles and see that there is now a jenkins-role.

### 5.3 - Setup Nexus Access

Now we are going to create content selectors, privileges and roles to configure access. The Sea Lion team wants their jars only available to their team. The Osprey team wants their jars available to all logged in users, but not anonymous users.



1. Copy the code from

[https://github.com/boyarsky/OracleCodeOne2018-HOL-Automating-Stack-Groovy/blob/master/nexus-scripts/src/main/groovy/teamSetup.groovy](https://github.com/boyarsky/OracleCodeOne2018-HOL-Automating-Stack-Groovy/blob/master/nexus-scripts/src/main/groovy/teamSetup.groovy)

2. Run it in a Groovy task in Nexus.

1. Now your turn. Try writing the Groovy code to set up the Sea Lion&#39;s access. Remember that they get access to the Osprey code in addition to their own.

Note: You probably noticed it is a bit tedious to run the Nexus tasks. Luckily, Nexus provides REST APIs. However, much of this REST API is the Scripting API we have been using. So you both upload and run the script directly from your machine (or a build server).