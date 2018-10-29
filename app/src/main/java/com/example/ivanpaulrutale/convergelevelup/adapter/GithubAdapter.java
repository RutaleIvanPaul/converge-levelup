package com.example.ivanpaulrutale.convergelevelup.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ivanpaulrutale.convergelevelup.ProfileDetailsActivity;
import com.example.ivanpaulrutale.convergelevelup.R;
import com.example.ivanpaulrutale.convergelevelup.model.DeveloperDataMapper;

import java.util.List;

public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.ViewHolder> {
    public GithubAdapter(List<DeveloperDataMapper> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    private List<DeveloperDataMapper> listItems;
    private Context context;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.developer_list_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final DeveloperDataMapper listItem = listItems.get(position);
        viewHolder.username.setText(listItem.getLogin());
        Glide.with(context).load(listItem.getAvatarUrl()).into(viewHolder.imageView);
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProfileDetailsActivity.class);
                intent.putExtra("profile_url", listItem.getUrl());
                intent.putExtra("image_url", listItem.getAvatarUrl());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView username;
        public RelativeLayout relativeLayout;
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.developer_username);
            relativeLayout = itemView.findViewById(R.id.list_item_layout);
            imageView = itemView.findViewById(R.id.profile_image);
        }
    }
}
