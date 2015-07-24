package com.udacity.gradle.builditbigger.backend;

import com.udacity.gradle.jokes.Joker;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyBean {
    private static Joker joker;
    private String myJoke;

    public void setMyJoke() {
        if (joker == null)
            joker = new Joker();
        myJoke = joker.getJoke();
    }

    public String getJoke() {
        return myJoke;
    }
}