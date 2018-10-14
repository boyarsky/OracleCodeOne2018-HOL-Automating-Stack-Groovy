import hudson.security.*
import jenkins.model.*
import com.michelin.cio.hudson.plugins.rolestrategy.*

def instance = Jenkins.getInstance()
def auth = new RoleBasedAuthorizationStrategy()  

// enable all permissions for administrators
def allPermissionIds = Permission.all
  .grep { it.enabled }
  .collect { it.id }
  .join(',')
auth.doAddRole('globalRoles', 'admin', allPermissionIds, 'true', '.*')
auth.doAssignRole('globalRoles', 'admin', 'admin')

// all authenticated users can login
def readOnly = 'hudson.model.Hudson.Read'
auth.doAddRole('globalRoles', 'authenticated', readOnly, 'true', '.*')

// create roles for Ospreys
def jobReadOnlyPermissions = 'hudson.model.Item.Read,hudson.model.Item.Discover,hudson.model.Item.Workspace'
auth.doAddRole('projectRoles', 'authenticated', jobReadOnlyPermissions, 'true', 'osprey.*')

def allJobAndRunPermissions = Permission.all
  .grep { it.enabled }
  .grep { it.id.startsWith('hudson.model.Item.') || it.id.startsWith('hudson.model.Run.') }
  .collect { it.id }
  .join(',')
auth.doAddRole('globalRoles', 'osprey-team', readOnly, 'true', '.*')
auth.doAddRole('projectRoles', 'osprey-team', allJobAndRunPermissions, 'true', 'osprey.*')

auth.doAssignRole('globalRoles', 'osprey-team', 'olivia')
auth.doAssignRole('globalRoles', 'osprey-team', 'owen')
auth.doAssignRole('projectRoles', 'osprey-team', 'olivia')
auth.doAssignRole('projectRoles', 'osprey-team', 'owen')
auth.doAssignRole('projectRoles', 'authenticated', 'olivia')
auth.doAssignRole('projectRoles', 'authenticated', 'owen')

instance.setAuthorizationStrategy(auth)
instance.save()

println 'Done'