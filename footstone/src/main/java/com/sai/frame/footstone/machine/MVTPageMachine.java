package com.sai.frame.footstone.machine;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;

import com.sai.frame.footstone.base.StateMachine;
import com.sai.frame.footstone.view.Actor;
import com.sai.frame.footstone.view.ActorCenter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static com.sai.frame.footstone.machine.MVTAppMachine.PREPARE_START;
import static com.sai.frame.footstone.machine.MVTAppMachine.STARTING;

/**
 * Created by sai on 17/11/29.
 */

public class MVTPageMachine extends StateMachine implements Application.ActivityLifecycleCallbacks {

    ArrayList<Activity> activities = new ArrayList<>();
    ConcurrentHashMap<String, Application.ActivityLifecycleCallbacks> lifecycleCallBacks = new ConcurrentHashMap<>();
    List<Application.ActivityLifecycleCallbacks> observer = new ArrayList<>();

    MVTAppMachine appMachine;
    ActorCenter actorCenter = new ActorCenter();

    MVTPageMachine(MVTAppMachine appMachine){
        this.appMachine = appMachine;
    }

    @Override
    public boolean checkChange_InMachineThread(State newState, State oldState) {
        return false;
    }

    @Override
    public void stateIn_InMachineThread(State state, Handler mainThreadHandler) {

    }

    @Override
    public void stateLeave_InMachineThread(State state, Handler mainThreadHandler) {

    }

    public Actor getActor(Object key) {
        return actorCenter.getActor(key);
    }

    public void addActor(Object key, Actor actor) {
        actorCenter.addActor(key, actor);
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        activities.add(activity);
        if(appMachine.getStateNow() == PREPARE_START && !appMachine.starter.isPrepareActivity(activity)){
            appMachine.changeState(STARTING);
        }
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        if(appMachine.getStateNow() == PREPARE_START && appMachine.starter.isPrepareActivity(activity)){
            appMachine.changeState(STARTING);
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        activities.remove(activity);
    }
}
