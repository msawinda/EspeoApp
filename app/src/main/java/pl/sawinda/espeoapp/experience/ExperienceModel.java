package pl.sawinda.espeoapp.experience;

import android.graphics.drawable.Drawable;

public class ExperienceModel {
    Drawable image;
    String name;
    int[] screens;

    public ExperienceModel(Drawable image, String name, int[] screens) {
        this.image = image;
        this.name = name;
        this.screens = screens;
    }
}
