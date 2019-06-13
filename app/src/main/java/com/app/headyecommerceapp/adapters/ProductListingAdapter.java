package com.app.headyecommerceapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.headyecommerceapp.R;
import com.app.headyecommerceapp.models.Product;

import java.util.List;


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

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

//        ((HeaderViewHolder) holder).relativeMainLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, CategoryActivity.class);
//                intent.putExtra("position", position);
//                context.startActivity(intent);
//               // ((Activity) context).finish();
//            }
//        });
        ((HeaderViewHolder) holder).txt_parent_category.setText(products.get(position).getName());
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearMainLayout;
        TextView txt_parent_category;

        public HeaderViewHolder(View view) {
            super(view);
            txt_parent_category = (TextView) view.findViewById(R.id.txt_parent_category);
            linearMainLayout = (LinearLayout) view.findViewById(R.id.linearMainLayout);
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
