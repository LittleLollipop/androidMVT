package com.sai.frame.mvt.demo;

import com.sai.frame.footstone.view.Actors.FixActionModeActor;
import com.sai.frame.footstone.view.Actors.SafeUpdateUIActor;
import com.sai.frame.footstone.view.BaseActor;

/**
 * Created by sai on 17/12/7.
 */

public class MVTDemoActorFactory {
    public static BaseActor getBaseActor() {
        
        return new FixActionModeActor(new SafeUpdateUIActor(null));
    }
}
