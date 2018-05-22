package cn.studyhuang.cook.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.WeakHashMap;

import butterknife.BindView;
import cn.studyhuang.cook.MyApplication;
import cn.studyhuang.cook.R;
import cn.studyhuang.cook.api.MenuAPI;
import cn.studyhuang.cook.pojo.Evaluate;
import cn.studyhuang.cook.utils.Constant;
import cn.studyhuang.cook.utils.Result;
import cn.studyhuang.cook.utils.RetrofitUtils;
import cn.studyhuang.cook.utils.ToastUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by huang on 2018/5/14.
 */

public class AddPingjiaActivity extends BaseActivity {

    protected static final String TAG = "AddPingjiaActivity";
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.replay_id)
    EditText mEditText;
    @BindView(R.id.send_pingjia)
    Button mButton;
    Evaluate evaluate=null;
    @Override
    protected int getLayoutId() {

        return R.layout.activity_addpingjia;
    }

    @Override
    public void initViews() {
        //TODO:更改表结构，添加回复字段 提交回复,服务器增加保存评论功能
        setSupportActionBar(mToolbar);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
         evaluate = (Evaluate) bundle.get("evaluate");
        ToastUtils.showToast(MyApplication.getContext(),evaluate.getEvaluateContent());
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = mEditText.getText().toString();
                evaluate.setEvaluateContent(text);
                evaluate.setReplyId(evaluate.getEvaluateId());
                ToastUtils.showToast(MyApplication.getContext(),text);
                try {
                    new Thread(
                       new Runnable() {
                                @Override
                                public void run() {
                                   RetrofitUtils.saveReply(evaluate);
                                }
                            }
                    ).start();


                }catch (Exception e){
                    Log.e(TAG,e.getMessage());
                    e.printStackTrace();
                }

            }
        });

    }


}
