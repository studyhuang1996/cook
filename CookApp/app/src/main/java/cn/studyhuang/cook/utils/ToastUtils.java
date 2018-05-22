package cn.studyhuang.cook.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by huang on 2018/5/2.
 */

public class ToastUtils {

        public static void showToast(Context ctx, String text){
            Toast.makeText(ctx,text,Toast.LENGTH_SHORT).show();
        }
}
