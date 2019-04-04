package com.ardovic.zimadtestcase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ardovic.zimadtestcase.api.Image;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFragment extends Fragment implements Contract.IView {

    public static final String CAT = "cat";
    public static final String DOG = "dog";

    public static final String TAB = "tab";
    public static final String POSITION = "position";

    public String TAG;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private int position;

    private ListAdapter adapter;
    private Contract.IPresenter presenter;

    public static ListFragment newInstance(@NonNull String tab) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString(TAB, tab);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            TAG = getArguments().getString(TAB, null);
            presenter = new Presenter(this, TAG);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null)
            position = savedInstanceState.getInt(POSITION);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION, position);
    }

    @Override
    public void populateList(List<Image> images) {
        adapter = new ListAdapter((FragmentCommunicator) getActivity(), images);
        recyclerView.setAdapter(adapter);
        recyclerView.getLayoutManager().scrollToPosition(position);
    }
}
