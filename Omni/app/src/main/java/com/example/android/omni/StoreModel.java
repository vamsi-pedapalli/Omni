package com.example.android.omni;

import static android.R.attr.name;

/**
 * Created by vamsi on 19-11-2016.
 */

public class StoreModel {

    private String storeName;
    private String storeAddress;
    private String storeOpenStatusTime;
    private int storeWallpaperId;
    private double storeDistance;
    private int noOfBookmarks;
    private boolean isThereKids;
   private boolean isThereMen;
   private boolean isThereWomen;

    public void setStoreDistance(double storeDistance) {
        this.storeDistance = storeDistance;
    }

    public StoreModel(int wallpaperId, String name, String address, String time) {

        storeWallpaperId = wallpaperId;
//        storeDistance = distance;
        storeOpenStatusTime = time;
        storeName = name;
        storeAddress = address;
//        noOfBookmarks = bookmarks;

    }



    public StoreModel(String name, String address, String time, double distance, int bookmarks) {

//        storeWallpaperId = wallpaperId;
        storeDistance = distance;
        storeOpenStatusTime = time;
        storeName = name;
        storeAddress = address;
        noOfBookmarks = bookmarks;

    }


    public StoreModel(int wallpaperId, String name, String address, String time, double distance, int bookmarks) {

        storeWallpaperId = wallpaperId;
        storeDistance = distance;
        storeOpenStatusTime = time;
        storeName = name;
        storeAddress = address;
        noOfBookmarks = bookmarks;

    }


    public StoreModel(int wallpaperId, String name, String address, String time, double distance, int bookmarks, boolean man, boolean woman,boolean kid) {

        storeWallpaperId = wallpaperId;
        storeDistance = distance;
        storeOpenStatusTime = time;
        storeName = name;
        storeAddress = address;
        noOfBookmarks = bookmarks;
        isThereKids=kid;
        isThereMen=man;
        isThereWomen=woman;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public String getStoreOpenStatusTime() {
        return storeOpenStatusTime;
    }

    public int getStoreWallpaperId() {
        return storeWallpaperId;
    }

    public double getStoreDistance() {
        return storeDistance;
    }

    public int getNoOfBookmarks() {
        return noOfBookmarks;
    }

    public boolean isThereKids() {
        return isThereKids;
    }

    public boolean isThereMen() {
        return isThereMen;
    }

    public boolean isThereWomen() {
        return isThereWomen;
    }
}
