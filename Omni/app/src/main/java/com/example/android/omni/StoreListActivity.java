package com.example.android.omni;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
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

import static com.example.android.omni.StoreListActivity.LOG_TAG;

public class StoreListActivity extends AppCompatActivity {

    private RecyclerView listView;
    private StoreListAdapter mAdapter;
    private List<StoreListModel> store = new ArrayList<>();
    public static final String LOG_TAG = StoreListActivity.class.getName();
    private StringBuilder sampleURL =
            new StringBuilder("http://104.199.230.125/stores/?lat=15.073&long=83.398&distance=3.0");
    static double userLat;
    static double userLon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectAll()   // or .detectAll() for all detectable problems
                    .penaltyLog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
        }
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "shoplist created");
        setContentView(R.layout.store_list_activity);
        Log.d(LOG_TAG, "list activity updated");
        setupActionbarTheme();
        Log.d(LOG_TAG, "actionbar setup");

//        HomePageActivity home = new HomePageActivity();
//        Log.d(LOG_TAG, "updated locations" + home.getCurrentLongitude());


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userLat = extras.getDouble("USER_LATITUDE");
            userLon = extras.getDouble("USER_LONGITUDE");
            updateURL(userLat, userLon, 3);
            Log.d(LOG_TAG, "updated locations" + userLat + "  " + userLon);
        }


        TextView mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();

        if (info != null && info.isConnected()) {
            StoresAsyncTask task = new StoresAsyncTask();
            Log.d(LOG_TAG, "URL Now is " + sampleURL.toString());
            task.execute(sampleURL.toString());
            Log.d(LOG_TAG, "task executing in back with " + sampleURL.toString());

        } else {

            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet);
        }


        mAdapter = new StoreListAdapter(this, R.layout.store_list_laout, store);
        listView = (RecyclerView) findViewById(R.id.store_list);
        listView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(layoutManager);
        listView.setNestedScrollingEnabled(false);
        listView.setAdapter(mAdapter);

        final FloatingActionButton listFab = (FloatingActionButton) findViewById(R.id.list_fab);
        listFab.setOnClickListener(new View.OnClickListener() {
                                       public void onClick(View v) {


                                           Fragment filterPage = new filterPageFragment();

                                           FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                           transaction.replace(R.id.placeholder, filterPage); // give your fragment container id in first parameter
                                           transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                                           transaction.commit();

                                       }
                                   }


        );

    }

    public class StoresAsyncTask extends AsyncTask<String, Void, List<StoreListModel>> {
        @Override
        protected List<StoreListModel> doInBackground(String... URLs) {

            Log.d(LOG_TAG, "Async task doing in background");
            if (URLs.length < 1 || URLs[0] == null) {
                Log.e(LOG_TAG, "URL is null");
                return null;
            }

            Log.e(LOG_TAG, "URL is not null" + URLs[0]);
            Log.d(LOG_TAG, "fetching storedata");
            List<StoreListModel> fetchedList = StoreListQueryHandler.fetchStoreData(URLs[0]);
            Log.d(LOG_TAG, "list fetched");
            return fetchedList;

        }

        @Override
        protected void onPostExecute(List<StoreListModel> listdata) {
            Log.e("QueryUtils", "post execution started");
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



