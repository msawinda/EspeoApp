package pl.sawinda.espeoapp.experience;

import java.util.ArrayList;
import java.util.List;

import pl.sawinda.espeoapp.R;

public class ExperiencePresenter implements ExperienceContract.Actions {
    private ExperienceFragment experienceView;

    public ExperiencePresenter(ExperienceFragment experienceView) {
        this.experienceView = experienceView;
    }

    @Override
    public void createTheList(){
        List<ExperienceModel> experienceSet = new ArrayList<>();
        int[] dotsensScreens = {R.drawable.dotsens_screen_1, R.drawable.dotsens_screen_2, R.drawable.dotsens_screen_3};
        int[] confrenzScreens = {R.drawable.confrenz_screen_1, R.drawable.confrenz_screen_2, R.drawable.confrenz_screen_3, R.drawable.confrenz_screen_4, R.drawable.confrenz_screen_5};
        experienceSet.add(new ExperienceModel(experienceView.getResources().getDrawable(R.drawable.logo_dotsens), experienceView.getResources().getString(R.string.dotsens_desc), dotsensScreens));
        experienceSet.add(new ExperienceModel(experienceView.getResources().getDrawable(R.drawable.logo_confrenz), experienceView.getResources().getString(R.string.confrenz_desc), confrenzScreens));
        experienceView.startRecyclerView(experienceSet);
    }
}
