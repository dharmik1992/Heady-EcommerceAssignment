package com.app.headyecommerceapp.realm.pojo;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Dharmik on 3/3/2017.
 */

public class DatabaseTable extends RealmObject {
    @PrimaryKey
    private String key;
    private String JsonData;

    public String getJsonData() {
        return JsonData;
    }

    public void setJsonData(String jsonData) {
        JsonData = jsonData;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}


