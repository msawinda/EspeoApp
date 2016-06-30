package pl.sawinda.espeoapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Appchance on 2016-06-30.
 */
public class Place {
    @SerializedName("latitude")
    private String lat;
    @SerializedName("longitude")
    private String lng;
    @SerializedName("name")
    private String name;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Place{" +
                "lat=" + lat +
                ", lng=" + lng +
                ", name='" + name + '\'' +
                '}';
    }
}
