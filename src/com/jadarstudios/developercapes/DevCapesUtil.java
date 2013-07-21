/**
 * Developer Capes by Jadar
 * License: Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * version 2.0
 */
package com.jadarstudios.developercapes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureObject;
import net.minecraft.util.ResourceLocation;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class DevCapesUtil {

	private static DevCapesUtil instance;
	public static final double version = 2.0;
	
	public DevCapesVersionChecker versionChecker;
	private HashMap<String, String> users;
	private HashMap<String, ResourceLocation> capeResources;
	private HashMap<String, ThreadDownloadImageData> downloadThreads;

	public boolean tickSetUp = false;

	/**
	 * Object constructor.
	 */
	private DevCapesUtil() {
		users = new HashMap<String, String>();
		capeResources = new HashMap<String, ResourceLocation>();
		downloadThreads = new HashMap<String, ThreadDownloadImageData>();
		
		versionChecker = new DevCapesVersionChecker();
		new Thread(versionChecker).run();
	}

	/**
	 * Get's the current DeveloperCapesAPI instance, or creates a new one if
	 * necessary.
	 */
	public static DevCapesUtil getInstance() {
		if (instance == null){
			instance = new DevCapesUtil();
		}
		return instance;
	}

	/**
	 * Set up capes. All cape URLs are in the txt file passed in.
	 * https://github.com/jadar/DeveloperCapesAPI/blob/master/SampleCape.txt
	 * 
	 * @param parTxtUrl
	 *            The URL of the .txt file containing the groups, members of
	 *            said groups, and the group's cape URL.
	 */
	public void addFileUrl(String parTxtUrl) {
		if(FMLCommonHandler.instance().getSide() != Side.CLIENT)
			return;
		
		try{
			URL url = new URL(parTxtUrl);
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;

			String username = "";
			String group = "";
			String capeUrl = "";

			while ((line = reader.readLine()) != null){

				// excludes commented lines
				if (!line.startsWith("#")){
					// loops through characters.
					for (int i = 0; i < line.length(); i++){
						// when char : is found do stuff.
						if (line.charAt(i) == '='){
							group = line.substring(0, i);
							String subLine = line.substring(i + 1);

							if (subLine.startsWith("http")){
								capeUrl = subLine;

								ResourceLocation r = new ResourceLocation("DevCapes/" + group);
								ThreadDownloadImageData t = makeDownloadThread(r, capeUrl, null, new DevCapesImageBufferDownload());

								this.addCapeResource(group, r);
								this.addDownloadThread(group, t);

								continue;
							}else{
								username = subLine.toLowerCase();
								addUser(username, group);
							}
						}
					}
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}

		// Makes sure to set up only one tick handler.
		if (!instance.tickSetUp){
			// Sets up the tick handler for capes.
			TickRegistry.registerTickHandler(new DevCapesTickHandler(), Side.CLIENT);
			instance.tickSetUp = true;
		}

	}

	public void checkForUpdates() {
		
	}
	
	/**
	 * Used to add user to users HashMap.
	 * 
	 * @param parUsername
	 *            The Username to add.
	 * @param parGroup
	 *            The group to add that Username to.
	 */
	public void addUser(String parUsername, String parGroup) {
		if (getUserGroup(parUsername) == null){
			users.put(parUsername, parGroup);

		}
	}

	/**
	 * Used to get user from users HashMap.
	 * 
	 * @param parUsername
	 *            The Username to get from the users HashMap.
	 * @return The Username found in the users HashMap.
	 */
	public String getUserGroup(String parUsername) {
		return users.get(parUsername.toLowerCase());
	}

	/**
	 * 
	 * Adds a cape ResourceLocation that is predownloaded.
	 * 
	 * @param parGroup
	 * @param parResource
	 */
	public void addCapeResource(String parGroup, ResourceLocation parResource) {
		if (getCapeResource(parGroup) == null){
			capeResources.put(parGroup, parResource);
		}
	}

	/**
	 * 
	 * Gets a cape ResourceLocation.
	 * 
	 * @param parGroup
	 * @return
	 */
	public ResourceLocation getCapeResource(String parGroup) {
		return capeResources.get(parGroup);
	}

	/**
	 * 
	 * Adds an ThreadDownloadImageData. Needed to change cape.
	 * 
	 * @param parGroup
	 * @param parResource
	 */
	public void addDownloadThread(String parGroup, ThreadDownloadImageData parResource) {
		if (getDownloadThread(parGroup) == null){
			downloadThreads.put(parGroup, parResource);
		}
	}

	/**
	 * 
	 * Gets the ThreadDownloadImageData that is associated with the group.
	 * 
	 * @param parGroup
	 * @return
	 */
	public ThreadDownloadImageData getDownloadThread(String parGroup) {
		return downloadThreads.get(parGroup);
	}

	/**
	 * 
	 * Used to download images. Copied from AbstractClientPlayer to remove
	 * a conditional.
	 * 
	 * @param par0ResourceLocation
	 * @param par1Str
	 * @param par2ResourceLocation
	 * @param par3IImageBuffer
	 * @return
	 */
	public static ThreadDownloadImageData makeDownloadThread(ResourceLocation par0ResourceLocation, String par1Str, ResourceLocation par2ResourceLocation, IImageBuffer par3IImageBuffer)
	{
		TextureManager texturemanager = Minecraft.getMinecraft().func_110434_K();

		TextureObject object = new ThreadDownloadImageData(par1Str, par2ResourceLocation, par3IImageBuffer);
		// Binds ResourceLocation to this.
		texturemanager.func_110579_a(par0ResourceLocation, object);

		return (ThreadDownloadImageData)object;
	}
}