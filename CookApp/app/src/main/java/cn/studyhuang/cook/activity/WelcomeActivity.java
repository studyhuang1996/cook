package cn.studyhuang.cook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import cn.studyhuang.cook.R;

/**
 * 欢迎也，导航也
 * 欢迎也，导航也
 */
public class WelcomeActivity extends AppCompatActivity {

    Handler mHandler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
          //      SharedPreferences sp=getPreferences(MODE_PRIVATE);
              //  Boolean isFirst=sp.getBoolean("isFirst",true);
                Intent intent =new Intent(WelcomeActivity.this,CooksActivity.class);
//                if (isFirst){
//                  //第一次进入引导页
//                   // sp.edit().commit("isFirst",flase);
//
//                }else {
//                 //进入首页
//                }
                startActivity(intent);
                //动画效果的实现
              //   overridePendingTransition();
                finish();
            }
        },3000);
    }
}
