package cn.studyhuang.cook.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import cn.studyhuang.cook.Adapter.MaterialAdapter;
import cn.studyhuang.cook.MyApplication;
import cn.studyhuang.cook.R;
import cn.studyhuang.cook.activity.MenusActivity;
import cn.studyhuang.cook.api.MenuAPI;
import cn.studyhuang.cook.pojo.Material;
import cn.studyhuang.cook.utils.JsonListResult;
import cn.studyhuang.cook.utils.RetrofitUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by huang on 2018/5/1.
 */
public class MaterialFragment extends BaseFragment {

    protected static final String TAG = "MaterialFragment";
    @BindView(R.id.recycler)
    RecyclerView mRecyclerView;
    LinearLayoutManager layoutManager;
    List<Material> mMaterials = null;
    private static Long mid = null;

    public static MaterialFragment newInstance(String mid) {
        MaterialFragment fragment = new MaterialFragment();
        Bundle bundle = new Bundle();
        bundle.putString("mid", mid);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initViews() {
        String step = getArguments().getString("mid");
         mid = Long.valueOf(step.trim());
        requestMaterials(mid);
//        layoutManager = new LinearLayoutManager(getActivity());
//        mRecyclerView.setLayoutManager(layoutManager);
//        MaterialAdapter stepAdapter = new MaterialAdapter(getActivity(), mid);
//         mRecyclerView.setAdapter(stepAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recy;
    }

    @Override
    protected void afterCreate(Bundle saveInstanceState) {

    }

    @Override
    protected void lazyLoad() {

    }

    public void requestMaterials(Long mid) {
        Retrofit retrofit = RetrofitUtils.getRetrofit();
        final MenuAPI request = retrofit.create(MenuAPI.class);

        Call<JsonListResult<Material>> call = request.getMaterials(mid);

        call.enqueue(new Callback<JsonListResult<Material>>() {
            @Override
            public void onResponse(Call<JsonListResult<Material>> call, Response<JsonListResult<Material>> response) {
                //     JsonListResult<Step> steps = response.body();
                Log.e(TAG, "CAISHICAI");
                if (response.body() != null) {

                    mMaterials = response.body().getData();
                    layoutManager = new LinearLayoutManager(getActivity());
                    mRecyclerView.setLayoutManager(layoutManager);
                    MaterialAdapter stepAdapter = new MaterialAdapter(getActivity(), mMaterials);
                    mRecyclerView.setAdapter(stepAdapter);

                }
            }

            @Override
            public void onFailure(Call<JsonListResult<Material>> call, Throwable t) {
                Toast.makeText(MyApplication.getContext(), "shiicai获取失败", Toast.LENGTH_SHORT).show();
            }
        });

    }
}




