package com.ardovic.zimadtestcase.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ardovic.zimadtestcase.App;
import com.ardovic.zimadtestcase.R;
import com.ardovic.zimadtestcase.data.Image;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PreviewFragment extends Fragment {

    static final String TAG = PreviewFragment.class.getSimpleName();

    private static final String IMAGE_URL = "IMAGE_URL";
    private static final String IMAGE_TITLE = "IMAGE_TITLE";

    @BindView(R.id.previewIV)
    ImageView preview;
    @BindView(R.id.titleTV)
    TextView title;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preview, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            String imageUrl = getArguments().getString(IMAGE_URL);
            String titleText = getArguments().getString(IMAGE_TITLE);
            title.setText(titleText);
            Picasso.with(App.getInstance().getApplicationContext())
                    .load(imageUrl)
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .into(preview, new Callback() {
                        @Override
                        public void onSuccess() {
                            progressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {
                            Picasso.with(App.getInstance().getApplicationContext())
                                    .load(imageUrl)
                                    .into(preview, new Callback() {
                                        @Override
                                        public void onSuccess() {
                                            progressBar.setVisibility(View.GONE);
                                        }

                                        @Override
                                        public void onError() {
                                            progressBar.setVisibility(View.GONE);
                                            ((FragmentCommunicator) getActivity()).showNetworkError();
                                        }
                                    });
                        }
                    });
        }
    }

    static PreviewFragment newInstance(Image image) {
        PreviewFragment fragment = new PreviewFragment();
        Bundle args = new Bundle();
        args.putString(IMAGE_URL, image.getUrl());
        args.putString(IMAGE_TITLE, image.getTitle());
        fragment.setArguments(args);
        return fragment;
    }
}
