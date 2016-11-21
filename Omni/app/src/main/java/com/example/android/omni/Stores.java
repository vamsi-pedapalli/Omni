package com.example.android.omni;

/**
 * Created by vamsi on 19-11-2016.
 */

public class Stores {

    private String storeName;
    private String storeAddress;
    private String storeOpenStatusTime;
    private int storeWallpaperId;
    private double storeDistance;
    private int noOfBookmarks;
    private boolean isThereKids;
   private boolean isThereMen;
   private boolean isThereWomen;


    public Stores(int wallpaperId, String name, String address, String time, double distance, int bookmarks) {

        storeWallpaperId = wallpaperId;
        storeDistance = distance;
        storeOpenStatusTime = time;
        storeName = name;
        storeAddress = address;
        noOfBookmarks = bookmarks;

    }


    public Stores(int wallpaperId, String name, String address, String time, double distance, int bookmarks, boolean kid, boolean man, boolean woman) {

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
