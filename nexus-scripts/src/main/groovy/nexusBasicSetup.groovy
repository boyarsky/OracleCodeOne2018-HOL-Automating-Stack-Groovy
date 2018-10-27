import org.sonatype.nexus.script.plugin.*
import org.sonatype.nexus.security.*
import org.sonatype.nexus.repository.maven.*
import org.sonatype.nexus.repository.storage.*
import org.sonatype.nexus.blobstore.api.*
import org.sonatype.nexus.repository.manager.*
import org.sonatype.nexus.security.user.*
import org.sonatype.nexus.security.role.*

// uncomment when need autocomplete (comment for commit so Nexus uses built in variables)
 RepositoryApi repository;
 SecurityApi security;

repositoryManager = container.lookup(RepositoryManager.class.name)
securitySystem = container.lookup(SecuritySystem.class.name)
authorizationManager = securitySystem.getAuthorizationManager('default')

// -------------------------------------------------------------------------

// create repositories (using default blob-store since this is just a lab)
if (! repositoryManager.exists("custom-snapshots")) {
  repository.createMavenHosted("custom-snapshots", BlobStoreManager.DEFAULT_BLOBSTORE_NAME, true, VersionPolicy.SNAPSHOT, WritePolicy.ALLOW_ONCE, LayoutPolicy.STRICT)
}
// release uses all the defaults so we don't have to specify all the parameters
if (! repositoryManager.exists("custom-releases")) {
	repository.createMavenHosted("custom-releases")
}

// -------------------------------------------------------------------------
 
// limit access for anonymous
updateAnonymousPrivileges()

// create a role with all Maven view/upload privileges
createJenkinsRole()

if (! securitySystem.searchUsers(new UserSearchCriteria('jenkins'))) {
  security.addUser("jenkins", "Jenkins", "Integration", "none@email.com", true, "jenkins123", ["jenkins-role"])
}

// -------------------------------------------------------------------------

def createJenkinsRole() {
  privileges = [
    "nx-search-read",
    "nx-repository-view-maven2-*-read",
    "nx-repository-view-maven2-*-browse",
    "nx-repository-view-maven2-*-add",
    "nx-repository-view-maven2-*-edit"]
  nestedRoles = []
  
  if (! authorizationManager.listRoles().grep { it.roleId == 'jenkins-role' }) {
    security.addRole("jenkins-role", "jenkins-role", "deploy to Maven repos", privileges, nestedRoles)
  }	

}
// -------------------------------------------------------------------------
def updateAnonymousPrivileges() {
	def limitedAnonRole = new RoleIdentifier('default', 'limited-anon')
	def anonUser = securitySystem.getUser('anonymous')
	anonUser.setRoles([ limitedAnonRole ].toSet())
	securitySystem.updateUser(anonUser)
}