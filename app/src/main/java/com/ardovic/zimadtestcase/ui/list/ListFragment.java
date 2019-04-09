package com.ardovic.zimadtestcase.ui.list;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.ardovic.zimadtestcase.R;
import com.ardovic.zimadtestcase.data.Image;
import com.ardovic.zimadtestcase.ui.FragmentCommunicator;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListFragment extends Fragment implements ContractMVP.IView {

    public static final String CAT = "cat";
    public static final String DOG = "dog";

    private static final String TAB = "tab";
    private static final String POSITION = "position";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private Parcelable recyclerViewState;

    private ContractMVP.IPresenter presenter;

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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null)
            recyclerViewState = savedInstanceState.getParcelable(POSITION);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        if (getArguments() != null)
            presenter = new Presenter(this, getArguments().getString(TAB, null), (FragmentCommunicator) getActivity());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable(POSITION, recyclerView.getLayoutManager().onSaveInstanceState());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void populateList(List<Image> images) {
        ListAdapter adapter = new ListAdapter(image -> presenter.onImageClicked(image), images);
        recyclerView.setAdapter(adapter);
        recyclerView.getLayoutManager().onRestoreInstanceState(recyclerViewState);
        hideProgressBar();
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    interface Listener {
        void onImageClicked(Image image);
    }
}
