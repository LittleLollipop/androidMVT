package com.sai.frame.footstone.machine;

import android.os.Handler;

import com.sai.frame.footstone.base.ManifoldValve;
import com.sai.frame.footstone.base.StateMachine;
import com.sai.frame.footstone.view.BaseActor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sai on 17/11/29.
 */

public class MVTBusinessMachine extends StateMachine {

    public static final int STATE_STARTING = 0;
    public static final int STATE_BUSINESS_RUNNING = 1;
    public static final int STATE_BUSINESS_CHANGING = 2;

    public static final State STARTING = new State(STATE_STARTING, "STATE_STARTING");
    public static final State BUSINESS_RUNNING = new State(STATE_BUSINESS_RUNNING, "STATE_BUSINESS_RUNNING");
    public static final State BUSINESS_CHANGING = new State(STATE_BUSINESS_CHANGING, "STATE_BUSINESS_CHANGING");

    ManifoldValve startManifoldValve;

    HashMap<String, MVTBaseBusiness> allBusiness = new HashMap<>();

    MVTBaseBusiness businessNow;
    MVTBaseBusiness newBusiness;

    MVTBusinessManager businessManager;
    MVTAppMachineImp appMachine;

    MVTBusinessMachine(MVTAppMachine mvtAppMachine, MVTBusinessManager businessManager){

        this.appMachine = (MVTAppMachineImp) mvtAppMachine;
        this.businessManager = businessManager;

        startManifoldValve = new ManifoldValve(new ManifoldValve.Outfall() {
            @Override
            public void discharge(ManifoldValve mManifoldValve) {
                changeState(BUSINESS_RUNNING);
            }
        },2);

        ArrayList<State> allState = new ArrayList<>();

        allState.add(STARTING);
        allState.add(BUSINESS_RUNNING);
        allState.add(BUSINESS_CHANGING);

        init(allState, STARTING, true);
    }

    @Override
    public boolean checkChange_InMachineThread(State newState, State oldState) {

        switch (oldState.stateNumber){
            case STATE_STARTING:
                if(newState == BUSINESS_RUNNING)
                    return true;
            case STATE_BUSINESS_RUNNING:
                if(newState == BUSINESS_CHANGING)
                    return true;
            case STATE_BUSINESS_CHANGING:
                if(newState == BUSINESS_RUNNING)
                    return true;
        }

        return false;
    }

    @Override
    public void stateIn_InMachineThread(State state, Handler mainThreadHandler) {

        if(state == STARTING){
            businessManager.init();
            allBusiness.putAll(businessManager.getAllBusiness());
            startManifoldValve.openValveOnce(0);
        }

        if(state == BUSINESS_RUNNING){
            businessManager.stateChanged(state);

            if(businessNow == null){
                startBusiness(businessManager.getFirstBusiness());
            }
        }

        if(state == BUSINESS_CHANGING){
            businessNow = newBusiness;
            newBusiness = null;
            businessNow.machine = this;
            businessNow.start();
        }

    }

    public void startBusiness(MVTBaseBusiness newBusiness) {

        this.newBusiness = newBusiness;
        changeState(BUSINESS_CHANGING);
    }

    private void onBusinessStartOver(MVTBaseBusiness newBusiness){
        changeState(BUSINESS_RUNNING);
    }

    @Override
    public void stateLeave_InMachineThread(State state, Handler mainThreadHandler) {

    }

    protected void onAppMachineRunning(){
        startManifoldValve.openValveOnce(1);
    }

    public static abstract class MVTBaseBusiness extends StateMachine{

        protected MVTBusinessMachine machine;

        public static final int STATE_INIT = 0;
        public static final int STATE_RUNNING_FOREGROUND = 1;
        public static final int STATE_RUNNING_BACKGROUND = 2;
        public static final int STATE_STOPED = 3;

        public static final State INIT = new State(STATE_INIT, "STATE_INIT");
        public static final State RUNNING_FOREGROUND = new State(STATE_RUNNING_FOREGROUND, "STATE_RUNNING_FOREGROUND");
        public static final State RUNNING_BACKGROUND = new State(STATE_RUNNING_BACKGROUND, "STATE_RUNNING_BACKGROUND");
        public static final State STOPED = new State(STATE_STOPED, "STATE_STOPED");

        public MVTBaseBusiness(){
            ArrayList<State> allState = new ArrayList<>();

            allState.add(INIT);
            allState.add(RUNNING_FOREGROUND);
            allState.add(RUNNING_BACKGROUND);
            allState.add(STOPED);

            init(allState, INIT, false);
        }

        public abstract void start();

        public void addActor(String tag, BaseActor actor){
            machine.appMachine.addActor(tag, actor);
        }

        public void startOver(){
            machine.onBusinessStartOver(this);
        }
    }

    public static abstract class MVTBusinessManager{

        public abstract Map<? extends String,? extends MVTBaseBusiness> getAllBusiness();

        /**
         *if you need do something at the beginning, override this
         */
        public void init() {}

        public abstract void stateChanged(State state);

        public abstract MVTBaseBusiness getFirstBusiness();
    }

}
