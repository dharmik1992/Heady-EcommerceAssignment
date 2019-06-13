
package com.app.headyecommerceapp.models;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Product extends RealmObject{
    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("date_added")
    @Expose
    private String dateAdded;
    @SerializedName("variants")
    @Expose
    private RealmList<Variant> variants = null;
    @SerializedName("tax")
    @Expose
    private Tax tax;
    private int viewCount;
    private int order_count;
    private int shares;

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getOrder_count() {
        return order_count;
    }

    public void setOrder_count(int order_count) {
        this.order_count = order_count;
    }

    public int getShares() {
        return shares;
    }


    public void setShares(int shares) {
        this.shares = shares;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public RealmList<Variant> getVariants() {
        return variants;
    }

    public void setVariants(RealmList<Variant> variants) {
        this.variants = variants;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateAdded='" + dateAdded + '\'' +
                ", variants=" + variants +
                ", tax=" + tax +
                ", viewCount=" + viewCount +
                ", order_count=" + order_count +
                ", shares=" + shares +
                '}';
    }
}
