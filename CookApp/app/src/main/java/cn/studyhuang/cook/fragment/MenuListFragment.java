package cn.studyhuang.cook.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import cn.studyhuang.cook.Adapter.MenuListAdapter;
import cn.studyhuang.cook.R;
import cn.studyhuang.cook.activity.MenusActivity;
import cn.studyhuang.cook.api.MenuAPI;
import cn.studyhuang.cook.pojo.Menu;
import cn.studyhuang.cook.utils.Constant;
import cn.studyhuang.cook.utils.HttpUtils;
import cn.studyhuang.cook.utils.JsonListResult;
import cn.studyhuang.cook.utils.JsonResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 *
 */
public class MenuListFragment extends Fragment {
    protected static final String TAG="MenuFragment";

    private StaggeredGridView menuListView;

    private MenuListAdapter dishListAdapter;

    private List<Menu> mMenuList=null;

    private SwipeRefreshLayout sr;

    private ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(
                R.layout.fragment_menu_list, container, false);
        //瀑布流控件
        menuListView= (StaggeredGridView) view.findViewById(R.id.channel_dish_list);
        //下拉刷新
        sr = (SwipeRefreshLayout) view.findViewById(R.id.sr);
        View header = inflater.inflate(R.layout.layout_search, menuListView, false);
      //  menuListView.addHeaderView(header);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
       // int[] imagesRes = {R.drawable.a, R.drawable.b, R.drawable.c};
        //kanner.setImagesRes(imagesRes);
        updateMenus();
        sr.setColorSchemeResources(
                R.color.colorPrimary,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        sr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateMenus();
                sr.setRefreshing(false);
            }
        });
       // init(this.getActivity());
    }

    private void updateMenus() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        MenuAPI menuAPI = retrofit.create(MenuAPI.class);


        Call<JsonListResult<Menu>>  call = menuAPI.getMenus();
         Log.e(TAG,call.request().url().toString());
        call.enqueue(new Callback<JsonListResult<Menu>>() {
            @Override
            public void onResponse(Call<JsonListResult<Menu>> call, Response<JsonListResult<Menu>> response) {
                Log.e(TAG,call.toString());
                Log.e(TAG,response.body().getMsg());
//                Gson gson = new Gson();
//                Type type = new TypeToken<JsonListResult<Menu>>() {}.getType();
//                JsonListResult<Menu> jsonResult = gson.fromJson(response.body().toString(), type);
                JsonListResult<Menu> jsonResult =response.body();
                mMenuList = response.body().getData();

                if (jsonResult.isSuccee() && mMenuList != null) {
                    Log.i(TAG, new String(response.toString()));
                    progressBar.setVisibility(View.GONE);
                    final MenuListAdapter menuListAdapter = new MenuListAdapter(getActivity(), mMenuList);
                    menuListAdapter.setOnItemClickListener(new MenuListAdapter.MyItemClickListener() {
                        @Override
                        public void onItemClick(View view, int postion) {
                            Intent intent = new Intent(getActivity(), MenusActivity.class);
                            intent.putExtra("menu", menuListAdapter.getMenus(postion).getMid()+" ");
                            getActivity().startActivity(intent);
                     //       Toast.makeText(getActivity(), "获取菜肴信息失败" + menuListAdapter.getMenus(postion).getMid(), Toast.LENGTH_LONG).show();
                        }
                    });
                    menuListView.setAdapter(menuListAdapter);
                } else {
                    Toast.makeText(getActivity(), "获取菜肴信息失败" + response.body().toString(), Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<JsonListResult<Menu>> call, Throwable t) {
                Toast.makeText(getActivity(), "网络繁忙", Toast.LENGTH_LONG).show();
            }
        });
    }


}
