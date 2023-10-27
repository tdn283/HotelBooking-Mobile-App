package com.example.hotelbooking_app.Homescreen.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ImageView;
import android.graphics.Color;
import android.view.ViewTreeObserver;
import android.content.Intent;
import android.widget.Toast;

import com.example.hotelbooking_app.Homescreen.Adapter.Homescreen_NearbyhotelAdapter;
import com.example.hotelbooking_app.Homescreen.Adapter.Homescreen_PopularHotelAdapter;
import com.example.hotelbooking_app.Homescreen.HomescreenActivity;
import com.example.hotelbooking_app.Homescreen.Hotels.Homescreen_Nearbyhotel;
import com.example.hotelbooking_app.Homescreen.Hotels.Homescreen_PopularHotel;
import com.example.hotelbooking_app.MainActivity;
import com.example.hotelbooking_app.R;
import com.example.hotelbooking_app.Searching.Activity.DetailActivity;
import com.example.hotelbooking_app.Searching.Activity.SearchingActivity;


import java.util.ArrayList;


public class Homescreen_home extends Fragment {
    HorizontalScrollView horizontalScrollView;
    LinearLayout lnNearbyHotel,lnPopularHotel;
    TextView nearbyHotels;
    ScrollView scrollview;
    ImageView btn_seach;
    ArrayList<Homescreen_Nearbyhotel> arrayNearByHotel;
    ArrayList<Homescreen_PopularHotel> arrayPopularHotel;
    Homescreen_NearbyhotelAdapter adapter;
    Homescreen_PopularHotelAdapter adapter_1;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homescreen_fragment_home, container, false);


        arrayNearByHotel = new ArrayList<>();
        arrayPopularHotel = new ArrayList<>();

        AnhXa();

        adapter = new Homescreen_NearbyhotelAdapter(getActivity(), R.layout.homescreen_item_nearbyhotel, arrayNearByHotel);

        adapter_1 = new Homescreen_PopularHotelAdapter(getActivity(), R.layout.homescreen_item_popularhotel, arrayPopularHotel);

        horizontalScrollView = (HorizontalScrollView) view.findViewById(R.id.homescreen_horizontal_scroll_view);

        lnNearbyHotel = (LinearLayout) view.findViewById(R.id.home_lvNearbyHotel);
//        lnPopularHotel = (LinearLayout) view.findViewById(R.id.home_lvpopularhotel);
        lnPopularHotel = (LinearLayout) horizontalScrollView.getChildAt(0);

        for (int i = 0; i < adapter.getCount(); i++) {
            View ittem = adapter.getView(i, null, null);
            lnNearbyHotel.addView(ittem);
        }

        for (int i = 0; i < adapter_1.getCount(); i++) {
            View ittem = adapter_1.getView(i, null, null);
            lnPopularHotel.addView(ittem);
        }

        //đổi màu textnearbyhotel
        nearbyHotels = view.findViewById(R.id.home_nearbyhotels);
        scrollview = view.findViewById(R.id.home_contentt);

        scrollview.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int scrollY = scrollview.getScrollY();
                if (scrollY > scrollview.getHeight()/8) {
                    nearbyHotels.setTextColor(Color.WHITE);
                } else {
                    nearbyHotels.setTextColor(Color.BLACK);
                }
            }
        });

        /* Truong Dinh Nhat code intent from Home to Detail */
        for (int i = 0; i < lnPopularHotel.getChildCount(); i++) {

            View childView = lnPopularHotel.getChildAt(i);

            childView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Handle click
                    Intent intent = new Intent(getActivity(), DetailActivity.class);
                    startActivity(intent);
                }
            });
        }

         //Intent searching
        btn_seach = (ImageView) view.findViewById(R.id.home_btn_search);
        btn_seach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchingActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }


    private void AnhXa() {
        arrayPopularHotel.add(new Homescreen_PopularHotel("HAIAN","My An Beach",4.9,500,"$100/Day",R.drawable.homescreen_haian));
        arrayPopularHotel.add(new Homescreen_PopularHotel("NOVOTEL","My An Beach",5,1000,"$100/Day",R.drawable.homescreen_novotel));
        arrayPopularHotel.add(new Homescreen_PopularHotel("HAIAN","My An Beach",4.9,500,"$100/Day",R.drawable.homescreen_haian));
        arrayPopularHotel.add(new Homescreen_PopularHotel("NOVOTEL","My An Beach",5,1000,"$100/Day",R.drawable.homescreen_novotel));
        arrayPopularHotel.add(new Homescreen_PopularHotel("HAIAN","My An Beach",4.9,500,"$100/Day",R.drawable.homescreen_haian));
        arrayPopularHotel.add(new Homescreen_PopularHotel("NOVOTEL","My An Beach",5,1000,"$100/Day",R.drawable.homescreen_novotel));

        arrayNearByHotel.add(new Homescreen_Nearbyhotel("MUONG THANH","My An Beach",4.9,1000,"$100/Day",R.drawable.homescreen_muongthanh));
        arrayNearByHotel.add(new Homescreen_Nearbyhotel("MERODA","My An Beach",4.9,500,"$100/Day",R.drawable.homescreen_meroda));
        arrayNearByHotel.add(new Homescreen_Nearbyhotel("MUONG THANH","My An Beach",4.9,1000,"$100/Day",R.drawable.homescreen_muongthanh));
        arrayNearByHotel.add(new Homescreen_Nearbyhotel("MERODA","My An Beach",4.9,500,"$100/Day",R.drawable.homescreen_meroda));
        arrayNearByHotel.add(new Homescreen_Nearbyhotel("MUONG THANH","My An Beach",4.9,1000,"$100/Day",R.drawable.homescreen_muongthanh));
        arrayNearByHotel.add(new Homescreen_Nearbyhotel("MERODA","My An Beach",4.9,500,"$100/Day",R.drawable.homescreen_meroda));

    }
}