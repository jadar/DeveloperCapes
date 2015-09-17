Developer Capes
=============

###Notice: This project is no longer actively maintained. You are free to fork, update, and use it as the license permits. Thank you.

####Version: 3.4.1.x

A Minecraft library for adding Developer/Tester only capes!
Use this to add tester or developer only capes to your mod!
Developer Capes reads a text file off a web server, public Dropbox folder, or a raw GitHub file. As long as it is not a download site, (such as MediaFire,) it should work.

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
3. Copy everything in src/main/java/ to the root of your mod source code.

<a name="usage"/>
###Usage:

Once Developer Capes is in your classpath somehow, you are going to want to add this to your mod when it initialized. It registers a cape config with DeveloperCapes. 

*Note: The note that was previously here, that said DevCapes didn't have to go in a client proxy, has been redacted. You need to put config registration in the client proxy again*

```java
DevCapes.getInstance().registerConfig($CAPES_JSON, $IDENTIFIER);
```

`$CAPES_JSON` is the `URL` (in `Object` or `String` form,) `InputStream`, or `File` to a text file hosted on a server, Dropbox, or GitHub, that has all the data Developer Capes needs to add your capes formatted in JSON. `$IDENTIFIER` is a unique mod identifier so DevCapes can keep one mod's groups separate from the other. 

The JSON for the config looks like this:
```json
{
    "Group1": {
        "capeUrl": "http://www.example.com/group1_cape.png",
        "users": [
            "JadarMC",
            "MinerID642"
        ]
    },
    "Captain_Shadows": "http://www.example.com/captain_cape.png"
}
```
  You are going to want to make sure there is a comma after every element in the array, or string, etc, -except- when it is the last one in it's scope. If you do the logs will make this very clear.

#####Images:
The cape image files are 22x17, and should be of the PNG format. Additionally, Developer Capes supports high definition capes. Those must be of the size that is divisible by 16. The height must be half of the width. So 1024x512, 2048x1024, and so on. Host them on a server, Dropbox, or GitHub. 

<a name="building">
###Building and Packaging your Mod:
When you build and package your mod, you're going to want to make sure you build with the Developer Capes classes.

Thats it! If you have any trouble, make a post in the Minecraft Forums post or make an issue here on GitHub.

<a name="faq">
###FAQ:
####1. I'm getting a NullPointerException when starting a dedicated server:
You're calling a @SideOnly(Side.CLIENT) class on a server. STOP IT. Put it in a ClientProxy.

####2. I'm getting a NoClassDefFoundError and/or a RuntimeExcption (related to an invalid side) on a dedicated server:
See answer to question 1.
