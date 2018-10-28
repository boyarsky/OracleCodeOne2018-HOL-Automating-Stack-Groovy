# 6.6 Editing a Pipeline Job

## Task

The Sea Lions have decided they want to deploy to Nexus on every other build. For even numbered builds they want to just print a message to the console that they are skipping deploy. For odd numbered builds, they want the pipeline to behave normally.

## Solution

````groovy
stage ('Publish') {
  def buildNum = env['BUILD_NUMBER'] as Integer
  if (buildNum % 2 == 0) {
     println 'skip publish'
  } else {
     withCredentials…
  }
}
   ````