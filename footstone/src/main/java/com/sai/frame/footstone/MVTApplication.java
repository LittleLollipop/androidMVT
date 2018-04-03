package com.sai.frame.footstone;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.sai.frame.footstone.machine.MVTAppMachine;
import com.sai.frame.footstone.machine.MVTAppMachineImp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by sai on 17/12/4.
 */

public abstract class MVTApplication extends Application {

    MVTAppMachineImp machine = new MVTAppMachineImp(getStarter());
    
    public MVTAppMachineImp getMachine() {
        return machine;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if(!isDelayedStart()){
            machine.start();
        }
        machine.registerActivityLifecycleCallbacks(this);
    }

    public abstract boolean isDelayedStart();

    protected abstract MVTAppMachine.Starter getStarter();

}
