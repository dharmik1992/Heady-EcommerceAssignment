package com.app.headyecommerceapp.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.app.headyecommerceapp.realm.operation.RealmQuery;

import io.realm.Realm;

/**
 * Created by user on 05/06/2019.
 */

public class ParentActivity extends AppCompatActivity {
     Realm realm;
    RealmQuery realmQuery;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        realmQuery = new RealmQuery();
        realm = Realm.getDefaultInstance();
    }
}
