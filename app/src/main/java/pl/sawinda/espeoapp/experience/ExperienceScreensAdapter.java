package pl.sawinda.espeoapp.experience;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import pl.sawinda.espeoapp.R;

public class ExperienceScreensAdapter extends
        RecyclerView.Adapter<ExperienceScreensAdapter.ViewHolder> {

    private Context context;
    private int[] dataSet;

    public ExperienceScreensAdapter(Context context, int[] dataSet){
        this.context = context;
        this.dataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.screenshot_row, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(dataSet[position]).into(holder.ivScreenShot);
//        holder.ivScreenShot.setImageDrawable(dataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSet.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivScreenShot;

        public ViewHolder(View itemView) {
            super(itemView);

            ivScreenShot = (ImageView) itemView.findViewById(R.id.ivScreenShot);
        }
    }
}