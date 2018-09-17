package com.fearaujo.citysearch;

import com.fearaujo.arch.executor.Executor;
import com.fearaujo.arch.executor.MainThread;
import com.fearaujo.citysearch.interactor.threading.TestMainThread;
import com.fearaujo.data.Repository;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public abstract class BaseTest {

    @Mock
    protected Executor mExecutor;
    @Mock
    protected Repository mRepository;

    protected MainThread mMainThread;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mMainThread = new TestMainThread();
    }
}
