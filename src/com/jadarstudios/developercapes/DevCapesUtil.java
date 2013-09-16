/**
 * DeveloperCapes by Jadar
 * License: MIT License (https://raw.github.com/jadar/DeveloperCapes/master/LICENSE)
 * version 2.1
 */
package com.jadarstudios.developercapes;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

/**
 * Wraps methods from DevCapes so it does not crash due to client-only class references.
 * Use this class to add a file URL instead of DevCapes to avoid a crash when starting a server.
 * @author Jadar
 */
public class DevCapesUtil {

	/**
	 * Wrapper for getting the DevCapes singleton.
	 */
	public static DevCapes getInstance() {
		return DevCapes.getInstance();
	}

	/**
	 * Wrapper for setting up capes. 
	 * *Will not run if on a server.*
	 * 
	 * All cape URLs are in the txt file passed in.
	 * https://github.com/jadar/DeveloperCapesAPI/blob/master/SampleCape.txt
	 * 
	 * @param parTxtUrl
	 *            The URL of the .txt file containing the groups, members of
	 *            said groups, and the group's cape URL.
	 */
	public static void addFileUrl(String parTxtUrl) {
	    if(FMLCommonHandler.instance().getSide().equals(Side.CLIENT))
	        getInstance().addFileUrl(parTxtUrl);
	}
}
