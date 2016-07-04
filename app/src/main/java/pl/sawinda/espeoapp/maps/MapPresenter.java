package pl.sawinda.espeoapp.maps;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapPresenter implements MapContract.Actions {
    private MapFragment mapView;
    private double lat;
    private double lng;
    private double longitudeGPS;
    private double latitudeGPS;
    private boolean firstLoaded = false;

    private Marker userMarker, locationMarker;
    private MarkerOptions userMarkerOptions, locationMarkerOptions;


    public MapPresenter(MapFragment mapView, double lat, double lng) {
        this.mapView = mapView;
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public void setUserPosition(){
        if(userMarker != null)
            userMarker.remove();
        LatLng position = new LatLng(latitudeGPS, longitudeGPS);
        userMarkerOptions = new MarkerOptions()
                .position(position);
        userMarker = mapView.getGoogleMap().addMarker(userMarkerOptions);
        calculateDistance();
    }

    @Override
    public void setLocationPosition(){
        LatLng position = new LatLng(lat, lng);
        GoogleMap googleMap = mapView.getGoogleMap();
        locationMarkerOptions = new MarkerOptions().position(position).title("ESPEO");
        locationMarker = googleMap.addMarker(locationMarkerOptions);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position, googleMap.getMaxZoomLevel()));
    }

    private final LocationListener locationListenerNetwork = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitudeGPS = location.getLongitude();
            latitudeGPS = location.getLatitude();

            mapView.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setUserPosition();
                    if(!firstLoaded)
                        zoomAllMarkers();
                }
            });
        }
        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }
        @Override
        public void onProviderEnabled(String s) {

        }
        @Override
        public void onProviderDisabled(String s) {

        }
    };

    private final LocationListener locationListenerGPS = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitudeGPS = location.getLongitude();
            latitudeGPS = location.getLatitude();
            mapView.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setUserPosition();
                    if(!firstLoaded)
                        zoomAllMarkers();
                }
            });
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
            Log.e("2", latitudeGPS +" "+longitudeGPS);
        }

        @Override
        public void onProviderEnabled(String s) {
            Log.e("2", latitudeGPS +" "+longitudeGPS);
        }

        @Override
        public void onProviderDisabled(String s) {
            Log.e("3", latitudeGPS +" "+longitudeGPS);
        }
    };

    @Override
    public void calculateDistance(){
        float[] results = new float[1];
        Location.distanceBetween(lat, lng,
                latitudeGPS, longitudeGPS,
                results);

        String distance = Float.toString(results[0]/1000).substring(0, 6) + "km";
        mapView.setDistance(distance);
    };

    @Override
    public void initiateListeners(){
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        LocationManager locationManager = mapView.getLocationManager();
        String provider = locationManager.getBestProvider(criteria, true);
        locationManager.requestLocationUpdates(provider, 1 * 10, 10, locationListenerGPS);
        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 10, 10, locationListenerNetwork);
    }

    @Override
    public void zoomAllMarkers(){
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(locationMarker.getPosition());
        builder.include(userMarker.getPosition());

        LatLngBounds bounds = builder.build();

        int padding = 80;
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        mapView.getGoogleMap().animateCamera(cu);
    }
}
