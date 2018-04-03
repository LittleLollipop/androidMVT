package com.sai.frame.mvt.demo;

import android.app.Activity;
import android.os.Handler;

import com.sai.frame.footstone.MVTApplication;
import com.sai.frame.footstone.machine.MVTAppMachine;
import com.sai.frame.footstone.machine.MVTBusinessMachine;

/**
 * Created by sai on 17/12/7.
 */

public class MVTDemoApplication extends MVTApplication implements MVTAppMachine.Starter {

    boolean startSucceed = false;

    static MVTDemoApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MVTDemoApplication getInstance() {
        return instance;
    }

    @Override
    public boolean isDelayedStart() {
        return true;
    }

    @Override
    protected MVTAppMachine.Starter getStarter() {
        return this;
    }

    @Override
    public void prepareStart(Handler mainThreadHandler) {
        getMachine().addActor(MVTDemoFirstActivity.TAG, new MVTDemoFirstActivity.FirstActivityActor(MVTDemoActorFactory.getBaseActor()));
    }

    @Override
    public void start(Handler mainThreadHandler) {
        startSucceed = true;
    }

    @Override
    public boolean isPrepareActivity(Activity activity) {
        return activity instanceof MVTDemoFirstActivity;
    }

    @Override
    public boolean isStartSucceed() {
        return startSucceed;
    }

    @Override
    public MVTBusinessMachine.MVTBusinessManager getMVTBusinessManager() {
        return MVTDemoBusinessManager.getInstance();
    }
}
