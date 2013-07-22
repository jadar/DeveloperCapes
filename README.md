Developer Capes
=============

####Version: 2.0

[![Build Status](http://ci.jadarstudios.com/job/Developer-Capes/badge/icon)](http://ci.jadarstudios.com/job/Developer-Capes/)

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
[Jenkins Builds](http://ci.jadarstudios.com/job/Developer-Capes)  

<a name="gettingStarted"/>
###Getting Started:
There are 2 ways to add Developer Capes to your classpath.

1. Download a pre-compiled version from Jenkins. You are going to have to add it to your project classpath, and the MCP mods directory. To release just put the DeveloperCapes jar in your jar file. [Guide](#guideJar)
2. Download the source code from GitHub and add it to your project. To test with MCP you will have to add an argument to the Virtual Machine to work correctly.  [Guide](#guideSourceCode)

<a name="guideJar"/>
###Guide: Set up with Pre-Compiled Jar:

1. Download the latest successful pre-compiled jar from [Jekins](http://ci.jadarstudios.com/job/Developer-Capes). [![Build Status](http://ci.jadarstudios.com/job/Developer-Capes/badge/icon)](http://ci.jadarstudios.com/job/Developer-Capes/)
2. You are going to want to put the jar at the root of your code directory.
3. Add the jar to your classpath.
  
    ####Eclipse
    - Right-click the project your code is in.
    - Navigate to "Build Path -> Configure Build Path."
    - Click "Jars", then "Add Jars."
    - Add the jar you downloaded that is in the root directory of your project.
  
    ####Other
    - If you know how to add jars to classpaths on other IDEs, write it up and submit a pull request.
4. You may need to add it to your MCP mods/ directory to test. I'm haven't tested if that is needed, yet.

<a name="guideSourceCode"/>
###Guide: Set up with Source Code:

<a name="usage"/>
###Usage:

  Once Developer Capes is in your classpath somehow, you are going to want to add this to your mod when it initilizes.  
*Note: Does not need to go into a ClientProxy method anymore. Can go anywhere, really.*
```java
DevCapesUtil.getInstance().addTxtUrl($CAPES_TXT_URL);
```

`$CAPES_TXT_URL` is the URL to a text file hosted on a server, Dropbox, or GitHub that has all the data Developer Capes needs to add your capes. The layout looks like this:
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

#####Images:
The cape image files are 22x17, and should be of the PNG format. Additionally, Developer Capes supports high definition capes. Those must be of the size that is divisible by 16. The height must be half of the width. So 1024x512, 2048x1024, and so on. Host them on a server, Dropbox, or GitHub. 

<a name="building"/>
###Building:
If you'd like to compile your own jar then you may do so using the provided Ant build script.

#####Requirements:
[Java JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html)  
[Apache Ant](http://ant.apache.org/) (Must be added to your PATH..)  
Git client  

#####Performing the build:
1. Clone this repository to an empty folder, or you may use your existing one.
2. Open up Command Prompt or a Unix terminal equivalent and change the directory to the base directory where `build.xml` is located.
3. Execute `ant build`.
4. The script will now procede to download MCP, decompile Minecraft, patch files, add source, recompile Minecraft, and package a jar.
5. If you would like to use your own Forge/MCP then in the same directory as `build.xml` make a directory called `currentBuild`. Then add the `forge/` directory to it. It should have the decompiled `mcp/` directory in it.

That should be it. The outputed jar is in `currentBuild\builds`.
