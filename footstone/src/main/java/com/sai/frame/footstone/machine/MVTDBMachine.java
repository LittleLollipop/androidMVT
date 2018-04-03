package com.sai.frame.footstone.machine;

import android.os.Handler;

import com.sai.frame.footstone.base.StateMachine;

/**
 * Created by sai on 17/11/29.
 */

public class MVTDBMachine extends StateMachine{

    public static final int STATE_STARTING = 0;
    public static final int STATE_WAITING = 1;
    public static final int STATE_USING = 2;


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
}
