package com.example.android.omni;

import android.app.Activity;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by vamsi on 26-11-2016.
 */

public class StoreProfileViewHolder {
    //declare views here
    private TextView storeName;
    private TextView storeAddress;
    private TextView rating;
    private TextView NoOfRatings;
    private TextView ongoingOffers;
    private TextView latestCollection;

    private Activity activity;

    public StoreProfileViewHolder(Activity mactivity) {

        this.activity = mactivity;
        this.storeName = (TextView) this.activity.findViewById(R.id.store_name_profile);
        this.storeAddress = (TextView) this.activity.findViewById(R.id.area_name_profile);
        this.rating = (TextView) this.activity.findViewById(R.id.rating_profile);

//        this.a = (TextView) this.activity.findViewById(R.id.some_view);

    }

    public void setDatatoViews(StoreProfileModel model) {
//        this.a.setText(model.getText());

    }
}
