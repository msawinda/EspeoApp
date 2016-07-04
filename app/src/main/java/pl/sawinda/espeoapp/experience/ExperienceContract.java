package pl.sawinda.espeoapp.experience;

import java.util.List;

public interface ExperienceContract {

    public interface View {
        public void startRecyclerView(List<ExperienceModel> experienceModels);
    }

    public interface Actions {
        public void createTheList();
    }
}
