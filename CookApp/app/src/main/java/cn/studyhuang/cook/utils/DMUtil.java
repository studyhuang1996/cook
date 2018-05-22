package cn.studyhuang.cook.utils;

import android.content.Context;

/**
 * Created by huang on 2018/4/26.
 */
public class DMUtil {
    public static int getScale(Context context, int num) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (num * scale + 0.5f);
    }
}
