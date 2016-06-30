package pl.sawinda.espeoapp.retrofitCalls;

import java.util.List;

import pl.sawinda.espeoapp.models.Place;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Appchance on 2016-06-30.
 */
public interface DataService {
    @GET("dgPELDPR")
    public Call<List<String>> getLocation();
}
