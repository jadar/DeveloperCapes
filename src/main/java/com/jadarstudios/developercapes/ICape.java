package com.jadarstudios.developercapes;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.util.ResourceLocation;

import java.awt.image.BufferedImage;

/**
 * @author jadar
 */
public interface ICape
{

    public String getName();
    public ITextureObject getTexture();
    public ResourceLocation getLocation();
    public void loadTexture(AbstractClientPlayer player);
    public boolean isTextureLoaded(AbstractClientPlayer player);
}
