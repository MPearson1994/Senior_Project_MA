package com.example.myles.ui_senior_project;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Myles.lab on 9/27/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private int[] clothing = {
            R.drawable.closet,
            R.drawable.weather,
            R.drawable.calendar,
            R.drawable.camera,
            R.drawable.favorites,
            R.drawable.settings
    };

    private String[] titles = {
            "Closet",
            "Weather",
            "Calendar",
            "Camera",
            "Favorites",
            "Settings"};

    private String[] details = {
            "Explore your Closet",
            "Check the weather",
            "Reserve outfits in your calendar",
            "Add clothing via your camera",
            "View and edit your favorites",
            "Customize your settings"};

    class ViewHolder extends RecyclerView.ViewHolder{

        public int currentItem;
        public ImageView itemImage;
        public TextView itemTitle;
        public TextView itemDetail;

        public ViewHolder(final View itemView) {
            super(itemView);
            itemImage = (ImageView)itemView.findViewById(R.id.item_image);
            itemTitle = (TextView)itemView.findViewById(R.id.item_title);
            itemDetail = (TextView)itemView.findViewById(R.id.item_detail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = getAdapterPosition();

                    Toast.makeText(itemView.getContext(),"Click detected on item " + position,
                            Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment_home, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.itemTitle.setText(titles[i]);
        viewHolder.itemDetail.setText(details[i]);
        viewHolder.itemImage.setImageResource(clothing[i]);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}
