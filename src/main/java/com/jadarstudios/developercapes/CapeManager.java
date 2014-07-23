package com.jadarstudios.developercapes;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
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

    public void addCape(ICape cape) {
        if (!capes.containsValue(cape)) {
            capes.put(cape.getName(), cape);
        }
    }

    public void addCapes(Collection<ICape> capes) {
        for (ICape c : capes) {
            this.addCape(c);
        }
    }

    public ICape getCape(String capeName) {
        return capes.get(capeName);
    }

    public ICape newInstance(String name) {
        StaticCape cape = new StaticCape(name);
        this.capes.put(name, cape);
        return cape;
    }

    public ICape parse(String name, Object object) {
        ICape cape = null;
        try {
            if (object instanceof String) {
                cape = new StaticCape(name, new URL((String)object));

                return cape;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } finally {
            return cape;
        }
    }
}
