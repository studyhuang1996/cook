package cn.studyhuang.cook.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.etsy.android.grid.StaggeredGridView;

import java.util.List;

import butterknife.BindView;
import cn.studyhuang.cook.Adapter.MenuListAdapter;
import cn.studyhuang.cook.MyApplication;
import cn.studyhuang.cook.R;
import cn.studyhuang.cook.activity.ErrorActivity;
import cn.studyhuang.cook.activity.MenusActivity;
import cn.studyhuang.cook.api.UserAPI;
import cn.studyhuang.cook.pojo.Menu;
import cn.studyhuang.cook.utils.JsonListResult;
import cn.studyhuang.cook.utils.RetrofitUtils;
import cn.studyhuang.cook.utils.ToastUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 我发布的菜谱.
 */
public class MyFoodFragment extends BaseFragment {

    @BindView(R.id.channel_dish_list)
    StaggeredGridView menuListView;

    private MenuListAdapter dishListAdapter;

    private List<Menu> mMenuList=null;
    @BindView(R.id.sr)
    SwipeRefreshLayout sr;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;


    public static MyFoodFragment newInstance(String userId){
        MyFoodFragment fragment = new MyFoodFragment();
        Bundle bundle = new Bundle();
        bundle.putString("userId",userId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initViews() {
        String step =   getArguments().getString("userId");
       final Long userId =Long.valueOf(step.trim());
        myFood(userId);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_cook;
    }

    @Override
    protected void afterCreate(Bundle saveInstanceState) {

    }

    @Override
    protected void lazyLoad() {

    }

    private void myFood(Long userId){
        // TODO: 2018/5/8  发布到服务器修改地址

        Retrofit retrofit = RetrofitUtils.getRetrofit();
        //     RetrofitUtils.getRetrofit();

        UserAPI userAPI = retrofit.create(UserAPI.class);

        Call<JsonListResult<Menu>> call= userAPI.findMenuByUserId(userId);
        call.enqueue(new Callback<JsonListResult<Menu>>() {
            @Override
            public void onResponse(Call<JsonListResult<Menu>> call, Response<JsonListResult<Menu>> response) {
                if (response.body() == null) {
                    Intent intent = new Intent(getActivity(), ErrorActivity.class);
                    startActivity(intent);
                }
                JsonListResult<Menu> jsonResult = response.body();
                mMenuList = response.body().getData();

                if (jsonResult.isSuccee() && mMenuList != null) {
                    //   Log.i(TAG, new String(response.toString()));
                    progressBar.setVisibility(View.GONE);
                    final MenuListAdapter menuListAdapter = new MenuListAdapter(getActivity(), mMenuList);
                    menuListAdapter.setOnItemClickListener(new MenuListAdapter.MyItemClickListener() {
                        @Override
                        public void onItemClick(View view, int postion) {
                            Intent intent = new Intent(getActivity(), MenusActivity.class);
                            intent.putExtra("menu", menuListAdapter.getMenus(postion).getMid() + " ");
                            startActivity(intent);

                        }
                    });
                    menuListView.setAdapter(menuListAdapter);

                }
            }
            @Override
            public void onFailure(Call<JsonListResult<Menu>> call, Throwable t) {
                ToastUtils.showToast(MyApplication.getContext(),"网络出错请稍后重试");
            }
        });
    }

}
