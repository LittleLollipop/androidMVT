package com.sai.frame.footstone.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ActionMode;

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

    public abstract Object getActorKey();
}
