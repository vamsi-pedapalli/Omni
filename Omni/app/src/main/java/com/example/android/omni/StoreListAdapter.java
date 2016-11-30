package com.example.android.omni;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import static android.media.CamcorderProfile.get;
import static com.example.android.omni.StoreListActivity.LOG_TAG;

/**
 * Created by vamsi on 19-11-2016.
 */


public class StoreListAdapter extends RecyclerView.Adapter<StoreListViewHolder> {

    private List<StoreListModel> store = new ArrayList<>();
    private int itemResource;
    private Context context;

    public void setStore(List<StoreListModel> store) {
        this.store = store;
    }

    public StoreListAdapter(Context context, int itemResource, List<StoreListModel> store) {

        this.store = store;
        this.itemResource = itemResource;
        this.context = context;
    }

    @Override
    public StoreListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.store_list_laout, parent, false);

        return new StoreListViewHolder(this.context, view);
    }

    @Override
    public void onBindViewHolder(StoreListViewHolder holder, int position) {
        StoreListModel stores = this.store.get(position);
        holder.bindStoreData(stores);
    }

    @Override
    public int getItemCount() {

        Log.e(LOG_TAG, "stores size"+ store.size());
        return this.store.size();
    }


//    @NonNull
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//
//        View listItemView = convertView;
//
//
//        com.example.android.omni.StoreListViewHolder viewHolder = new com.example.android.omni.StoreListViewHolder();
//
//        if (listItemView == null) {
//            listItemView = LayoutInflater.from(getContext()).inflate(
//                    R.layout.store_list_laout, parent, false);
//
//            viewHolder.storeWallpaper = (ImageView) listItemView.findViewById(R.id.store_wallpaper);
//            viewHolder.storeName = (TextView) listItemView.findViewById(R.id.store_name);
//            viewHolder.storeDistance = (TextView) listItemView.findViewById(R.id.store_distance);
//            viewHolder.storeAddress = (TextView) listItemView.findViewById(R.id.store_address);
//            viewHolder.storeNoOfBookmarks = (TextView) listItemView.findViewById(R.id.no_of_bookmarks);
//            viewHolder.storeOpenStatusTime = (TextView) listItemView.findViewById(R.id.store_open_time);
//            viewHolder.men = (ImageView) listItemView.findViewById(R.id.icon_man);
//            viewHolder.women = (ImageView) listItemView.findViewById(R.id.icon_woman);
//            viewHolder.kids = (ImageView) listItemView.findViewById(R.id.icon_kid);
//
//            listItemView.setTag(viewHolder);
//
//
//        } else {
//            viewHolder = (com.example.android.omni.StoreListViewHolder) listItemView.getTag();
//        }
//
//        StoreListModel currentStore = getItem(position);
//
//        viewHolder.storeName.setText(currentStore.getStoreName());
//        viewHolder.storeAddress.setText(currentStore.getStoreAddress());
//        viewHolder.storeOpenStatusTime.setText(currentStore.getStoreOpenStatusTime());
//
//        String formattedDistance = formatdouble(currentStore.getStoreDistance()) + " km";
//        viewHolder.storeDistance.setText(formattedDistance);
//
//        String bookmarksNumber = Integer.toString(currentStore.getNoOfBookmarks());
//        viewHolder.storeNoOfBookmarks.setText(bookmarksNumber);
//
//        viewHolder.storeWallpaper.setImageResource(currentStore.getStoreWallpaperId());
//
////        if (!currentStore.isThereMen()) {
////            viewHolder.men.setVisibility(GONE);
////        }
////
////        if (!currentStore.isThereWomen()) {
////            viewHolder.women.setVisibility(GONE);
////        }
////
////        if (!currentStore.isThereKids()) {
////            viewHolder.kids.setVisibility(GONE);
////        }
//
//        return listItemView;
//    }
//
//    private String formatdouble(double rating) {
//        DecimalFormat ratingFormat = new DecimalFormat("#.00");
//        return ratingFormat.format(rating);
//    }
//    static class com.example.android.omni.StoreListViewHolder {
//        TextView storeName;
//        TextView storeDistance;
//        TextView storeAddress;
//        ImageView storeWallpaper;
//        TextView storeOpenStatusTime;
//        TextView storeNoOfBookmarks;
//        ImageView men;
//        ImageView women;
//        ImageView kids;
//        int position;
//    }

}



