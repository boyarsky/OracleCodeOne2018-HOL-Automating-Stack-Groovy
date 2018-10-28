### 2.3 Writing your own script in Jenkins

## Task

Fill in the blank to print out all the strings that end with “day”.

````groovy
def list = ['monday', 'wednesday', 'chocolate', 'friday']
println ______________________________________
````


## Solution

Groovy way:

````groovy
def list = ['monday', 'wednesday', 'chocolate', 'friday']
println list.grep { it.endsWith 'day' }
````

Java developers might be more comfortable with this:

````groovy
def list = ['monday', 'wednesday', 'chocolate', 'friday']
println list.grep { d -> d.endsWith('day') }
````

