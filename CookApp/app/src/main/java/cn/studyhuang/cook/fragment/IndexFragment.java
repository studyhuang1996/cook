package cn.studyhuang.cook.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cn.studyhuang.cook.R;
import cn.studyhuang.cook.api.RetrofitAPI;
import cn.studyhuang.cook.pojo.Evaluate;
import cn.studyhuang.cook.utils.RetrofitUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class IndexFragment  extends  Fragment{

//    TabLayout tabLayout ;
//
//    ViewPager viewPager;
//    String[] titles = {"热门", "推荐"};
//
//    @Override
//    protected void initViews() {
//        final List<TopicDetailFragment> fragmentList = new ArrayList<>();
//        fragmentList.add(TopicDetailFragment.getInstance(53));//热门
//        fragmentList.add(TopicDetailFragment.getInstance(58));//推荐
//        viewPager.setAdapter(new FragmentStatePagerAdapter(getActivity().getSupportFragmentManager()) {
//
//            @Override
//            public CharSequence getPageTitle(int position) {
//                return titles[position];
//            }
//
//            @Override
//            public Fragment getItem(int position) {
//                return fragmentList.get(position);
//            }
//
//            @Override
//            public int getCount() {
//                return titles.length;
//            }
//        });
//        tabLayout.setupWithViewPager(viewPager);
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.fragment_topic;
//    }
//
//    @Override
//    protected void afterCreate(Bundle saveInstanceState) {
//
//    }
//
//    @Override
//    protected void lazyLoad() {
//
//    }

//    private  View mView;
//    private ListView mListView;
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//
//       mView = inflater.inflate(R.layout.fragment_index,container,false);
//
//
//        return mView;
//    }
//
//    public void getPingjia(String mid){
//        Retrofit retrofit = RetrofitUtils.getRetrofit();
//       RetrofitAPI retrofitAPI =  retrofit.create(RetrofitAPI.class);
//        Call<Evaluate> call =retrofitAPI.getEvaluate();
//        call.enqueue(new Callback<Evaluate>() {
//            @Override
//            public void onResponse(Call<Evaluate> call, Response<Evaluate> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<Evaluate> call, Throwable t) {
//
//            }
//        });
//
//    }
}
