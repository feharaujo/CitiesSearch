package com.fearaujo.citysearch.main.items;

import com.fearaujo.arch.presentation.BaseContract;
import com.fearaujo.model.City;

import java.util.List;

public interface ItemsContract extends BaseContract {

    interface View extends BaseContract.View {

        void setupRecyclerView();

        void updateItems(List<City> cities);

        void hideLoading();

        void showItems();

        void showFilter();

        void attachFilterWatcher();

        void detachFilterWatcher();

        void errorMessage();
    }

    interface Presenter extends BaseContract.Presenter<ItemsContract.View> {
        void executeSearch(String prefix);
    }

}
