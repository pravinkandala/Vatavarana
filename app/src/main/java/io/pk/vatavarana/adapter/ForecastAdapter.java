package io.pk.vatavarana.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.pk.vatavarana.R;
import io.pk.vatavarana.model.Forecast;


public class ForecastAdapter extends RecyclerView.Adapter<io.pk.vatavarana.adapter.ForecastAdapter.ViewHolder> {

    private List<Forecast> mForecast;
    private Context mContext;

    public ForecastAdapter(Context context, List<Forecast> forecasts) {
        mContext = context;
        mForecast = forecasts;
    }


    public Context getContext() {
        return mContext;
    }


    //inflating a layout(xml) and returning the holder
    @Override
    public io.pk.vatavarana.adapter.ForecastAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View locationView = inflater.inflate(R.layout.item_forecast, parent, false);

        //return a new holder instance
        ViewHolder viewHolder = new ViewHolder(locationView, mForecast);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(io.pk.vatavarana.adapter.ForecastAdapter.ViewHolder holder, int position) {

        Forecast forecast = mForecast.get(position);

        TextView details = holder.mDetail;
        details.setText(forecast.getFcttext());

        TextView title = holder.mTitle;
        title.setText(forecast.getTitle());

        ImageView icon = holder.mIcon;
        Glide.with(mContext).load(forecast.getIcon_url()).override(100, 100).fitCenter().into(icon);


    }

    @Override
    public int getItemCount() {
        return mForecast.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTitle;
        public TextView mDetail;
        public ImageView mIcon;
        List<Forecast> mForecast;
        private Context mContext;

        public ViewHolder(View view, List<Forecast> forecasts) {
            super(view);
            this.mContext = view.getContext();

            mForecast = forecasts;
            mTitle = (TextView) view.findViewById(R.id.day_title);
            mDetail = (TextView) view.findViewById(R.id.details);
            mIcon = (ImageView) view.findViewById(R.id.forecast_icon);

        }

        //@TODO: implement onclick for more details in future.

//        @Override
//        public void onClick(View view) {
//            int position = getAdapterPosition();
//            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
//
//            }
//        }
    }

}
