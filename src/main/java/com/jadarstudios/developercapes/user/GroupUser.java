/**
 * DeveloperCapes by Jadar
 * License: MIT License
 * (https://raw.github.com/jadar/DeveloperCapes/master/LICENSE)
 * version 3.3.0.0
 */
package com.jadarstudios.developercapes.user;

import net.minecraft.client.renderer.texture.ITextureObject;

import com.jadarstudios.developercapes.DevCapes;

public class GroupUser extends DefaultUser
{
    
    public String group;
    
    public GroupUser(final String name, final String group)
    {
        super(name);
        this.group = group;
    }
    
    @Override
    public ITextureObject getTexture()
    {
        return DevCapes.getInstance().getGroupTexture(this.group);
    }
}
