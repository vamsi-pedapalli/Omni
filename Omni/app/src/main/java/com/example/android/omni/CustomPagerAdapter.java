package com.example.android.omni;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by vamsi on 29-11-2016.
 */

public class CustomPagerAdapter extends PagerAdapter {

    LayoutInflater mLayoutInflater;
    private Context mContext;
    public int[] mResources = {
            R.drawable.louis_voitton,
            R.drawable.retailer_profile_wallpaper_sample,
            R.drawable.gucci,
            R.drawable.zara,
            R.drawable.gucci,
            R.drawable.retailer_profile_wallpaper_sample
    };

    public CustomPagerAdapter(Context context) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);
        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
        imageView.setImageResource(mResources[position]);
        container.addView(itemView);
        return itemView;

    }


}
