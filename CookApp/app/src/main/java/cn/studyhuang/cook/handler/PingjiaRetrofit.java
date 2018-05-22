package cn.studyhuang.cook.handler;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import cn.studyhuang.cook.api.MenuAPI;
import cn.studyhuang.cook.pojo.Evaluate;
import cn.studyhuang.cook.utils.Constant;
import cn.studyhuang.cook.utils.JsonListResult;
import cn.studyhuang.cook.utils.RetrofitUtils;
import cn.studyhuang.cook.view.PingjiaView;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by huang on 2018/5/2.
 */

public class PingjiaRetrofit {

    private PingjiaView mPingjiaView;
    private Handler mHandler;
   Gson  gson = new GsonBuilder().create();

    public PingjiaRetrofit( PingjiaView mPingjiaView) {
        this.mPingjiaView = mPingjiaView;
        mHandler = new Handler(Looper.getMainLooper());
    }

    public void getComment(final Long mid) {

     //   Retrofit retrofit = RetrofitUtils.getRetrofit();

        Retrofit retrofit =RetrofitUtils.getRetrofit();

        MenuAPI menuAPI = retrofit.create(MenuAPI.class);


     //   Call<JsonListResult<Evaluate>> call = menuAPI.getEvalutes(mid);
//        call.enqueue(new Callback<JsonListResult<Evaluate>>() {
//            @Override
//            public void onResponse(Call<JsonListResult<Evaluate>> call, retrofit2.Response<JsonListResult<Evaluate>> response) {
//                if (response.body().isSuccee()){
//                    try {
//                        final JsonListResult<Evaluate> evaluate = gson.fromJson(response.body().toString(),
//                                new TypeToken<JsonListResult<Evaluate>>() {
//                                }.getType());
//                        mHandler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                mPingjiaView.onGetComment(evaluate.getData().get(1));
//                            }
//                        });
//                    }catch (JsonSyntaxException e){
//                        mPingjiaView.onFailed(response.code());
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<JsonListResult<Evaluate>> call, Throwable t) {
//
//            }
//        });


    }
}
