package com.sai.frame.footstone.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadHandlerPool implements Application.ActivityLifecycleCallbacks {

    private static ConcurrentHashMap<String, StateMachine.HandlerThreadHolder> threads = new ConcurrentHashMap<>();

    static int numFlag = 1;

    public static StateMachine.HandlerThreadHolder createHandlerThread(String threadName){

        StateMachine.HandlerThreadHolder threadHolder = threads.get(threadName);
        if(threadHolder == null){
            threadHolder = new StateMachine.HandlerThreadHolder(threadName);
            threadHolder.buildHandler();
            threads.put(threadName, threadHolder);
        }
        return threadHolder;
    }

    public static ThreadHandlerPool initPool(Context context){

        createThreads();
        ThreadHandlerPool threadHandlerPool = new ThreadHandlerPool();
        ((Application)context.getApplicationContext()).registerActivityLifecycleCallbacks(threadHandlerPool);
        return threadHandlerPool;
    }

    private static void createThreads(){
        for (int i = 0; i < 10; i++){
            createHandlerThread("ThreadHandlerPool_T" + i);
        }
    }

    public static void post(Runnable r){

        int num = numFlag++;

        threads.get("ThreadHandlerPool_T" + num).handlers.get(0).post(r);
    }

    public static void postDelayed(Runnable r, long delayMillis){

        int num = numFlag++;

        threads.get("ThreadHandlerPool_T" + num).handlers.get(0).postDelayed(r, delayMillis);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
