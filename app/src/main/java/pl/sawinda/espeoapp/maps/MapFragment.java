package pl.sawinda.espeoapp.maps;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

import pl.sawinda.espeoapp.R;

public class MapFragment extends Fragment implements OnMapReadyCallback, MapContract.View {

    private MapView mapView;
    private GoogleMap googleMap;
    private TextView tvLength;
    private double lat;
    private double lng;
    private final int LOCATION_PERMISSION = 123;
    private MapPresenter mapPresenter;
    private LocationManager locationManager;



    public MapFragment() {
    }

    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        mapPresenter = new MapPresenter(this, lat, lng);
        tvLength = (TextView) view.findViewById(R.id.tvLengthToGo);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        return view;
    }

    public GoogleMap getGoogleMap() {
        return googleMap;
    }

    public LocationManager getLocationManager() {
        return locationManager;
    }

    @Override
    public void setDistance(String distance){
        tvLength.setText(distance);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mapView = (MapView) view.findViewById(R.id.location_map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);

        if (!isLocationEnabled())
            showAlert();
    }


    private void startListening(){
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int hasCallPermission = getActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);
            if (hasCallPermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                        LOCATION_PERMISSION);
                return;
            } else {
                mapPresenter.initiateListeners();
            }
        } else {
            mapPresenter.initiateListeners();
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case LOCATION_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mapPresenter.initiateListeners();
                } else {
                    Toast.makeText(getActivity(), "Location access Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        mapPresenter.setLocationPosition();
        startListening();
    }

    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }


    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(getResources().getString(R.string.dialog_location_title))
                .setMessage(getResources().getString(R.string.dialog_location_message))
                .setPositiveButton(getResources().getString(R.string.dialog_location_positive), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    }
                });
        dialog.show();
    }

    public void setPosition(double lat, double lng){
        this.lat = lat;
        this.lng = lng;
    }

}
