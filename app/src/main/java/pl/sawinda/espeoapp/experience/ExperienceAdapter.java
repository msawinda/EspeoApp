package pl.sawinda.espeoapp.experience;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import pl.sawinda.espeoapp.R;

public class ExperienceAdapter extends
        RecyclerView.Adapter<ExperienceAdapter.ViewHolder> {

    private List<ExperienceModel> dataSet;
    private Context context;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    public ExperienceAdapter(List<ExperienceModel> dataSet, Context context){
        this.dataSet = dataSet;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.experience_row, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.ivScreen.setImageDrawable(dataSet.get(position).image);
        holder.tvName.setText(dataSet.get(position).name);

        holder.tvScrenshots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.rv.getVisibility() == View.VISIBLE) {
                    holder.rv.setVisibility(View.GONE);
                    holder.tvScrenshots.setText(R.string.screenshots);
                }
                else {
                    holder.rv.setVisibility(View.VISIBLE);
                    holder.tvScrenshots.setText(R.string.screenshots_hide);
                }
            }
        });


        mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        holder.rv.setLayoutManager(mLayoutManager);
        mAdapter = new ExperienceScreensAdapter(context, dataSet.get(position).screens);
        holder.rv.setAdapter(mAdapter);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public Button tvScrenshots;
        public ImageView ivScreen;
        public RecyclerView rv;

        public ViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvScrenshots = (Button) itemView.findViewById(R.id.tvScreenshots);
            ivScreen = (ImageView) itemView.findViewById(R.id.ivScreen);
            rv = (RecyclerView) itemView.findViewById(R.id.rvExperienceScreens);


        }
    }
}