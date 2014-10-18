package com.jadarstudios.developercapes.cape;

import com.jadarstudios.developercapes.user.Group;
import com.jadarstudios.developercapes.user.User;

import java.util.HashMap;

/**
 * @author jadar
 */
public class CapeConfig {
    public HashMap<String, Group> groups;
    public HashMap<String, User> users;

    public CapeConfig() {
        groups = new HashMap<String, Group>();
        users = new HashMap<String, User>();
    }
}