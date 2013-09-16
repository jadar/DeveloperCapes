Developer Capes
=============

####Version: 2.1

A Minecraft library for adding Developer/Tester only capes!
Use this to add tester or developer only capes to your mod!
Developer Capes reads a text file off a web server, public Dropbox folder, or a raw GitHub file. As long as it is not a download site, (such as MediaFire,) it should work.

*Note: Previously called DeveloperCapesAPI, DeveloperCapes was renamed because Jadar realized he was stupid. And that this is a library.*

### Table of Contents  
[Links](#links)  
[Getting Started](#gettingStarted)  
[Guide: Set up with Source Code](#guideSourceCode)  
[Usage](#usage)  
[Building](#building)
[FAQ](#faq)

<a name="links"/>
###Links:
[Minecraft Forum Post](http://www.minecraftforum.net/topic/1725536-151apilibraryforge-developer-capes-api-now-with-hd-cape-support/)  

<a name="gettingStarted"/>
###Getting Started:
To start using Developer Capes, [setup your development enviroment](#guideSetUp). See [usage](#usage) to find out how to implement the library in your mod. 

<a name="guideSetUp"/>
###Setting Up the Development Environment:
1. Download source code as a zip by clicking "Download Zip" on the right.  
2. Unzip to a tempory directory. You can delete this once the source is copied.  
3. Copy everything in src/ to the root of your mod source code.
4. Next open up your IDE and refresh the project. You'll notice 3 errors in ```DevCapesTickHandler```. This is normal.
5. Go into ```DevCapesTickHandler``` and mouse over one of the errors. Click the solution that will change the access of the field in ```AbstractClientPlayer``` to public.
  - You can also use the included ```patchfiles``` script. Although it does require command line to input a working MCP directory, it will patch ```AbstractClientPlayer.java``` automaticly. Also you're going to want to ```cd``` into the directory that contains the scripts. Usage is ```patchfiles.cmd/.sh <mcp location>```.  
6. Do this for the other one. There are only 2. 
7. You are now all set up. Go on to [Usage](#usage) to add cape files.

<a name="usage"/>
###Usage:

  Once Developer Capes is in your classpath somehow, you are going to want to add this to your mod when it initialized.  
*Note: Does not need to go into a ClientProxy method anymore. Can go anywhere, really.*
```java
DevCapesUtil..addFileUrl($CAPES_TXT_URL);
```
*Note: Do not call DevCapeUtil.getInstance() as it is deprecated and should not be used unless you know what you're doing. It'll throw an Exception if you're on a server and not calling from a client proxy class, or checking for server/client side.*

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
You can have as many groups as you want. Just name them separately.
Also, you should probably make a unique name for your groups so as
you do not conflict with other mods using the library as well.

#####Images:
The cape image files are 22x17, and should be of the PNG format. Additionally, Developer Capes supports high definition capes. Those must be of the size that is divisible by 16. The height must be half of the width. So 1024x512, 2048x1024, and so on. Host them on a server, Dropbox, or GitHub. 

<a name="building">
###Building and Packaging your Mod:
When you build and package your mod, you're going to want to do a few extra things.

1. Make sure you build with the Developer Capes classes.
2. When you reobfuscate Minecraft, do not package the obfuscated class. It is the AbstractClientPlayer class that you modified/pathed while setting up the environment for Developer Capes.  

Thats it! If you have any trouble, make a post in the Minecraft Forums post or make an issue here on GitHub.

<a name="faq">
###FAQ:
####1. I'm getting a NullPointerException when starting a dedicated server:
You're probably trying to register a file url in a way that is not recommended. Use DevCapesUtil.addFileUrl() instead!
If that's not what you're doing then make an issue on GitHub.

####2. I'm getting a NoClassDefFoundError and/or a RuntimeExcption (related to an invalid side) on a dedicated server:
See answer to question 1.
