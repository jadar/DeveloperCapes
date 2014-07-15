package com.jadarstudios.developercapes.user;

import com.jadarstudios.developercapes.CapeManager;
import com.jadarstudios.developercapes.ICape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jadar
 */
public enum GroupManager {
    INSTANCE;

    private HashMap<String, Group> groups;

    private GroupManager() {
        this.groups = new HashMap<String, Group>();
    }

    public void addGroup(String capeName, Group group) {
        groups.put(capeName, group);
    }

    public Group getGroup(String capeName) {
        return groups.get(capeName);
    }

    public Group newInstance(String name) {
        if (this.getGroup(name) != null) {
            return this.getGroup(name);
        }
        Group group = new Group(name);
        return group;
    }

    public Group parse(String name, Map<String, Object> data) {
        Group group = this.newInstance(name);

        ArrayList<String> users = (ArrayList)data.get("users");
        String capeUrl = (String)data.get("capeUrl");

        if (users == null || capeUrl == null) {
            // fail
        }

        group.cape = CapeManager.INSTANCE.newInstance(capeUrl);

        for (String username : users) {
            User user = UserManager.INSTANCE.newInstance(username);
            if (user != null) {
                group.addUser(user);
            } else {

            }
        }

        return group;
    }
}
