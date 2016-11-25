package com.example.android.omni;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;

public class ShopsListActivity extends AppCompatActivity {

    private RecyclerView listView;
    private StoreListAdapter mAdapter;
    private List<StoreModel> store = new ArrayList<>();
    public static final String LOG_TAG = ShopsListActivity.class.getName();
    private StringBuilder sampleURL =
            new StringBuilder("http://104.199.230.125/stores/?lat=15.073&long=83.398&distance=3.0");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "shoplist created");
        setContentView(R.layout.activity_shops_list);
        Log.d(LOG_TAG, "list activity updated");
        setupActionbarTheme();
        Log.d(LOG_TAG, "actionbar setup");

        HomePageActivity home = new HomePageActivity();
        Log.d(LOG_TAG, "updated locations" + home.getCurrentLongitude());

        updateURL(home.getCurrentLatitude(), home.getCurrentLongitude(), 3);
        Log.d(LOG_TAG, "updated locations" + home.getCurrentLatitude() + "  " + home.getCurrentLongitude());


        TextView mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();

        if (info != null && info.isConnected()) {
            StoresAsyncTask task = new StoresAsyncTask();
//            task.execute(sampleURL);
//            Log.d(LOG_TAG, "URL before is " + sampleURL.toString());
//            HomePageActivity home = new HomePageActivity();
//            updateURL(home.getCurrentLatitude(),home.getCurrentLongitude(),5);
            Log.d(LOG_TAG, "URL Now is " + sampleURL.toString());
            task.execute(sampleURL.toString());
            Log.d(LOG_TAG, "task executing in back with " + sampleURL.toString());

        } else {

            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet);
        }


        mAdapter = new StoreListAdapter(this, R.layout.list_item_layout, store);
        listView = (RecyclerView) findViewById(R.id.store_list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(layoutManager);
        listView.setNestedScrollingEnabled(false);
        listView.setAdapter(mAdapter);
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


    public class StoresAsyncTask extends AsyncTask<String, Void, List<StoreModel>> {
        @Override
        protected List<StoreModel> doInBackground(String... URLs) {

            Log.d(LOG_TAG, "Async task doing in background");
            if (URLs.length < 1 || URLs[0] == null) {
                Log.e(LOG_TAG, "URL is null");
                return null;
            }

            Log.e(LOG_TAG, "URL is not null" + URLs[0]);
            Log.d(LOG_TAG, "fetching storedata");
            List<StoreModel> fetchedList = QueryHandler.fetchStoreData(URLs[0]);
            Log.d(LOG_TAG, "list fetched");
            return fetchedList;

        }

        @Override
        protected void onPostExecute(List<StoreModel> listdata) {
            Log.e("QueryUtils", "post execution started");
//            Toast.makeText(ShopsListActivity.this, "post executed",Toast.LENGTH_SHORT).show();
            View loadingIndicator = findViewById(R.id.loading_indicator);

            if (listdata != null && !listdata.isEmpty()) {
                mAdapter.setStore(listdata);
                Log.d(LOG_TAG, "setting store data" + listdata);
                mAdapter.notifyDataSetChanged();
                Log.d(LOG_TAG, "data set changed" + listdata);
                loadingIndicator.setVisibility(View.GONE);
            }
        }
    }


    private void setupActionbarTheme() {

        Toolbar Toolbar =
                (Toolbar) findViewById(R.id.shoplist_toolbar);

        setSupportActionBar(Toolbar);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_list);
        collapsingToolbar.setTitle("ZARA");

        ActionBar ab = getSupportActionBar();
        ab.setTitle(null);
        ab.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.notification:

                Toast.makeText(this, "no notifications", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);


        }
    }

    public void updateURL(double lat, double lon, double dist) {
        Log.d(LOG_TAG, "updating URL" + sampleURL);

        sampleURL.replace(sampleURL.indexOf("=") + 1,
                sampleURL.length() + 1,
                lat + "&long=" + lon + "&distance=" + dist);
        Log.d(LOG_TAG, "url updated" + sampleURL);

    }


}



