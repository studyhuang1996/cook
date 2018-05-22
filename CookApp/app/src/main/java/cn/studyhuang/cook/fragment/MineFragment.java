package cn.studyhuang.cook.fragment;



import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import cn.studyhuang.cook.Adapter.EvaluateAdapter;
import cn.studyhuang.cook.MyApplication;
import  cn.studyhuang.cook.R;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.WeakHashMap;


import cn.studyhuang.cook.activity.CooksActivity;
import cn.studyhuang.cook.activity.ModifyUserActivity;
import cn.studyhuang.cook.api.UserAPI;
import cn.studyhuang.cook.pojo.User;
import cn.studyhuang.cook.utils.Constant;
import cn.studyhuang.cook.utils.JsonResult;

import cn.studyhuang.cook.utils.Result;
import cn.studyhuang.cook.utils.RetrofitUtils;
import cn.studyhuang.cook.utils.ToastUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class MineFragment extends BaseFragment {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.person_img)
    ImageView userImg;
    @BindView(R.id.person_name)
    EditText userName;
    @BindView(R.id.email)
    EditText uEmail;
    @BindView(R.id.user_date)
    EditText userDate;
    @BindView(R.id.edit_password)
    Button editPwd;
    @BindView(R.id.user_edit)
    Button editUser;
     Long userId =null;
    @OnClick({R.id.edit_password,R.id.user_edit})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.edit_password:
                Intent intent = new Intent(getActivity(), ModifyUserActivity.class);
                startActivity(intent);
                break;
            case R.id.user_edit:
//                ToastUtils.showToast(getActivity(),"确认修改个人信息");
                editUsers();
                break;
        }
    }

    public static MineFragment newInstance(String userId){
        MineFragment fragment = new MineFragment();
        Bundle bundle = new Bundle();
        bundle.putString("userId",userId);
        fragment.setArguments(bundle);
        return fragment;
    }



    @Override
    protected void initViews() {
          mToolbar.setTitle("个人信息");
        String userId1 =   getArguments().getString("userId");
         userId =Long.valueOf(userId1.trim());
         userInfo(userId);
    }

    //显示个人信息
    private void userInfo(Long userId) {
        Retrofit retrofit = RetrofitUtils.getRetrofit();
//                new Retrofit.Builder()
//                           .baseUrl("http://192.168.2.101:8080/")
//                            .addConverterFactory(GsonConverterFactory.create())
//                            .addConverterFactory(ScalarsConverterFactory.create())
//                            .build();
            UserAPI userApi= retrofit.create(UserAPI.class);
             Call<JsonResult<User>> call = userApi.getUserInfo(userId);

            call.enqueue(new Callback<JsonResult<User>>() {
                @Override
                public void onResponse(Call<JsonResult<User>> call, Response<JsonResult<User>> response) {
                    if (response.body() == null){
                        ToastUtils.showToast(MyApplication.getContext(),"网络出错");
                       return;
                    }
                    Glide.with(getActivity())
                            .load(Constant.AVATER)
                            .into(userImg);
                    User user = response.body().getData();
                    userName.setText(user.getUname());
                    Calendar calendar =Calendar.getInstance();
                    calendar.setTimeInMillis(user.getUbirthday());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    userDate.setText(sdf.format(calendar.getTime()));
                    uEmail.setText(user.getUemail());
                }

                @Override
                public void onFailure(Call<JsonResult<User>> call, Throwable t) {
                    ToastUtils.showToast(MyApplication.getContext(),"网络出错");
                }
            });

    }

    public  void editUsers(){
        String name = userName.getText().toString();
        String email =uEmail.getText().toString();
        String date =userDate.getText().toString();

        if (name ==null || email ==null){
            ToastUtils.showToast(MyApplication.getContext(),"用户名或邮箱为空");
        }

        Retrofit retrofit = RetrofitUtils.getRetrofit();
//                new Retrofit.Builder()
//                .baseUrl("http://192.168.2.101:8080/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .build();
        UserAPI userAPI = retrofit.create(UserAPI.class);

        Call<Result> call= userAPI.editUsers(userId,name,email,date);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if (response.body() == null){
                    ToastUtils.showToast(MyApplication.getContext(),"修改失败");
                }
                ToastUtils.showToast(MyApplication.getContext(),"修改个人信息成功");
                Intent intent = new  Intent(getActivity(), CooksActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                ToastUtils.showToast(MyApplication.getContext(),"网络出错");
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void afterCreate(Bundle saveInstanceState) {

    }

    @Override
    protected void lazyLoad() {

    }


}
