package com.example.android.omni;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by vamsi on 21-11-2016.
 */

public class storeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView storeName;
    private TextView storeDistance;
    private TextView storeAddress;
    private ImageView storeWallpaper;
    private TextView storeOpenStatusTime;
    private TextView storeNoOfBookmarks;
    private ImageView men;
    private ImageView women;
    private ImageView kids;

    private StoreModel stores;
    private Context context;
//    private int position;


    public storeViewHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        this.storeWallpaper = (ImageView) itemView.findViewById(R.id.store_wallpaper);
        this.storeName = (TextView) itemView.findViewById(R.id.store_name);
        this.storeDistance = (TextView) itemView.findViewById(R.id.store_distance);
        this.storeAddress = (TextView) itemView.findViewById(R.id.store_address);
        this.storeNoOfBookmarks = (TextView) itemView.findViewById(R.id.no_of_bookmarks);
        this.storeOpenStatusTime = (TextView) itemView.findViewById(R.id.store_open_time);
        this.men = (ImageView) itemView.findViewById(R.id.icon_man);
        this.women = (ImageView) itemView.findViewById(R.id.icon_woman);
        this.kids = (ImageView) itemView.findViewById(R.id.icon_kid);

        itemView.setOnClickListener(this);

    }

    public void bindStoreData(StoreModel stores){

        this.stores = stores;

        this.storeName.setText(stores.getStoreName());
        this.storeAddress.setText(stores.getStoreAddress());
        this.storeOpenStatusTime.setText(stores.getStoreOpenStatusTime());

        String formattedDistance = formatdouble(stores.getStoreDistance()) + " km";
        this.storeDistance.setText(formattedDistance);

        String bookmarksNumber = Integer.toString(stores.getNoOfBookmarks());
        this.storeNoOfBookmarks.setText(bookmarksNumber + "Marks");

        this.storeWallpaper.setImageResource(stores.getStoreWallpaperId());


        if(!(stores.isThereMen())){
            this.men.setVisibility(View.GONE);
        }

        if(!(stores.isThereWomen())){
            this.women.setVisibility(View.GONE);
        }

        if(!(stores.isThereKids())){
            this.kids.setVisibility(View.GONE);
        }
    }


    @Override
    public void onClick(View v) {

        Intent i = new Intent(context,RetailerProfileActivity.class);
        context.startActivity(i);



//        if (this.StoreModel != null) {

//        Toast.makeText(this.context, "Clicked on " , Toast.LENGTH_SHORT ).show();


    }

    private String formatdouble(double rating) {
        DecimalFormat ratingFormat = new DecimalFormat("#.00");
        return ratingFormat.format(rating);
    }
}
