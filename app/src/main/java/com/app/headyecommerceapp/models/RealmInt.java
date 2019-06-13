package com.app.headyecommerceapp.models;

import io.realm.RealmObject;

/**
 * Created by user on 07/06/2019.
 */

public class RealmInt extends RealmObject {
    private int val;

    public RealmInt() {
    }

    public RealmInt(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}
