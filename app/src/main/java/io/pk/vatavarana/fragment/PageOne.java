package io.pk.vatavarana.fragment;


import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import io.pk.vatavarana.R;
import io.pk.vatavarana.adapter.ForecastAdapter;
import io.pk.vatavarana.model.Forecast;
import io.pk.vatavarana.network.ServerForecastCallback;
import io.pk.vatavarana.network.WeatherSearchManager;
import io.pk.vatavarana.service.LocationService;
import io.pk.vatavarana.service.WeatherDisplayService;
import io.pk.vatavarana.util.SnackbarUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class PageOne extends Fragment {


    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    Context mContext;
    LocationService mLocationService;
    Location mLocation;

    public PageOne() {
        // Required empty public constructor
    }

    public static View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_page_one, container, false);

        mContext = view.getContext();

        mLocationService = new LocationService(mContext);

        mLocation = mLocationService.getLocation();



        mRecyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        initList();

        return view;
    }


    /**
     * Load json from url
     */
    public void initList() {

        if(mLocation!=null) {

            WeatherSearchManager.getForecastResults(mContext, mLocation.getLatitude() + "," + mLocation.getLongitude(), new ServerForecastCallback() {
                @Override
                public void onSuccess(final List<Forecast> forecasts) {

                    mAdapter = new ForecastAdapter(mContext, forecasts);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
                    mRecyclerView.setAdapter(mAdapter);

                    WeatherDisplayService weather = new  WeatherDisplayService();
                    weather.displayWeatherInfo(mContext,new LatLng(mLocation.getLatitude(),mLocation.getLongitude()));



                }
            });

        }else{
            SnackbarUtils.make(view, "Error!", Snackbar.LENGTH_SHORT);
        }
    }



}
