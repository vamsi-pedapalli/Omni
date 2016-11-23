package com.example.android.omni;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ViewFlipper;

public class RetailerProfileActivity extends AppCompatActivity implements View.OnClickListener {

    ViewFlipper flip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_profile);
        setupActionbarTheme();

        flip = (ViewFlipper) this.findViewById(R.id.flipper);
        flip.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        flip.startFlipping();

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

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("ZARA");

        ActionBar ab = getSupportActionBar();
        ab.setTitle(null);
        ab.setDisplayHomeAsUpEnabled(true);

    }
}
