/**
 * DeveloperCapes by Jadar
 * License: MIT License
 * (https://raw.github.com/jadar/DeveloperCapes/master/LICENSE)
 * version 3.3.0.0
 */
package com.jadarstudios.developercapes.user;

import com.jadarstudios.developercapes.ICape;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.util.ResourceLocation;

import com.jadarstudios.developercapes.HDImageBuffer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.List;

@Deprecated
/**
 * This class is used by DevCapesOld as a default implementation of {@link IUser}
 * 
 * @author Jadar
 */
@SideOnly(Side.CLIENT)
public class DefaultUser implements IUser
{
    
    public final String      username;
    private GameProfile userProfile;
    private ITextureObject   texture;
    private ResourceLocation resource;

    /**
     * @param name
     *            The name of the user
     */
    public DefaultUser(final String name)
    {
        this(name, "");
    }
    
    /**
     * 
     * @param name
     *            The name of the user
     * @param capeUrl
     *            The URL as a String of the user's cape
     */
    public DefaultUser(final String name, final String capeUrl)
    {
        this.username = name;
        this.userProfile = new GameProfile(null, this.username);
//        MinecraftProfileTexture profileTexture = Minecraft.getMinecraft().func_152347_ac().getTextures(this.userProfile, false).get(MinecraftProfileTexture.Type.CAPE);
//        this.resource = new ResourceLocation("DevCapesOld/" + name);
        this.resource = new ResourceLocation("cloaks/" + name);
        this.texture = new ThreadDownloadImageData(null, capeUrl, null, new HDImageBuffer());
    }
    
    @Override
    public ResourceLocation getResource()
    {
        return this.resource;
    }
    
    @Override
    public ITextureObject getTexture()
    {
        return this.texture;
    }

    public void loadTexture(AbstractClientPlayer player) {
        ResourceLocation location = player.getLocationCape();
        if (location == null) {
            location = this.getResource();
            player.func_152121_a(MinecraftProfileTexture.Type.CAPE, location);
        }

        Minecraft.getMinecraft().renderEngine.loadTexture(location, this.getTexture());
    }

    public boolean isTextureLoadedForPlayer(AbstractClientPlayer player) {
        if (player.getLocationCape() == this.getResource()) {
            return true;
        }
        return false;
    }
}