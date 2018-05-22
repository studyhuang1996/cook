package cn.studyhuang.cook.api;

import java.util.WeakHashMap;

import cn.studyhuang.cook.pojo.Menu;
import cn.studyhuang.cook.pojo.User;
import cn.studyhuang.cook.utils.JsonListResult;
import cn.studyhuang.cook.utils.JsonResult;
import cn.studyhuang.cook.utils.Result;
import retrofit2.Call;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by huang on 2018/1/15.
 */

public interface UserAPI {

    @GET("user/login/")
    Call<JsonResult<User>> getUser(@Query("userName") String userName, @Query("password") String password);

    @FormUrlEncoded
    @POST("user/save/")
    Call<Result>  saveUser(@FieldMap WeakHashMap<String,Object> params);

    /**
     * 查询自己发布的菜谱
     */
    @GET("my/menu/{userId}/")
    Call<JsonListResult<Menu>> findMenuByUserId(@Path("userId") Long userId);

    @GET("user/get/{uid}/")
    Call<JsonResult<User>> getUserInfo(@Path("uid") Long uid);

    @FormUrlEncoded
    @POST("my/user/pwd/")
    Call<Result> editPassword(@Field("uid") Long uid,@Field("upassword") String upassword);

    @FormUrlEncoded
    @POST("my/edit/user/")
    Call<Result> editUsers(@Field("uid") Long uid,@Field("uname") String uanme
                          , @Field("uemail") String uemail,@Field("udate") String udate);
}
