package com.fearaujo.citysearch.search;

import com.fearaujo.arch.executor.Executor;
import com.fearaujo.arch.executor.MainThread;
import com.fearaujo.citysearch.search.threading.TestMainThread;
import com.fearaujo.data.Repository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class RepositoryCreationInteractorImplTest {

    @Mock
    private Executor mExecutor;
    @Mock
    private Repository mRepository;
    @Mock
    private RepositoryCreationInteractor.Callback mCallback;

    private MainThread mMainThread;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mMainThread = new TestMainThread();
    }

    @Test
    public void repositoryInit_success() {
        repositoryInitExec(true);
    }

    @Test
    public void repositoryInit_failed() {
        repositoryInitExec(false);
    }

    private void repositoryInitExec(boolean mockResult) {
        RepositoryCreationInteractorImpl interactor = new RepositoryCreationInteractorImpl(
                mExecutor, mMainThread, mCallback, mRepository
        );

        when(mRepository.initRepository()).thenReturn(mockResult);

        interactor.run();

        verify(mRepository).initRepository();
        verifyNoMoreInteractions(mRepository);
        verify(mCallback).onRepositoryCreation(mockResult);
    }


}