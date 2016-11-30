package com.example.android.omni;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.android.omni.HomePageActivity.currentLatitude;

/**
 * Created by vamsi on 21-11-2016.
 */

public class StoreListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView storeName;
    private TextView storeDistance;
    private TextView storeAddress;
    private ImageView storeWallpaper;
    private TextView storeOpenStatusTime;
    private TextView storeNoOfBookmarks;
    private ImageView men;
    private ImageView women;
    private ImageView kids;
    private TextView openStatus;

    private StoreListModel stores;
    private Context context;


    public StoreListViewHolder(Context context, View itemView) {
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
        this.openStatus = (TextView) itemView.findViewById(R.id.open_status);

        itemView.setOnClickListener(this);

    }

    public void bindStoreData(StoreListModel stores) {

        this.stores = stores;

        this.storeName.setText(stores.getStoreName());
        this.storeAddress.setText(stores.getStoreAddress());
        this.storeOpenStatusTime.setText(stores.getOpenTime() +" to "+ stores.getCloseTime() );

        double dist = stores.getStoreDistance() / 1000;
        String formattedDistance = (formatdouble(dist)) + " km";
        this.storeDistance.setText(formattedDistance);

        String bookmarksNumber = Integer.toString(stores.getNoOfBookmarks());
        this.storeNoOfBookmarks.setText(bookmarksNumber + " Marks");

        this.storeWallpaper.setImageResource(stores.getStoreWallpaperId());


        if (!(stores.isThereMen())) {
            this.men.setVisibility(View.GONE);
        }

        if (!(stores.isThereWomen())) {
            this.women.setVisibility(View.GONE);
        }

        if (!(stores.isThereKids())) {
            this.kids.setVisibility(View.GONE);
        }

        try {
            if (checkTime(stores.getOpenTime(), stores.getCloseTime())) {
                this.openStatus.setText("Open Now");
            } else {
                this.openStatus.setText("Closed");

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onClick(View v) {

        Intent i = new Intent(context, StoreProfileActivity.class);
        i.putExtra("USER_LATITUDE", currentLatitude);
        context.startActivity(i);



    }

    private String formatdouble(double rating) {
        DecimalFormat ratingFormat = new DecimalFormat("#.00");
        return ratingFormat.format(rating);
    }

    private boolean checkTime(String openTime, String closeTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        boolean checked = false;

        try {
            String currentDateString = DateFormat.getTimeInstance().format(new Date());
            Date currentTime = sdf.parse(currentDateString);
            Date opentime = sdf.parse(openTime);
            Date closetime = sdf.parse(closeTime);
            Log.e("QueryUtils", "open time is" + opentime + "close time" + closetime + "current time" + currentTime);
            if (currentTime.after(opentime) && currentTime.before(closetime)) {
                checked = true;
            }

        } catch (ParseException e) {
            Log.e("QueryUtils", "open time is" + openTime + "close time" + closeTime + "current time");
            Log.e("QueryUtils", "Problem checking open status", e);

        }

        return checked;

    }
}
