package com.sai.frame.footstone.tools;

import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by sai on 17/12/1.
 */

public class ViewList extends ArrayList {

    public void findViews(View root, int[] ids){

        ensureCapacity(ids.length);

        for(int id : ids){
            this.add(root.findViewById(id));
        }
    }

    public void setOnClickListener(ViewListClickListener mViewListClickListener, int[] numbersInList){

        mViewListClickListener.mViewList = this;

        for (int number : numbersInList){
            ((View)this.get(number)).setOnClickListener(mViewListClickListener);
        }
    }

    public void setOnTouchListener(ViewListTouchListener mViewListTouchListener, int[] numbersInList){
        mViewListTouchListener.mViewList = this;

        for (int number : numbersInList){
            ((View)this.get(number)).setOnTouchListener(mViewListTouchListener);
        }
    }


    public <V extends View> V get(int index, Class<V> v) {
        return (V) super.get(index);
    }

    public abstract static class ViewListTouchListener implements View.OnTouchListener {
        ViewList mViewList;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if(mViewList != null){
                return onTouch(v, event, mViewList.indexOf(v));
            }else{
                //no list, something error happened
            }
            return false;
        }

        public abstract boolean onTouch(View v, MotionEvent event, int numberInList);
    }

    public abstract static class ViewListClickListener implements View.OnClickListener {

        ViewList mViewList;

        @Override
        public void onClick(View v) {

            if(mViewList != null){
                onClick(v, mViewList.indexOf(v));
            }else{
                //no list, something error happened
            }
        }

        public abstract void onClick(View v, int numberInList);
    }
    
}
