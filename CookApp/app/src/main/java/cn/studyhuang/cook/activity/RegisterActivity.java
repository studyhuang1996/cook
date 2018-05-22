package cn.studyhuang.cook.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.WeakHashMap;

import cn.studyhuang.cook.MyApplication;
import cn.studyhuang.cook.R;
import cn.studyhuang.cook.api.UserAPI;
import cn.studyhuang.cook.pojo.User;
import cn.studyhuang.cook.utils.Constant;
import cn.studyhuang.cook.utils.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


/**
 * 用户注册:请求数据到后台保存，然后跳转到登录页面
 * created by huang
 * date 2018-2-26
 */
public class RegisterActivity extends AppCompatActivity {
    protected static final String TAG = "RegisterActivity";
    private EditText userName;
    private EditText userEmial;
    private EditText password;
    private Button mButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userName = (EditText) findViewById(R.id.uname);
        userEmial = (EditText) findViewById(R.id.uemail);
        password = (EditText) findViewById(R.id.upassword);
        mButton = (Button) findViewById(R.id.btn_register);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user =new User();
                final String name =userName.getText().toString();
                String email = userEmial.getText().toString();
                String upassword = password.getText().toString();
                user.setUname(name);
                user.setUemail(email);
                user.setUpassword(upassword);
                boolean check =false;

                if (TextUtils.isEmpty(name)||name.length()!=11){
                    Toast.makeText(MyApplication.getContext(),"账号不为空，账号必须11位",Toast.LENGTH_SHORT);
                    check = true ;
                }
                if (TextUtils.isEmpty(email)||!email.contains("@")){
                    Toast.makeText(MyApplication.getContext(),"邮箱格式有错",Toast.LENGTH_SHORT);
                    check =true;
                }
                if (TextUtils.isEmpty(upassword)||upassword.length()<6){
                    Toast.makeText(MyApplication.getContext(),"密码不小于6位",Toast.LENGTH_SHORT);
                    check =true;
                }
                if (!check){
//                 new Thread(new Runnable() {
//                     @Override
//                     public void run() {
//
//                     }
//                 }).start();
                   saveUsers(user);

                }else{
                    Toast.makeText(MyApplication.getContext(),"输入格式错误，注册失败",Toast.LENGTH_LONG).show();
                }

            }
        });

    }



    public void saveUsers(User user){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        UserAPI userAPI = retrofit.create(UserAPI.class);
        WeakHashMap<String,Object> maps = new WeakHashMap<>();
        maps.put("uname",user.getUname());
        maps.put("uemail",user.getUemail());
        maps.put("upassword",user.getUpassword());
        Call<Result> call= userAPI.saveUser(maps);
        Log.e("sdsdc","ss");
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
               String msg=response.body().getMsg();
                if (response.body().isSuccee() ){
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);
                 Toast.makeText(MyApplication.getContext(),"注册成功",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(MyApplication.getContext(),"网络繁忙，请稍后注册",Toast.LENGTH_SHORT).show();
            }
        });
    }


}
