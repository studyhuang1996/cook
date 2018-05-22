package cn.studyhuang.cook.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.studyhuang.cook.MyApplication;
import cn.studyhuang.cook.R;
import cn.studyhuang.cook.api.MenuAPI;
import cn.studyhuang.cook.pojo.Step;
import cn.studyhuang.cook.utils.JsonListResult;
import cn.studyhuang.cook.utils.RetrofitUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by huang on 2018/1/23.
 */

public class StepAdapter extends  RecyclerView.Adapter<StepAdapter.ViewHolder> {

    private Context mContext;
    List<Step> mSteps;
    Long mid=null;

    private LayoutInflater inflater;
    public  StepAdapter(Context mContext,List<Step> mSteps){
        this.mSteps = mSteps;
      //  this.mid = mid;
        this.mContext = mContext;
        inflater=LayoutInflater.from(mContext);
    }
    public static class ViewHolder extends  RecyclerView.ViewHolder{
       ImageView mImageView;
        TextView mTextView;
       public ViewHolder(View mView){
           super(mView);
           mImageView = (ImageView) mView.findViewById(R.id.imgv_step);
           mTextView = (TextView) mView.findViewById(R.id.text_content);
       }
   }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_cook_detail_step,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        Step step =   mSteps.get(position);
        holder.mTextView.setText(step.getSdesc());
        Glide.with(mContext).load(step.getSpic()).into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mSteps.size() ;
    }
}
