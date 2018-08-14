package com.sai.frame.footstone.view;


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
    public void executeCreate(PolymorphicActivity polymorphicActivity) {

    }

    @Override
    public void executeStart(PolymorphicActivity polymorphicActivity) {

    }

    @Override
    public void executeRestart(PolymorphicActivity polymorphicActivity) {

    }

    @Override
    public void executeResume(PolymorphicActivity polymorphicActivity) {

    }

    @Override
    public void executePause(PolymorphicActivity polymorphicActivity) {

    }

    @Override
    public void executeStop(PolymorphicActivity polymorphicActivity) {

    }

    @Override
    public void executeDestroy(PolymorphicActivity polymorphicActivity) {

    }

    @Override
    public void executeSaveInstanceState(PolymorphicActivity polymorphicActivity, Bundle outState) {

    }

    @Override
    public void executeRestoreInstanceState(PolymorphicActivity polymorphicActivity, Bundle savedInstanceState) {

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
    public boolean executeKeyUp(PolymorphicActivity activity, int keyCode, KeyEvent event) {
        return false;
    }

    @Override
    public void executeActivityResult(int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void executeRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

    }
}
