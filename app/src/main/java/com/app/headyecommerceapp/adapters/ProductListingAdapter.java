package com.app.headyecommerceapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.headyecommerceapp.R;
import com.app.headyecommerceapp.activities.ProductListingActivity;
import com.app.headyecommerceapp.models.Product;
import com.app.headyecommerceapp.models.Variant;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import io.realm.RealmList;


public class ProductListingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<Product> products;

    public ProductListingAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_product_item, parent, false);
        return new HeaderViewHolder(view);
    }

    Set<String> avialblecolors = new HashSet<>();
    Set<Integer> availableSize = new HashSet<>();


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ((HeaderViewHolder) holder).txt_parent_category.setText(products.get(position).getName());

        for (int i = 0; i < products.get(position).getVariants().size(); i++) {
            avialblecolors.add(products.get(position).getVariants().get(i).getColor());
        }
        Iterator avialblecolorsIterator = avialblecolors.iterator();
        String colors = "";
        while (avialblecolorsIterator.hasNext()) {
            String color = avialblecolorsIterator.next().toString();
            colors += "  " + color;
        }
        for (int i = 0; i < products.get(position).getVariants().size(); i++) {
            availableSize.add(products.get(position).getVariants().get(i).getSize());
        }

        Iterator iter = availableSize.iterator();
        String sizes = "";
        while (iter.hasNext()) {
            String size = iter.next().toString();
            sizes += "  " + size;
        }

        ((HeaderViewHolder) holder).txt_colors.setText(colors);
        ((HeaderViewHolder) holder).txt_size.setText(sizes);
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        TextView txt_colors, txt_size, txt_parent_category;

        public HeaderViewHolder(View view) {
            super(view);
            txt_parent_category = (TextView) view.findViewById(R.id.txt_parent_category);
            txt_colors = (TextView) view.findViewById(R.id.txt_colors);
            txt_size = (TextView) view.findViewById(R.id.txt_size);
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
