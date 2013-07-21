/**
 * Developer Capes by Jadar
 * License: Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * version 2.0
 */
package com.jadarstudios.developercapes;

import java.util.EnumSet;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class DevCapesTickHandler implements ITickHandler {

	private static final Minecraft mc = Minecraft.getMinecraft();
	private static final DevCapesUtil instance = DevCapesUtil.getInstance();

	// Keep at false when packaging..
	private boolean debug = false;


	private int counter = 0;
	private boolean notified = false;

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {


		// Will not run if there is no world, and if there are no player entities
		// in the playerEntities list. 
		if ((mc.theWorld != null) && (mc.theWorld.playerEntities.size() > 0)){
			// List of players.
			@SuppressWarnings("unchecked")
			List<AbstractClientPlayer> players = mc.theWorld.playerEntities;

			// resets the counter if it is too high.
			if(counter >= players.size())
				counter = 0;

			AbstractClientPlayer p = players.get(counter);
			if(p != null) {

				String lowerUsername = p.username.toLowerCase();

				if (instance.getUserGroup(lowerUsername) != null){
					// If the player had no cape before, (or is some cases
					// has a cape from another mod,) then it will be true.
					// This statement checks for false. Will not replace any
					// capes.
					if (!p.field_110315_c.func_110557_a()) {
						String userGroup = instance.getUserGroup(lowerUsername);

						if(debug)
							System.out.println("Changing the cape of: " + p.username);
						// Sets the cape URL.
						p.field_110313_e = instance.getCapeResource(userGroup);
						p.field_110315_c = instance.getDownloadThread(userGroup);
					}

					//notifies qualified user that developer capes is outdated.
					if(!notified){
						if (FMLClientHandler.instance().getClient().currentScreen == null) {
							if (instance.versionChecker.getResult() == 1) {

								notified = true;

								FMLClientHandler.instance().getClient().ingameGUI.getChatGUI().printChatMessage("§6[DevCapes]: §fDevCapes is outdated.");
							}
						}
					}
				}
			}

			counter++;
		}
	}

	/*
	 * Not used, stub method.
	 */
	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.CLIENT);
	}

	@Override
	public String getLabel() {
		return "DeveloperCapesTickHandler";
	}
}