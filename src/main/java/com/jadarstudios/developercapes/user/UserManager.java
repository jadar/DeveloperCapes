package com.jadarstudios.developercapes.user;

import com.jadarstudios.developercapes.CapeManager;
import com.jadarstudios.developercapes.ICape;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * @author jadar
 */
public enum UserManager {
    INSTANCE;

    protected HashMap<String, User> users;

    private UserManager() {
        this.users = new HashMap<String, User>();
    }

    public User getUser(String username) {
        return this.users.get(username);
    }

    public User newInstance(String username) {
        User instance = null;
        if (this.users.containsKey(username)) {
            instance = this.getUser(username);
        } else {
            instance = new User(username);
            this.users.put(username, instance);
        }

        return instance;
    }

    public User parse(Object user, Object cape) throws MalformedURLException {
        if (user instanceof String) {
            User userInstance = this.newInstance((String)user);
            ICape capeInstance = CapeManager.INSTANCE.parse((String)user, new URL((String)cape));
            userInstance.capes.add(capeInstance);
            return userInstance;
        }
        return null;
    }
}
