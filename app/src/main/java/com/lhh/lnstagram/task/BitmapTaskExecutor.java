package com.lhh.lnstagram.task;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author sunling
 * @date 2019/7/11
 */
public class BitmapTaskExecutor {

    private static BitmapTaskExecutor instance;
    private Executor executor;

    private BitmapTaskExecutor(){
        executor = Executors.newFixedThreadPool(4);
    }

    public static BitmapTaskExecutor getInstance() {
        if (instance==null){
            synchronized (BitmapTaskExecutor.class) {
                if (instance==null){
                    return instance = new BitmapTaskExecutor();
                }
            }
        }
        return instance;
    }

    public Executor getExecutor() {
        return executor;
    }


}
