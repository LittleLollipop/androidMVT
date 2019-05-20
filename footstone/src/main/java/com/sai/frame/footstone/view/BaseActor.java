package com.sai.frame.footstone.view;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.MotionEvent;

/**
 * Created by sai on 17/12/6.
 */

public class BaseActor extends Actor {

    public BaseActor(Actor actor) {
        super(actor);
    }

    @Override
    public void executeRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

    }

    @Override
    public void executeCreate(Activity polymorphicActivity, ActorInterface actorInterface) {

    }

    @Override
    public void executeStart(Activity polymorphicActivity, ActorInterface actorInterface) {

    }

    @Override
    public void executeRestart(Activity polymorphicActivity, ActorInterface actorInterface) {

    }

    @Override
    public void executeResume(Activity polymorphicActivity, ActorInterface actorInterface) {

    }

    @Override
    public void executePause(Activity polymorphicActivity, ActorInterface actorInterface) {

    }

    @Override
    public void executeStop(Activity polymorphicActivity, ActorInterface actorInterface) {

    }

    @Override
    public void executeDestroy(Activity polymorphicActivity, ActorInterface actorInterface) {

    }

    @Override
    public void executeSaveInstanceState(Activity polymorphicActivity, Bundle outState, ActorInterface actorInterface) {

    }

    @Override
    public void executeRestoreInstanceState(Activity polymorphicActivity, Bundle savedInstanceState, ActorInterface actorInterface) {

    }

    @Override
    public void executeCommand(String command, Object info) {

    }

    @Override
    public void executeActionModeStarted(ActionMode mode) {

    }

    @Override
    public void executeActionModeFinished(ActionMode mode) {

    }

    @Override
    public boolean executeTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public boolean executeKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public boolean executeKeyUp(Activity activity, ActorInterface actorInterface, int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public void executeActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public boolean executeDispatchTouchEvent(MotionEvent ev) {
        return false;
    }


}
