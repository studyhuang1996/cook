package cn.studyhuang.cook.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import java.util.List;

import cn.studyhuang.cook.MyApplication;
import cn.studyhuang.cook.R;
import cn.studyhuang.cook.activity.CooksActivity;
import cn.studyhuang.cook.pojo.Menu;

import static cn.studyhuang.cook.MyApplication.initImageLoader;

/**
 * Created by huang on 2018/1/13.
 */

public class MenuListAdapter extends BaseAdapter{

    List<Menu> mMenus;
    Context mContext;
    private MyItemClickListener myItemClickListener;
    public MenuListAdapter(Context context, List<Menu> menus) {
        super();
        mContext = context;
        mMenus = menus;
    }

    public void setMyItemClickListener(MyItemClickListener myItemClickListener) {
        this.myItemClickListener = myItemClickListener;
    }

    public MyItemClickListener getMyItemClickListener() {
        return myItemClickListener;
    }

    public interface MyItemClickListener {
        public void onItemClick(View view,int postion);
    }
    /**
     * 设置Item点击监听
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener){
        this.myItemClickListener = listener;
    }

    public  Menu getMenus(int position){
        return   mMenus.get(position);
    }

    public static class ViewHolder implements View.OnClickListener{
        ImageView dishImageView;
        TextView titleTextView;
        MyItemClickListener mItemClickListener;
        int mPosition;

        ViewHolder(View view, MyItemClickListener myItemClickListener, int position) {
            dishImageView= (ImageView) view.findViewById(R.id.user_back_image);
            titleTextView= (TextView) view.findViewById(R.id.title_text);
            mItemClickListener=myItemClickListener;
            mPosition = position;
            view.setOnClickListener(this);

        }
        /**
         * 点击监听
         */
        @Override
        public void onClick(View v) {
            if(mItemClickListener != null){
                mItemClickListener.onItemClick(v, mPosition);
            }
        }

    }

    @Override
    public int getCount() {
        return mMenus.size();
    }

    public List<Menu> getMenus() {
        return mMenus;
    }

    public void setMenus(List<Menu> menus) {
        mMenus = menus;
    }

    @Override
    public Object getItem(int position) {
        return mMenus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(null==convertView){
            convertView=View.inflate(mContext, R.layout.cook_item, null);
            holder = new ViewHolder(convertView, myItemClickListener, position);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        Menu menu=(Menu) getItem(position);
        //显示作品图片的配置
        DisplayImageOptions dishOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.loading_large)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
      // imageLoader.init(ImageLoaderConfiguration.createDefault(MainActivity.this));
        Context context = MyApplication.getContext();
        initImageLoader(context);

        ImageLoader.getInstance().displayImage(menu.getMpic(), holder.dishImageView, dishOptions);
//        //显示头像图片的配置response.body()
//        DisplayImageOptions pictureSmallOptions = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.loading_small)
//                .showImageOnFail(R.drawable.user)
//                .cacheInMemory(true)
//                .cacheOnDisk(true)
//                .bitmapConfig(Bitmap.Config.RGB_565)
//                .build();
//        ImageLoader.getInstance().displayImage(dishInfo.getPicture(), holder.userImageView, pictureSmallOptions);
        holder.titleTextView.setText(menu.getMname());
        
        return convertView;

    }


}
