package io.pk.vatavarana.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Forecast implements Serializable{

    private String icon_url;
    private String title;
    private String fcttext;
    private String fcttext_metric;

    public String getFcttext() {
        return fcttext;
    }

    public void setFcttext(String fcttext) {
        this.fcttext = fcttext;
    }

    public String getFcttext_metric() {
        return fcttext_metric;
    }

    public void setFcttext_metric(String fcttext_metric) {
        this.fcttext_metric = fcttext_metric;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static Forecast fromJsonObject(JSONObject object) throws JSONException {

        Forecast forecast = new Forecast();
        forecast.setIcon_url(object.getString("icon_url"));
        forecast.setTitle(object.getString("title"));
        forecast.setFcttext(object.getString("fcttext"));
        forecast.setFcttext_metric(object.getString("fcttext_metric"));

        return forecast;
    }
}
