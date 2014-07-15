package com.jadarstudios.developercapes.user;

import com.jadarstudios.developercapes.CapeManager;
import com.jadarstudios.developercapes.ICape;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @author jadar
 */
public class Group
{

    protected HashMap<String, User> users;
    protected ICape cape;
    public final String name;

    public Group(String name) {
        this.users = new HashMap<String, User>();
        this.name = name;
    }

    public void addUser(User user) {
        if (!this.users.containsValue(user)) {
            user.capes.add(this.cape);
            this.users.put(user.username, user);
        }
    }

    public void addUsers(Set<User> users) {
        for (User user : users) {
            this.addUser(user);
        }
    }

    public void removeUser(User user) {
        if (this.users.containsValue(user)) {
            this.users.remove(user);
            user.capes.remove(this.cape);
        }
    }

    public ICape getCape() {
        return this.cape;
    }

    public void setCape(ICape cape) {
        for (User user : this.users.values()) {
            user.capes.remove(this.cape);
        }

        this.cape = cape;
    }
}
