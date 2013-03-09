/**
 * Copyright (c) Jadar, 2013
 * Developer Capes API by Jadar
 * 
 * version 1.2
 */
package com.jadarstudios.api.DeveloperCapesAPI;

public class DeveloperCapesUser {
	
	private final String username;
	private final String group;
	
	DeveloperCapesUser(String parUsername, String parGroup) {
		username = parUsername;
		group = parGroup;
		
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getGroup() {
		return this.group;
	}
}
