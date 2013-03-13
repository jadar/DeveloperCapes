/**
 * Copyright (c) Jadar, 2013
 * Developer Capes API by Jadar
 * License: Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 * version 1.3
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
