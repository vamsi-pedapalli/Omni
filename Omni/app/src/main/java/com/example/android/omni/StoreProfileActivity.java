package com.example.android.omni;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class StoreProfileActivity extends AppCompatActivity {

    private String StoreName;
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
        setContentView(R.layout.store_profile_activity);
        setupActionbarTheme();


        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new CustomPagerAdapter(this));
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager, true);

        final TextView storeHead = (TextView) findViewById(R.id.about_the_store_head);
        final TextView storeBody = (TextView) findViewById(R.id.about_the_store_body);

        final TextView ExchangeHead = (TextView) findViewById(R.id.Exchange_policy_head);
        final TextView ExchangeBody = (TextView) findViewById(R.id.Exchange_policy_body);

        storeHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExchangeHead.setTextColor(ContextCompat.getColor(StoreProfileActivity.this, R.color.Black));
                storeHead.setTextColor(ContextCompat.getColor(StoreProfileActivity.this, R.color.brand_red));
                if (storeBody.isShown() || ExchangeBody.isShown()) {
                    ExchangeBody.setVisibility(View.GONE);
                    storeBody.setVisibility(View.GONE);
                    storeHead.setTextColor(ContextCompat.getColor(StoreProfileActivity.this, R.color.Black));

                } else {
                    storeHead.setTextColor(ContextCompat.getColor(StoreProfileActivity.this, R.color.brand_red));
                    leftSlideDown(StoreProfileActivity.this, storeBody);
                    storeBody.setVisibility(View.VISIBLE);
                    ExchangeBody.setVisibility(View.INVISIBLE);
                    ExchangeBody.setVisibility(View.GONE);
                }

            }
        });

        ExchangeHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeHead.setTextColor(ContextCompat.getColor(StoreProfileActivity.this, R.color.Black));
                ExchangeHead.setTextColor(ContextCompat.getColor(StoreProfileActivity.this, R.color.brand_red));
                if (ExchangeBody.isShown() || storeBody.isShown()) {
                    ExchangeBody.setVisibility(View.GONE);
                    storeBody.setVisibility(View.GONE);
                    ExchangeHead.setTextColor(ContextCompat.getColor(StoreProfileActivity.this, R.color.Black));

                } else {
                    ExchangeHead.setTextColor(ContextCompat.getColor(StoreProfileActivity.this, R.color.brand_red));
                    rightSlideDown(StoreProfileActivity.this, ExchangeBody);
                    ExchangeBody.setVisibility(View.VISIBLE);
                    storeBody.setVisibility(View.INVISIBLE);
                    storeBody.setVisibility(View.GONE);
                }
            }
        });

        //      set data to views here
//        new StoreProfileViewHolder(this).setDatatoViews(QueryHandler.fetchdata(););

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_page_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share_action:

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Try this app : URL");
                startActivity(Intent.createChooser(intent, "Share with"));
                return true;

            default:
                return super.onOptionsItemSelected(item);


        }
    }


    private void setupActionbarTheme() {

        Toolbar myChildToolbar =
                (Toolbar) findViewById(R.id.profile_page_toolbar);

        setSupportActionBar(myChildToolbar);

//        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//        StoreName = "Allen Solly";
//        collapsingToolbar.setTitle(null);

        ActionBar ab = getSupportActionBar();
        StoreName = "Allen Solly";
        ab.setTitle(StoreName);
        ab.setDisplayHomeAsUpEnabled(true);

    }

    public static void leftSlideDown(Context ctx, View v) {

        Animation a = AnimationUtils.loadAnimation(ctx, R.anim.left_slide_down);
        if (a != null) {
            a.reset();
            if (v != null) {
                v.clearAnimation();
                v.startAnimation(a);
            }
        }
    }

    public static void rightSlideDown(Context ctx, View v) {
        Animation a = AnimationUtils.loadAnimation(ctx, R.anim.right_slide_down);
        if (a != null) {
            a.reset();
            if (v != null) {
                v.clearAnimation();
                v.startAnimation(a);
            }
        }
    }
}
