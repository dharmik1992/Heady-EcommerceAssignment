
package com.app.headyecommerceapp.models;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Ranking extends RealmObject {
    @PrimaryKey
    @SerializedName("ranking")
    @Expose
    private String ranking;
    @SerializedName("products")
    @Expose
    private RealmList<RankingProduct> products = null;

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public RealmList<RankingProduct> getProducts() {
        return products;
    }

    public void setProducts(RealmList<RankingProduct> products) {
        this.products = products;
    }
}
