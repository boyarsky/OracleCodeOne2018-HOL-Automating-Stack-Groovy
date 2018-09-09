import org.sonatype.nexus.script.plugin.*;
import org.sonatype.nexus.repository.maven.*;
import org.sonatype.nexus.repository.storage.*;
import org.sonatype.nexus.blobstore.api.*;

// uncomment when need autocomplete (comment for commit so Nexus uses built in variables)
// RepositoryApi repository;
// SecurityApi security;

// create repositories (using default blob-store since this is just a lab)
repository.createMavenHosted("custom-snapshots", BlobStoreManager.DEFAULT_BLOBSTORE_NAME, true, VersionPolicy.SNAPSHOT, WritePolicy.ALLOW_ONCE, LayoutPolicy.STRICT)
// release uses all the defaults so we don't have to specify all the parameters
repository.createMavenHosted("custom-release")
 

// create a role with all Maven view/upload privileges
createRole()

security.addUser("jenkins", "Jenkins", "Integration", "none@email.com", true, "jenkins123", ["jenkins-role"])

def createRole() {
  privileges = [
    "nx-search-read",
    "nx-repository-view-maven2-*-read",
    "nx-repository-view-maven2-*-browse",
    "nx-repository-view-maven2-*-add",
    "nx-repository-view-maven2-*-edit"]
  nestedRoles = []
  security.addRole("jenkins-role", "jenkins-role", "deploy to Maven repos", privileges, nestedRoles)

}