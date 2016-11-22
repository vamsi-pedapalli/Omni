package com.example.android.omni;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ShopsList extends AppCompatActivity {

    private RecyclerView listView;
    private StoreListAdapter mAdapter;
    private List<StoreModel> store = new ArrayList<>();
    public static final String LOG_TAG = ShopsList.class.getName();
    private String sampleURL = "http://104.199.230.125/stores/1.json/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops_list);
//        loadData();

        StoresAsyncTask task = new StoresAsyncTask();

        task.execute(sampleURL);

//        QueryHandler.fetchStoreData(sampleURL);

        mAdapter = new StoreListAdapter(this, R.layout.list_item_layout, store);
        listView = (RecyclerView) findViewById(R.id.store_list);

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

//    private void loadData() {
//
//        stores = new ArrayList<StoreModel>();
///
//
//
//        stores.add(new StoreModel(R.drawable.louis_voitton, "Louis Voitton", "HSR Layout, Silkboard", "11:00AM to 6:00PM", 8.5, 854, false, true, false));
//        stores.add(new StoreModel(R.drawable.woodland, "WoodLand", "near temple, 17th main, indiranagar", "09:00AM to 5:00PM", 10.3, 93, true, true, true));
//        stores.add(new StoreModel(R.drawable.gucci, "Gucci", "old airport road, HAL", "10:00AM to 6:00PM", 3.8, 11, true, false, true));
//        stores.add(new StoreModel(R.drawable.retailer_profile_wallpaper_sample, "Allen Solly", "100 Feet Road", "11:00AM to 6:00PM", 1.2, 103, false, true, true));
//        stores.add(new StoreModel(R.drawable.woodland, "WoodLand", "near temple, 17th main, indiranagar", "09:00AM to 5:00PM", 10.3, 93, true, true, true));
//        stores.add(new StoreModel(R.drawable.gucci, "Gucci", "old airport road, HAL", "10:00AM to 6:00PM", 3.8, 11, true, false, true));
//        stores.add(new StoreModel(R.drawable.louis_voitton, "Louis Voitton", "HSR Layout, Silkboard", "11:00AM to 6:00PM", 8.5, 854, false, true, false));
//        stores.add(new StoreModel(R.drawable.woodland, "WoodLand", "near temple, 17th main, indiranagar", "09:00AM to 5:00PM", 10.3, 93, true, true, true));
//        stores.add(new StoreModel(R.drawable.gucci, "Gucci", "old airport road, HAL", "10:00AM to 6:00PM", 3.8, 11, true, false, true));
//        stores.add(new StoreModel(R.drawable.retailer_profile_wallpaper_sample, "Allen Solly", "100 Feet Road", "11:00AM to 6:00PM", 1.2, 103, false, true, true));
//        stores.add(new StoreModel(R.drawable.zara, "ZARA", "Madhura nagar", "08:00AM to 7:00PM", 3.5, 1103, false, false, true));
//        stores.add(new StoreModel(R.drawable.zara, "ZARA", "Madhura nagar", "08:00AM to 7:00PM", 3.5, 1103, false, false, true));
//        stores.add(new StoreModel(R.drawable.louis_voitton, "Louis Voitton", "HSR Layout, Silkboard", "11:00AM to 6:00PM", 8.5, 854, false, true, false));
//        stores.add(new StoreModel(R.drawable.woodland, "WoodLand", "near temple, 17th main, indiranagar", "09:00AM to 5:00PM", 10.3, 93, true, true, true));
//        stores.add(new StoreModel(R.drawable.gucci, "Gucci", "old airport road, HAL", "10:00AM to 6:00PM", 3.8, 11, true, false, true));
//        stores.add(new StoreModel(R.drawable.royal, "Royal Enfield", "ville, 8A main ", "5:00AM to 6:00PM", 8.5, 33, true, false, false));
//        stores.add(new StoreModel(R.drawable.woodland, "WoodLand", "near temple, 17th main, indiranagar", "09:00AM to 5:00PM", 10.3, 93, true, true, true));
//        stores.add(new StoreModel(R.drawable.louis_voitton, "Louis Voitton", "HSR Layout, Silkboard", "11:00AM to 6:00PM", 8.5, 854, false, true, false));
//        stores.add(new StoreModel(R.drawable.woodland, "WoodLand", "near temple, 17th main, indiranagar", "09:00AM to 5:00PM", 10.3, 93, true, true, true));
//        stores.add(new StoreModel(R.drawable.gucci, "Gucci", "old airport road, HAL", "10:00AM to 6:00PM", 3.8, 11, true, false, true));
//        stores.add(new StoreModel(R.drawable.louis_voitton, "Louis Voitton", "HSR Layout, Silkboard", "11:00AM to 6:00PM", 8.5, 854, false, true, false));
//        stores.add(new StoreModel(R.drawable.woodland, "WoodLand", "near temple, 17th main, indiranagar", "09:00AM to 5:00PM", 10.3, 93, true, true, true));
//        stores.add(new StoreModel(R.drawable.gucci, "Gucci", "old airport road, HAL", "10:00AM to 6:00PM", 3.8, 11, true, false, true));
//        stores.add(new StoreModel(R.drawable.retailer_profile_wallpaper_sample, "Allen Solly", "100 Feet Road", "11:00AM to 6:00PM", 1.2, 103, false, true, true));
//        stores.add(new StoreModel(R.drawable.zara, "ZARA", "Madhura nagar", "08:00AM to 7:00PM", 3.5, 1103, false, false, true));


    private class StoresAsyncTask extends AsyncTask<String, Void, List<StoreModel>> {
        @Override
        protected List<StoreModel> doInBackground(String... URLs) {


            if (URLs.length < 1 || URLs[0] == null) {
                Log.e("QueryUtils", "URL is is null");
                return null;
            }
            Log.e("QueryUtils", "URL is not null" + URLs[0]);

            return QueryHandler.fetchStoreData(URLs[0]);
        }
//
//        @Override
//        protected void onPostExecute(List<StoreModel> data) {
//
//            listView.setAdapter(mAdapter);
//            super.onPostExecute(data);
////            Log.e("QueryUtils", "URL is is null" + data);
//
////            mAdapter = new StoreListAdapter(ShopsList.this, R.layout.list_item_layout, stores);
////            RecyclerView listView = (RecyclerView) findViewById(R.id.store_list);
////            listView.setAdapter(mAdapter);
//
//
//
//        }


        @Override
        protected void onPostExecute(List<StoreModel> data) {
            Log.e("QueryUtils", "on post execute");
//            Toast.makeText(ShopsList.this, "post executed",Toast.LENGTH_SHORT).show();

            mAdapter.setStore(data);
            mAdapter.notifyDataSetChanged();
//            listView.setAdapter(mAdapter);
//            super.onPostExecute(data);
//            stores.addAll(data);
//            mAdapter = new StoreListAdapter(ShopsList.this, R.layout.list_item_layout, data);
//            listView.setAdapter(mAdapter);
//            super.onPostExecute(data);

        }
    }


}

