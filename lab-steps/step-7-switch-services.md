## Step 7 – Switch services (Optional)

### /clone - Download and Build sea-lion Project

Before we install and start SonarQube, we need to utilize our local Nexus repository to build the sea-lion project.

1. Start Nexus if it is not already running using the command:
**docker start nexus**

1. You&#39;ll need a local copy of the sea-lion-project folder located at:

[https://github.com/boyarsky/OracleCodeOne2018-HOL-Automating-Stack-Groovy/tree/master/sea-lion-project](https://github.com/boyarsky/OracleCodeOne2018-HOL-Automating-Stack-Groovy/tree/master/sea-lion-project)

For this step, it&#39;s probably easier to just download/clone the entire project repository if you haven&#39;t already:

[https://github.com/boyarsky/OracleCodeOne2018-HOL-Automating-Stack-Groovy](https://github.com/boyarsky/OracleCodeOne2018-HOL-Automating-Stack-Groovy)

3. Navigate (cd) into the sea-lion-project folder

1. Start a gradle build by executing the following command (the ./ prefix is required for Linux/Mac, but not Windows)

./gradlew build

5. The build should finish with the message BUILD SUCCESSFUL.

### 7.2 - Stop Nexus and Jenkins to free up some resources

SonarQube tends to use a lot of CPU and memory.  To improve performance for the next section of this lab, we can save a good deal of memory usage by turning off Jenkins and Nexus used in the previous labs by running the following two commands:

- docker stop jenkins

- docker stop nexus

Note: If you want to use Nexus and/or Jenkins again later, just run the command with start instead of stop

### 7.3 - Install SonarQube

At the command line, run:

**docker run -d -p 9000:9000 -p 9092:9092 --name sonarqube --network hol-network sonarqube:7.1**

Note: this uses the in memory database. For a real install, you&#39;ll want to use an external database.

_Validation:_

In a browser, go to http://localhost:9000. It might take about a minute before the URL returns a webpage.