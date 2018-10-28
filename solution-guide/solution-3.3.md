# 3.3 Writing your own script in Nexus

## Task

Now try to write your own Groovy script that prints out a countdown. 

## Solution

````groovy
(10..1).each{ log.info '--> ' + it }
log.info 'Blast off!'
````