package com.app.headyecommerceapp.realm.operation;




import com.app.headyecommerceapp.realm.pojo.DatabaseTable;

import io.realm.Realm;

/**
 * Created by USER on 03/10/2016.
 */

public class RealmQuery {

    public static void clearRealm(Realm realm, Class pojo) {
        realm.beginTransaction();
        realm.clear(pojo);
        realm.commitTransaction();
    }

    public static void clearAllRealm(Realm realm) {

        realm.beginTransaction();
        realm.clear(DatabaseTable.class);
        realm.commitTransaction();

    }

    public static boolean hasRealmRecords(Realm realm, Class pojo) {

        return !realm.allObjects(pojo).isEmpty();
    }

//    public static io.realm.RealmQuery<DatabaseTable> getValueByKey(Realm realm, String key, String value) {
//        io.realm.RealmQuery<DatabaseTable> databaseTable = realm.where(DatabaseTable.class).equalTo(key, value);
//        return databaseTable;
//    }

    public static String getValueByKey(Realm realm, String key, String value) {
        String json = realm.where(DatabaseTable.class).equalTo(key, value).findFirst().getJsonData();
        return json;
    }

    public static boolean deleteValueBykey(Realm realm, String key, String value) {
//        try {
            realm.beginTransaction();
            realm.where(DatabaseTable.class).equalTo(key, value).findFirst().removeFromRealm();
            realm.commitTransaction();
//        } catch (Exception e) {
//            e.printStackTrace();
////            realm.commitTransaction();
//        }
        return true;
    }
//    public static boolean deleteValueByKey(Realm realm, String key, String value) {
//        try {
//            realm.beginTransaction();
//            DatabaseTable databaseTable = realm.where(DatabaseTable.class).equalTo(key, value).findFirst();
//            databaseTable.removeFromRealm();
//            realm.commitTransaction();
//        } catch (Exception e) {
//            e.printStackTrace();
//            Loggers.D("exceptions ", e.getMessage());
//        } finally {
//            realm.commitTransaction();
//        }
//        return true;
//    }
}
