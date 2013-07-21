/**
 * Developer Capes by Jadar
 * License: Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * version 2.0
 */
package com.jadarstudios.developercapes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import argo.jdom.JdomParser;

public class DevCapesVersionChecker implements Runnable {

	private static final String versionFileURL = "https://dl.dropboxusercontent.com/u/22865035/version.json";//"http://raw.github.com/Jadar/DeveloperCapesAPI/master/version";

	private byte result = 0;
	
	private static final byte ERROR = 0;
	private static final byte OLD = 1;
	private static final byte CURRENT = 2;

	@Override
	public void run() {

		try {

			URL url = new URL(versionFileURL);
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			double version = Double.valueOf(new JdomParser().parse(reader).getStringValue("version"));
			
			if(version > DevCapesUtil.version)
				result = OLD;
			else if(version == DevCapesUtil.version)
				result = CURRENT;
			else
				result = ERROR;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public byte getResult() {
		return result;
	}
}
