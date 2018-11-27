package com.sai.frame.footstone.machine;

import com.sai.frame.footstone.MVTApplication;
import com.sai.frame.footstone.view.Actor;
import com.sai.frame.footstone.view.BaseActor;

/**
 * Created by sai on 17/12/4.
 */

public class MVTAppMachineImp extends MVTAppMachine {

    public MVTAppMachineImp(Starter starter) {
        super(starter);
    }

    public Actor getActor(Object key) {
        return mPageMachine.getActor(key);
    }

    public void start() {
        changeState(STARTING);
    }

    public void addActor(Object key, Actor actor) {
        mPageMachine.addActor(key, actor);
    }

    public void registerActivityLifecycleCallbacks(MVTApplication mvtApplication) {
        mvtApplication.registerActivityLifecycleCallbacks(mPageMachine);
    }

    public MVTPageMachine getPageMachine(){
        return mPageMachine;
    }
}
