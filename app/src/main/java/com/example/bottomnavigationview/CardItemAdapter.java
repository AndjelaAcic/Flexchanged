package com.example.bottomnavigationview;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CardItemAdapter extends RecyclerView.Adapter<CardItemAdapter.ExampleViewHolder> {
    private ArrayList<CardItem> mCardItemList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextViewName;
        public TextView mTextViewEmail;
        public TextView mTextViewPhone;
        public TextView mTextViewGen;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextViewName = itemView.findViewById(R.id.txt_Name);
            mTextViewEmail = itemView.findViewById(R.id.txt_Email);
            mTextViewPhone = itemView.findViewById(R.id.txt_Phone);
            mTextViewGen = itemView.findViewById(R.id.txt_Gen);
        }
    }

    public CardItemAdapter(ArrayList<CardItem> cardItemList) {
        mCardItemList = cardItemList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        CardItem currentItem = mCardItemList.get(position);

      //  holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextViewName.setText(currentItem.getName());
        holder.mTextViewEmail.setText(currentItem.getEmail());
        holder.mTextViewPhone.setText(currentItem.getPhone());
        holder.mTextViewGen.setText(currentItem.getGeneration());

    }

    @Override
    public int getItemCount() {
        return mCardItemList.size();
    }
}
