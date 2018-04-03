package com.sai.frame.mvt.demo;

import android.support.v4.util.ArrayMap;

import com.sai.frame.footstone.base.StateMachine;
import com.sai.frame.footstone.machine.MVTBusinessMachine;
import com.sai.frame.mvt.demo.business.MVTDemoBusiness1;
import com.sai.frame.mvt.demo.business.MVTDemoBusiness2;

import java.util.Map;

/**
 * Created by sai on 2017/12/25.
 */

public class MVTDemoBusinessManager extends MVTBusinessMachine.MVTBusinessManager {

    private static MVTDemoBusinessManager instance = new MVTDemoBusinessManager();

    ArrayMap<String, MVTBusinessMachine.MVTBaseBusiness> allBusiness = new ArrayMap<>();

    public static MVTDemoBusinessManager getInstance() {
        return instance;
    }

    @Override
    public Map<? extends String, ? extends MVTBusinessMachine.MVTBaseBusiness> getAllBusiness() {

        allBusiness.put(MVTDemoBusiness1.class.getName(), new MVTDemoBusiness1());
        allBusiness.put(MVTDemoBusiness2.class.getName(), new MVTDemoBusiness2());

        return allBusiness;
    }

    @Override
    public void stateChanged(StateMachine.State state) {

    }

    @Override
    public MVTBusinessMachine.MVTBaseBusiness getFirstBusiness() {
        return allBusiness.get(MVTDemoBusiness1.class.getName());
    }
}
