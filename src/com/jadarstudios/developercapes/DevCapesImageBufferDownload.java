/**
 * DeveloperCapes by Jadar
 * License: MIT License (https://raw.github.com/jadar/DeveloperCapes/master/LICENSE)
 * version 2.1
 */
package com.jadarstudios.developercapes;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.IImageBuffer;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

@SideOnly(Side.CLIENT)
public class DevCapesImageBufferDownload implements IImageBuffer
{
    @Override
    public BufferedImage parseUserSkin(BufferedImage par1BufferedImage)
    {
        if (par1BufferedImage == null)
        {
            return null;
        }
        else
        {
            int imageWidth = (par1BufferedImage.getWidth(null) <= 64) ? 64 : (par1BufferedImage.getWidth(null));
            int imageHeight = (par1BufferedImage.getHeight(null) <= 32) ? 32 : (par1BufferedImage.getHeight(null));

            BufferedImage capeImage = new BufferedImage(imageWidth, imageHeight, 2);

            Graphics graphics = capeImage.getGraphics();
            graphics.drawImage(par1BufferedImage, 0, 0, null);
            graphics.dispose();

            return capeImage;
        }
    }
}
