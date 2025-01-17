package com.example.hotelbooking_app.Booking.Activity;

import android.content.Intent;
import android.os.Bundle;
//import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hotelbooking_app.Booking.Data.BookingFormDetailData;
import com.example.hotelbooking_app.Booking.Fragment.BookingGuestsSelectBottomSheet;
import com.example.hotelbooking_app.Booking.Fragment.BookingRoomsSelectBottomSheet;

import com.example.hotelbooking_app.Booking.Item.BookingRoomType;

import com.example.hotelbooking_app.Booking.Interface.OnSaveClickListener;

import com.example.hotelbooking_app.R;
import com.example.hotelbooking_app.Searching.Activity.DetailActivity;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.util.Pair;
import androidx.lifecycle.ViewModelProvider;

public class BookingActivity extends AppCompatActivity implements OnSaveClickListener {
    private FrameLayout backBtn;

    private TextView guestsSelect;
    private TextView roomsSelect;
    private TextView datesSelect;
    private EditText phoneNumberSelect;
    private AppCompatButton continueBtn;
    private MaterialDatePicker<Pair<Long, Long>> datePicker;
    BookingFormDetailData bookingFormDetailData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_layout);

        // Initialize Form Detail Data
        bookingFormDetailData = new BookingFormDetailData();

        // Initialize UI elements
        guestsSelect = findViewById(R.id.guests_number_select);
        roomsSelect = findViewById(R.id.room_type_select);
        datesSelect = findViewById(R.id.dates_select);
        phoneNumberSelect = findViewById(R.id.booking_phone_number);
        continueBtn = findViewById(R.id.booking_continue_button);
        backBtn = findViewById(R.id.booking_back_button);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            bookingFormDetailData = (BookingFormDetailData) bundle.getSerializable("bookingFormData");
            updateBookingFormView(bookingFormDetailData);
        }

        setupGuestsSelect(bookingFormDetailData);
        setupRoomsSelect(bookingFormDetailData);
        setupDateSelect();
        setUpNavigateToCheckout();
        setUpNavigateBackToDetail();
    }

    private void updateBookingFormView(BookingFormDetailData data) {
        if (data != null) {
            String dateFormatted = formatDateRange(data.getStartDate(), data.getEndDate());
            String guestRoomQuantity = (data.getSelectedAdultValue() + data.getSelectedChildValue()) + " guests " + "- " + data.getSelectedRoomValue() + " rooms";
            String roomTypeQuantity = data.getRoomTypeList().size() + " room types";
            String phoneNumber = data.getPhoneNumber();

            datesSelect.setText(dateFormatted);
            guestsSelect.setText(guestRoomQuantity);
            roomsSelect.setText(roomTypeQuantity);
            phoneNumberSelect.setText(phoneNumber);
        }
    }

    private void setUpNavigateBackToDetail() {
        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, DetailActivity.class);
            startActivity(intent);
        });
    }

    private void setUpNavigateToCheckout() {
        continueBtn.setOnClickListener(v -> {
            bookingFormDetailData.setPhoneNumber(String.valueOf(phoneNumberSelect.getText()));

            Intent intent = new Intent(this, BookingCheckoutActivity.class);
            Bundle bundle = new Bundle();

            bundle.putSerializable("bookingFormData", (Serializable) bookingFormDetailData);
            intent.putExtras(bundle);

            startActivity(intent);
        });
    }

    private void setupGuestsSelect(BookingFormDetailData bookingFormDetailData) {
        guestsSelect.setOnClickListener(v -> showGuestsSelectBottomSheet(bookingFormDetailData));
    }

    private void showGuestsSelectBottomSheet(BookingFormDetailData bookingFormDetailData) {
        BookingGuestsSelectBottomSheet guestsSelectBottomSheet = new BookingGuestsSelectBottomSheet(bookingFormDetailData);
        guestsSelectBottomSheet.show(getSupportFragmentManager(), guestsSelectBottomSheet.getTag());
    }

    private void setupRoomsSelect(BookingFormDetailData bookingFormDetailData) {
        roomsSelect.setOnClickListener(v -> showRoomsSelectBottomSheet(bookingFormDetailData));
    }

    private void showRoomsSelectBottomSheet(BookingFormDetailData bookingFormDetailData) {
        BookingRoomsSelectBottomSheet bookingRoomsSelectBottomSheet = new BookingRoomsSelectBottomSheet(bookingFormDetailData);
        bookingRoomsSelectBottomSheet.show(getSupportFragmentManager(), bookingRoomsSelectBottomSheet.getTag());
    }

    private void setupDateSelect() {
        datePicker = MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("Select Date")
                .setTheme(R.style.ThemeMaterialCalendar)
                .build();


        datePicker.addOnPositiveButtonClickListener(selection -> {
            Pair<Long, Long> dateRange = datePicker.getSelection();

            Date startDate = new Date(dateRange.first);
            Date endDate = new Date(dateRange.second);
            String formattedDateRange = formatDateRange(startDate, endDate);

            bookingFormDetailData.setStartDate(startDate);
            bookingFormDetailData.setEndDate(endDate);
            datesSelect.setText(formattedDateRange);
        });

        datesSelect.setOnClickListener(v -> showDatePicker());
    }


    private void showDatePicker() {
        datePicker.show(getSupportFragmentManager(), "datePicker_tag");
    }


    private String formatDateRange(Date startDate, Date endDate) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String formattedStart = dateFormat.format(startDate);
        String formattedEnd = dateFormat.format(endDate);

        return formattedStart + " - " + formattedEnd;
    }

    @Override
    public void onSaveClick(int totalGuests, int totalRooms) {
        String selectedValue = totalGuests + " guests " + "- " + totalRooms + " rooms";
        guestsSelect.setText(selectedValue);
    }

    @Override
    public void onSelectClick(ArrayList<BookingRoomType> roomTypeList) {
        String displayValue = roomTypeList.size() + " room types";

        bookingFormDetailData.setRoomTypeList(roomTypeList);
        roomsSelect.setText(displayValue);
    }
}
