package cn.studyhuang.cook.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import cn.studyhuang.cook.api.RetrofitAPI;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by huang on 2018/5/17.
 */

public class UploadRetrofit {

    /**
     * 超时时间60s
     */
    private static final long DEFAULT_TIMEOUT = 60;
    private volatile static UploadRetrofit mInstance;
    public Retrofit mRetrofit;
    public RetrofitAPI mRetrofitAPI;
    private static Map<String, RequestBody> params;
    String boundary = "---------------------------823928434";
    String end = "\r\n";
    String twoHyphens = "--";

    private UploadRetrofit() {// .client(genericClient())
       // TODO
        mRetrofit =RetrofitUtils.getRetrofit();
//                new Retrofit.Builder()
//                .baseUrl("http://192.168.2.101:8080/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .build();

        mRetrofitAPI = mRetrofit.create(RetrofitAPI.class);
    }

    public static UploadRetrofit getInstance() {
        if (mInstance == null) {
            synchronized (UploadRetrofit.class) {
                if (mInstance == null)
                    mInstance = new UploadRetrofit();
                params = new HashMap<String, RequestBody>();
            }
        }
        return mInstance;
    }

//    /**
//     * 添加统一超时时间,http日志打印
//     *
//     * @return
//     */
//    private OkHttpClient genericClient() {
//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient httpClient = new OkHttpClient.Builder()
//                .addInterceptor(logging)
//                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
//                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
//                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
//                .build();
//        return httpClient;
//    }



    /**
     * 添加参数
     * 根据传进来的Object对象来判断是String还是File类型的参数
     */
    public UploadRetrofit addParameter(String key, Object o) {

        if (o instanceof String) {
            RequestBody body = RequestBody.create(MediaType.parse("text/plain;charset=UTF-8"), (String) o);
            params.put(key, body);
        } else if (o instanceof File) {
            RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data;charset=UTF-8 "), (File) o);
            params.put(key + "\"; filename=\"" + ((File) o).getName() + "", body);
        }
        return this;
    }

    /**
     * 构建RequestBody
     */
    public Map<String, RequestBody> bulider() {

        return params;
    }

    public void clear(){
        params.clear();
    }
}
