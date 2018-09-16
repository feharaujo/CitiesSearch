package com.fearaujo.citysearch.search;

import com.fearaujo.arch.interactors.base.Interactor;

public interface RepositoryCreationInteractor extends Interactor {

    interface Callback {

        void onRepositoryCreation(boolean success);

    }

}
