DeveloperCapesAPI
=============

##Version: 1.3

A Minecraft API for adding Developer/Tester only capes!
Use this to add tester or Developer only capes to your mod!

#Usage:
For now, make a new method in your CommonProxy and ClientProxy classes called capesInit or something (Or just use your registerRenderInformation sort of method). Leave the Common one blank and put this in the ClientProxy one. We do this so it only executes on client side. Put this in it.

```java
DeveloperCapesAPI.getInstance().init(*CAPES TXT*);
```

Then in your preInit or init method in your main mod class just call the capesInit method.

The capes txt is a text file hosted on a server (or Dropbox) that has all the users you want to have capes in. It also has all the cape urls in it. The layout looks like this
```
developer=http://www.myurl.com/DEV_CAPE.png
developer=username1
developer=username2
tester=http://www.myurl.com/TESTER_CAPE.png
tester=username3
tester=username4
```
Make sure there is no space between '=' and the username or url.

The cape image files are 22x17, and should be of the PNG format. Host them on a server or Dropbox. Maybe I'll add client hosted capes.
