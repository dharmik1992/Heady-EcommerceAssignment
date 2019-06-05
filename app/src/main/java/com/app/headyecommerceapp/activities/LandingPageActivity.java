package com.app.headyecommerceapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.app.headyecommerceapp.R;
import com.app.headyecommerceapp.Utils.Loggers;
import com.app.headyecommerceapp.adapters.ParentCategoryAdapter;
import com.app.headyecommerceapp.customClasses.ProgressBarView;
import com.app.headyecommerceapp.interfaces.getCallBackResponce;
import com.app.headyecommerceapp.restApi.APIResponce;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.app.headyecommerceapp.interfaces.ApplicationConfig.GET;
import static com.app.headyecommerceapp.interfaces.ApplicationConfig.SUCCESS;
import static com.app.headyecommerceapp.interfaces.Urls.getCategoryResults;

public class LandingPageActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.headerTitle)
    TextView headerTitle;
    @BindView(R.id.progressbarview)
    ProgressBarView progressbarview;
    @BindView(R.id.recycler_view_parentCategory)
    RecyclerView recycler_view_parentCategory;
    LinearLayoutManager linearLayoutManager;
    ParentCategoryAdapter parentCategoryAdapter;
    APIResponce getAPIResponce;
    HashMap<String, Object> body = new HashMap<String, Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        headerTitle.setText("Shopify");

        SyncData();
    }


    public void SyncData() {
        progressbarview.setVisibility(View.VISIBLE);
        getAPIResponce = new APIResponce(this, getCategoryResults, "getCategoryResults", body, new getCallBackResponce() {
            @Override
            public void getCallBackResponce(int code, String message, String Tag, JSONObject jsonObject) {
                Loggers.D("jsonObject ", jsonObject + "");
                if (code == SUCCESS) {
                    setRecyclerView();
                    progressbarview.setVisibility(View.GONE);
                    try {
                        if (jsonObject.getJSONArray("categories").length() > 0) {

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Util.ShowSnackBar(btnLogin, LoginActivity.this, message);
                }
            }
        }, GET);
    }

    public void setRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        parentCategoryAdapter = new ParentCategoryAdapter(this);
        recycler_view_parentCategory.setLayoutManager(linearLayoutManager);
        recycler_view_parentCategory.setAdapter(parentCategoryAdapter);
    }
}
