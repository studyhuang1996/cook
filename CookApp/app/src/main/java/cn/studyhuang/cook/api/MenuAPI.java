package cn.studyhuang.cook.api;

import java.util.List;
import java.util.WeakHashMap;

import cn.studyhuang.cook.pojo.Evaluate;
import cn.studyhuang.cook.pojo.Material;
import cn.studyhuang.cook.pojo.Menu;

import cn.studyhuang.cook.pojo.MenuDetails;
import cn.studyhuang.cook.pojo.Step;
import cn.studyhuang.cook.utils.JsonListResult;
import cn.studyhuang.cook.utils.JsonResult;
import cn.studyhuang.cook.utils.Result;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by huang on 2018/1/13.
 */

public interface MenuAPI {
    @GET("api/menu/list/")
    Call<JsonListResult<Menu>> getMenus();
    @GET("menu/listmenus/")
    Call<JsonListResult<Menu>>  getMenusByName(@Query("querys") String querys);

   @GET("menu/get/{mid}/")
    Call<JsonResult<Menu>>  getMenu(@Path("mid") Long mid);

    @GET("material/getByMid/{mid}/")
    Call<JsonListResult<Material>> getMaterials(@Path("mid") Long mid);
    @GET("step/getStepsByMid/{mid}/")
    Call<JsonListResult<Step>> getSteps(@Path("mid") Long mid);

   @GET("menu/list/{cid}/")
    Call<JsonListResult<Menu>> getMenuByCid(@Path("cid") Long cid);
  // @GET("menu/get/{mid}/")
  //  Call<JsonListResult<MenuDetails>>  getMenu(@Path("mid") Long mid);

    /**
     * 获取评论列表
     * @param mid
     * @return
     */
    @GET("api/commond/{mid}/")
    Call<JsonListResult<Evaluate>> getEvalutes(@Path("mid") Long mid);

    @FormUrlEncoded
    @POST("api/evaluate/save/")
    Call<Result>  saveEvaluate(@FieldMap WeakHashMap<String,Object> params);

    @GET("api/reply/{replyId}/")
    Call<JsonListResult<Evaluate>> getReplys(@Path("replyId") Long replyId);
}