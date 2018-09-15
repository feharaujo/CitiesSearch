package com.fearaujo.data;

import com.fearaujo.data.util.ResourcesUtils;
import com.fearaujo.model.City;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.when;

@PrepareForTest(ResourcesUtils.class)
@RunWith(PowerMockRunner.class)
public class RepositoryImplTest {

    private Repository repository;

    @Before
    public void setUp() {
        repository = new RepositoryImpl();
    }

    @Test
    public void shouldFailLoadRepository_returnFalse() throws Exception {
        PowerMockito.mockStatic(ResourcesUtils.class);

        when(ResourcesUtils.loadDataFromResourcesFolder(ArgumentMatchers.any())).thenThrow(new IOException());
        Boolean result = repository.initRepository();
        assertFalse(result);
    }

    @Test
    public void shouldLoadRepositoryWithSuccess_searchCitiesByPrefix_findSevenResults() {
        Boolean result = repository.initRepository();
        assertTrue(result);

        List<City> cities = repository.searchCities("Arara");
        assertEquals(7, cities.size());
    }

}