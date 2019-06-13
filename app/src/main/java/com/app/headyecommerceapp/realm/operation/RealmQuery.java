package com.app.headyecommerceapp.realm.operation;


import com.app.headyecommerceapp.models.ApiResponseData;
import com.app.headyecommerceapp.models.Category;
import com.app.headyecommerceapp.models.Product;
import com.app.headyecommerceapp.models.Ranking;
import com.app.headyecommerceapp.models.RankingProduct;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;


public class RealmQuery {

    public void saveDataToRealm(ApiResponseData apiResponseData, Realm realm) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(apiResponseData);
        realm.commitTransaction();
    }

    public RealmResults<Category> getParentCategory(Realm realm) {
        RealmResults<Category> parentCategories = realm.where(Category.class).findAll().where().
                in("name", new String[]{"Mens Wear", "Electronics"}).findAll();
        return parentCategories;
    }

    public static RealmList<Category> getChildCategory(int parentID, Realm realm) {
        RealmList<Category> childCategories = new RealmList<>();
        RealmResults<Category> parentCategory = realm.where(Category.class).findAll().where().equalTo("id", parentID).findAll();
        for (Iterator<Integer> iter = parentCategory.get(0).getChildCategories().iterator(); iter.hasNext(); ) {
            Integer element = iter.next();
            childCategories.add(realm.where(Category.class).findAll().where().equalTo("id", element).findFirst());
        }
        return childCategories;
    }


    public List<Product> getProducts(int categoryId, Realm realm) {
        RealmResults<Category> parentCategory = realm.where(Category.class).findAll().where().equalTo("id", categoryId).findAll();
        RealmList<Product> products = parentCategory.get(0).getProducts();
        return products;
    }

    public boolean hasMoreCategories(int parentID, Realm realm) {
        RealmResults<Category> parentCategory = realm.where(Category.class).findAll().where().equalTo("id", parentID).findAll();
        if (parentCategory.get(0).getChildCategories().size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public RealmList<RankingProduct> getMostViewedProductd(Realm realm, String query) {
        RealmResults<Ranking> rankingProducts = realm.where(Ranking.class).findAll().where().equalTo("ranking", query).findAll();
        RealmList<RankingProduct> productsRanking = rankingProducts.get(0).getProducts();
        productsRanking.get(0).getId();
        return productsRanking;
    }

    public List<Product> sortProducts(Realm realm, String query, int CategoryId) {
        List<RankingProduct> rankingProducts = getMostViewedProductd(realm, query);
        List<Product> products = getProducts(CategoryId, realm);
        List<Product> newProductsList = new RealmList<>();
        for (int i = 0; i < products.size(); i++) {
            Product p = new Product();
            p.setId(products.get(i).getId());
            p.setName(products.get(i).getName());
            p.setTax(products.get(i).getTax());
            p.setVariants(products.get(i).getVariants());
            for (int j = 0; j < rankingProducts.size(); j++) {
                if (products.get(i).getId() == rankingProducts.get(j).getId()) {
                    if (query.equalsIgnoreCase("Most Viewed Products")) {
                        p.setViewCount(rankingProducts.get(j).getViewCount());
                    } else if (query.equalsIgnoreCase("Most ShaRed Products")) {
                        p.setShares(rankingProducts.get(j).getShares());
                    } else if (query.equalsIgnoreCase("Most OrdeRed Products")) {
                        p.setOrder_count(rankingProducts.get(j).getOrderCount());
                    }
                    break;
                } else {
                    p.setViewCount(0);
                    p.setShares(0);
                    p.setOrder_count(0);
                }
            }
            newProductsList.add(p);
        }
        if (query.equalsIgnoreCase("Most Viewed Products")) {
            Collections.sort(newProductsList, new SortByViewCount());
        } else if (query.equalsIgnoreCase("Most ShaRed Products")) {
            Collections.sort(newProductsList, new SortByShareCount());
        } else if (query.equalsIgnoreCase("Most OrdeRed Products")) {
            Collections.sort(newProductsList, new SortByOrderCount());
        }

        return newProductsList;

    }

    class SortByViewCount implements Comparator<Product> {

        @Override
        public int compare(Product product, Product t1) {
            return t1.getViewCount() - product.getViewCount();
        }
    }

    class SortByOrderCount implements Comparator<Product> {

        @Override
        public int compare(Product product, Product t1) {
            return t1.getOrder_count() - product.getOrder_count();
        }
    }

    class SortByShareCount implements Comparator<Product> {

        @Override
        public int compare(Product product, Product t1) {
            return t1.getShares() - product.getShares();
        }
    }

    public static boolean hasRealmRecords(Realm realm) {

        return realm.isEmpty();
    }


}
