package com.jadarstudios.api.DonorCapesAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class DonorCapesAPI {
	
	private static DonorCapesAPI instance;
	
	public ArrayList<String> donorUsers;
	public ArrayList<String> testerUsers;
	

	private DonorCapesAPI() {
		donorUsers = new ArrayList<String>();
		testerUsers = new ArrayList<String>();
	}
	
	
	public static DonorCapesAPI getInstance() {
		if(instance == null) {
			instance = new DonorCapesAPI();
		}
		
		return instance;
	}
	
	
	public void init(String par1String) {
		
		try {

			URL url = new URL(par1String);
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

			String line;

			while((line = reader.readLine()) != null) {
				if(line.startsWith("donor:")) {
					donorUsers.add(line.substring(7));
				}

				if(line.startsWith("tester:")) {
					testerUsers.add(line.substring(8));
				}
			}
		}
		catch(IOException x) {
			
		}
	
		TickRegistry.registerTickHandler(new DonorCapesTickHandler(), Side.CLIENT);
	
	
	}
}
