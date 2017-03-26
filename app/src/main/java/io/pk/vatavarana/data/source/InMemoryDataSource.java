package io.pk.vatavarana.data.source;

import java.util.ArrayList;
import java.util.List;

import io.pk.vatavarana.model.Forecast;

public class InMemoryDataSource implements DataSource {
    private static List<Forecast> mForecast = new ArrayList<>();
    public List<Forecast> getForecast() { return mForecast; }
}
