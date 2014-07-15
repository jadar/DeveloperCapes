package com.jadarstudios.developercapes;

import com.google.common.io.ByteStreams;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;

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
    public HashMap<String, Object> groups;
    public HashMap<String, String> users;

    public CapeConfig() {
        parser = new Gson();
        groups = new HashMap<String, Object>();
        users = new HashMap<String, String>();
    }





}
