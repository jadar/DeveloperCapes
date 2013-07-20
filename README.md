Developer Capes
=============

##Version: 2.0

A Minecraft library for adding Developer/Tester only capes!
Use this to add tester or developer only capes to your mod!

##### Table of Contents  
[Getting Started](#Getting Started)  
[Guide: Set up with Source Code:](#Guide: Set up with Source Code:)  
[Guide: Set up with Source Code:](#Guide: Set up with Source Code:)  
[Usage](#Usage)  

#Getting Started:
There are 2 ways to use DeveloperCapes.

1) Download a pre-compiled version from Jenkins. You are going to have to add it to your project classpath, and the MCP mods directory. To release just put the DeveloperCapes jar in your jar. Guide.
2) Download the source code from GitHub and add it to your project. To test with MCP you will have to add an argument to the Virtaul Machine to work correctly.  Guide.

#Guide: Set up with Source Code:

#Guide: Set up with Pre-Compiled Jar:

#Usage:
```java
DeveloperCapesAPI.getInstance().init(*CAPES TXT*);
```

Then in your preInit or init method in your main mod class just call the capesInit method.

The capes.txt is a text file hosted on a server, Dropbox, or GitHub that has all the users you want to have capes in. It also has all the cape urls in it. The layout looks like this
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


The cape image files are 22x17, and should be of the PNG format. Host them on a server or Dropbox. Maybe I'll add client hosted capes.
