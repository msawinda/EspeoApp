package pl.sawinda.espeoapp.models;

import com.google.gson.annotations.SerializedName;

public class Place {
    @SerializedName("latitude")
    private double lat;
    @SerializedName("longitude")
    private double lng;
    @SerializedName("name")
    private String name;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
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
