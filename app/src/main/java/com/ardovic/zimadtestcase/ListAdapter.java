package com.ardovic.zimadtestcase;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ardovic.zimadtestcase.api.Image;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private FragmentCommunicator communicator;
    private List<Image> images;

    ListAdapter(FragmentCommunicator communicator, List<Image> images) {
        this.communicator = communicator;
        this.images = images;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.bindView(images.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.textView)
        TextView textView;

        private Image image;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
            itemView.setOnClickListener(this);
        }

        void bindView(final Image image) {
            this.image = image;
            textView.setText(image.getTitle());
            Picasso.with(App.getInstance().getApplicationContext())
                    .load(image.getUrl())
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {
                            //Try again online if cache failed
                            Picasso.with(App.getInstance().getApplicationContext())
                                    .load(image.getUrl())
                                    //.error(R.drawable.header)
                                    .into(imageView, new Callback() {
                                        @Override
                                        public void onSuccess() {

                                        }

                                        @Override
                                        public void onError() {

                                        }
                                    });
                        }
                    });
        }

        @Override
        public void onClick(View v) {
            communicator.showFullImage(image.getUrl());
        }
    }
}
