package com.app.headyecommerceapp.adapters;

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
import com.app.headyecommerceapp.activities.ProductListingActivity;
import com.app.headyecommerceapp.activities.SubCategoryActivty;
import com.app.headyecommerceapp.models.Category;
import com.app.headyecommerceapp.realm.operation.RealmQuery;

import io.realm.Realm;
import io.realm.RealmList;


public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    RealmList<Category> subCategories;
    RealmQuery realmQuery;
    Realm realm;

    public CategoryAdapter(Context context, RealmList<Category> subCategories, Realm realm, RealmQuery realmQuery) {
        this.context = context;
        this.subCategories = subCategories;
        this.realm = realm;
        this.realmQuery = realmQuery;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_sub_category, parent, false);
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ((HeaderViewHolder) holder).relativeMainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (realmQuery.hasMoreCategories(subCategories.get(position).getId(), realm)) {
                    intent = new Intent(context, SubCategoryActivty.class);
                    intent.putExtra("position", subCategories.get(position).getId());
                    intent.putExtra("title", subCategories.get(position).getName());
                    context.startActivity(intent);
                }else{
                    intent = new Intent(context, ProductListingActivity.class);
                    intent.putExtra("position", subCategories.get(position).getId());
                    intent.putExtra("title", subCategories.get(position).getName());
                    context.startActivity(intent);
                }
                // ((Activity) context).finish();
            }
        });
        ((HeaderViewHolder) holder).txt_parent_category.setText(subCategories.get(position).getName());
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout relativeMainLayout;
        TextView txt_parent_category;

        public HeaderViewHolder(View view) {
            super(view);
            txt_parent_category = (TextView) view.findViewById(R.id.txt_parent_category);
            relativeMainLayout = (RelativeLayout) view.findViewById(R.id.relativeMainLayout);
        }
    }

    @Override
    public int getItemCount() {
        return subCategories.size();
    }
}
