package com.jadarstudios.developercapes;

import com.google.common.io.ByteStreams;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;
import com.jadarstudios.developercapes.user.Group;
import com.jadarstudios.developercapes.user.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jadar
 */
public class CapeConfig
{
    private Gson parser;
    public HashMap<String, Group> groups;
    public HashMap<String, User> users;

    public CapeConfig() {
        parser = new Gson();
        groups = new HashMap<String, Group>();
        users = new HashMap<String, User>();
    }





}
