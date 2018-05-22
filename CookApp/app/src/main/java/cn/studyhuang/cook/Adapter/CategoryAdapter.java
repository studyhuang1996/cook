package cn.studyhuang.cook.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.studyhuang.cook.R;
import cn.studyhuang.cook.pojo.Category;
import cn.studyhuang.cook.utils.Constant;

/**
 * Created by huang on 2018/1/12.
 */

public class CategoryAdapter  extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> implements View.OnClickListener{

    private Context mContext;
    private List<Category.DataBean> mCategories;
    private OnItemClickListener mOnItemClickListener = null;

    static class ViewHolder extends  RecyclerView.ViewHolder{
        CardView mCardView;
        ImageView mImageView;
        TextView cname;
        public ViewHolder(View mView){
            super(mView);
            mCardView =(CardView) mView;
            mImageView = (ImageView) mView.findViewById(R.id.image_category);
            cname = (TextView) mView.findViewById(R.id.textView);
        }


    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    //define interface
    public static interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

    public Category.DataBean getCates(int position){
        return   mCategories.get(position);
    }
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    public CategoryAdapter(List<Category.DataBean> categories){
        this.mCategories = categories;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.content_category,parent,false);
        ViewHolder vh = new ViewHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
         Category.DataBean category = mCategories.get(position);

        holder.cname.setText(category.getCname());
        if (category.getCpic()==null){
            Glide.with(mContext).load(Constant.CATEGORY).into(holder.mImageView);
        }
        Glide.with(mContext).load(category.getCpic()).placeholder(R.drawable.cate).into(holder.mImageView);
          holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }



}
