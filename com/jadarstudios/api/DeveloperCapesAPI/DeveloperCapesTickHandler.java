/**
 * Copyright (c) Jadar, 2013
 * Developer Capes API by Jadar
 * License: Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 * version 1.3
 */
package com.jadarstudios.api.DeveloperCapesAPI;

import java.util.EnumSet;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.entity.player.EntityPlayer;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import com.jadarstudios.api.DeveloperCapesAPI.DeveloperCapesUser;

@SideOnly(Side.CLIENT)
public class DeveloperCapesTickHandler implements ITickHandler {

	private static final Minecraft mc = Minecraft.getMinecraft();
	private static final DeveloperCapesAPI instance = DeveloperCapesAPI.getInstance();

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		// will not run if there is no world, and if there are player entities in the playerEntities list.
		if ((mc.theWorld != null) && (mc.theWorld.playerEntities.size() > 0)) {

			// grabs a list of all the players, and the world.
			@SuppressWarnings("unchecked")
			List<EntityPlayer> players = mc.theWorld.playerEntities;

			// the loops that goes through each player
			for (int counter = 0; counter < players.size(); counter++) {

				// helps keep from getting an ArrayOutOfBoundException
				if (players.get(counter) != null) {

					// get the player from the players list.
					EntityPlayer player = players.get(counter);
					String oldCloak = player.cloakUrl;
					
					// make it equal the old cloak so it has something to revert to if the player in question is not in the HashMap.
					String groupUrl = oldCloak;

					if(player.cloakUrl.startsWith("http://skins.minecraft.net/MinecraftCloaks/")) {
						// lowercase username, so no problems with case.
						String lowerUsername = player.username.toLowerCase();
						
						if(instance.getUser(lowerUsername) != null) {
							
							// get the user from the hash map and get the cape url.
							DeveloperCapesUser hashUser = (DeveloperCapesUser)instance.getUser(lowerUsername);
							groupUrl = instance.getGroupUrl(hashUser.getGroup());
							
							// set cape url.
							player.cloakUrl = groupUrl;
						}

						// if the set cloak does not equal the old cloak then download the cloak.
						if ( player.cloakUrl != oldCloak & player.cloakUrl != oldCloak) {
							// download the cloak. the second arguement is an image buffer that makes sure the cape is the right dimensions.
							mc.renderEngine.obtainImageData(player.cloakUrl, new ImageBufferDownload());
						}
					}
				}
			}
		}
	}

	/*
	 * Not used, stub method.
	 */
	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

	@Override
	public String getLabel() {
		return "DeveloperCapesTickHandler";
	}

}
