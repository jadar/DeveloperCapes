package com.jadarstudios.developercapes.user;

import com.jadarstudios.developercapes.ICape;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jadar
 */
public class User {

    public List<ICape> capes;
    public final String username;

    public User(String username) {
        this.username = username;
        this.capes = new ArrayList<ICape>();
    }



}
