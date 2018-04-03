package com.sai.frame.footstone.view.Actors;

import android.view.ActionMode;

import com.sai.frame.footstone.view.Actor;
import com.sai.frame.footstone.view.BaseActor;
import com.sai.frame.footstone.view.PolymorphicActivity;

/**
 * Created by sai on 17/12/6.
 */

public class FixActionModeActor extends BaseActor {

    private ActionMode mActionMode;

    public FixActionModeActor(Actor actor) {
        super(actor);
    }

    @Override
    public void executePause(PolymorphicActivity polymorphicActivity) {
        endActionMode();
    }

    /**
     * Makes sure action mode is ended
     */
    private void endActionMode() {
        if (mActionMode != null) {
            mActionMode.finish(); /** immediately calls {@link #onActionModeFinished(ActionMode)} */
        }
    }

    /**
     * Clear action mode every time it finishes.
     * @param mode
     */
    @Override
    public void executeActionModeFinished(ActionMode mode) {
        mActionMode = null;
    }

    /**
     * Store action mode if it is started
     * @param mode
     */
    @Override
    public void executeActionModeStarted(ActionMode mode) {
        mActionMode = mode;
    }
}
