package com.app.headyecommerceapp.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.app.headyecommerceapp.R;
import com.app.headyecommerceapp.adapters.SubCategoryAdapter;
import com.app.headyecommerceapp.customClasses.ProgressBarView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 05/06/2019.
 */

public class SubCategoryActivty extends ParentActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.headerTitle)
    TextView headerTitle;
    @BindView(R.id.progressbarview)
    ProgressBarView progressbarview;
    @BindView(R.id.recycler_view_parentCategory)
    RecyclerView recycler_view_parentCategory;
    GridLayoutManager gridLayoutManager;
    SubCategoryAdapter subCategoryAdapter;
    int categoryId;
    String title;
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
        setRecyclerView();
    }

    public void setRecyclerView() {
        gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        subCategoryAdapter = new SubCategoryAdapter(this, realmQuery.getChildCategory(categoryId, realm));
        recycler_view_parentCategory.setLayoutManager(gridLayoutManager);
        recycler_view_parentCategory.setAdapter(subCategoryAdapter);
    }
}
