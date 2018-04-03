package com.sai.frame.mvt.demo.business;

import android.os.Handler;

import com.sai.frame.footstone.machine.MVTBusinessMachine;

/**
 * Created by sai on 2017/12/26.
 */

public class MVTDemoBusiness2 extends MVTBusinessMachine.MVTBaseBusiness {
    @Override
    public void start() {
        
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
}
