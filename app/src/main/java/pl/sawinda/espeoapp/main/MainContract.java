package pl.sawinda.espeoapp.main;

import android.support.v4.app.Fragment;

/**
 * Created by Fort Knox on 04-07-2016.
 */
public interface MainContract {

    public interface View {

    }

    public interface Actions {
        public void prepareMap();
        public void changeFragment(Fragment fragment);
    }
}
