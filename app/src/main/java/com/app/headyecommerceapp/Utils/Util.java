package com.app.headyecommerceapp.Utils;

import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.headyecommerceapp.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Collection;


public class Util {

    public static int prePosition = -1;

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null;
    }


    public static void ShowToast(Context context, String Message) {
        Toast.makeText(context, Message, Toast.LENGTH_SHORT).show();
    }


    public static void ShowSnackBar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }


    public static String printRawResponse(Object response) {
        String data = Util.gson().toJson(response);
        Loggers.D("RawData ", "   " + data);
        return data;

    }


    public static void ShowSnackBar(View view, Context context, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);

        View snackbarView = snackbar.getView();

        int snackbarTextId = android.support.design.R.id.snackbar_text;
        TextView textView = (TextView) snackbarView.findViewById(snackbarTextId);
        textView.setTextColor(context.getResources().getColor(R.color.white));
        snackbar.show();
    }


    public static Gson gson() {
        return
                new GsonBuilder().serializeNulls()
                        .registerTypeHierarchyAdapter(
                                Collection.class, new CollectionAdapter())
                        .create();
    }

    public static class CollectionAdapter implements JsonSerializer<Collection<?>> {
        @Override
        public JsonElement serialize(Collection<?> src, Type typeOfSrc,
                                     JsonSerializationContext context) {
            if (src == null || src.isEmpty())
                return null;

            JsonArray array = new JsonArray();

            for (Object child : src) {
                JsonElement element = context.serialize(child);
                array.add(element);
            }

            return array;
        }
    }


    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }


}