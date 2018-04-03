package com.sai.frame.footstone.base;

import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by sai on 17/11/29.
 */

public abstract class StateMachine {

    private final String TAG = StateMachine.this.getClass().getName();

    private static ConcurrentHashMap<String, HandlerThreadHolder> threads = new ConcurrentHashMap<>();
    State stateNow;
    ArrayList<State> allState;

    protected Handler mHandler;
    private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

    boolean stoped = false;
    boolean starting = false;
    boolean autoStart = false;

    State firstState;

    /**
     *
     * @param allState 所有状态
     * @param firstState 首个状态
     * @param autoStart 自动启动，如果开启此功能则在状态机完全启动之后进入首个状态，并收到相关回调，完全启动之前状态为空。关闭此功能则默认处于首个状态，没有空状态，也不会有进入首个状态的回调。
     */
    protected void init(ArrayList<State> allState, State firstState, boolean autoStart){
        this.autoStart = autoStart;
        this.firstState = firstState;

        if(autoStart){
            doInit(allState, null);
        }else{
            doInit(allState, firstState);
        }
    }

    Runnable init = new Runnable() {
        @Override
        public void run() {

            if(mHandler == null){

                HandlerThreadHolder threadHolder = threads.get(TAG);

                if(threadHolder == null){
                    threadHolder = new HandlerThreadHolder(TAG);
                    threads.put(TAG, threadHolder);
                }
                mHandler = threadHolder.buildHandler();

            }
            starting = false;

            if(autoStart){
                changeState(firstState);
            }
        }
    };

    Runnable close = new Runnable() {
        @Override
        public void run() {

            if(starting){
                RunnablePocket.postDelayed(this, 10);
                return;
            }

            HandlerThreadHolder threadHolder = threads.get(TAG);

            if(threadHolder != null){
                stoped = true;
                threadHolder.destroyHandler(mHandler);
                mHandler = null;

                if(threadHolder.clientNumber == 0){
                    threads.remove(TAG);
                } else if(threadHolder.clientNumber < 0){
                    threads.remove(TAG);
                    Log.e(TAG, "you need To burn incense and pray");
                }

            }else{
                if(stoped){
                    Log.e(TAG, "call stop to much ");
                }else{
                    Log.e(TAG, "call stop() before init() or call stop() more than init() ");
                }
            }
        }
    };

    protected final void doInit(final ArrayList<State> allState, final State firstState){

        starting = true;
        StateMachine.this.allState = allState;
        StateMachine.this.stateNow = firstState;


        /**
         * init 与 stop 如果在多线程中被同时调用可能引发新的状态使用一个已经停止或正在停止的线程，从而导致底层Looper和MessageQueue随机出现空指针
         */
        if(Looper.myLooper() != Looper.getMainLooper()){
            RunnablePocket.post(init);
        }else{
            init.run();
        }

    }

    public final void stop(){

        /**
         * init 与 stop 如果在多线程中被同时调用可能引发新的状态使用一个已经停止或正在停止的线程，从而导致底层Looper和MessageQueue随机出现空指针
         */
        if(Looper.myLooper() != Looper.getMainLooper()){
            RunnablePocket.post(close);
        }else{
            close.run();
        }


    }

    public void changeState(final String stateName){

        if(stoped){
            Log.d(TAG,"state machine is stoped");
            return;
        }

        if(mHandler == null){
            RunnablePocket.postDelayed(new Runnable() {
                @Override
                public void run() {
                    changeState(stateName);
                }
            },10);
        }else{
            for(State state : allState){
                if(state.stateName.equals(stateName)){
                    mHandler.post(new ChangeTask(state));
                }
            }
        }
    }

    public void runInMachine(Runnable runnable){
        if(mHandler == null){
            RunnablePocket.postDelayed(runnable, 10);
        }else{
            mHandler.post(runnable);
        }
    }

    public void changeState(final State newState){

        if(stoped){
            Log.d(TAG, "state machine is stoped");
            return;
        }

        if(mHandler == null || (autoStart && stateNow == null && newState != firstState)){
            RunnablePocket.postDelayed(new Runnable() {
                @Override
                public void run() {
                    changeState(newState);
                }
            },10);
        }else{
            for(State state : allState){
                if(state.stateName.equals(newState.stateName)){
                    mHandler.post(new ChangeTask(state));
                }
            }
        }

    }

    public State getStateNow(){
        return stateNow;
    }

    public abstract boolean checkChange_InMachineThread(State newState, State oldState);

    public abstract void stateIn_InMachineThread(State state, Handler mainThreadHandler);

    public abstract void stateLeave_InMachineThread(State state, Handler mainThreadHandler);

    public void refresh() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                stateIn_InMachineThread(stateNow,mainThreadHandler);
            }
        });
    }

    class ChangeTask implements Runnable {

        State newState;

        ChangeTask(State newState){
            this.newState = newState;
        }

        @Override
        public void run() {
            doChange(newState);
        }

        private void doChange(State newState) {

            if(stateNow == null && autoStart){
                stateNow = newState;
                stateIn_InMachineThread(stateNow,mainThreadHandler);
                return;
            }

            if(checkChange_InMachineThread(newState, stateNow) && newState != stateNow){
                stateLeave_InMachineThread(stateNow,mainThreadHandler);
                stateNow = newState;
                stateIn_InMachineThread(stateNow,mainThreadHandler);
                return;
            }
        }
    }

    public static class State{

        public int stateNumber;
        public String stateName;

        public State(int stateNumber, String stateName){
            this.stateName = stateName;
            this.stateNumber = stateNumber;
        }

    }

    private static class HandlerThreadHolder{

        private static final String TAG = HandlerThreadHolder.class.getName();
        HandlerThread mThread;
        String name;

        int clientNumber = 0;
        ArrayList<Handler> handlers = new ArrayList<>();

        public HandlerThreadHolder(String name) {
            this.name = name;
            mThread = new HandlerThread(name);
            mThread.start();
        }

        public Handler buildHandler(){
            clientNumber++;

            if(mThread.getLooper() == null){
                reBuildThread();
            }

            Handler handler = new Handler(mThread.getLooper());
            handlers.add(handler);
            return handler;
        }

        private void reBuildThread() {
            mThread = new HandlerThread(name);
            mThread.start();

            if(handlers.size() > 0){
                Log.e(TAG, "The thread is forced to stop :" + handlers.size());
            }

            handlers.clear();
        }

        public void destroyHandler(Handler mHandler) {

            if(handlers.remove(mHandler)){
                mHandler.removeCallbacksAndMessages(null);
                clientNumber--;

                if(clientNumber <= 0){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                        mThread.quitSafely();
                    }else{
                        mThread.quit();
                    }
                }
            }else{
                Log.e(TAG, "call stop to much " + name);
            }

        }
    }

}
