DonorCapesAPI
=============

Version: 1.0

A Minecraft API for adding Donor/Tester only capes!
Use this to add tester or donor only capes to your mod!


Usage:

In your preInit method add this:

DonorCapesAPI.init(*CAPES TXT*, 
  			*DONOR CAPE URL*,
				*TESTER CAPE URL*);


The capes txt is a text file hosted on a server (or Dropbox) that has all the users you want to have capes in. The layout looks like this

donor: username1
donor: username2
tester: username3
tester: username4

Make sure to have the space between ':' and the username.

The cape image files are 22x17, and should be of the PNG format.
