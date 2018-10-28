# 4.2 Creating a user

## Task

Add four more lines of code so Owen, Sam, Sophia and Daisy get accounts. 

## Solution

````groovy
import jenkins.model.Jenkins
def instance = Jenkins.getInstance()
def realm = Jenkins.getInstance().securityRealm
realm.createAccount('olivia', 'olivia')
realm.createAccount('owen', 'owen')
realm.createAccount('sam', 'sam')
realm.createAccount('sophia', 'sophia')
realm.createAccount('daisy', 'daisy')
instance.save()
````