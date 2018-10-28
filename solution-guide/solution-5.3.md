# 5.3 Setup Nexus access

## Task

Try writing the Groovy code to set up the Sea Lion’s access. Remember that they get access to the Osprey code in addition to their own

## Solution

````groovy
def seaLionSelectorConfig = new SelectorConfiguration(
name: 'sea-lion-selector',
type: 'csel',
description: 'sea-lion packages',
attributes: ['expression': 'format == "maven2" and coordinate.groupId =^ "net.selikoff.oraclecodeone.groovy.sea-lion"']
)
if (selectorManager.browse().find { it -> it.name == seaLionSelectorConfig.name } == null) {
  selectorManager.create(seaLionSelectorConfig)
}

def seaLionReleaseProperties = ['contentSelector' : seaLionSelectorConfig.name,
'repository' : 'custom-releases', 'actions' : 'browse,read,edit']
def seaLionReleasePrivilege = new org.sonatype.nexus.security.privilege.Privilege(
 id: "sea-lion-release-priv",
 version: '',
 name: "sea-lion-release-priv",
 description: "Content Selector Release privilege",
 type: "repository-content-selector",
 properties: seaLionReleaseProperties
)

def seaLionSnapshotProperties = ['contentSelector' : 'sea-lion-selector',
 'repository' : 'custom-snapshots', 'actions' : 'browse,read,edit']
def seaLionSnapshotPrivilege = new org.sonatype.nexus.security.privilege.Privilege(
id: "sea-lion-snapshot-priv",
 version: '',
 name: "sea-lion-snapshot-priv",
 description: "Content Selector Snapshot privilege",
 type: "repository-content-selector",
 properties: seaLionSnapshotProperties
)
addOrReplacePrivilege(seaLionReleasePrivilege)
addOrReplacePrivilege(seaLionSnapshotPrivilege)

def seaLionRole = new org.sonatype.nexus.security.role.Role(
roleId: "sea-lion-role",
source: "Nexus",
name: "sea-lion-role",
description: "Sea Lion Role",
readOnly: false,
privileges: [ seaLionSnapshotPrivilege.id, seaLionReleasePrivilege.id, ospreySnapshotPrivilege.id, ospreyReleasePrivilege.id ],
roles: []
)
addOrReplaceRole(seaLionRole)

if (! securitySystem.searchUsers(new UserSearchCriteria('sam')))
   security.addUser("sam", "Sam", "S", "sam@none.com", true, "sam", [ 'sea-lion-role', 'limited-anon' ])
if (! securitySystem.searchUsers(new UserSearchCriteria('sophia')))
   security.addUser("sophia", "Sophia", "S", "sophia@none.com", true, "sophia", [ 'sea-lion-role', 'limited-anon' ])

````