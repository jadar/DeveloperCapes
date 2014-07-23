package com.jadarstudios.developercapes.user;

import com.jadarstudios.developercapes.CapeManager;
import com.jadarstudios.developercapes.ICape;

import java.util.*;

/**
 * @author jadar
 */
public enum GroupManager {
    INSTANCE;

    private HashMap<String, Group> groups;

    private GroupManager() {
        this.groups = new HashMap<String, Group>();
    }

    public void addGroup(Group group) {
        groups.put(group.name, group);

        try {
            UserManager.INSTANCE.addUsers(new HashSet<User>(group.users.values()));
            CapeManager.INSTANCE.addCape(group.cape);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        group.cape = CapeManager.INSTANCE.parse(name, capeUrl);

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
