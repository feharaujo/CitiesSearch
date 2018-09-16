package com.fearaujo.arch.executor.impl;

import com.fearaujo.arch.executor.Executor;
import com.fearaujo.arch.interactors.base.AbstractInteractor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * This singleton class will make sure that each interactor operation gets a background thread.
 * <p/>
 */
public class ThreadExecutor implements Executor {

    private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private static final int MAX_POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private static final int KEEP_ALIVE_TIME = 1;
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private static final BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<Runnable>();
    // This is a singleton
    private static volatile ThreadExecutor sThreadExecutor;
    private ThreadPoolExecutor mThreadPoolExecutor;

    //private ExecutorService executorService;

    private ThreadExecutor() {
        long keepAlive = KEEP_ALIVE_TIME;
        mThreadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                keepAlive,
                TIME_UNIT,
                WORK_QUEUE);
    }

    /**
     * Returns a singleton instance of this executor. If the executor is not initialized then it initializes it and returns
     * the instance.
     */
    public static Executor getInstance() {
        if (sThreadExecutor == null) {
            sThreadExecutor = new ThreadExecutor();
        }


        return sThreadExecutor;
    }

    @Override
    public void execute(final AbstractInteractor interactor) {
        mThreadPoolExecutor.submit(() -> {
            // run the main logic
            interactor.run();

            // mark it as finished
            interactor.onFinished();
        });
    }

}
