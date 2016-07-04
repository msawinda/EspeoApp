package pl.sawinda.espeoapp.experience;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pl.sawinda.espeoapp.R;

public class ExperienceFragment extends Fragment implements ExperienceContract.View{
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView rv;
    private ExperiencePresenter experiencePresenter;

    public ExperienceFragment() {
        // Required empty public constructor
    }

    public static ExperienceFragment newInstance() {
        ExperienceFragment fragment = new ExperienceFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        experiencePresenter = new ExperiencePresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_experience, container, false);
        rv = (RecyclerView) view.findViewById(R.id.rvExperience);
        experiencePresenter.createTheList();
        return view;
    }

    @Override
    public void startRecyclerView(List<ExperienceModel> experienceModels) {

        mLayoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(mLayoutManager);
        mAdapter = new ExperienceAdapter(experienceModels, getActivity().getApplicationContext());
        rv.setAdapter(mAdapter);

    }





}
