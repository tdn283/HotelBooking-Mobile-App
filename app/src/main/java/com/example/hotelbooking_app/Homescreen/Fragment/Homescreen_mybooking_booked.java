package com.example.hotelbooking_app.Homescreen.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.example.hotelbooking_app.R;
import com.example.hotelbooking_app.Homescreen.Adapter.Homescreen_BookedAdapter;
import com.example.hotelbooking_app.Homescreen.Hotels.Homescreen_Booked;

import java.util.ArrayList;

public class Homescreen_mybooking_booked extends Fragment {
    LinearLayout lnBookedHotel;
    ArrayList<Homescreen_Booked> arrayBookedHotel;
    Homescreen_BookedAdapter  adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homescreen_mybooking_fragment_booked, container, false);
        arrayBookedHotel = new ArrayList<>();
        AnhXa();

        adapter = new Homescreen_BookedAdapter(getActivity(),R.layout.homescreen_item_booked, arrayBookedHotel);

        lnBookedHotel = (LinearLayout) view.findViewById(R.id.lvBookedHotel);

        for (int i = 0; i < adapter.getCount(); i++) {
            View ittem = adapter.getView(i, null, null);
            lnBookedHotel.addView(ittem);
        }
        return view;
    }
    private void AnhXa() {
        arrayBookedHotel.add(new Homescreen_Booked("MUONG THANH", "My An Beach", 4.9, 500, "$100/Day", R.drawable.homescreen_muongthanh,"27 March","14 December"));
        arrayBookedHotel.add(new Homescreen_Booked("HAIAN", "My An Beach", 4.9, 500, "$100/Day", R.drawable.homescreen_haian,"23 June","26 October"));
        arrayBookedHotel.add(new Homescreen_Booked("NOVOTEL", "My An Beach", 4.9, 500, "$100/Day", R.drawable.homescreen_novotel,"27 March","14 December"));
        arrayBookedHotel.add(new Homescreen_Booked("MERODA", "My An Beach", 4.9, 500, "$100/Day", R.drawable.homescreen_meroda,"23 June","26 October"));
    }
}