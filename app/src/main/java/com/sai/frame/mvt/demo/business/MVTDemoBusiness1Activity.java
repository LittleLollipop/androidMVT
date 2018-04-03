package com.sai.frame.mvt.demo.business;

import com.sai.frame.footstone.view.Actor;
import com.sai.frame.footstone.view.BaseActor;
import com.sai.frame.footstone.view.PolymorphicActivity;
import com.sai.frame.mvt.demo.MVTDemoFirstActivity;

/**
 * Created by sai on 2017/12/26.
 */

public class MVTDemoBusiness1Activity extends PolymorphicActivity {

    public static final String TAG = MVTDemoBusiness1Activity.class.getName();

    @Override
    public Object getActorKey() {
        return TAG;
    }

}
