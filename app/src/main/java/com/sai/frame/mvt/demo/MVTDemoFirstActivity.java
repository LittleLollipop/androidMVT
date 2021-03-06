package com.sai.frame.mvt.demo;

import android.app.Activity;

import com.sai.frame.footstone.view.Actor;
import com.sai.frame.footstone.view.ActorInterface;
import com.sai.frame.footstone.view.BaseActor;
import com.sai.frame.footstone.view.PolymorphicActivity;
import com.sai.frame.mvt.R;

/**
 * Created by sai on 17/12/7.
 */

public class MVTDemoFirstActivity extends PolymorphicActivity {

    public static final String TAG = MVTDemoFirstActivity.class.getName();

    @Override
    public Object getActorKey() {
        return TAG;
    }

    static class FirstActivityActor extends BaseActor{

        public FirstActivityActor(Actor actor) {
            super(actor);
        }

        @Override
        public void executeCreate(Activity polymorphicActivity, ActorInterface actorInterface) {
            polymorphicActivity.setContentView(R.layout.activity_first);
        }
    }


    public void dealBackBtnPressed() {
        System.out.print("back");
    }
}
