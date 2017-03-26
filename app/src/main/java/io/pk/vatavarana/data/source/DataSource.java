package io.pk.vatavarana.data.source;

import java.util.List;

import io.pk.vatavarana.model.Forecast;

public interface DataSource {
    List<Forecast> getForecast();
}
