package cn.studyhuang.cook.utils;

import android.util.Log;

import java.io.IOException;
import java.util.HashSet;
import java.util.prefs.Preferences;


import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by huang on 2018/2/4.
 */

public class ReadCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
    //    HashSet<String> preferences = (HashSet) Preferences.getDefaultPreferences().getStringSet(Preferences.PREF_COOKIES, new HashSet<>());
//        for (String cookie : preferences) {
//            builder.addHeader("Cookie", cookie);
//            Log.v("OkHttp", "Adding Header: " + cookie); // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
//        }

        return chain.proceed(builder.build());
    }
}
