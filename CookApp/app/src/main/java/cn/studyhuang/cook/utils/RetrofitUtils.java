package cn.studyhuang.cook.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.WeakHashMap;

import cn.studyhuang.cook.MyApplication;
import cn.studyhuang.cook.activity.CooksActivity;
import cn.studyhuang.cook.activity.MenusActivity;
import cn.studyhuang.cook.api.MenuAPI;
import cn.studyhuang.cook.pojo.Evaluate;
import cn.studyhuang.cook.pojo.Menu;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by huang on 2018/1/23.
 */

public class RetrofitUtils {


    public static  Retrofit getRetrofit(){
      return  new Retrofit.Builder()
                .baseUrl(Constant.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }


    /**
     * 回复评论
     * @param evaluate
     */
    public static void saveReply(Evaluate evaluate){
        //TODO:  RetrofitUtils.getRetrofit();

        Retrofit retrofit =  RetrofitUtils.getRetrofit();

        MenuAPI menuAPI  =retrofit.create(MenuAPI.class);
        WeakHashMap<String,Object> maps = new WeakHashMap<>();
        maps.put("replyId",evaluate.getReplyId());
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences("data", Context.MODE_PRIVATE);
        String userId= (String) sharedPreferences.getString("userId","");
        maps.put("userId",userId);
        maps.put("evaluateContent",evaluate.getEvaluateContent());
        maps.put("mid",evaluate.getMid());
        if (evaluate.getScore() != null){
            maps.put("score",evaluate.getScore());
        }
         maps.put("score",4.8);
        Call<Result> resultCall = menuAPI.saveEvaluate(maps);

        resultCall.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.body().getCode()==0) {
                    ToastUtils.showToast(MyApplication.getContext(),"回复成功" );
                    Intent intent = new Intent(MyApplication.getContext(),MenusActivity.class);
                    MyApplication.getContext().startActivity(intent);
                  //  return;
                }else{
                    ToastUtils.showToast(MyApplication.getContext(),"回复失败" );
                    Intent intent = new Intent(MyApplication.getContext(),MenusActivity.class);
                    MyApplication.getContext().startActivity(intent);
                    return;
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                ToastUtils.showToast(MyApplication.getContext(),"回复评论失败");
                Intent intent  = new Intent(MyApplication.getContext(),CooksActivity.class);
                MyApplication.getContext().startActivity(intent);
            }
        });
    }

}
