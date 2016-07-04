package pl.sawinda.espeoapp.retrofitCalls;

import pl.sawinda.espeoapp.models.Place;
import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {
    @GET("WGTw1fqa")
    public Call<Place> getLocation();
}
