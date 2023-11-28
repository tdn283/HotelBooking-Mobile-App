package com.example.hotelbooking_app.Searching.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotelbooking_app.R;
import com.example.hotelbooking_app.Searching.Domain.PopularHotel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PopularHotelAdapter extends RecyclerView.Adapter<PopularHotelAdapter.myViewHolder> {
    List<PopularHotel> mListPopularHotel;

    public PopularHotelAdapter(List<PopularHotel> mListPopularHotel) {
        this.mListPopularHotel = mListPopularHotel;
    }

    public void setPopularHotels(List<PopularHotel> popularHotels) {
        mListPopularHotel = popularHotels;
    }

    @NonNull
    @Override
    public PopularHotelAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.searching_item_recently_viewed, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularHotelAdapter.myViewHolder holder, int position) {
        PopularHotel hotel = mListPopularHotel.get(position);

        // Set other data to the views
        holder.tvName.setText(hotel.getName());
        holder.tvAddress.setText(hotel.getAddress());
        holder.tvRating.setText("0");
        holder.tvCount.setText("" + hotel.getReviewQuantity());

        // Load image using Picasso
        if (hotel.getImageDetails() != null && !hotel.getImageDetails().isEmpty()) {
            // Assuming you want to load the first image in the list
            String imageUrl = hotel.getImageDetails().get(0).getImg();
            Picasso.get().load(imageUrl).into(holder.imgHotel);
        } else {
            holder.imgHotel.setImageResource(R.drawable.searching_image_muongthanh);
        }
    }

    @Override
    public int getItemCount() {
        return mListPopularHotel.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvAddress, tvRating, tvCount;
        ImageView imgHotel;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.item_tv_recently_viewed_name);
            tvAddress = itemView.findViewById(R.id.item_tv_recently_viewed_address);
            tvRating = itemView.findViewById(R.id.item_tv_recently_viewed_score);
            tvCount = itemView.findViewById(R.id.item_tv_recently_viewed_count);
            imgHotel = itemView.findViewById(R.id.item_img_recently_viewed);
        }
    }
}
