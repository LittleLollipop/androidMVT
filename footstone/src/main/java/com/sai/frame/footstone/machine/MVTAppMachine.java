package com.sai.frame.footstone.machine;

import android.app.Activity;
import android.os.Handler;

import com.sai.frame.footstone.base.RunnablePocket;
import com.sai.frame.footstone.base.StateMachine;

import java.util.ArrayList;

/**
 * Created by sai on 17/11/29.
 */

public class MVTAppMachine extends StateMachine {

    public static final int STATE_PREPARE_START = 0;
    public static final int STATE_STARTING = 1;
    public static final int STATE_RUNNING = 2;
    public static final int STATE_BACKGROUND = 3;
    public static final int STATE_EXITING = 4;

    public static final State PREPARE_START = new State(STATE_PREPARE_START, "STATE_PREPARE_START");
    public static final State STARTING = new State(STATE_STARTING, "STATE_STARTING");
    public static final State RUNNING = new State(STATE_RUNNING, "STATE_RUNNING");
    public static final State BACKGROUND = new State(STATE_BACKGROUND, "STATE_BACKGROUND");
    public static final State EXITING = new State(STATE_EXITING, "STATE_EXITING");

    MVTPageMachine mPageMachine;
    MVTBusinessMachine mBusinessMachine;
    Starter starter;

    MVTAppMachine(Starter starter){
        this.starter = starter;
        mPageMachine = new MVTPageMachine(this);


        ArrayList<State> allState = new ArrayList<>();

        allState.add(PREPARE_START);
        allState.add(STARTING);
        allState.add(RUNNING);
        allState.add(BACKGROUND);
        allState.add(EXITING);

        init(allState, PREPARE_START, true);
    }

    @Override
    public boolean checkChange_InMachineThread(State newState, State oldState) {

        switch (oldState.stateNumber){
            case STATE_PREPARE_START:
                if(newState == STARTING)
                    return true;
            case STATE_STARTING:
                if(newState == RUNNING)
                    return true;
        }

        return false;
    }

    @Override
    public void stateIn_InMachineThread(State state, Handler mainThreadHandler) {

        if(state == PREPARE_START){
            initMachine();
            starter.prepareStart(mainThreadHandler);
        }

        if(state == STARTING){
            starter.start(mainThreadHandler);
            RunnablePocket.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(starter.isStartSucceed())
                        changeState(RUNNING);
                    else
                        RunnablePocket.postDelayed(this, 10);
                }
            }, 10);
        }

        if(state == RUNNING){
            mBusinessMachine.onAppMachineRunning();
        }

    }

    @Override
    public void stateLeave_InMachineThread(State state, Handler mainThreadHandler) {

    }

    private void initMachine() {
        mBusinessMachine = new MVTBusinessMachine(this, starter.getMVTBusinessManager());
    }

    public interface Starter{

        /**
         * default callback in machine thread
         * @param mainThreadHandler
         */
        public void prepareStart(Handler mainThreadHandler);

        /**
         * default callback in machine thread
         * @param mainThreadHandler
         */
        public void start(Handler mainThreadHandler);

        public boolean isPrepareActivity(Activity activity);

        boolean isStartSucceed();

        MVTBusinessMachine.MVTBusinessManager getMVTBusinessManager();
    }
}
