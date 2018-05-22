package cn.studyhuang.cook.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;


import java.util.List;

import butterknife.BindView;
import cn.studyhuang.cook.Adapter.StepAdapter;
import cn.studyhuang.cook.MyApplication;
import cn.studyhuang.cook.R;
import cn.studyhuang.cook.activity.ErrorActivity;
import cn.studyhuang.cook.api.MenuAPI;
import cn.studyhuang.cook.pojo.Menu;
import cn.studyhuang.cook.pojo.Step;
import cn.studyhuang.cook.utils.Constant;
import cn.studyhuang.cook.utils.JsonListResult;
import cn.studyhuang.cook.utils.JsonResult;
import cn.studyhuang.cook.utils.RetrofitUtils;
import cn.studyhuang.cook.utils.ToastUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepsFragment extends BaseFragment {
    protected static final String TAG="StepsFragment";

    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
     LinearLayoutManager layoutManager;

    @Override
    protected void initViews() {
       String step =   getArguments().getString("mid");
        Long mid =Long.valueOf(step.trim());
        requestSteps(mid);
        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);

    }

    @Override
    protected int getLayoutId() {
        return  R.layout.fragment_recy;
    }

    @Override
    protected void afterCreate(Bundle saveInstanceState) {

    }

    @Override
    protected void lazyLoad() {

    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//    }

    public static StepsFragment newInstance(String mid){
        StepsFragment fragment = new StepsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("mid",mid);
        fragment.setArguments(bundle);
        return fragment;
    }


    public void requestSteps(Long mid){

        Retrofit retrofit = RetrofitUtils.getRetrofit();
        final MenuAPI request= retrofit.create(MenuAPI.class);
        Call<JsonListResult<Step>> call= request.getSteps(mid);
      //  Call<JsonListResult<Step>> call= request.getSteps(mid);

        call.enqueue(new Callback<JsonListResult<Step>>() {
            @Override
            public void onFailure(Call<JsonListResult<Step>> call, Throwable t) {
                Toast.makeText(MyApplication.getContext(),"获取步骤失败",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Call<JsonListResult<Step>> call, Response<JsonListResult<Step>> response) {
                Log.e(TAG, "BUZHOU" + response.body().getMsg());

                Gson gson = new Gson();
                if (response.body().getData() !=null){
                    List<Step> steps = response.body().getData();
                    StepAdapter stepAdapter = new StepAdapter(getActivity(),steps);
                    mRecyclerView.setAdapter(stepAdapter);

                }else {
                    ToastUtils.showToast(MyApplication.getContext(),"该菜单没有步骤");
//                    Intent intent = new Intent(getActivity(), ErrorActivity.class);
//                    startActivity(intent);
                }


//                for (int i = 0; i < response.body().getData().size(); i++) {
//                    if (response.body().getData().get(i).getSdesc() != "" && response.body().getData().get(i).getSdesc() != null) {
//                        if (response.body().getData().get(i).getSdesc() != null && response.body().getData().get(i).getSdesc().length() > 0) {
//                            mTextView.setText(response.body().getData().get(i).getSdesc());
//                        } else {
//                            mTextView.setText("没步骤描述");
//                        }
//                    }
//                    if (response.body().getData().get(i).getSpic() != "" && response.body().getData().get(i).getSpic() != null) {
//                        Glide.with(MyApplication.getContext())
//                                .load(response.body().getData().get(i).getSpic())
//                                .into(mImageView);
//                    }
//
//                }

            }


        });

    }
}
