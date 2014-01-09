/**
 * DeveloperCapes by Jadar
 * License: MIT License
 * (https://raw.github.com/jadar/DeveloperCapes/master/LICENSE)
 * version 3.3.0.0
 */
package com.jadarstudios.developercapes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;

import org.apache.logging.log4j.LogManager;

import com.google.common.io.ByteStreams;
import com.google.gson.Gson;
import com.google.gson.stream.MalformedJsonException;
import com.jadarstudios.developercapes.user.DefaultUser;
import com.jadarstudios.developercapes.user.GroupUser;
import com.jadarstudios.developercapes.user.IUser;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
/**
 * This library adds capes for people you specify.
 * Use DevCapesUtil to add your capes if you do not call the addFileUrl method
 * in a client proxy.
 * 
 * @author Jadar
 */
public class DevCapes
{
    
    private static DevCapes                             instance;
    
    public static final org.apache.logging.log4j.Logger logger = LogManager.getLogger("DevCapes");
    
    /**
     * Get's the DevCapes instance.
     */
    public static DevCapes getInstance()
    {
        if (instance == null)
            instance = new DevCapes();
        return instance;
    }
    
    // name->group
    private HashMap<String, IUser>          users;
    
    private HashMap<String, ITextureObject> groups;
    
    /**
     * Object constructor.
     */
    private DevCapes()
    {
        this.groups = new HashMap<String, ITextureObject>();
        this.users = new HashMap<String, IUser>();
    }
    
    public void addGroup(final String groupName, final ITextureObject texture)
    {
        this.groups.put(groupName, texture);
    }
    
    public void addGroup(final String groupName, final String capeUrl)
    {
        this.addGroup(groupName, new ThreadDownloadImageData(capeUrl, null, new HDImageBuffer()));
    }
    
    /**
     * Used to add user to users HashMap.
     */
    public void addGroupUser(final String username, final String group)
    {
        IUser user = this.users.get(username);
        if (user == null)
        {
            user = new GroupUser(username, group);
            this.users.put(username, user);
            
            // make sure to call this last.
            this.loadCape(username);
        }
    }
    
    public void addSingleUser(final String username, final String capeUrl)
    {
        IUser user = this.users.get(username);
        if (user == null)
        {
            user = new DefaultUser(username, capeUrl);
            this.users.put(username, user);
            this.loadCape(username);
        }
    }
    
    public ITextureObject getGroupTexture(final String group)
    {
        return this.groups.get(group);
    }
    
    public IUser getUser(final String username)
    {
        return this.users.get(username);
    }
    
    /**
     * Used to get user from users HashMap.
     */
    public String getUserGroup(final String username)
    {
        return this.isPlayerInGroup(username) ? ((GroupUser) this.users.get(username)).group : null;
    }
    
    protected ITextureObject getUserTexture(final String username)
    {
        return this.getUser(username).getTexture();
    }
    
    public boolean isPlayerInGroup(final String username)
    {
        return this.users.containsKey(username) && this.users.get(username) instanceof GroupUser;
    }
    
    /**
     * Used to load the cape of a player.
     */
    public boolean loadCape(final String username)
    {
        IUser user = this.users.get(username);
        return Minecraft.getMinecraft().renderEngine.loadTexture(user.getResource(), user.getTexture());
    }
    
    public void registerConfig(final File jsonFile, final String identifier)
    {
        try
        {
            this.registerConfig(new FileInputStream(jsonFile), identifier);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    public void registerConfig(final InputStream input, final String modId)
    {
        try
        {
            String data = new String(ByteStreams.toByteArray(input));
            input.close();
            
            Map<String, Object> groups = new Gson().fromJson(data, Map.class);
            for (Entry<String, Object> e : groups.entrySet())
            {
                final String nodeName = e.getKey();
                final Object obj = e.getValue();
                if (obj instanceof Map)
                {
                    String groupName = nodeName + modId;
                    Map<String, Object> group = (Map<String, Object>) obj;
                    
                    String capeUrl = (String) group.get("capeUrl");
                    
                    this.addGroup(groupName, capeUrl);
                    
                    ArrayList<String> users = (ArrayList<String>) group.get("users");
                    if (users != null)
                        for (String username : users)
                            this.addGroupUser(username, groupName);
                }
                else if (obj instanceof String)
                    this.addSingleUser(nodeName, (String) obj);
            }
            
        }
        catch (MalformedJsonException e)
        {
            e.printStackTrace();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void registerConfig(final URL jsonUrl, final String identifier)
    {
        try
        {
            this.registerConfig(jsonUrl.openStream(), identifier);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
}
