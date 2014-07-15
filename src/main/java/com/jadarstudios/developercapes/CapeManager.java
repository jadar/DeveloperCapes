package com.jadarstudios.developercapes;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author jadar
 */
public enum CapeManager {
    INSTANCE;

    private HashMap<String, ICape> capes;

    private CapeManager() {
        this.capes = new HashMap<String, ICape>();
    }

    public void addCape(String capeName, ICape cape) {
        capes.put(capeName, cape);
    }

    public ICape getCape(String capeName) {
        return capes.get(capeName);
    }

    public ICape newInstance(String name) {
        StaticCape cape = new StaticCape();
        this.capes.put(name, cape);
        return cape;
    }

    public ICape parse(String name, Object object) {
        if (object instanceof URL) {
            ICape cape = this.newInstance(name);
            ((StaticCape)cape).setURL((URL)object);
            ((StaticCape)cape).setName(name);
            return cape;
        }

        return null;
    }




}
