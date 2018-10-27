## Step 0: Prerequisites and Tools

This lab requires the following:

1. Docker
2. Docker images
3. Groovy

### 0.1 Basic Requirements – PLEASE READ!

#### Space Requirements

For this lab, you will need approximately **2 gigabytes** of space. See Appendix I at the end of this lab for instructions on how to reclaim disk space after you have completed the lab.

#### Ports Requirements

For this lab, we will be using default ports of **8080** , **8081** , **9000**.  If you already have services running on your computer, you will either need to stop those services or change the ports you use to install the various services throughout this lab.

#### Docker ID Requirements

In order to download Docker from the Docker Store and install the software, you will need to have a Docker ID account.  If you do not have one, you can create one for free at:

[https://store.docker.com/signup](https://store.docker.com/signup)

Please create a Docker ID account now if you do not already have one.

**Note: If you have any odd Java error messages in this lab, try using Java 8 instead of 9 or 10 or …**

### 0.2 - Docker

To install Docker, follow the instructions at using your Docker ID account:

[https://docs.docker.com/install](https://docs.docker.com/install)

If your OS doesn&#39;t support Docker, you won&#39;t be able to do the lab on your machine. You can ask another attendee to pair with you.

_Validation:_

At the command line, run **docker –version** (Small version differences are ok in the output)

$ **docker --version**

        Docker version 18.06.0-ce, build 0ffa825

### 0.3 - Docker Images

At the command line run:

```
docker pull sonatype/nexus3:3.13.0
docker pull jenkins/jenkins:2.146
docker pull sonarqube:7.1
```

**Note: &quot;latest&quot; will probably work. These version numbers are the ones we tested with.**

**$ docker pull sonatype/nexus3:3.13.0**

```3.13.0: Pulling from sonatype/nexus3
256b176beaff: Pull complete
18d124afa1e9: Pull complete
9bb412307f82: Pull complete
Digest: sha256:19d186d5bc8be1ea4f7bae72756baa830e79bf20aae0e9e7b1a0c7d3ce7ac136
Status: Downloaded newer image for sonatype/nexus3:3.13.0
```
**$ docker pull jenkins/jenkins:2.146**
```
2.146: Pulling from jenkins/jenkins
55cbf04beb70: Pull complete
1607093a898c: Pull complete
9a8ea045c926: Pull complete
d4eee24d4dac: Pull complete
c58988e753d7: Pull complete
794a04897db9: Pull complete
70fcfa476f73: Pull complete
806029475e0c: Pull complete
67959b355155: Pull complete
4d217ccd3d4c: Pull complete
0261bb88a4a5: Pull complete
96f2a3ae5539: Pull complete
f6bf99db32d5: Pull complete
bb47d4bbb0e1: Pull complete
4b48ec5d60cf: Pull complete
7280a8dfb767: Pull complete
91091f8d44ca: Pull complete
8ca02cad320f: Pull complete
46009bfec329: Pull complete
f9860b79812e: Pull complete
89ac8103ea67: Pull complete
Digest: sha256:161cb25fbb23a1c5ac5fdd0feebd713edd62c235e199e68b34d1a78205a42da7
Status: Downloaded newer image for jenkins/jenkins:2.146
```
**$ docker pull sonarqube:7.1**
```
7.1: Pulling from library/sonarqube
55cbf04beb70: Already exists
1607093a898c: Already exists
9a8ea045c926: Already exists
d4eee24d4dac: Already exists
c58988e753d7: Already exists
794a04897db9: Already exists
70fcfa476f73: Already exists
806029475e0c: Already exists
67959b355155: Already exists
1e6b3af7f55a: Pull complete
e0b67c57c8e1: Pull complete
ce12e009fbe7: Pull complete
3edf8e47f9c4: Pull complete
Digest: sha256:4438a37735caa24d80da31ee29e72d686abdaa8f5009746ec60e0d43519e1a57
Status: Downloaded newer image for sonarqube:7.1
```
### 0.4 – Optional: Groovy

If you want to use Groovy in this lab (vs running everything on the server), ensure JDK 8 is on your path and then follow the instructions at: [http://groovy-lang.org/install.html](http://groovy-lang.org/install.html)

Note: Please download Groovy 2.X rather than 3.X. Version 3.X is in alpha at the time of writing this lab.

Validation:

At the command line, run **groovy –version** (Small version differences are ok in the output)

$ **groovy -version**
```
Groovy Version: 2.5.2 JVM: 1.8.0\_45 Vendor:
Oracle Corporation OS: Mac OS X
```