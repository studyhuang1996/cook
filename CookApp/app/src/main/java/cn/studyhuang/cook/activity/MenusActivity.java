package cn.studyhuang.cook.activity;

import android.app.Fragment;
import android.content.Intent;


import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.hjm.bottomtabbar.BottomTabBar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import cn.studyhuang.cook.Adapter.MaterialAdapter;
import cn.studyhuang.cook.Adapter.StepAdapter;
import cn.studyhuang.cook.MyApplication;
import cn.studyhuang.cook.R;
import cn.studyhuang.cook.api.MenuAPI;
import cn.studyhuang.cook.fragment.BaseFragment;
import cn.studyhuang.cook.fragment.MaterialFragment;
import cn.studyhuang.cook.fragment.MineFragment;
import cn.studyhuang.cook.fragment.PingjiaFragment;
import cn.studyhuang.cook.fragment.StepsFragment;
import cn.studyhuang.cook.pojo.Category;
import cn.studyhuang.cook.pojo.Material;
import cn.studyhuang.cook.pojo.Menu;
import cn.studyhuang.cook.pojo.MenuDetails;
import cn.studyhuang.cook.pojo.Step;
import cn.studyhuang.cook.utils.Constant;
import cn.studyhuang.cook.utils.JsonListResult;
import cn.studyhuang.cook.utils.JsonResult;
import cn.studyhuang.cook.utils.RetrofitUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 菜单详情页
 */
public class MenusActivity extends AppCompatActivity {
    // StepsFragment stepsFragment = new StepsFragment();
    private  Menu mMenu;
    private ImageView mImageView;
    private TextView mTextView;
    private TextView mname;
    private TextView  menu_info;
    private RecyclerView mRecyclerView;
    private RecyclerView material_rec;
     TabLayout mTabLayout;
    ViewPager mViewPager;
    private StepsFragment mStepsFragment;
    private MaterialFragment mMaterialFragment;
    private PingjiaFragment mPingjiaFragment;
    private String[] titles = {"步骤", "食材", "评价"};

    private String mid = null;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus);
        mImageView =(ImageView) findViewById(R.id.cook_img);
        mTextView =(TextView) findViewById(R.id.mdesc);
        mname =(TextView) findViewById(R.id.mname);
        menu_info =(TextView) findViewById(R.id.menu_info);
//        mRecyclerView =(RecyclerView) findViewById(R.id.step_recy);
//        material_rec =(RecyclerView) findViewById(R.id.material_recy);
        mTabLayout =(TabLayout) findViewById(R.id.tl_content);
        mViewPager = (ViewPager) findViewById(R.id.vp_cook);
        Intent intent = getIntent();
        mid= intent.getStringExtra("menu");

        final String[] titles = new String[]{"步骤", "食材", "评价"};
        final List<BaseFragment> fragmentList = new ArrayList<>();
        fragmentList.add(StepsFragment.newInstance(mid));
        fragmentList.add(MaterialFragment.newInstance(mid));
        fragmentList.add(PingjiaFragment.newInstance(mid));
        mViewPager.setAdapter(new FragmentStatePagerAdapter((getSupportFragmentManager())) {
            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }

            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);

        new Thread(new Runnable() {
            @Override
            public void run() {

                requests(mid);
           //     requestMaterials(mid);
             //   requestSteps(mid);
            }
        }) .start();

//        Bundle bundle = new Bundle();
//        bundle.putString("mid",mid);
//        stepsFragment.setArguments(bundle);
//       getSupportFragmentManager().beginTransaction()
//               .add(R.id.step,stepsFragment).commit();
     //   Toast.makeText(MyApplication.getContext(), "menus"+mid, Toast.LENGTH_SHORT).show();
    }

    private void hideTab(FragmentTransaction transaction) {
        if (mStepsFragment != null) {
            transaction.hide(mStepsFragment);
        }
        if (mMaterialFragment != null) {
            transaction.hide(mMaterialFragment);
        }
        if (mPingjiaFragment != null) {
            transaction.hide(mPingjiaFragment);
        }
    }
    public  void  requests(String mid){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        MenuAPI request= retrofit.create(MenuAPI.class);

          Call<JsonResult<Menu>>  call= request.getMenu(Long.parseLong(mid.trim()));

        call.enqueue(new Callback<JsonResult<Menu>>() {
            @Override
            public void onResponse(Call<JsonResult<Menu>> call, Response<JsonResult<Menu>> response) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                Log.e("menu",response.body().getMsg());
                mname.setText(response.body().getData().getMname()+"");
                menu_info.setText("发布者："+response.body().getData().getUserid()+"          " +
                        "日期:"+ sdf.format(new Date(response.body().getData().getMdate())));
                Glide.with(MyApplication.getContext())
                        .load(response.body().getData().getMpic())
                      .into(mImageView);
          //      mTextView.setText(response.body().getData().getMdesc()+"");

            }

            @Override
            public void onFailure(Call<JsonResult<Menu>> call, Throwable t) {
                Toast.makeText(MyApplication.getContext(),"获取数据失败",Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void requestSteps(String mid){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        MenuAPI request= retrofit.create(MenuAPI.class);

        Call<JsonListResult<Step>>  call= request.getSteps(Long.parseLong(mid.trim()));

        call.enqueue(new Callback<JsonListResult<Step>>() {
            @Override
            public void onResponse(Call<JsonListResult<Step>> call, Response<JsonListResult<Step>> response) {
                //     JsonListResult<Step> steps = response.body();
                if (response.body() != null) {
                    List<Step> step = response.body().getData();
                    if (step != null && step.size() > 0) {
                   //     final StepAdapter stepAdapter = new StepAdapter(MyApplication.getContext(), step);
                   //     mRecyclerView.setAdapter(stepAdapter);
                    }

                }
            }
            @Override
            public void onFailure(Call<JsonListResult<Step>> call, Throwable t) {
                Toast.makeText(MyApplication.getContext(),"步骤获取失败",Toast.LENGTH_SHORT).show();
            }
        });
    }




}