package com.fearaujo.citysearch.interactor;

import com.fearaujo.citysearch.BaseTest;
import com.fearaujo.model.City;

import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class SearchInteractorImplTest extends BaseTest {

    @Mock
    private SearchInteractor.Callback mCallback;

    @Test
    public void searchWithSuccess() {
        List<City> cities = new ArrayList<>();
        cities.add(new City.Builder().country("BR").name("Araras").fullname("Araras, BR").build());

        when(mRepository.searchCities(anyString())).thenReturn(cities);

        SearchInteractorImpl interactor = new SearchInteractorImpl(
                mExecutor, mMainThread, mCallback, mRepository, ""
        );
        interactor.run();

        verify(mRepository).searchCities(anyString());
        verifyNoMoreInteractions(mRepository);

        verify(mCallback).onResponse(cities);
    }

    @Test
    public void noResultSearch_emptyList() {
        List<City> emptyList = new ArrayList<>();

        when(mRepository.searchCities(anyString())).thenReturn(emptyList);

        SearchInteractorImpl interactor = new SearchInteractorImpl(
                mExecutor, mMainThread, mCallback, mRepository, ""
        );
        interactor.run();

        verify(mRepository).searchCities(anyString());
        verifyNoMoreInteractions(mRepository);

        verify(mCallback).onNoResultFound();
    }

    @Test
    public void noResultSearch_null() {
        when(mRepository.searchCities(anyString())).thenReturn(null);

        SearchInteractorImpl interactor = new SearchInteractorImpl(
                mExecutor, mMainThread, mCallback, mRepository, ""
        );
        interactor.run();

        verify(mRepository).searchCities(anyString());
        verifyNoMoreInteractions(mRepository);

        verify(mCallback).onNoResultFound();
    }
}