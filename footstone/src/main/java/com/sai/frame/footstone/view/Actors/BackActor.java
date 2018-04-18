package com.sai.frame.footstone.view.Actors;

import android.view.KeyEvent;
import android.view.MotionEvent;

import com.sai.frame.footstone.view.Actor;
import com.sai.frame.footstone.view.BaseActor;
import com.sai.frame.footstone.view.PolymorphicActivity;

/**
 * Created by sai on 2018/4/18.
 */

public class BackActor extends BaseActor {

    boolean mKeyBackDown = false;

    public BackActor(Actor actor) {
        super(actor);
    }

    @Override
    public boolean executeKeyUp(PolymorphicActivity activity, int keyCode, KeyEvent event) {

        if(event.getKeyCode() == KeyEvent.KEYCODE_BACK && (mKeyBackDown)){
            mKeyBackDown = false;
            activity.dealBackBtnPressed();
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
