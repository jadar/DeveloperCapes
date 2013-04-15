/**
 * Copyright (c) Jadar, 2013
 * Developer Capes API by Jadar
 * License: Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 * version 1.3.1
 */
package com.jadarstudios.api.developercapesapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ImageBufferDownload;

import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public final class DeveloperCapesAPI {

	private static DeveloperCapesAPI instance;

	private static final Minecraft mc = Minecraft.getMinecraft();
	
	private HashMap<String, String> users;
	private HashMap<String, String> groupUrls;

	private boolean tickSetUp = false;
	
	/**
	 * Object constructor.
	 * 
	 * @param parTxtUrl
	 * @param parDeveloperCape
	 * @param parTesterCape
	 */
	private DeveloperCapesAPI() {
		users = new HashMap<String, String>();
		groupUrls = new HashMap<String, String>();
	}

	public static DeveloperCapesAPI getInstance() {
		if(instance == null) {
			instance = new DeveloperCapesAPI();
		}
		return instance;
	}

	/**
	 * Set up capes. All cape urls are in the txt file passed in. 
	 * https://github.com/jadar/DeveloperCapesAPI/blob/master/SampleCape.txt
	 * 
	 * @param parTxtUrl
	 */
	public void init(String parTxtUrl) {
		try {
			URL url = new URL(parTxtUrl);
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;

			String username = "";
			String group = "";
			String capeUrl = "";
			System.out.println(parTxtUrl);
			while((line = reader.readLine()) != null) {
				
				// excludes commented lines
				if(!line.startsWith("#")) {
					// loops through characters.
					for(int i=0; i<line.length(); i++) {
						// when char : is found do stuff.
						if(line.charAt(i) == '=') {
							group = line.substring(0, i);
							String subLine = line.substring(i+1);
							
							if(subLine.startsWith("http")) {
								capeUrl = subLine;
								getInstance().addGroupUrl(group, capeUrl);
								mc.renderEngine.obtainImageData(capeUrl, new ImageBufferDownload());
								System.out.println(capeUrl);
								continue;
							}
							else {
								username = subLine.toLowerCase();
								getInstance().addUser(username, group);
							}
						}
					}
				}
			}
		}
		catch(IOException x) {
			x.printStackTrace();
		}

		// make sure to set up only one tick handler.
		if(!instance.tickSetUp) {
			// set up tick handler for capes.
			TickRegistry.registerTickHandler(new DeveloperCapesTickHandler(), Side.CLIENT);
			instance.tickSetUp = true;
		}
	}

	/**
	 * Used to add user to users HashMap.
	 * @param parUsername
	 * @param parGroup
	 */
	public void addUser(String parUsername, String parGroup) {
		if(getUserGroup(parUsername) == null) {
			users.put(parUsername, parGroup);

		}
	}

	/**
	 * Used to get user from users HashMap.
	 * @param parUsername
	 * @return
	 */
	public String getUserGroup(String parUsername)  {
		return users.get(parUsername.toLowerCase());
	}

	/**
	 * Used to add group and url to groupUrls HashMap.
	 * @param parGroup
	 * @param parCapeUrl
	 */
	public void addGroupUrl(String parGroup, String parCapeUrl) {
		if(getGroupUrl(parGroup) == null) {
			groupUrls.put(parGroup, parCapeUrl);
		}
	}

	/**
	 * Used to get url from groupUrl by the group name.
	 * @param group
	 * @return
	 */
	public String getGroupUrl(String group) {
		return groupUrls.get(group);
	}
}
