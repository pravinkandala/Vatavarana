package io.pk.vatavarana.network;


import java.util.List;

import io.pk.vatavarana.model.Forecast;

public interface ServerForecastCallback {

        void onSuccess(List<Forecast> forecasts);

}
