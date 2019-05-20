package com.sai.frame.footstone.view.Actors;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.sai.frame.footstone.view.Actor;
import com.sai.frame.footstone.view.ActorInterface;
import com.sai.frame.footstone.view.BaseActor;
import com.sai.frame.footstone.view.PolymorphicActivity;

/**
 * Created by sai on 2018/4/18.
 */

public class BackActor extends BaseActor {

    public static final String COMMAND_BACK = "COMMAND_BACK";

    boolean mKeyBackDown = false;

    public BackActor(Actor actor) {
        super(actor);
    }

    @Override
    public boolean executeKeyUp(Activity activity, ActorInterface actorInterface, int keyCode, KeyEvent event) {

        if(event.getKeyCode() == KeyEvent.KEYCODE_BACK && (mKeyBackDown)){
            mKeyBackDown = false;
            actorInterface.onCommand(COMMAND_BACK);
            return true;
        }

        return false;
    }

    @Override
    public boolean executeKeyDown(int keyCode, KeyEvent event) {

        if(event.getKeyCode() == KeyEvent.KEYCODE_BACK){
            mKeyBackDown = true;
            return true;
        }

        return false;
    }
}
