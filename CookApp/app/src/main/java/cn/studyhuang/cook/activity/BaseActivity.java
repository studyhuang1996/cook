package cn.studyhuang.cook.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Window;



import butterknife.ButterKnife;
import cn.studyhuang.cook.R;

/**
 * Created by huang on 2018/1/20.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Transition transition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        }
        setContentView(getLayoutId());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            transition = TransitionInflater.from(this).inflateTransition(R.transition.activity_slide);
            getWindow().setExitTransition(transition);
            getWindow().setEnterTransition(transition);
            getWindow().setReenterTransition(transition);
        }
        ButterKnife.bind(this);
        initViews();
    }

    protected abstract int getLayoutId();

    public abstract void initViews();

}
