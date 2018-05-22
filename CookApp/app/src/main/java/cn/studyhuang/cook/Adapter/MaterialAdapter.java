package cn.studyhuang.cook.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.studyhuang.cook.MyApplication;
import cn.studyhuang.cook.R;
import cn.studyhuang.cook.api.MenuAPI;
import cn.studyhuang.cook.pojo.Material;
import cn.studyhuang.cook.utils.JsonListResult;
import cn.studyhuang.cook.utils.RetrofitUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.makeramen.roundedimageview.RoundedImageView.TAG;


/**
 * Created by huang on 2018/1/24.
 */

public class MaterialAdapter extends  RecyclerView.Adapter<MaterialAdapter.ViewHolder> {


    private Context mContext;
    private   List<Material> mMaterials =null;
    private LayoutInflater inflater;
    Long mid=null;
    public  MaterialAdapter(Context mContext, List<Material> mMaterials){
      //  this.mSteps = mSteps;
        //this.mid = mid;
        this.mMaterials = mMaterials;
        this.mContext = mContext;
        inflater=LayoutInflater.from(mContext);
    }
    public static class ViewHolder extends  RecyclerView.ViewHolder{
        TextView mName;
        TextView mTextView;
        public ViewHolder(View mView){
            super(mView);
            mName = (TextView) mView.findViewById(R.id.material_name);
            mTextView = (TextView) mView.findViewById(R.id.material_weight);
        }
    }
    @Override
    public int getItemCount() {
        //  return mSteps.size();
        return mMaterials.size();
    }

    @Override
    public MaterialAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_material,parent,false);

        return new MaterialAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final  MaterialAdapter.ViewHolder holder, final  int position) {

        Material material = mMaterials.get(position);
        holder.mTextView.setText(material.getMaterialWeight());
        holder.mName.setText(material.getMaterialName());

    }


}
