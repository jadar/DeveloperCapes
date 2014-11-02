package net.jadar.devcapesdemo;

import com.jadarstudios.developercapes.DevCapes;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = "devcapesdemo", name = "Developer Capes Demo", version = "1.0")
public class DevCapesDemo {

    @Mod.Instance
    public static DevCapesDemo instance;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
                DevCapes.getInstance().registerConfig("https://dl.dropboxusercontent.com/u/22865035/ModHosting/capes/capes.json");
        }
    }
}