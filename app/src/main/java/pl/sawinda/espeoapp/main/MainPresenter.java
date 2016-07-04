package pl.sawinda.espeoapp.main;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import pl.sawinda.espeoapp.R;
import pl.sawinda.espeoapp.maps.MapFragment;
import pl.sawinda.espeoapp.models.Place;
import pl.sawinda.espeoapp.retrofitCalls.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainPresenter implements MainContract.Actions {
    private MainActivity mainActivityView;
    private Gson gson;
    private Retrofit retrofit;

    public MainPresenter(MainActivity mainActivityView) {

        this.mainActivityView = mainActivityView;

        gson = new GsonBuilder().create();
        final OkHttpClient.Builder client = new OkHttpClient.Builder();
        retrofit = new Retrofit.Builder()
                .client(client.build())
                .baseUrl("http://pastebin.com/raw/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }


    @Override
    public void prepareMap(){

        final MapFragment mapFragment = new MapFragment();

        DataService service = retrofit.create(DataService.class);

        Call<Place> call = service.getLocation();
        call.enqueue(new Callback<Place>() {
            @Override
            public void onResponse(Call<Place> call, Response<Place> response) {
                Place place = response.body();
                mapFragment.setPosition(place.getLat(), place.getLng());
                changeFragment(mapFragment);

            }

            @Override
            public void onFailure(Call<Place> call, Throwable t) {
                Log.e("MAP ERROR", t.toString());
            }
        });


    }


    @Override
    public void changeFragment(Fragment fragment){
        android.support.v4.app.FragmentTransaction fragmentTransaction = mainActivityView.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }
}
