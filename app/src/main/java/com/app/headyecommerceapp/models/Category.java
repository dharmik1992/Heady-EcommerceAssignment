
package com.app.headyecommerceapp.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Category extends RealmObject{
    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("products")
    @Expose
    private RealmList<Product> products = null;
    @SerializedName("child_categories")
    @Expose
    private RealmList<Integer> childCategories = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public RealmList<Integer> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(RealmList<Integer> childCategories) {
        this.childCategories = childCategories;
    }

    public void setName(String name) {
        this.name = name;
    }


    public RealmList<Product> getProducts() {
        return products;
    }

    public void setProducts(RealmList<Product> products) {
        this.products = products;
    }
}
