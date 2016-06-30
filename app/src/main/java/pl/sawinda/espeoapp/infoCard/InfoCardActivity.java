package pl.sawinda.espeoapp.infoCard;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pl.sawinda.espeoapp.R;
import pl.sawinda.espeoapp.models.Place;
import pl.sawinda.espeoapp.retrofitCalls.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoCardActivity extends AppCompatActivity {

    Gson gson;
    Retrofit retrofit;
//    OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_card);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        gson = new GsonBuilder().create();
//        client = new OkHttpClient();
        final OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(logging);
        retrofit = new Retrofit.Builder()
                .client(client.build())
                .baseUrl("http://pastebin.com/raw/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataService service = retrofit.create(DataService.class);

                Call<List<String>> call = service.getLocation();
                call.enqueue(new Callback<List<String>>() {
                    @Override
                    public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                        Log.e("TUTAJ DANE", response.toString());
                    }

                    @Override
                    public void onFailure(Call<List<String>> call, Throwable t) {
                        Log.e("TUTAJ ERROR", t.toString());
                    }
                });

            }
        });
    }

}
