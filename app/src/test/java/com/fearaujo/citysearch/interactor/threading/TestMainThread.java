package com.fearaujo.citysearch.interactor.threading;

import com.fearaujo.arch.executor.MainThread;

public class TestMainThread implements MainThread {

    @Override
    public void post(Runnable runnable) {
        // tests can run on this thread, no need to invoke other threads
        runnable.run();
    }
}
