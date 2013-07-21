/**
 * Developer Capes by Jadar
 * License: Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * version 2.0
 */
package com.jadarstudios.developercapes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import net.minecraft.client.renderer.IImageBuffer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class DevCapesImageBufferDownload implements IImageBuffer {
	
	private int imageWidth;
	private int imageHeight;
	
	@Override
	public BufferedImage parseUserSkin(BufferedImage par1BufferedImage) {
		if (par1BufferedImage == null) {
			return null;
		}
		else {
			this.imageWidth = (par1BufferedImage.getWidth((ImageObserver)null) <= 64) ? 64: (par1BufferedImage.getWidth((ImageObserver)null));
			this.imageHeight = (par1BufferedImage.getHeight((ImageObserver)null) <= 32) ? 32: (par1BufferedImage.getHeight((ImageObserver)null));
			
			BufferedImage capeImage = new BufferedImage(this.imageWidth, this.imageHeight, 2);
			
			Graphics graphics = capeImage.getGraphics();
			graphics.drawImage(par1BufferedImage, 0, 0, (ImageObserver)null);
			graphics.dispose();
			
			return capeImage;
		}
	}
}
