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

import java.util.List;

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

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ((HeaderViewHolder) holder).txt_parent_category.setText(products.get(position).getName());
//        for (int i = 0; i < products.get(position).getVariants().size(); i++) {
//            addradiobuttonview(i, products.get(position).getVariants().get(i).getColor(), products.get(position).getVariants().get(i).toString(),
//                    ((HeaderViewHolder) holder).rd_color_variant);
//        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder {

        TextView txt_parent_category;
        TextView txt_price;
        RadioGroup rd_color_variant;

        public HeaderViewHolder(View view) {
            super(view);
            rd_color_variant = (RadioGroup) view.findViewById(R.id.rd_color_variant);
            txt_parent_category = (TextView) view.findViewById(R.id.txt_parent_category);
            txt_price = (TextView) view.findViewById(R.id.txt_price);
        }
    }

    @SuppressLint("NewApi")
    public void addradiobuttonview(final int id, final String text, final String tag, final RadioGroup radioGroup) {
        AppCompatRadioButton rdbtn = new AppCompatRadioButton(context);
        rdbtn.setButtonTintList(ColorStateList.valueOf(context.getColor(R.color.colorPrimary)));
        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 25, 25);
        rdbtn.setTextSize(16);
        rdbtn.setLayoutParams(params);
        rdbtn.setId(id);
        rdbtn.setText(text);
        rdbtn.setTag(tag);
        radioGroup.addView(rdbtn);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioBtn = (RadioButton) group.findViewById(checkedRadioButtonId);
//                Variant variantList = new Gson().fromJson(radioBtn.getTag().toString(), Variant.class);
//                ((HeaderViewHolder) holder).txt_price.setText(variantList.getPrice());
//                Toast.makeText(context, radioBtn.getText(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
