package cn.studyhuang.cook.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;

import java.util.List;

import cn.studyhuang.cook.Adapter.MenuListAdapter;
import cn.studyhuang.cook.MyApplication;
import cn.studyhuang.cook.R;
import cn.studyhuang.cook.api.MenuAPI;
import cn.studyhuang.cook.api.RetrofitAPI;
import cn.studyhuang.cook.pojo.Menu;
import cn.studyhuang.cook.utils.Constant;
import cn.studyhuang.cook.utils.JsonListResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SearchCateActivity extends AppCompatActivity {

    protected static final String TAG="SearchCateActivity";
    private StaggeredGridView menuListView;

    private MenuListAdapter menuListAdapter;

    private List<Menu> mMenuList=null;

    private SwipeRefreshLayout sr;

    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cook);
        //瀑布流控件
        menuListView= (StaggeredGridView) findViewById(R.id.channel_dish_list);
        //下拉刷新
        sr = (SwipeRefreshLayout) findViewById(R.id.sr);
        progressBar =(ProgressBar) findViewById(R.id.progress_bar);
        Intent intent =getIntent();
        String cid = intent.getStringExtra("cateInfo");
        requestMenuByMid(cid);
       Toast.makeText(MyApplication.getContext(),"shdjshfus"+cid,Toast.LENGTH_SHORT).show();
    }


    public  void requestMenuByMid(String cid){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        MenuAPI request= retrofit.create(MenuAPI.class);

        Call<JsonListResult<Menu>> call= request.getMenuByCid(Long.valueOf(cid.trim()));
      call.enqueue(new Callback<JsonListResult<Menu>>() {
          @Override
          public void onResponse(Call<JsonListResult<Menu>> call, Response<JsonListResult<Menu>> response) {
              JsonListResult<Menu> jsonResult =response.body();
              mMenuList = response.body().getData();

              if (jsonResult.isSuccee() && mMenuList != null) {
                  Log.e(TAG,"===============");
                  progressBar.setVisibility(View.GONE);
                  menuListAdapter = new MenuListAdapter(MyApplication.getContext(), mMenuList);
                  menuListAdapter.setOnItemClickListener(new MenuListAdapter.MyItemClickListener() {
                      @Override
                      public void onItemClick(View view, int postion) {
                          Intent intent = new Intent(SearchCateActivity.this, MenusActivity.class);
                          Log.e(TAG,"==="+menuListAdapter.getMenus(postion).getMid());
                          intent.putExtra("menu", menuListAdapter.getMenus(postion).getMid()+" ");
                          startActivity(intent);
                      }
                  });
                  menuListView.setAdapter(menuListAdapter);
              } else {
                  Toast.makeText(MyApplication.getContext(), "获取菜肴信息失败" + response.body().toString(), Toast.LENGTH_LONG).show();
              }

          }

          @Override
          public void onFailure(Call<JsonListResult<Menu>> call, Throwable t) {

          }
      });
    }
}
