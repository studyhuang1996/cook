package cn.studyhuang.cook.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import cn.studyhuang.cook.Adapter.EvaluateAdapter;
import cn.studyhuang.cook.MyApplication;
import cn.studyhuang.cook.R;
import cn.studyhuang.cook.activity.AddPingjiaActivity;
import cn.studyhuang.cook.activity.MenusActivity;
import cn.studyhuang.cook.api.MenuAPI;
import cn.studyhuang.cook.handler.PingjiaRetrofit;
import cn.studyhuang.cook.pojo.Evaluate;
import cn.studyhuang.cook.utils.JsonListResult;
import cn.studyhuang.cook.utils.RetrofitUtils;
import cn.studyhuang.cook.utils.ToastUtils;
import cn.studyhuang.cook.view.PingjiaView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.makeramen.roundedimageview.RoundedImageView.TAG;

/**
 * Created by huang on 2018/5/1.
 */
public class PingjiaFragment extends  BaseFragment{// implements PingjiaView

    protected static final String TAG="PingjiaFragment";

    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    private PingjiaRetrofit mPingjiaRetrofit;
    private Handler mHandler;
    static List<Evaluate> evaluates=null;
    Gson gson = new GsonBuilder().create();
       static Long mid =null;

    //发表评论
    @BindView(R.id.send)
    Button mButton;
    @BindView(R.id.comment_et)
    EditText mEditText;

    LinearLayoutManager layoutManager;

    public static PingjiaFragment newInstance(String mid){
        PingjiaFragment fragment = new PingjiaFragment();
        Bundle bundle = new Bundle();
        bundle.putString("mid",mid);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    protected void initViews() {
        String step =   getArguments().getString("mid");
        mid =Long.valueOf(step.trim());
//        mPingjiaRetrofit = new PingjiaRetrofit(this);
//        mPingjiaRetrofit.getComment(mid);
         requestPingjia();
          mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String comment = mEditText.getText().toString();
                    if (comment != null) {
                        Evaluate evaluate = new Evaluate();
                        evaluate.setMid(mid);
                        evaluate.setReplyId(Long.valueOf(0));
                        evaluate.setEvaluateContent(comment);
                        //  evaluate.setScore(5.00);
                        RetrofitUtils.saveReply(evaluate);
                        Intent intent = new Intent(getActivity(), MenusActivity.class);
                        startActivity(intent);
                    }else{
                    ToastUtils.showToast(MyApplication.getContext(),"请填写评价");}
                }

            });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_evaluate ;
    }//fragment_recy

    @Override
    protected void afterCreate(Bundle saveInstanceState) {

    }

    @Override
    protected void lazyLoad() {

    }

    public void requestPingjia() {

        Retrofit retrofit = RetrofitUtils.getRetrofit();

        MenuAPI menuAPI = retrofit.create(MenuAPI.class);

        Call<JsonListResult<Evaluate>> call = menuAPI.getEvalutes(mid);


        call.enqueue(new Callback<JsonListResult<Evaluate>>() {
            @Override
            public void onResponse(Call<JsonListResult<Evaluate>> call, retrofit2.Response<JsonListResult<Evaluate>> response) {
               Log.e(TAG,"pingjai");
                if (response.body().isSuccee()){
                    try {

//                        final JsonListResult<Evaluate> evaluate = gson.fromJson(response.body().toString(),
//                                new TypeToken<JsonListResult<Evaluate>>() {
//                                }.getType());
                        evaluates=response.body().getData();
                        layoutManager = new LinearLayoutManager(getActivity());
                        mRecyclerView.setLayoutManager(layoutManager);
                        if (evaluates != null) {
                            EvaluateAdapter evaluateAdapter = new EvaluateAdapter(getActivity(), evaluates);
                            mRecyclerView.setAdapter(evaluateAdapter);
                            Log.e(TAG, "sds");
                        }else{
                            ToastUtils.showToast(MyApplication.getContext(),"该菜谱还未有评价，可前往评价");
//                            Intent intent = new Intent(getActivity(), AddPingjiaActivity.class);
//                            startActivity(intent);
                        }

                    }catch (JsonSyntaxException e){
                        //   mPingjiaView.onFailed(response.code());
                        ToastUtils.showToast(MyApplication.getContext(),e.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonListResult<Evaluate>> call, Throwable t) {
                ToastUtils.showToast(MyApplication.getContext(),"获取评论失败");
            }
        });
    }



}
