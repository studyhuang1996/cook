package cn.studyhuang.cook.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import cn.studyhuang.cook.R;
import cn.studyhuang.cook.api.UserAPI;
import cn.studyhuang.cook.pojo.User;
import cn.studyhuang.cook.utils.Constant;
import cn.studyhuang.cook.utils.HttpUtils;
import cn.studyhuang.cook.utils.JsonResult;
import cn.studyhuang.cook.utils.PrefUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LoginActivity extends Activity {

    protected static final String TAG = "LoginActivity";

    private EditText username;
    private EditText mPasswordView;

    private Button mSignInButton;
    private Button mSwitchButton;
    private View  mProgressView;
   // private Boolean isRegiterAction = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        username = (EditText) findViewById(R.id.username);
        mPasswordView = (EditText) findViewById(R.id.edit_password);


        mSignInButton = (Button) findViewById(R.id.btn_login);
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                attemptLogin();

            }

        });
        //注册
        mSwitchButton = (Button) findViewById(R.id.btn_register);
        mSwitchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        mProgressView = findViewById(R.id.progress_login);

    }
    private void clearText() {
        username.setText("");
        mPasswordView.setText("");
    }

    //登录
    private void attemptLogin() {
        // Reset errors.
        username.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String uname = username.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
            View focusView = null;


            if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
                mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        // Check for a valid email address.
        if (TextUtils.isEmpty(uname)) {
            username.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        } else if (!isPhoneValid(uname)) {
            username.setError(getString(R.string.error_invalid_email));
            focusView = mPasswordView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();

            UserAPI userAPI = retrofit.create(UserAPI.class);
            Call<JsonResult<User>> call = userAPI.getUser(uname, password);
            Log.e(TAG, "CALL"+call);
            call.enqueue(new Callback<JsonResult<User>>() {
                @Override
                public void onResponse(Call<JsonResult<User>> call, Response<JsonResult<User>> response) {
                    Gson gson = new Gson();
                    Type type = new TypeToken<JsonResult<User>>() {}.getType();
                  //  JsonResult<User> jsonResult = gson.fromJson(new String(response.body().toString()), type);
                 //   String status = jsonResult.getStatus();
               //     User user = jsonResult.getData();
                    User user1 = response.body().getData();
                    Log.e(TAG, response.body().getMsg());

                    if ( response.body().isSuccee() && null != user1) {
                  /*      PersistentCookieStore myCookieStore = new PersistentCookieStore(getApplicationContext());
                            HttpUtils.client.setCookieStore(myCookieStore);
                           List<Cookie> cookies = myCookieStore.getCookies();
                            String JSESSIONID = "";
                            if (cookies.isEmpty()) {
                               Log.i("session", "None");
                            } else {
                                for (int i = 0; i < cookies.size(); i++) {
                                    if("JSESSIONID".equals(cookies.get(i).getName())){
                                       JSESSIONID = cookies.get(i).getValue();  // 第二种方法 通过 JSESSIONID
                                        System.out.println(JSESSIONID);
                                        break;
                                    }
                                }
                                PrefUtils.set("user","session",JSESSIONID,getBaseContext());                                Log.i("session", "保存"+JSESSIONID);
                            }
                            BasicClientCookie newCookie = new BasicClientCookie("userId",user.getId());
                           newCookie.setVersion(1);                        newCookie.setDomain("114.215.135.153");
                           newCookie.setPath("/");
                           myCookieStore.addCookie(newCookie);*/
                           //保存用户id到本地
//                           PrefUtils.set("user","userId",user.getId(),getBaseContext());
//                           Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                           intent.putExtra("userInfo", user);
//                            startActivity(intent);
//                           LoginActivity.this.finish();
//                            showProgress(false);


                        SharedPreferences sp  = getSharedPreferences("data",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putBoolean("OK",true);
                        editor.putString("username", user1.getUname());
                        editor.putString("password",user1.getUpassword());
                        editor.putString("email",user1.getUemail());
                        editor.putString("userId",String.valueOf(user1.getUid()));
                        editor.commit();

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("user",user1);
                        Intent intent = new Intent(LoginActivity.this, CooksActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                    }
                    showProgress(false);

                }

                @Override
                public void onFailure(Call<JsonResult<User>> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "用户名或者密码错误", Toast.LENGTH_LONG).show();
                    showProgress(false);
                }
            });


        }
    }

    private boolean isLogin(){
        boolean hasLogin = false;
        SharedPreferences sp = this.getSharedPreferences("has",MODE_PRIVATE);
        hasLogin = sp.getBoolean("OK",false);
        return hasLogin;
    }


    private boolean isPhoneValid(String phone) {
        //TODO: Replace this with your own logic
        return phone.length() == 11;
    }


    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() >= 6;
    }


    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);


            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            //mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}






