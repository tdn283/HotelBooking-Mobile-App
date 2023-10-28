package com.example.hotelbooking_app.Homescreen.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.hotelbooking_app.Homescreen.Activity.Homescreen_myprofile;
import com.example.hotelbooking_app.Login.Activity.LoginActivity;
import com.example.hotelbooking_app.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;




public class Homescreen_setting extends Fragment {
    BottomNavigationView bottomNavigationView;
    ImageButton btn_back;
    LinearLayout setting_logout,setting_editprofile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homescreen_fragment_setting, container, false);
        // back home
        btn_back = (ImageButton) view.findViewById(R.id.myprofile_btn_back);
        bottomNavigationView = getActivity().findViewById(R.id.homescreen_bottom_navigation);

        // Set a click listener for the button
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomNavigationView.setSelectedItemId(R.id.btn_home);
            }
        });
        //logout
        setting_logout = (LinearLayout) view.findViewById(R.id.setting_btn_logout);
        setting_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSignOutDialog();
            }
        });

        //edit profile
        setting_editprofile = (LinearLayout) view.findViewById(R.id.setting_btn_editprofile);
        setting_editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Homescreen_myprofile.class);
                startActivity(intent);
            }
        });



        return view;

    }
    public void showSignOutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getLayoutInflater().inflate(R.layout.homescreen_arlert_signout, null);
        builder.setView(view);

        builder.setTitle("Logout of your account?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}