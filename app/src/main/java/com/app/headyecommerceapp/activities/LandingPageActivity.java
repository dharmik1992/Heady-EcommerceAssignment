package com.app.headyecommerceapp.activities;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.headyecommerceapp.R;
import com.app.headyecommerceapp.Utils.Loggers;
import com.app.headyecommerceapp.Utils.Util;
import com.app.headyecommerceapp.adapters.ParentCategoryAdapter;
import com.app.headyecommerceapp.customClasses.ProgressBarView;
import com.app.headyecommerceapp.interfaces.getCallBackResponce;
import com.app.headyecommerceapp.models.ApiResponseData;
import com.app.headyecommerceapp.restApi.APIResponce;

import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.app.headyecommerceapp.interfaces.ApplicationConfig.GET;
import static com.app.headyecommerceapp.interfaces.ApplicationConfig.SUCCESS;
import static com.app.headyecommerceapp.interfaces.Urls.getCategoryResults;

public class LandingPageActivity extends ParentActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.img_default)
    ImageView img_default;
    @BindView(R.id.headerTitle)
    TextView headerTitle;
    @BindView(R.id.txt_title)
    TextView txt_title;
    @BindView(R.id.progressbarview)
    ProgressBarView progressbarview;
    @BindView(R.id.recycler_view_parentCategory)
    RecyclerView recycler_view_parentCategory;
    GridLayoutManager gridLayoutManager;
    ParentCategoryAdapter parentCategoryAdapter;

    APIResponce getAPIResponce;
    HashMap<String, Object> body = new HashMap<String, Object>();
    ApiResponseData apiResponseData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        progressbarview.setVisibility(View.GONE);
        txt_title.setVisibility(View.VISIBLE);
        img_default.setVisibility(View.VISIBLE);
        headerTitle.setText("Shopify");

        if (realm.isEmpty()) {
            SyncData();
        } else {
            setRecyclerView();
        }
    }


    public void SyncData() {
        progressbarview.setVisibility(View.VISIBLE);
        getAPIResponce = new APIResponce(this, getCategoryResults, "getCategoryResults", body, new getCallBackResponce() {
            @Override
            public void getCallBackResponce(int code, String message, String Tag, JSONObject jsonObject) {
                Loggers.D("jsonObject ", jsonObject + "");
                if (code == SUCCESS) {
                    progressbarview.setVisibility(View.GONE);
                    apiResponseData = Util.gson().fromJson(jsonObject.toString(), ApiResponseData.class);
                    realmQuery.saveDataToRealm(apiResponseData, realm);
                    setRecyclerView();
                } else {
                    // Util.ShowSnackBar(btnLogin, LoginActivity.this, message);
                }
            }
        }, GET);
    }


    public void setRecyclerView() {
        gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        parentCategoryAdapter = new ParentCategoryAdapter(this, realmQuery.getParentCategory(realm));
        recycler_view_parentCategory.setLayoutManager(gridLayoutManager);
        recycler_view_parentCategory.setAdapter(parentCategoryAdapter);
    }
}
