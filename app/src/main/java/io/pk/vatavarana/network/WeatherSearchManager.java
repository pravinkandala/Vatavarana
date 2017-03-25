package io.pk.vatavarana.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import io.pk.vatavarana.R;
import io.pk.vatavarana.model.CurrentObservation;


public class WeatherSearchManager {

    public static void getLocalSearchResults(final Context context, String location, final ServerCallback callback) {

        String finalUrl = context.getString(R.string.BASE_URL) + context.getString(R.string.API_ID) + context.getString(R.string.FORECAST) + location + ".json";

        Log.d("API_INFO", finalUrl);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                finalUrl, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(final JSONObject response) {
                CurrentObservation observation = new CurrentObservation();


                try {

                    JSONObject observationJsonObject = response.getJSONObject("current_observation");
                    observation.setTemperature_string(observationJsonObject.getString("temperature_string"));
                    observation.setWeather(observationJsonObject.getString("weather"));
                    observation.setIcon_url(observationJsonObject.getString("icon_url"));
                    observation.setHumidity(observationJsonObject.getString("relative_humidity"));
                    observation.setForecast_link(observationJsonObject.getString("forecast_url"));
                    observation.setWind(observationJsonObject.getString("wind_mph") + " mph " + observationJsonObject.getString("wind_dir"));
                    observation.setLocal_time(observationJsonObject.getString("observation_time"));

                    JSONObject display_location = observationJsonObject.getJSONObject("display_location");
                    observation.setLocation_name(display_location.getString("full"));


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                callback.onSuccess(observation);
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Volley", "Error: " + error.getMessage());

            }
        });


        // Adding request to request queue
        VolleyRequestQueue.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }
}
