package com.sai.frame.footstone.base;

/**
 * Created by sai on 2018/5/31.
 */

public abstract class DataRunnable implements Runnable {

    protected Object data;

    public void setData(Object data) {
        this.data = data;
    }
}
