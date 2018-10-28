# 5.1 Learn what objects are available

## Task

Write your own Groovy task to output the classes in use here

## Solution

````groovy
log.info "Core:  ${core.class}"
log.info "Repository:  ${repository.class}"
log.info "Blob Store:  ${blobStore.class}"
log.info "Security:  ${security.class}"
log.info "Container:  ${container.class}"
````

Output
````
Core:  class org.sonatype.nexus.internal.provisioning.CoreApiImpl
Repository:  class org.sonatype.nexus.script.plugin.internal.provisioning.RepositoryApiImpl
Blob Store:  class org.sonatype.nexus.internal.provisioning.BlobStoreApiImpl
Security:  class org.sonatype.nexus.security.internal.SecurityApiImpl
Container: class
org.sonatype.nexus.internal.app.GlobalComponentLookupHelperImpl
````