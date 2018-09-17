package com.fearaujo.arch.presentation;

public abstract class BasePresenter<V extends BaseContract.View> implements BaseContract.Presenter<V> {

    protected V mView;

    @Override
    public void attachView(V view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }

    @Override
    public boolean isViewAttached() {
        return mView != null;
    }

    @Override
    public void onViewCreated() {

    }

    @Override
    public void onDestroyView() {

    }
}
