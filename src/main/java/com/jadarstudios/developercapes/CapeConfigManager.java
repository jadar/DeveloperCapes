package com.jadarstudios.developercapes;

import com.google.common.collect.HashBiMap;
import com.google.common.primitives.UnsignedBytes;
import com.google.common.primitives.UnsignedInts;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jadarstudios.developercapes.user.GroupManager;
import com.jadarstudios.developercapes.user.UserManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.BitSet;
import java.util.Map;

/**
 * @author jadar
 */
public enum CapeConfigManager {
    INSTANCE;

    protected static BitSet availableIds = new BitSet(256);
    protected HashBiMap<Integer, CapeConfig> configs;

    static {
        availableIds.clear(availableIds.size());
    }

    private CapeConfigManager() {
        configs = HashBiMap.create();
    }

    public void addConfig(int id, CapeConfig config) {
        int realId = claimId(id);
//        CapeConfig config = this.parse(json);
        this.configs.put(new Integer(id), config);
    }

    public CapeConfig getConfig(int id) {
        return this.configs.get(new Integer(id));
    }

    public int getIdForConfig(CapeConfig config) {
        return this.configs.inverse().get(config).intValue();
    }

    public static int getUniqueId() {
        return availableIds.nextClearBit(0);
    }

    public static int claimId(int id) {
        try {
            UnsignedBytes.checkedCast(id);
        } catch(IllegalArgumentException e) {

        }

        boolean isRegistered = availableIds.get(id);
        if (isRegistered) {

        }

        availableIds.set(id);
        return id;
    }

//    public CapeConfig newInstance() {
//        CapeConfig instance = new CapeConfig();
//        this.addConfig(this.getUniqueId(), );
//        return instance;
//    }

    public CapeConfig parse(String config) {
        CapeConfig instance = new CapeConfig();

        try {
            Map<String, Object> entries = new Gson().fromJson(config, Map.class);

            for (Map.Entry<String, Object> entry : entries.entrySet()) {
                final String nodeName = entry.getKey();
                final Object obj = entry.getValue();
                if (obj instanceof Map) {
                    Map group = (Map)obj;

                    instance.groups.put(nodeName, group);
                    GroupManager.INSTANCE.parse(nodeName, group);
                } else if (obj instanceof String) {
                    instance.users.put(nodeName, (String) obj);
                    UserManager.INSTANCE.parse(nodeName, obj);
                }
            }

        } catch(JsonSyntaxException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return instance;
    }

    public CapeConfig parseFromStream(InputStream is) {
        CapeConfig instance = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String json = "";
            while (reader.ready()) {
                json += reader.readLine();
            }
            instance = CapeConfigManager.INSTANCE.parse(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return instance;
    }
}
