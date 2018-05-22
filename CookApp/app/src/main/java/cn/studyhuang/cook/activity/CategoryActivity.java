package cn.studyhuang.cook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.studyhuang.cook.Adapter.CategoryAdapter;
import cn.studyhuang.cook.R;
import cn.studyhuang.cook.api.RetrofitAPI;
import cn.studyhuang.cook.pojo.Category;
import cn.studyhuang.cook.utils.Constant;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class CategoryActivity extends AppCompatActivity {

    private CategoryAdapter mCategoryAdapter;
    private  RecyclerView recyclerView;
    private List<Category.DataBean> mCategorie= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
         recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        new Thread(new Runnable() {
            @Override
            public void run() {
                requests();
            }
        }).start();
        if (mCategorie == null){
          finish();
        }

    }


    public  void  requests(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        RetrofitAPI request= retrofit.create(RetrofitAPI.class);

        Call<Category> call=request.getCall();
        Log.e("CategoryActivity",call.request().url().toString());
        if (call==null)
        {
            Toast.makeText(CategoryActivity.this,"存储",Toast.LENGTH_SHORT).show();
        }
        call.enqueue(new Callback<Category>() {

            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {

                // 步骤7：处理返回的数据结果
                Gson gson = new Gson();
                Log.e("login",response.body().getMsg());
                mCategorie = response.body().getData();
              //gson.fromJson(response.body().toString(), Category.class);
              //  Log.e("logins",mCategorie.getCode()+"");
                for (Category.DataBean category : mCategorie){
                    Log.d("login",category.getCname());

                }
                mCategoryAdapter = new CategoryAdapter(mCategorie);
                recyclerView.setAdapter(mCategoryAdapter);
                mCategoryAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(CategoryActivity.this,"position"+mCategoryAdapter.getCates(position).getCid(),Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CategoryActivity.this, SearchCateActivity.class);
                        intent.putExtra("cateInfo", mCategoryAdapter.getCates(position).getCid()+" ");
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {

                Toast.makeText(CategoryActivity.this,"获取数据失败",Toast.LENGTH_SHORT).show();
            }
        });


    }

}
