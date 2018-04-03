package com.sai.frame.footstone.view;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by sai on 17/12/1.
 */

public class ActorCenter {

    protected Hashtable<Object, Actor> actors = new Hashtable<>();

    public Actor getActor(Object key) {
        return actors.get(key);
    }

    public void addActor(Object key, Actor actor) {
        actors.put(key, actor);
    }
}
