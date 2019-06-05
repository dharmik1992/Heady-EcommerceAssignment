package com.app.headyecommerceapp.Utils;

import android.util.Log;

public class Loggers {
    // Common function created to make LOGS enable/disable
    public static void I(String tag, String value) {
        if (Constants.DEBUG) {
            Log.i(tag, value);
        }
    }

    public static void E(String tag, String value) {
        if (Constants.DEBUG) {
            Log.e(tag, value);
        }
    }

    public static void D(String tag, String value) {
        if (Constants.DEBUG) {
            Log.d(tag, value);
        }
    }

    public static void D(String tag, String value, String value1) {
        if (Constants.DEBUG) {
            Log.d(tag, value + "  " + value1);
        }
    }

    public static void V(String tag, String value) {
        if (Constants.DEBUG) {
            Log.v(tag, value);
        }
    }

    public static void W(String tag, String value) {
        if (Constants.DEBUG) {
            Log.w(tag, value);
        }
    }
}
