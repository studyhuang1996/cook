package cn.studyhuang.cook.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import cn.studyhuang.cook.MyApplication;
import cn.studyhuang.cook.activity.CooksActivity;
import cn.studyhuang.cook.activity.LoginActivity;

/**
 * Created by huang on 2018/5/18.
 */

public class SPUtils {

    public  static  SharedPreferences sharedPreferences = MyApplication.getContext()
            .getSharedPreferences("data",Context.MODE_PRIVATE);

    public static SharedPreferences getInstance(){

        return sharedPreferences;
    }
    public static void gotoLogin(Context context){
        if (SPUtils.sharedPreferences == null){
            Intent login = new Intent(context, LoginActivity.class);
            context.startActivity(login);

            return ;
        }
    }
}


