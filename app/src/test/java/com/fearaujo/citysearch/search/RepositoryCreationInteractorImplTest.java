package com.fearaujo.citysearch.search;

import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class RepositoryCreationInteractorImplTest extends BaseInteractorTest {

    @Mock
    private RepositoryCreationInteractor.Callback mCallback;

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