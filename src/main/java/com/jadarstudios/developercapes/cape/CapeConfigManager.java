package com.jadarstudios.developercapes.cape;

import com.google.common.collect.HashBiMap;
import com.google.common.io.ByteStreams;
import com.google.common.primitives.UnsignedBytes;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jadarstudios.developercapes.DevCapes;
import com.jadarstudios.developercapes.user.Group;
import com.jadarstudios.developercapes.user.GroupManager;
import com.jadarstudios.developercapes.user.User;
import com.jadarstudios.developercapes.user.UserManager;

import java.io.IOException;
import java.io.InputStream;
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
        this.configs.put(realId, config);
        try {
            for (User u : config.users.values()) {
                UserManager.INSTANCE.addUser(u);
            }

            for (Group g : config.groups.values()) {
                GroupManager.INSTANCE.addGroup(g);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CapeConfig getConfig(int id) {
        return this.configs.get(id);
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
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        boolean isRegistered = availableIds.get(id);
        if (isRegistered) {
            DevCapes.logger.error(String.format("The config ID %d is already claimed.", id));
        }

        availableIds.set(id);
        return id;
    }

    public CapeConfig parse(String config) {
        CapeConfig instance = new CapeConfig();

        try {
            Map<String, Object> entries = new Gson().fromJson(config, Map.class);

            for (Map.Entry<String, Object> entry : entries.entrySet()) {
                final String nodeName = entry.getKey();
                final Object obj = entry.getValue();
                if (obj instanceof Map) {
                    Map group = (Map) obj;

                    Group g = GroupManager.INSTANCE.parse(nodeName, group);
                    if (g != null) {
                        instance.groups.put(g.name, g);
                    }
                } else if (obj instanceof String) {
                    User u = UserManager.INSTANCE.parse(nodeName, obj);
                    if (u != null) {
                        instance.users.put(nodeName, u);
                    }
                }
            }
        } catch (JsonSyntaxException e) {
        	DevCapes.logger.error("CapeConfig could not be parsed because:");
            e.printStackTrace();
        }

        return instance;
    }

    public CapeConfig parseFromStream(InputStream is) {
        if (is == null) {
            DevCapes.logger.error("Can't parse a null input stream!");
            return null;
        }
        CapeConfig instance = null;
        try {
            String json = new String(ByteStreams.toByteArray(is));
            instance = CapeConfigManager.INSTANCE.parse(json);
        } catch (IOException e) {
        	DevCapes.logger.error("Failed to read the input stream!");
            e.printStackTrace();
        } finally {
        	try {
        		is.close();
        	} catch (IOException e) {
        		// Ignoring this
        	}
        }

        return instance;
    }
}
