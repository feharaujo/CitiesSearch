package com.fearaujo.citysearch.main.items;

import com.fearaujo.arch.executor.Executor;
import com.fearaujo.arch.executor.MainThread;
import com.fearaujo.arch.presentation.BasePresenter;
import com.fearaujo.citysearch.interactor.RepositoryCreationInteractor;
import com.fearaujo.citysearch.interactor.RepositoryCreationInteractorImpl;
import com.fearaujo.citysearch.interactor.SearchInteractor;
import com.fearaujo.citysearch.interactor.SearchInteractorImpl;
import com.fearaujo.data.Repository;
import com.fearaujo.model.City;

import java.util.ArrayList;
import java.util.List;

public class ItemsPresenter extends BasePresenter<ItemsContract.View>
        implements ItemsContract.Presenter, RepositoryCreationInteractor.Callback, SearchInteractor.Callback {

    private final Executor mExecutor;
    private final MainThread mMainThread;
    private final Repository mRepository;

    private RepositoryCreationInteractor mCreationInteractor;

    public ItemsPresenter(Executor executor, MainThread mainThread, Repository repository) {
        this.mExecutor = executor;
        this.mMainThread = mainThread;
        this.mRepository = repository;
    }

    @Override
    public void onViewCreated() {
        super.onViewCreated();

        mView.setupRecyclerView();
        mView.attachFilterWatcher();

        mCreationInteractor = new RepositoryCreationInteractorImpl(
                mExecutor, mMainThread, this, mRepository
        );
        mCreationInteractor.execute();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mView.detachFilterWatcher();
    }


    @Override
    public void onRepositoryCreation(boolean success) {
        if (success) {
            executeSearch("");
        } else {
            mView.errorMessage();
            mView.hideLoading();
        }
    }

    @Override
    public void onNoResultFound() {
        mView.updateItems(new ArrayList<>());
        mView.showFilter();
        mView.showItems();
        mView.hideLoading();
    }

    @Override
    public void onResponse(List<City> cities) {
        mView.updateItems(cities);
        mView.showFilter();
        mView.showItems();
        mView.hideLoading();
    }

    @Override
    public void executeSearch(String prefix) {
        SearchInteractor interactor = new SearchInteractorImpl(
                mExecutor, mMainThread, this, mRepository, prefix
        );
        interactor.execute();
    }
}
