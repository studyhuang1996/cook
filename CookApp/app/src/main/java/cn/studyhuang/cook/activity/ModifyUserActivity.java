package cn.studyhuang.cook.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.WeakHashMap;

import butterknife.BindView;
import cn.studyhuang.cook.MyApplication;
import cn.studyhuang.cook.R;
import cn.studyhuang.cook.api.UserAPI;
import cn.studyhuang.cook.utils.Result;
import cn.studyhuang.cook.utils.RetrofitUtils;
import cn.studyhuang.cook.utils.SPUtils;
import cn.studyhuang.cook.utils.ToastUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ModifyUserActivity extends BaseActivity {

    @BindView(R.id.old_pwd)
    EditText oldPwd;
    @BindView(R.id.new_pwd)
    EditText newPwd;
    @BindView(R.id.confirm_pwd)
    EditText confirmPwd;
    @BindView(R.id.edit_pw)
    Button mButton;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_modify_user;
    }

    @Override
    public void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("修改密码");
       mButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               editPw();
           }
       });
    }



    public void editPw(){
        SharedPreferences sp =SPUtils.getInstance();
        String userPw = (String) sp.getString("password","123456");
        String uid = (String) sp.getString("userId","");
        String oldPw = oldPwd.getText().toString();
        String newPassword = newPwd.getText().toString();
        String confPwd =confirmPwd.getText().toString();
        if (!userPw.equals(oldPw)){
            ToastUtils.showToast(this,"密码与原来密码不一致");
        }
        if (!newPassword.equals(confPwd)){
            ToastUtils.showToast(this,"两次输入密码不一致");
        }
        Retrofit retrofit = RetrofitUtils.getRetrofit();
//                           new Retrofit.Builder()
//                           .baseUrl("http://192.168.2.101:8080/")
//                            .addConverterFactory(GsonConverterFactory.create())
//                            .addConverterFactory(ScalarsConverterFactory.create())
//                            .build();

             UserAPI userAPI= retrofit.create(UserAPI.class);
             WeakHashMap<String,Object> params =new WeakHashMap<>();
              params.put("uid",Long.valueOf(uid));
              params.put("upassword",newPassword);
             Call<Result> call = userAPI.editPassword(Long.valueOf(uid),newPassword);
            call.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    if (!response.body().isSuccee()){
                        ToastUtils.showToast(MyApplication.getContext(),"修改密码失败");
                    }
                    Intent intent = new Intent(MyApplication.getContext(),LoginActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    ToastUtils.showToast(MyApplication.getContext(),"网络错误");
                }
            });
    }

}
