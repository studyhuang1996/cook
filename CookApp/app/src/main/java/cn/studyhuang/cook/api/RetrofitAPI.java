package cn.studyhuang.cook.api;



import java.util.Map;

import cn.studyhuang.cook.pojo.Category;
import cn.studyhuang.cook.pojo.Evaluate;
import cn.studyhuang.cook.utils.UploadResult;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by huang on 2018/1/12.
 */

public interface RetrofitAPI {

    @GET("api/category/list/")
    Call<Category> getCall();

    /**
     * 评论列表
     * @return
     */
    @GET("api/evaluate/{mid}/")
    Call<Evaluate> getEvaluate();

    /**
     * 图片上传
     *
     */
    @Multipart
    @POST("upload/api/addmenu/")
    Call<UploadResult> upload(@PartMap Map<String, RequestBody> params);



}
