## Appendix I: Cleanup

### I.1 Stop containers:

Remember you can enter the first couple characters of the hash rather than the whole thing.
```
docker ps
docker stop nexus
docker stop jenkins
docker stop sonarqube
```
### II.2 Reclaiming Disk Space

#### Remove containers
```
docker ps –a
docker rm sonarqube
docker rm jenkins
docker rm nexus
```

#### Remove images:
```
docker images
docker rmi <hash1>
docker rmi <hash2>
docker rmi <hash3>
```

#### Delete configuration

```
docker network rm hol-network
```


