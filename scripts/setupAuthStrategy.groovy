import hudson.security.*
import jenkins.model.*

def instance = Jenkins.getInstance()
def auth = new ProjectMatrixAuthorizationStrategy()  

// enable all permissions for administrator
Permission.all
  .grep { it.enabled }
  .each { auth.add(it, "admin")  }

// for authenticated users, give overall login and job/view level permissions
Permission.all
  .grep { it.enabled }
  .grep { it.id.equals('hudson.model.Hudson.Read') || 
          it.id.startsWith('hudson.model.Item') || 
          it.id.startsWith('hudson.model.Run') || 
          it.id.startsWith('hudson.model.View') }
  .each { auth.add(it, 'authenticated')  }

instance.setAuthorizationStrategy(auth)
instance.save()
