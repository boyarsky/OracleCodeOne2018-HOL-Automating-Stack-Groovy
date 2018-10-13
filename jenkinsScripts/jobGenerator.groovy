import org.jenkinsci.plugins.scriptsecurity.scripts.*
import jenkins.model.*

def gitUrl = build.buildVariableResolver.resolve("gitUrl")
def gitFolder = build.buildVariableResolver.resolve("gitFolder")

def xml = """
<flow-definition plugin="workflow-job@2.25">
<actions/>
<description/>
<keepDependencies>false</keepDependencies>
<properties/>
<definition class="org.jenkinsci.plugins.workflow.cps.CpsFlowDefinition" plugin="workflow-cps@2.54">
<script>
// Powered by Infostretch 

timestamps {

node () {

	stage ('Checkout') {
 	 checkout([\$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '', url: '$gitUrl']]]) 
	}
	stage ('Build') {
	    
	   withCredentials([usernamePassword(credentialsId: 'nexus', passwordVariable: 'nexusPassword', usernameVariable: 'nexusUserName')]) {
          sh "./$gitFolder/gradlew clean build -b $gitFolder/build.gradle -PnexusBaseUrl=http://nexus:8081 -PnexusUserName=\${nexusUserName} -PnexusPassword=\${nexusPassword}"
       }

		// JUnit Results
		junit '$gitFolder/build/test-results/test/*.xml' 
	}
	
	stage ('Publish') {
	    
	   withCredentials([usernamePassword(credentialsId: 'nexus', passwordVariable: 'nexusPassword', usernameVariable: 'nexusUserName')]) {
          sh "./$gitFolder/gradlew publish -b $gitFolder/build.gradle -PnexusBaseUrl=http://nexus:8081 -PnexusUserName=\${nexusUserName} -PnexusPassword=\${nexusPassword}"
       }
	}
}
}
</script>
<sandbox>false</sandbox>
</definition>
<triggers/>
<disabled>false</disabled>
</flow-definition>"""

// create job
def stream = new ByteArrayInputStream(xml.bytes)
def job =Jenkins.instance.createProjectFromXML(gitFolder, stream)
job.save()

// approve all pending script approvals (don't do this blindly on a real project)
def sa = ScriptApproval.get()
// make a copy of the list to avoid concurrent modification exception
def pending = sa.pendingScripts.collect() 
pending.each { sa.approveScript(it.hash)
}
println "Created job $job.fullName"
