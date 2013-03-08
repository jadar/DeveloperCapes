/**
 * Copyright (c) Jadar, 2013
 * Developer Capes API by Jadar
 */
package com.jadarstudios.api.DeveloperCapesAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public final class DeveloperCapesAPI {

	public static DeveloperCapesAPI instance;

	public static ArrayList<String> devUsers;
	public static ArrayList<String> testerUsers;
	public static String devCape = "";
	public static String testerCape = "";

	/**
	 * Object constructor.
	 * 
	 * @param parTxtUrl
	 * @param parDeveloperCape
	 * @param parTesterCape
	 */
	private DeveloperCapesAPI(String parTxtUrl, String parDeveloperCape, String parTesterCape) {
		devUsers = new ArrayList<String>();
		testerUsers = new ArrayList<String>();
		devCape = parDeveloperCape;
		testerCape = parTesterCape;

		try {
			URL url = new URL(parTxtUrl);
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;

			while((line = reader.readLine()) != null) {
				line = line.toLowerCase();
				if(line.startsWith("developer:")) {
					DeveloperCapesAPI.devUsers.add(line.substring(7));
				}

				if(line.startsWith("tester:")) {
					DeveloperCapesAPI.testerUsers.add(line.substring(8));
				}
			}
		}
		catch(IOException x) {
		}
	}

	/**
	 * Set up capes.
	 * 
	 * @param parTxtUrl
	 * @param parDeveloperCape
	 * @param parTesterCape
	 */
	public static void init(String parTxtUrl, String parDeveloperCape, String parTesterCape) {
		// if no instance is created, make a new instance and register tick handler.
		if(getInstance() == null) {
			instance = new DeveloperCapesAPI(parTxtUrl, parDeveloperCape, parTesterCape);
		}

		TickRegistry.registerTickHandler(new DeveloperCapesTickHandler(), Side.CLIENT);

	}

	public static DeveloperCapesAPI getInstance() {
		if(instance == null) {
			return null;
		}
		return instance;
	}

}
