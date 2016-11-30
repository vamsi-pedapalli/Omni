package com.example.android.omni;

import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vamsi on 30-11-2016.
 */

public class filterPageFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_filter_page,container,false);
        return view;

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {




        ArrayList<TextView> textview_id = new ArrayList<>();

        textview_id.add((TextView) getView().findViewById(R.id.apparel));
        textview_id.add((TextView) getView().findViewById(R.id.footwear));
        textview_id.add((TextView) getView().findViewById(R.id.accessories));
        textview_id.add((TextView) getView().findViewById(R.id.bs));
        textview_id.add((TextView) getView().findViewById(R.id.travel));
        textview_id.add((TextView) getView().findViewById(R.id.bags));
        textview_id.add((TextView) getView().findViewById(R.id.bridal_filter));
        textview_id.add((TextView) getView().findViewById(R.id.work));
        textview_id.add((TextView) getView().findViewById(R.id.ethnic));
        textview_id.add((TextView) getView().findViewById(R.id.party));
        textview_id.add((TextView) getView().findViewById(R.id.casual));
        textview_id.add((TextView) getView().findViewById(R.id.sports));
        textview_id.add((TextView) getView().findViewById(R.id.exclusive));
        textview_id.add((TextView) getView().findViewById(R.id.multi));
        textview_id.add((TextView) getView().findViewById(R.id.designer));
        textview_id.add((TextView) getView().findViewById(R.id.gift));

        final Map<View,Boolean> viewState=new HashMap<>();

        for(int i=0;i<textview_id.size();i++) {
            viewState.put(textview_id.get(i),true);
        }

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView vt = (TextView) v;
                boolean state = viewState.get(vt);
                Drawable shape1 = ResourcesCompat.getDrawable(getResources(),R.drawable.rectangle_border_pressed,null);
                Drawable shape2 = ResourcesCompat.getDrawable(getResources(),R.drawable.rectangle_unpressed,null);

                if(state) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        vt.setBackground(shape1);
                    }
                    vt.setTextColor(Color.WHITE);
                }
                else{
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        vt.setBackground(shape2);
                    }
                    vt.setTextColor(Color.parseColor("#424242"));
                }
                viewState.put(v,!state);
            }
        };

        for(int i=0;i<textview_id.size();i++) {
            textview_id.get(i).setOnClickListener(listener);
        }
        super.onViewCreated(view, savedInstanceState);
    }
}
