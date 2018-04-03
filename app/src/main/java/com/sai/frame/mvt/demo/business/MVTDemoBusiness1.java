package com.sai.frame.mvt.demo.business;

import android.content.Intent;
import android.os.Handler;

import com.sai.frame.footstone.machine.MVTBusinessMachine;
import com.sai.frame.footstone.view.Actor;
import com.sai.frame.footstone.view.BaseActor;
import com.sai.frame.footstone.view.PolymorphicActivity;
import com.sai.frame.mvt.R;
import com.sai.frame.mvt.demo.MVTDemoActorFactory;
import com.sai.frame.mvt.demo.MVTDemoApplication;
import com.sai.frame.mvt.demo.MVTDemoFirstActivity;

/**
 * Created by sai on 2017/12/26.
 */

public class MVTDemoBusiness1 extends MVTBusinessMachine.MVTBaseBusiness {

    @Override
    public void start() {
        changeState(RUNNING_FOREGROUND);
    }

    @Override
    public boolean checkChange_InMachineThread(State newState, State oldState) {

        switch (oldState.stateNumber){
            case STATE_INIT:
                return true;
        }

        return false;
    }

    @Override
    public void stateIn_InMachineThread(State state, Handler mainThreadHandler) {

        if(state == RUNNING_FOREGROUND){
            addActor(MVTDemoBusiness1Activity.TAG, new Business1Actor(MVTDemoActorFactory.getBaseActor()));
            Intent i = new Intent(MVTDemoApplication.getInstance(), MVTDemoBusiness1Activity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            MVTDemoApplication.getInstance().startActivity(i);
        }
    }



    @Override
    public void stateLeave_InMachineThread(State state, Handler mainThreadHandler) {

    }

    class Business1Actor extends BaseActor {

        public Business1Actor(Actor actor) {
            super(actor);
        }

        @Override
        public void onCreate(PolymorphicActivity polymorphicActivity) {
            super.onCreate(polymorphicActivity);
            polymorphicActivity.setContentView(R.layout.activity_business1);
        }

        @Override
        public void onResume(PolymorphicActivity polymorphicActivity) {
            super.onResume(polymorphicActivity);
            startOver();
        }
    }
}
