package com.fearaujo.arch.presentation;

public interface BaseContract {

    interface View {
    }

    interface Presenter<V extends BaseContract.View> {

        void attachView(V view);

        void detachView();

        boolean isViewAttached();

        void onViewCreated();

        void onDestroyView();

    }

}
