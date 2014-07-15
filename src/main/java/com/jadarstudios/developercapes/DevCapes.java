/**
* DeveloperCapes by Jadar
* License: MIT License
* (https://raw.github.com/jadar/DeveloperCapes/master/LICENSE)
* version 4.0.0.x
*/

package com.jadarstudios.developercapes;

import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * DeveloperCapes is a library for Minecraft. It allows developers to quickly add capes for players they specify. DevCapes uses Minecraft Forge.
 *
 * @author jadar
 */
public class DevCapes {
    private static DevCapes instance;

    public static final Logger logger = LogManager.getLogger("DevCapes");

    protected DevCapes() {
        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }

    public static DevCapes getInstance() {
        if (instance == null) {
            instance = new DevCapes();
        }
        return instance;
    }

    public InputStream getStreamForURL(URL url) {
        InputStream is = null;
        try {
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", System.getProperty("java.version"));
            connection.connect();

            is = connection.getInputStream();
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            return is;
        }
    }

    public InputStream getStreamForFile(File file) {
        InputStream is = null;
        try {
            is =  new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            return is;
        }
    }
}
