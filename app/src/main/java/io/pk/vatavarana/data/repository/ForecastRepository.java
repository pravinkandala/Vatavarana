package io.pk.vatavarana.data.repository;

import java.util.List;

import io.pk.vatavarana.data.source.DataSource;
import io.pk.vatavarana.data.source.InMemoryDataSource;
import io.pk.vatavarana.model.Forecast;

public class ForecastRepository {

    private DataSource mDataSource;
    public ForecastRepository() { mDataSource = new InMemoryDataSource(); }


    public void add(Forecast forecast) {
        mDataSource.getForecast().add(forecast);
    }

    /**
     * store list of locations
     */
    public void addAll(List<Forecast> forecasts) {
        mDataSource.getForecast().clear();
        mDataSource.getForecast().addAll(forecasts);
    }
    /**
     * returns stored list
     */
    public List<Forecast> getAll() {
        return mDataSource.getForecast();
    }


}
