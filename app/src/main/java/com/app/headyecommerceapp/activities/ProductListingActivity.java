package com.app.headyecommerceapp.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.app.headyecommerceapp.R;
import com.app.headyecommerceapp.adapters.ProductListingAdapter;
import com.app.headyecommerceapp.customClasses.ProgressBarView;
import com.app.headyecommerceapp.models.Product;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ProductListingActivity extends ParentActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.headerTitle)
    TextView headerTitle;
    @BindView(R.id.progressbarview)
    ProgressBarView progressbarview;
    @BindView(R.id.recycler_view_parentCategory)
    RecyclerView recycler_view_parentCategory;
    LinearLayoutManager linearLayoutManager;
    ProductListingAdapter productListingAdapter;
    int categoryId;
    String title;
    List<Product> products;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        categoryId = getIntent().getIntExtra("position", 0);
        title = getIntent().getStringExtra("title");
        headerTitle.setText(title);
        progressbarview.setVisibility(View.GONE);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        products = realmQuery.getProducts(categoryId, realm);
        setRecyclerView(products);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item1:
                List<Product> most_viewed_products = realmQuery.sortProducts(realm, "Most Viewed Products", categoryId);
                setRecyclerView(most_viewed_products);
                return true;
            case R.id.item2:
                List<Product> most_ordered_products = realmQuery.sortProducts(realm, "Most OrdeRed Products", categoryId);
                setRecyclerView(most_ordered_products);
                return true;
            case R.id.item3:
                List<Product> most_shared_product = realmQuery.sortProducts(realm, "Most ShaRed Products", categoryId);
                setRecyclerView(most_shared_product);
                return true;
            case R.id.item4:
                setRecyclerView(products);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setRecyclerView(List<Product> products) {
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        productListingAdapter = new ProductListingAdapter(this, products);
        recycler_view_parentCategory.setLayoutManager(linearLayoutManager);
        recycler_view_parentCategory.setAdapter(productListingAdapter);

    }
}
