package com.fearaujo.citysearch;

import android.app.Application;

import com.fearaujo.data.Repository;
import com.fearaujo.data.RepositoryImpl;

public class AppApplication extends Application {

    private Repository repository;

    @Override
    public void onCreate() {
        super.onCreate();

        repository = new RepositoryImpl();
    }

    public Repository getRepository() {
        return repository;
    }
}
