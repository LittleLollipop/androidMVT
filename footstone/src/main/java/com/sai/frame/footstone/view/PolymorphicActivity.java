package com.sai.frame.footstone.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.sai.frame.footstone.MVTApplication;

/**
 * Created by sai on 17/12/1.
 */

public abstract class PolymorphicActivity extends Activity {

    Actor actor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        actor = ((MVTApplication)getApplication()).getMachine().getActor(getActorKey());
        actor.onCreate(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        actor.onStart(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        actor.onRestart(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        actor.onResume(this);
    }

    @Override
    protected void onPause() {
        actor.onPause(this);
        super.onPause();
    }

    @Override
    protected void onStop() {
        actor.onStop(this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        actor.onDestroy(this);
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        actor.onSaveInstanceState(this, outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        actor.onRestoreInstanceState(this, savedInstanceState);
    }

    @Override
    public void onActionModeStarted(ActionMode mode) {
        super.onActionModeStarted(mode);
        actor.onActionModeStarted(mode);
    }

    @Override
    public void onActionModeFinished(ActionMode mode) {
        super.onActionModeFinished(mode);
        actor.onActionModeFinished(mode);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(actor.onKeyDown(keyCode, event))
            return true;
        else
            return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(actor.onKeyUp(this, keyCode, event))
            return true;
        else
            return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(actor.onTouchEvent(event))
            return true;
        else
            return super.onTouchEvent(event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        actor.onActivityResult(requestCode, resultCode, data);
    }

    public abstract Object getActorKey();

    public void onCommand(String commandBack) {
        actor.onCommand(commandBack, this);
    }
}
