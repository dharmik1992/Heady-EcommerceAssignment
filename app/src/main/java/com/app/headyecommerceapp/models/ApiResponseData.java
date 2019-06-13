
package com.app.headyecommerceapp.models;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ApiResponseData extends RealmObject {
    @PrimaryKey
    private Integer id=1;

    @SerializedName("categories")
    @Expose
    private RealmList<Category> categories = null;
    @SerializedName("rankings")
    @Expose
    private RealmList<Ranking> rankings = null;

    public RealmList<Category> getCategories() {
        return categories;
    }

    public void setCategories(RealmList<Category> categories) {
        this.categories = categories;
    }

    public RealmList<Ranking> getRankings() {
        return rankings;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRankings(RealmList<Ranking> rankings) {
        this.rankings = rankings;
    }
}
