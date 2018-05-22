package cn.studyhuang.cook.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.studyhuang.cook.MyApplication;
import cn.studyhuang.cook.R;
import cn.studyhuang.cook.activity.AddPingjiaActivity;
import cn.studyhuang.cook.api.MenuAPI;
import cn.studyhuang.cook.pojo.Evaluate;
import cn.studyhuang.cook.utils.Constant;
import cn.studyhuang.cook.utils.DMUtil;
import cn.studyhuang.cook.utils.JsonListResult;
import cn.studyhuang.cook.utils.RetrofitUtils;
import cn.studyhuang.cook.utils.ToastUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.makeramen.roundedimageview.RoundedImageView.TAG;

/**
 * Created by huang on 2018/5/2.
 *
 */
public class EvaluateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    private Long mid = null;
    Gson gson = new GsonBuilder().create();
    private List<Evaluate> evaluates;


    public EvaluateAdapter(Context context,List<Evaluate> evaluates) {
       // this.mid = mid;
        this.context = context;
        this.evaluates = evaluates;
    }


    @Override
    public int getItemCount() {
//        if (evaluates.size()==0){
//            return 1;
//        }
        return evaluates.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_pingjia,parent, false);

        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CommentViewHolder) {

           int index = getItemCount() - position - 1;
          final Evaluate bean = evaluates.get(index);
            Glide.with(context)
                    .load(Constant.AVATER)
                    .into(((CommentViewHolder) holder).avatar);
//            Glide.with(context)
//                    .load(Constant.AVATER)
//                    .transform(new GlideGircleTransform(context))
//                    .into(((CommentViewHolder) holder).avatar);
            ((CommentViewHolder) holder).nickname.setText(bean.getUserId()+"");
           ((CommentViewHolder) holder).floor.setText( (position+1)+"楼");
            final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(bean.getEvaluteDate());
          ((CommentViewHolder) holder).date.setText(sdf.format(calendar.getTime()));
           ((CommentViewHolder) holder).content.setText(bean.getEvaluateContent());
            ((CommentViewHolder) holder).mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 //   ToastUtils.showToast(MyApplication.getContext(),"sdsdsds");
                    Intent intent = new Intent(context, AddPingjiaActivity.class);
                    Bundle bundle =new Bundle();
                     bundle.putSerializable("evaluate",bean);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });

            Retrofit retrofit = RetrofitUtils.getRetrofit();

            MenuAPI menuAPI = retrofit.create(MenuAPI.class);

            Call<JsonListResult<Evaluate>> call = menuAPI.getReplys(bean.getEvaluateId());
            call.enqueue(new Callback<JsonListResult<Evaluate>>() {
                @Override
                public void onResponse(Call<JsonListResult<Evaluate>> call, Response<JsonListResult<Evaluate>> response) {
                    if (response.body().getData() != null) {
                        List<Evaluate> evaluates = response.body().getData();

                        for (int i = 0; i < evaluates.size(); i++) {
                            //  Comment.ReviewListBean.ChildListBean listBean = bean.getChild_list().get(i);

                            //容器
                            RelativeLayout inner = new RelativeLayout(context);
                            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
                            );
                            int scale = DMUtil.getScale(context, 10);
                            params.topMargin = DMUtil.getScale(context, 10);
                            params.addRule(RelativeLayout.BELOW, R.id.tv_comment_content);
                            inner.setPadding(scale, scale, scale, scale);
                            inner.setLayoutParams(params);

                            //虚线
                            TextView xuxian = new TextView(context);
                            ViewGroup.LayoutParams line = new ViewGroup.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT, 3
                            );
//                xuxian.setBackgroundResource(R.drawable.xuxian);
                            xuxian.setLayoutParams(line);
                            int xuxian_id = View.generateViewId();
                            xuxian.setId(xuxian_id);
                            inner.addView(xuxian);

                            //内容
                            TextView content = new TextView(context);
                            RelativeLayout.LayoutParams con = new RelativeLayout.LayoutParams(
                                    RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT
                            );
                            con.topMargin = DMUtil.getScale(context, 10);
                            String name = "<font color=\"#33b5e5\">" + evaluates.get(i).getUserId() + "</font>";
                            content.setText(Html.fromHtml(
                                    name + "：" +evaluates.get(i).getEvaluateContent()
                            ));// "回复内容"
                            int content_id = View.generateViewId();
                            content.setId(content_id);
                            con.addRule(RelativeLayout.BELOW, xuxian_id);
                            content.setLayoutParams(con);
                            inner.addView(content);

                            ((CommentViewHolder) holder).root.addView(inner);


                        }}
//                    }else {
//                      //  ToastUtils.showToast(MyApplication.getContext(),"没回复，别慌");
//                    }
                }

                @Override
                public void onFailure(Call<JsonListResult<Evaluate>> call, Throwable t) {
                    ToastUtils.showToast(MyApplication.getContext(),"网络故障");
                }
            });



 //               ((CommentViewHolder) holder).inner.setVisibility(View.VISIBLE);

//                if (bean.getChild_list().size() != 0) {
//
//                    String name = "<font color=\"#33b5e5\">" + bean.getChild_list().get(0).getName() + "</font>";
//                    ((CommentViewHolder) holder).inner_content.setText(Html.fromHtml(
//                            name + "：" + bean.getChild_list().get(0).getReplay()
//                    ));
//                }

        }
    }


    class CommentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_comment_avatar)
        ImageView avatar;
        @BindView(R.id.tv_comment_nickname)
        TextView nickname;
        @BindView(R.id.tv_comment_floor)
        TextView floor;
        @BindView(R.id.tv_comment_date)
        TextView date;
        @BindView(R.id.tv_comment_content)
        TextView content;
        //回复
        @BindView(R.id.rl_inner_comment)
        RelativeLayout inner;
        @BindView(R.id.tv_replycomond)
        TextView inner_content;
        @BindView(R.id.rl_comment_root)
        LinearLayout root;

        @BindView(R.id.pinglun)
        ImageView mImageView;

        public CommentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    /**
     * 获取每条评论下特定的回复
     */
    public void getReply(){



    }


}

