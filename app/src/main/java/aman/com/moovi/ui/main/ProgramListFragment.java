package aman.com.moovi.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import aman.com.moovi.R;
import aman.com.moovi.Utils;
import aman.com.moovi.model.Program;
import aman.com.moovi.ui.RecyclerViewClickListener;
import aman.com.moovi.ui.adapter.ProgramListAdapter;
import aman.com.moovi.ui.detail.DetailActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by amanpreetsingh on 12/21/17.
 */

public class ProgramListFragment extends Fragment implements ProgramListView, RecyclerViewClickListener {

    ProgramListPresenter mProgramListPresenter = new ProgramListPresenter();
    String mProgramType;

    @BindView(R.id.program_list) RecyclerView mProgramList;
    @BindView(R.id.progress_bar) ProgressBar mProgressBar;

    public ProgramListFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgramType = getArguments().getString(Utils.PROGRAM_TYPE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_movie, container, false);
        ButterKnife.bind(this, fragmentView);

        mProgramListPresenter.attachView(this);
        mProgramListPresenter.loadPrograms(mProgramType);
        return fragmentView;
    }

    public static ProgramListFragment newInstance(String programType) {

        Bundle args = new Bundle();
        args.putString(Utils.PROGRAM_TYPE, programType);
        ProgramListFragment fragment = new ProgramListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public <T extends Program> void showProgramList(ArrayList<T> programs) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mProgramList.setLayoutManager(layoutManager);
        mProgramList.setAdapter(new ProgramListAdapter(getActivity(), programs, this));
        mProgressBar.setVisibility(View.GONE);
        mProgramList.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError() {

    }

    @Override
    public void onItemClicked(Program program) {
        Intent intent = DetailActivity.getStartIntent(getActivity());
        intent.putExtra(Utils.PROGRAM_EXTRA, program);
        startActivity(intent);
    }
}
