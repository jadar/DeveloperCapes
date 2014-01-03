/**
 * DeveloperCapes by Jadar
 * License: MIT License (https://raw.github.com/jadar/DeveloperCapes/master/LICENSE)
 * version 2.1
 */
package com.jadarstudios.developercapes;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ThreadDownloadImageData;

import java.lang.reflect.Field;
import java.util.EnumSet;
import java.util.List;

@SideOnly(Side.CLIENT)
public class DevCapesTickHandler implements ITickHandler
{

    private static final Minecraft mc       = Minecraft.getMinecraft();
    private static final DevCapes  instance = DevCapesUtil.getInstance();

    // Keep at false when packaging..
    private boolean debug = false;

    private static Field downloadImageCapeField = getHackField(2);
    private static Field locationCapeField      = getHackField(4);

    private int     counter  = 0;
    private boolean notified = false;

    @Override
    public void tickStart(EnumSet<TickType> type, Object... tickData)
    {
        try
        {
            // Will not run if there is no world, and if there are no player entities
            // in the playerEntities list.
            if ((mc.theWorld != null) && (mc.theWorld.playerEntities.size() > 0))
            {
                // List of players.
                @SuppressWarnings("unchecked") List<AbstractClientPlayer> players = mc.theWorld.playerEntities;

                // resets the counter if it is too high.
                if (counter >= players.size()) counter = 0;

                AbstractClientPlayer p = players.get(counter);
                if (p != null)
                {

                    String lowerUsername = p.username.toLowerCase();

                    if (instance.getUserGroup(lowerUsername) != null)
                    {
                        // If the player had no cape before, (or is some cases
                        // has a cape from another mod,) then it will be true.
                        // This statement checks for false. Will not replace any
                        // capes.
                        if (!((ThreadDownloadImageData) downloadImageCapeField.get(p)).isTextureUploaded())
                        {
                            String userGroup = instance.getUserGroup(lowerUsername);

                            if (debug) System.out.println("Changing the cape of: " + p.username);
                            // Sets the cape URL.
                            locationCapeField.set(p, instance.getCapeResource(userGroup));
                            downloadImageCapeField.set(p, instance.getDownloadThread(userGroup));
                        }

                        //notifies qualified user that developer capes is outdated.
                        if (!notified)
                        {
                            if (FMLClientHandler.instance().getClient().currentScreen == null)
                            {
                                if (instance.versionChecker.getResult() == 1)
                                {

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
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }

    /*
     * Not used, stub method.
     */
    @Override
    public void tickEnd(EnumSet<TickType> type, Object... tickData) {}

    @Override
    public EnumSet<TickType> ticks()
    {
        return EnumSet.of(TickType.CLIENT);
    }

    @Override
    public String getLabel()
    {
        return "DeveloperCapesTickHandler";
    }

    /**
     * Them cheaty ways...
     */
    private static Field getHackField(int i)
    {
        Field f = AbstractClientPlayer.class.getDeclaredFields()[i];
        f.setAccessible(true);
        return f;
    }
}
