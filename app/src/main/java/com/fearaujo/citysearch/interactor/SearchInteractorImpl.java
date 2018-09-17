package com.fearaujo.citysearch.interactor;

import com.fearaujo.arch.executor.Executor;
import com.fearaujo.arch.executor.MainThread;
import com.fearaujo.arch.interactors.base.AbstractInteractor;
import com.fearaujo.data.Repository;
import com.fearaujo.model.City;

import java.util.List;

public class SearchInteractorImpl extends AbstractInteractor implements SearchInteractor {

    private final Callback callback;
    private final Repository repository;
    private final String prefix;

    public SearchInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                Callback callback,
                                Repository repository,
                                String prefix) {
        super(threadExecutor, mainThread);

        this.callback = callback;
        this.repository = repository;
        this.prefix = prefix;
    }

    @Override
    public void run() {
        final List<City> cities = repository.searchCities(prefix);

        mMainThread.post(() -> {
            if (cities == null || cities.size() == 0) {
                callback.onNoResultFound();
            } else {
                callback.onResponse(cities);
            }
        });
    }
}
