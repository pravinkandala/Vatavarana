package io.pk.vatavarana.service;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.model.LatLng;

import io.pk.vatavarana.R;
import io.pk.vatavarana.model.CurrentObservation;
import io.pk.vatavarana.network.ServerCallback;
import io.pk.vatavarana.network.WeatherSearchManager;
import io.pk.vatavarana.util.SnackbarUtils;

import static io.pk.vatavarana.fragment.PageTwo.view;

public class WeatherDisplayService {

    public void displayWeatherInfo(final Context context, LatLng latLng) {

        view = ((Activity) context).findViewById(android.R.id.content);

        String url = latLng.latitude + "," + latLng.longitude;
        WeatherSearchManager.getLocalSearchResults(context, url, new ServerCallback() {
            @Override
            public void onSuccess(final CurrentObservation observation) {

                if (observation.getWeather()==null) {
                    SnackbarUtils.make(view, "No weather station here", Snackbar.LENGTH_SHORT);
                } else {
                    displayContent(view, observation);
                }
            }
        });
    }

    public void displayWeatherInfoDialog(final Context context, LatLng latLng) {

        LayoutInflater factory = LayoutInflater.from(context);
        view = factory.inflate(R.layout.custom_weather_observation_display, null);

        final Dialog dialog = new Dialog(context);


        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);


        String url = latLng.latitude + "," + latLng.longitude;
        WeatherSearchManager.getLocalSearchResults(context, url, new ServerCallback() {
            @Override
            public void onSuccess(final CurrentObservation observation) {

                if (observation.getWeather()==null) {
                    view = ((Activity) context).findViewById(android.R.id.content);
                    SnackbarUtils.make(view, "No weather station here!", Snackbar.LENGTH_SHORT);
                } else {
                    displayContent(view, observation);
                    dialog.show();

                }
            }
        });


    }




    public void displayContent(View view, CurrentObservation observation){
        TextView itemDate = (TextView) view.findViewById(R.id.widgetTime);
        itemDate.setText(observation.getLocal_time());

        TextView itemWind = (TextView) view.findViewById(R.id.widgetWind);
        itemWind.setText("Wind: "+observation.getWind());


        TextView weather = (TextView) view.findViewById(R.id.widgetDescription);
        weather.setText(observation.getWeather());

        TextView humidity = (TextView) view.findViewById(R.id.widgetHumidity);
        humidity.setText("Humidity: "+observation.getHumidity());

        TextView location = (TextView) view.findViewById(R.id.widgetLocation);
        location.setText(observation.getLocation_name());


        TextView temperature = (TextView) view.findViewById(R.id.widgetTemperature);
        temperature.setText(observation.getTemperature_string());

        TextView link = (TextView) view.findViewById(R.id.widgetLink);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            link.setText(Html.fromHtml("<a href="+ observation.getForecast_link() + ">Forecast</a>", Html.FROM_HTML_MODE_LEGACY));
        }else{
            link.setText(Html.fromHtml("<a href="+ observation.getForecast_link() + ">Forecast</a>"));
        }
        link.setMovementMethod(LinkMovementMethod.getInstance());


        ImageView icon = (ImageView) view.findViewById(R.id.widgetIcon);

        Glide.with(view.getContext()).load(observation.getIcon_url()).override(100,100).fitCenter().into(icon);



    }
}
