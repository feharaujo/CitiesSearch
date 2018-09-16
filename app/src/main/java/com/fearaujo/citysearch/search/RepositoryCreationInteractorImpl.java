package com.fearaujo.citysearch.search;

import com.fearaujo.arch.executor.Executor;
import com.fearaujo.arch.executor.MainThread;
import com.fearaujo.arch.interactors.base.AbstractInteractor;
import com.fearaujo.data.Repository;

public class RepositoryCreationInteractorImpl extends AbstractInteractor implements RepositoryCreationInteractor {

    private final RepositoryCreationInteractor.Callback callback;
    private final Repository repository;

    public RepositoryCreationInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                            RepositoryCreationInteractor.Callback callback,
                                            Repository repository) {
        super(threadExecutor, mainThread);

        this.callback = callback;
        this.repository = repository;
    }

    @Override
    public void run() {
        final boolean success = repository.initRepository();
        mMainThread.post(() -> callback.onRepositoryCreation(success));
    }
}
