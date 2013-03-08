/**
 * Copyright (c) Jadar, 2013
 * Donor Capes API by Jadar
 */
package com.jadarstudios.api.DonorCapesAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public final class DonorCapesAPI {

	public static DonorCapesAPI instance;

	public static ArrayList<String> donorUsers;
	public static ArrayList<String> testerUsers;
	public static String donorCape = "";
	public static String testerCape = "";

	/**
	 * Object constructor.
	 * 
	 * @param parTxtUrl
	 * @param parDonorCape
	 * @param parTesterCape
	 */
	private DonorCapesAPI(String parTxtUrl, String parDonorCape, String parTesterCape) {
		donorUsers = new ArrayList<String>();
		testerUsers = new ArrayList<String>();
		donorCape = parDonorCape;
		testerCape = parTesterCape;

		try {
			URL url = new URL(parTxtUrl);
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;

			while((line = reader.readLine()) != null) {
				line = line.toLowerCase();
				if(line.startsWith("donor:")) {
					DonorCapesAPI.donorUsers.add(line.substring(7));
				}

				if(line.startsWith("tester:")) {
					DonorCapesAPI.testerUsers.add(line.substring(8));
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
	 * @param parDonorCape
	 * @param parTesterCape
	 */
	public static void init(String parTxtUrl, String parDonorCape, String parTesterCape) {
		// if no instance is created, make a new instance and register tick handler.
		if(getInstance() == null) {
			instance = new DonorCapesAPI(parTxtUrl, parDonorCape, parTesterCape);
		}

		TickRegistry.registerTickHandler(new DonorCapesTickHandler(), Side.CLIENT);

	}

	public static DonorCapesAPI getInstance() {
		if(instance == null) {
			return null;
		}
		return instance;
	}

}
