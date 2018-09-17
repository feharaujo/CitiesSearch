package com.fearaujo.citysearch.interactor;

import com.fearaujo.arch.interactors.base.Interactor;

public interface RepositoryCreationInteractor extends Interactor {

    interface Callback {

        void onRepositoryCreation(boolean success);

    }

}
