## Appendix II:  Corrupted Jenkins Roles

"Uh oh. I managed to make it so the admin user doesn't have read access to Jenkins"

If you manage to change permissions so admin no longer has admin rights, you have two choices on how to fix it

### II.1 Faster way – install your favorite text editor

1. Launch bash as root

**docker exec -it --user root jenkins bash**

2. Install your favorite text editor. We choose vim

Note: If you get an error running the following, omit the -y flag
````
apt-get update
apt-get install –y vim
````

3. Open the config.xml

**vim /var/jenkins_home/config.xml**

4. Update the authorization strategy and security realm to the following:

```html
<authorizationStrategy class="hudson.security.FullControlOnceLoggedInAuthorizationStrategy">
   <denyAnonymousReadAccess>true</denyAnonymousReadAccess>
</authorizationStrategy>

<securityRealm class="hudson.security.HudsonPrivateSecurityRealm">
   <disableSignup>true</disableSignup>
   <enableCaptcha>false</enableCaptcha>
</securityRealm>
```
Alternatively, you can set the assigned side to your id in the XML.

Note: You might need to re-enter the initial password from /var/jenkins_home/secrets

5. Exit your text editor

6. Exit bash

7. Restart Jenkins

**docker restart jenkins**

###   II.2 Slower way – delete your docker container/image and start over

1. docker rm jenkins

2. Follow the setup again (the run command and all future steps)



