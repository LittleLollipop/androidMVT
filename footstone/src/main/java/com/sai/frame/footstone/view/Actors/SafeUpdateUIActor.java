package com.sai.frame.footstone.view.Actors;

import android.app.Activity;
import android.os.Looper;

import com.sai.frame.footstone.base.ManifoldValve;
import com.sai.frame.footstone.base.RunnablePocket;
import com.sai.frame.footstone.view.Actor;
import com.sai.frame.footstone.view.ActorInterface;
import com.sai.frame.footstone.view.BaseActor;
import com.sai.frame.footstone.view.PolymorphicActivity;

/**
 * Created by sai on 17/12/6.
 */

public class SafeUpdateUIActor extends BaseActor {

    public static final String COMMAND_SAFE_UPDATE_UI = "COMMAND_SAFE_UPDATE_UI";

    private ManifoldValve safeUpdateUIManifoldValve;

    public SafeUpdateUIActor(Actor actor) {
        super(actor);
    }

    @Override
    public void executeCreate(Activity polymorphicActivity, ActorInterface actorInterface) {
        createManifoldValve();
    }

    @Override
    public void executeResume(Activity polymorphicActivity, ActorInterface actorInterface) {
        safeUpdateUIManifoldValve.openValveOnce(1);
    }

    @Override
    public void executePause(Activity polymorphicActivity, ActorInterface actorInterface) {
        createManifoldValve();
    }

    @Override
    public void executeCommand(String command, Object info) {
        if (COMMAND_SAFE_UPDATE_UI.equals(command)){
            registerSafeUpdateUITask((Runnable) info);
        }
    }

    private void createManifoldValve(){
        safeUpdateUIManifoldValve = new ManifoldValve(new ManifoldValve.Outfall() {
            @Override
            public void discharge(ManifoldValve mHDManifoldValve) {
                safeUpdateUI((Runnable) mHDManifoldValve.getData_object(0));
            }
        },2);
    }

    public void registerSafeUpdateUITask(Runnable runnable) {
        safeUpdateUIManifoldValve.setData(runnable, 0);
        safeUpdateUIManifoldValve.openValve(0);
    }

    private void safeUpdateUI(Runnable runnable) {
        if(Looper.myLooper() == Looper.getMainLooper()){
            runnable.run();
        }else{
            RunnablePocket.post(runnable);
        }
    }
}
