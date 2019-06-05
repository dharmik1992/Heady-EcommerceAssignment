package com.app.headyecommerceapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.app.headyecommerceapp.R;

import java.util.ArrayList;


public class ParentCategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
//    QuestionData questionData;
//    ArrayList<Datum> data;

    public ParentCategoryAdapter(Context context) {
        this.context = context;
//        this.questionData = questionData;
//        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_parent_category, parent, false);
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {



    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout relativeLayout;
        TextView txt_count;
        TextView tvCategoryTitle;
        TextView txt_answer_value;

        public HeaderViewHolder(View view) {
            super(view);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
