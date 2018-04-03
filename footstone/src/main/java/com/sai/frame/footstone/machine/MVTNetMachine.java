package com.sai.frame.footstone.machine;

import android.os.Handler;

import com.sai.frame.footstone.base.StateMachine;

/**
 * Created by sai on 17/11/29.
 */

public class MVTNetMachine extends StateMachine {

    public static final int STATE_STARTING = 0;
    public static final int STATE_WAITING = 1;
    public static final int STATE_SENDING = 2;
    public static final int STATE_PROCESSING = 3;
    public static final int STATE_STOPED = 4;

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
