package com.fearaujo.citysearch.search;

import com.fearaujo.arch.interactors.base.Interactor;
import com.fearaujo.model.City;

import java.util.List;

public interface SearchInteractor extends Interactor {

    interface Callback {

        void onNoResultFound();

        void onResponse(List<City> cities);

    }

}
