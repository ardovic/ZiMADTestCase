package com.ardovic.zimadtestcase.ui.list;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ardovic.zimadtestcase.App;
import com.ardovic.zimadtestcase.R;
import com.ardovic.zimadtestcase.data.Image;
import com.ardovic.zimadtestcase.ui.FragmentCommunicator;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ListFragment.Listener listener;
    private List<Image> images;

    ListAdapter(ListFragment.Listener listener, List<Image> images) {
        this.listener = listener;
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
        return images == null ? 0 : images.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.textView)
        TextView textView;

        private Image image;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
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
                            // do nothing
                        }

                        @Override
                        public void onError() {
                            //Try again online if cache failed
                            Picasso.with(App.getInstance().getApplicationContext())
                                    .load(image.getUrl())
                                    .into(imageView, new Callback() {
                                        @Override
                                        public void onSuccess() {
                                            // do nothing
                                        }

                                        @Override
                                        public void onError() {
                                            // do nothing
                                        }
                                    });
                        }
                    });
        }

        @Override
        public void onClick(View v) {
            listener.onImageClicked(image);
        }
    }
}
