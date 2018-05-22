package cn.studyhuang.cook.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PrefUtils {
    public static void setDefaults(String key, String value, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }
    public static String getDefaults(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }
    public static void set(String filename,String key, String value, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(filename,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }
    public static String get(String filename,String key, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(filename,Context.MODE_PRIVATE);
        return prefs.getString(key, null);
    }
    public static void remove(String filename,String key, Context context) {
        SharedPreferences prefs = context.getSharedPreferences(filename,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(key);
        editor.commit();
    }

}