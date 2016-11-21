package com.example.android.omni;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

public class ShopsList extends AppCompatActivity {

    private ArrayList<Stores> stores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops_list);
        loadData();

        StoreListAdapter mAdapter = new StoreListAdapter(this, R.layout.list_item_layout, stores);
        RecyclerView listView = (RecyclerView) findViewById(R.id.store_list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(layoutManager);

        listView.setAdapter(mAdapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Intent i = new Intent(ShopsList.this, RetailerProfileActivity.class);
//                startActivity(i);
//            }
//        });

    }
    private void loadData() {

        stores = new ArrayList<Stores>();



        stores.add(new Stores(R.drawable.louis_voitton, "Louis Voitton", "HSR Layout, Silkboard", "11:00AM to 6:00PM", 8.5, 854, false, true, false));
        stores.add(new Stores(R.drawable.woodland, "WoodLand", "near temple, 17th main, indiranagar", "09:00AM to 5:00PM", 10.3, 93, true, true, true));
        stores.add(new Stores(R.drawable.gucci, "Gucci", "old airport road, HAL", "10:00AM to 6:00PM", 3.8, 11, true, false, true));
        stores.add(new Stores(R.drawable.retailer_profile_wallpaper_sample, "Allen Solly", "100 Feet Road", "11:00AM to 6:00PM", 1.2, 103, false, true, true));
        stores.add(new Stores(R.drawable.woodland, "WoodLand", "near temple, 17th main, indiranagar", "09:00AM to 5:00PM", 10.3, 93, true, true, true));
        stores.add(new Stores(R.drawable.gucci, "Gucci", "old airport road, HAL", "10:00AM to 6:00PM", 3.8, 11, true, false, true));
        stores.add(new Stores(R.drawable.louis_voitton, "Louis Voitton", "HSR Layout, Silkboard", "11:00AM to 6:00PM", 8.5, 854, false, true, false));
        stores.add(new Stores(R.drawable.woodland, "WoodLand", "near temple, 17th main, indiranagar", "09:00AM to 5:00PM", 10.3, 93, true, true, true));
        stores.add(new Stores(R.drawable.gucci, "Gucci", "old airport road, HAL", "10:00AM to 6:00PM", 3.8, 11, true, false, true));
        stores.add(new Stores(R.drawable.retailer_profile_wallpaper_sample, "Allen Solly", "100 Feet Road", "11:00AM to 6:00PM", 1.2, 103, false, true, true));
        stores.add(new Stores(R.drawable.zara, "ZARA", "Madhura nagar", "08:00AM to 7:00PM", 3.5, 1103, false, false, true));
        stores.add(new Stores(R.drawable.zara, "ZARA", "Madhura nagar", "08:00AM to 7:00PM", 3.5, 1103, false, false, true));
        stores.add(new Stores(R.drawable.louis_voitton, "Louis Voitton", "HSR Layout, Silkboard", "11:00AM to 6:00PM", 8.5, 854, false, true, false));
        stores.add(new Stores(R.drawable.woodland, "WoodLand", "near temple, 17th main, indiranagar", "09:00AM to 5:00PM", 10.3, 93, true, true, true));
        stores.add(new Stores(R.drawable.gucci, "Gucci", "old airport road, HAL", "10:00AM to 6:00PM", 3.8, 11, true, false, true));
        stores.add(new Stores(R.drawable.royal, "Royal Enfield", "ville, 8A main ", "5:00AM to 6:00PM", 8.5, 33, true, false, false));
        stores.add(new Stores(R.drawable.woodland, "WoodLand", "near temple, 17th main, indiranagar", "09:00AM to 5:00PM", 10.3, 93, true, true, true));
        stores.add(new Stores(R.drawable.louis_voitton, "Louis Voitton", "HSR Layout, Silkboard", "11:00AM to 6:00PM", 8.5, 854, false, true, false));
        stores.add(new Stores(R.drawable.woodland, "WoodLand", "near temple, 17th main, indiranagar", "09:00AM to 5:00PM", 10.3, 93, true, true, true));
        stores.add(new Stores(R.drawable.gucci, "Gucci", "old airport road, HAL", "10:00AM to 6:00PM", 3.8, 11, true, false, true));
        stores.add(new Stores(R.drawable.louis_voitton, "Louis Voitton", "HSR Layout, Silkboard", "11:00AM to 6:00PM", 8.5, 854, false, true, false));
        stores.add(new Stores(R.drawable.woodland, "WoodLand", "near temple, 17th main, indiranagar", "09:00AM to 5:00PM", 10.3, 93, true, true, true));
        stores.add(new Stores(R.drawable.gucci, "Gucci", "old airport road, HAL", "10:00AM to 6:00PM", 3.8, 11, true, false, true));
        stores.add(new Stores(R.drawable.retailer_profile_wallpaper_sample, "Allen Solly", "100 Feet Road", "11:00AM to 6:00PM", 1.2, 103, false, true, true));
        stores.add(new Stores(R.drawable.zara, "ZARA", "Madhura nagar", "08:00AM to 7:00PM", 3.5, 1103, false, false, true));


    }

}
