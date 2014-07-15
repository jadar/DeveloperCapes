/**
 * DeveloperCapes by Jadar
 * License: MIT License
 * (https://raw.github.com/jadar/DeveloperCapes/master/LICENSE)
 * version 3.3.0.0
 */
package com.jadarstudios.developercapes.user;

import net.minecraft.client.renderer.texture.ITextureObject;

@Deprecated
/**
 * This class is used by DevCapesOld as an implementation of {@link DefaultUser}
 * that allows the user to have a group
 * 
 * @author Jadar
 */
public class GroupUser extends DefaultUser
{
    
    public String group;
    
    /**
     * @param name
     *            The name of the user
     * @param group
     *            The name of the group that the user belongs to
     */
    public GroupUser(final String name, final String group)
    {
        super(name);
        this.group = group;
    }
    
    @Override
    public ITextureObject getTexture()
    {
        return null;//DevCapesOld.getInstance().getGroupTexture(this.group);
    }
}