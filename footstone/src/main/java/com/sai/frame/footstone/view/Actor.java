package com.sai.frame.footstone.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.MotionEvent;

/**
 * Created by sai on 17/12/1.
 */

public abstract class Actor {

    Actor mActor;

    Actor(Actor actor){
        mActor = actor;
    }

    public void onCreate(PolymorphicActivity polymorphicActivity){
        if(mActor != null)
            mActor.onCreate(polymorphicActivity);

        executeCreate(polymorphicActivity);
    }

    public void onStart(PolymorphicActivity polymorphicActivity){
        if(mActor != null)
            mActor.onStart(polymorphicActivity);

        executeStart(polymorphicActivity);
    }

    public void onRestart(PolymorphicActivity polymorphicActivity){
        if(mActor != null)
            mActor.onRestart(polymorphicActivity);

        executeRestart(polymorphicActivity);
    }

    public void onResume(PolymorphicActivity polymorphicActivity){
        if(mActor != null)
            mActor.onResume(polymorphicActivity);

        executeResume(polymorphicActivity);
    }

    public void onPause(PolymorphicActivity polymorphicActivity){
        if(mActor != null)
            mActor.onPause(polymorphicActivity);

        executePause(polymorphicActivity);
    }

    public void onStop(PolymorphicActivity polymorphicActivity){
        if(mActor != null)
            mActor.onStop(polymorphicActivity);

        executeStop(polymorphicActivity);
    }

    public void onDestroy(PolymorphicActivity polymorphicActivity){
        if(mActor != null)
            mActor.onDestroy(polymorphicActivity);

        executeDestroy(polymorphicActivity);
    }

    public void onSaveInstanceState(PolymorphicActivity polymorphicActivity, Bundle outState){
        if(mActor != null)
            mActor.onSaveInstanceState(polymorphicActivity, outState);

        executeSaveInstanceState(polymorphicActivity, outState);
    }

    public void onRestoreInstanceState(PolymorphicActivity polymorphicActivity, Bundle savedInstanceState){
        if(mActor != null)
            mActor.onRestoreInstanceState(polymorphicActivity, savedInstanceState);

        executeRestoreInstanceState(polymorphicActivity, savedInstanceState);
    }

    public void onActionModeStarted(ActionMode mode) {
        if(mActor != null)
            mActor.onActionModeStarted(mode);

        executeActionModeStarted(mode);
    }

    public void onActionModeFinished(ActionMode mode) {
        if(mActor != null)
            mActor.onActionModeFinished(mode);

        executeActionModeFinished(mode);
    }

    public void onCommand(String command, Object info){
        if (mActor != null)
            mActor.onCommand(command, info);

        executeCommand(command, info);
    }

    public boolean onDispatchTouchEvent(MotionEvent ev){
        if (mActor != null){
            boolean b = mActor.onDispatchTouchEvent(ev);
            if(b)
                return b;
            else
                return executeDispatchTouchEvent(ev);
        }
        return false;
    }

    public boolean onTouchEvent(MotionEvent event){
        if (mActor != null){
            boolean b = mActor.onTouchEvent(event);
            if(b)
                return b;
            else
                return executeTouchEvent(event);
        }
        return false;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mActor != null){
            boolean b = mActor.onKeyDown(keyCode, event);
            if(b)
                return b;
            else
                return executeKeyDown(keyCode, event);
        }
        return false;
    }

    public boolean onKeyUp(PolymorphicActivity activity, int keyCode, KeyEvent event) {
        if (mActor != null){
            boolean b = mActor.onKeyUp(activity, keyCode, event);
            if(b)
                return b;
            else
                return executeKeyUp(activity, keyCode, event);
        }
        return false;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(mActor != null)
            mActor.onActivityResult(requestCode, resultCode, data);

        executeActivityResult(requestCode, resultCode, data);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        if(mActor != null)
            mActor.onRequestPermissionsResult(requestCode, permissions, grantResults);

        executeRequestPermissionsResult(requestCode, permissions, grantResults);
    }



    public abstract void executeRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);

    public abstract void executeCreate(PolymorphicActivity polymorphicActivity);

    public abstract void executeStart(PolymorphicActivity polymorphicActivity);

    public abstract void executeRestart(PolymorphicActivity polymorphicActivity);

    public abstract void executeResume(PolymorphicActivity polymorphicActivity);

    public abstract void executePause(PolymorphicActivity polymorphicActivity);

    public abstract void executeStop(PolymorphicActivity polymorphicActivity);

    public abstract void executeDestroy(PolymorphicActivity polymorphicActivity);

    public abstract void executeSaveInstanceState(PolymorphicActivity polymorphicActivity, Bundle outState);

    public abstract void executeRestoreInstanceState(PolymorphicActivity polymorphicActivity, Bundle savedInstanceState);

    public abstract void executeCommand(String command, Object info);

    public abstract void executeActionModeStarted(ActionMode mode);

    public abstract void executeActionModeFinished(ActionMode mode);

    public abstract boolean executeTouchEvent(MotionEvent event);

    public abstract boolean executeKeyDown(int keyCode, KeyEvent event);

    public abstract boolean executeKeyUp(PolymorphicActivity activity, int keyCode, KeyEvent event);

    public abstract void executeActivityResult(int requestCode, int resultCode, Intent data);

    public abstract boolean executeDispatchTouchEvent(MotionEvent ev);

}
