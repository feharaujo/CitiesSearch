package com.fearaujo.citysearch.search;

import com.fearaujo.arch.executor.Executor;
import com.fearaujo.arch.executor.MainThread;
import com.fearaujo.citysearch.search.threading.TestMainThread;
import com.fearaujo.data.Repository;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public abstract class BaseInteractorTest {

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
