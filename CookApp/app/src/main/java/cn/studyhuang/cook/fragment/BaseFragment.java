package cn.studyhuang.cook.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by huang on 2016/5/20.
 */
public abstract class BaseFragment extends android.support.v4.app.Fragment {
    protected View mRootView;
    protected boolean isVisible = false;
    protected boolean isPrepared = false;
    protected Boolean flag = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), null);
        }
       ButterKnife.bind(this, mRootView);
//        mRootView = (View) find;
        initViews();
        isPrepared = true;
        lazyLoad();
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        afterCreate(savedInstanceState);
    }

    protected abstract void initViews();

    protected abstract int getLayoutId();

    protected abstract void afterCreate(Bundle saveInstanceState);

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint() && !isVisible) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    private void onVisible() {
        lazyLoad();
    }

    protected abstract void lazyLoad();

    private void onInvisible() {
    }
}
