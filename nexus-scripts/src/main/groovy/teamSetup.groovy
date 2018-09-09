import org.sonatype.nexus.script.plugin.*
import org.sonatype.nexus.selector.*
import org.sonatype.nexus.security.*
import org.sonatype.nexus.common.app.*;
import org.sonatype.nexus.common.entity.*
import org.sonatype.nexus.security.authz.*

// most of this code is based off of https://gist.github.com/nblair/1a0e05713c3edb7e5360c2b0222c7623
// with changes for CSEL content selector syntax


// use container.lookup to fetch internal APIs we need to use
def selectorManager = container.lookup(SelectorManager.class.name)
def securitySystem = container.lookup(SecuritySystem.class.name)
def authorizationManager = securitySystem.getAuthorizationManager('default')

// create content selector (if not already present)
def selectorConfig = new SelectorConfiguration(
    name: 'sea-lion-selector',
    type: 'csel',
    description: 'sea-lion packages',
    attributes: ['expression': 'format == "maven2" and coordinate.groupId =^ "net.selikoff.oraclecodeone.groovy.sea-lion"']
)
if (selectorManager.browse().find { it -> it.name == selectorConfig.name } == null) {
  selectorManager.create(selectorConfig)
}

// create content selector privilege for release repo
def releaseProperties = ['contentSelector' : selectorConfig.name, 
   'repository' : 'custom-releases', 'actions' : 'read,edit']
def releasePrivilege = new org.sonatype.nexus.security.privilege.Privilege(
	id: "sea-lion-release-priv",
	version: '',
	name: "sea-lion-release-priv",
	description: "Content Selector Release privilege",
	type: "repository-content-selector",
	properties: releaseProperties
)
authorizationManager.addPrivilege(releasePrivilege)

// create content selector privilege for snapshot repo
def snapshotProperties = ['contentSelector' : 'sea-lion-selector',
	'repository' : 'custom-snapshots', 'actions' : 'read,edit']
def snapshotPrivilege = new org.sonatype.nexus.security.privilege.Privilege(
	id: "sea-lion-snapshot-priv",
	version: '',
	name: "sea-lion-snapshot-priv",
	description: "Content Selector Snapshot privilege",
	type: "repository-content-selector",
	properties: snapshotProperties
)
authorizationManager.addPrivilege(snapshotPrivilege)

// create a role with the snapshot and release privileges
def role = new org.sonatype.nexus.security.role.Role(
	roleId: "sea-lion-role",
	source: "Nexus",
	name: "sea-lion-role",
	description: "Sea Lion Role",
	readOnly: false,
	privileges: [ snapshotPrivilege.id, releasePrivilege.id ],
	roles: []
)
authorizationManager.addRole(role)

// add a local user account with the role
security.addUser("sam", "Sam", "S", "sam@none.com", true, "sam", [ 'sea-lion-role', 'nx-anonymous' ])