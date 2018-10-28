# 9 Extra credit

## Task

Jenkins validation logic

## Solution

````groovy
def validateProject(job, expectedProject) {
  
  def jobXml = job.configFile.asString()
  // get the code inside the tags (?s) matches line breaks
  def groovyCode = jobXml
    .replaceFirst('(?s)^.*<script>', '')
    .replaceFirst('(?s)</script>.*$', '')
  // remove expected gradle config
  def codeWithoutGoodBuildGradleCalls = groovyCode.replaceAll("$expectedProject/build.gradle", '');
  // fail if any others
  if (codeWithoutGoodBuildGradleCalls.contains('build.gradle')) {
    throw new IllegalStateException("${job.name} calls a build.gradle file for a different project.")
  }  
}

jenkins.model.Jenkins.instance.getAllItems()
   .grep { it.name.contains 'osprey' }
   .forEach { validateProject(it, 'osprey-project') }

jenkins.model.Jenkins.instance.getAllItems()
   .grep { it.name.contains 'sea-lion' }
   .forEach { validateProject(it, 'sea-lion-project') }

   ````