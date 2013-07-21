Developer Capes
=============

####Version: 2.0

A Minecraft library for adding Developer/Tester only capes!
Use this to add tester or developer only capes to your mod!

*Note: Previously called DeveloperCapesAPI, DeveloperCapes was renamed because Jadar realized he was stupid. And that this is a library.*

### Table of Contents  
[Links](#links)
[Getting Started](#gettingStarted)  
[Guide: Set up with Pre-Compiled Jar](#guideJar)  
[Guide: Set up with Source Code](#guideSourceCode)  
[Usage](#usage)  

<a name="links"/>
###Links:
[Minecraft Forum Post](http://www.minecraftforum.net/topic/1725536-151apilibraryforge-developer-capes-api-now-with-hd-cape-support/)

[Jenkins build server](http://ci.jadarstudios.com/job/Developer-Capes)

<a name="gettingStarted"/>
###Getting Started:
There are 2 ways to add Developer Capes to your classpath.

1. Download a pre-compiled version from Jenkins. You are going to have to add it to your project classpath, and the MCP mods directory. To release just put the DeveloperCapes jar in your jar file. [Guide](#guideJar)
2. Download the source code from GitHub and add it to your project. To test with MCP you will have to add an argument to the Virtual Machine to work correctly.  [Guide](#guideSourceCode)

<a name="guideJar"/>
###Guide: Set up with Pre-Compiled Jar:

Download the pre-compiled jar from Jekins.

####Setting up build environment.
You are going to want to 


<a name="guideSourceCode"/>
###Guide: Set up with Source Code:

<a name="usage"/>
###Usage:

Once Developer Capes is in your classpath somehow, you are going to want to add this to your mod when it initilizes.
```java
DevcCapesUtil.getInstance().addTxtUrl(*CAPES FILE URL*);
```

The "*CAPES FILE URL*" is the URL to a text file hosted on a server, Dropbox, or GitHub that has all the data Developer Capes needs to add your cape. The layout looks like this:
```
developer=http://www.myurl.com/DEV_CAPE.png
developer=username1
developer=username2
tester=http://www.myurl.com/TESTER_CAPE.png
tester=username3
tester=username4
```
Make sure there is no space between '=' and the username or url.
You can have as many groups as you want. Just name them seperately.
Also, you should probably make a unique name for your groups so as
you do not conflict with other mods using the library as well.


The cape image files are 22x17, and should be of the PNG format. Additionally, Developer Capes supports high definition capes. Those must be of the size that is divisible by 16. The height must be half of the width. So 1024x512, 2048x1024, and so on. Host them on a server, Dropbox, or GitHub. 

<a name="building"/>
###Building:

To build a 
