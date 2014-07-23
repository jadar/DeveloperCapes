package com.jadarstudios.developercapes.test;

import com.jadarstudios.developercapes.DevCapes;
import com.jadarstudios.developercapes.cape.CapeConfig;
import com.jadarstudios.developercapes.cape.CapeConfigManager;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author jadar
 */
@Mod(modid = "dct", name = "DevCapesOld Test", version = "na")
public class DevCapesTestMod {


    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        try {
            InputStream is = DevCapes.getInstance().getStreamForURL(new URL("https://dl.dropboxusercontent.com/u/22865035/ModHosting/capes/capes.json"));
            CapeConfig config = CapeConfigManager.INSTANCE.parseFromStream(is);
            CapeConfigManager.INSTANCE.addConfig(CapeConfigManager.getUniqueId(), config);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
