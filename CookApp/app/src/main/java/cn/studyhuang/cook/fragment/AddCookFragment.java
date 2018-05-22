package cn.studyhuang.cook.fragment;


import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import butterknife.BindView;
import butterknife.OnClick;
import cn.studyhuang.cook.MyApplication;
import cn.studyhuang.cook.R;
import cn.studyhuang.cook.activity.CooksActivity;
import cn.studyhuang.cook.activity.LoginActivity;
import cn.studyhuang.cook.activity.MenusActivity;
import cn.studyhuang.cook.api.RetrofitAPI;
import cn.studyhuang.cook.utils.RetrofitUtils;
import cn.studyhuang.cook.utils.ToastUtils;
import cn.studyhuang.cook.utils.UploadResult;
import cn.studyhuang.cook.utils.UploadRetrofit;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Url;

import static android.app.Activity.RESULT_OK;

/**
 *添加菜谱
 * create by huang
 *
 */
public class AddCookFragment extends BaseFragment {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.upLoad_image1)
    ImageView upLoadImage1;
    @BindView(R.id.desc)
    EditText imageDesc;
    @BindView(R.id.menu_name)
    EditText menuName;
    @BindView(R.id.submits)
    Button mButton;
//    @BindView(R.id.spinner)
//    Spinner mSpinner;

    private static final String[] PERMISSION_EXTERNAL_STORAGE = new String[] {
            Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private static final int REQUEST_EXTERNAL_STORAGE = 100;

    private final int IMAGE_OPEN = 1; //打开图片标记
    private final int CAPTURE_OPEN = 3; //打开相机
    private final int CAIJIAN = 4; //裁剪
    private String pathImage = null;
    private String userId;

    @OnClick({R.id.upLoad_image1,R.id.submits})
    public void onClick(View view){
      switch (view.getId()){
          case R.id.upLoad_image1:
              //上传图片
              showChoise();
              break;
          case R.id.submits:
              //提交代码
              SharedPreferences sp = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
              if (sp == null) {
                  ToastUtils.showToast(MyApplication.getContext(),"没登录不能添加菜谱");
                  Intent login = new Intent(MyApplication.getContext(), LoginActivity.class);
                  startActivity(login);
                  break;
              }
              userId= (String) sp.getString("userId","");
              uploadImage();
              break;
          default:
              break;
      }
    }

    @Override
    protected void initViews() {
     //  getActivity().setSupportActionBar(mToolbar);
        mToolbar.setTitle("添加菜谱");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_add_cook;
    }

    @Override
    protected void afterCreate(Bundle saveInstanceState) {

    }

    @Override
    protected void lazyLoad() {

    }

    private String temppic = "";
    private String[] cities = {"相机拍照", "本地图片"};
    private void showChoise() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy( builder.build() );
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("选择图片来源");
        builder.setItems(cities, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(cities[which].equals("相机拍照")){
                    File file = null;
                    temppic = UUID.randomUUID()+".jpg";

                    try {
                        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                                + File.separator + temppic);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

//                   /* Uri uri = Uri.fromFile(file);*/
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    //判断版本
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //如果在Android7.0以上,使用FileProvider获取Uri
                        intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                        Uri contentUri = FileProvider.getUriForFile(MyApplication.getContext(), "cn.studyhuang.cook", file);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
                    } else {    //否则使用Uri.fromFile(file)方法获取Uri
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                    }
                    intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
                  //  intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    startActivityForResult(intent, CAPTURE_OPEN);
                }else if(cities[which].equals("本地图片")){
                    openFile(IMAGE_OPEN);
                }
            }
        });
        builder.show();
    }

    private void openFile(int openType){
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, openType);
    }

    private void setImageView(String url,ImageView iv){
        Glide.with(MyApplication.getContext())
                .load(url)
                .placeholder(R.drawable.upload)
                .into(iv);//.error(R.drawable.upload)

    }

    private void setImageViewFile(Uri url, ImageView iv){
        Glide.with(MyApplication.getContext())
                .load(url)
                .placeholder(R.drawable.upload)
                .error(R.drawable.upload)
                .into(iv);//
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        verifyStoragePermissions(getActivity());
        //获取图片路径 响应startActivityForResult
     //   super.onActivityResult(requestCode, resultCode, data);
        //打开图片
        if (resultCode == RESULT_OK && requestCode == IMAGE_OPEN) {
            if (data == null) {
                ToastUtils.showToast(MyApplication.getContext(),"选择图片文件出错");
                return;
            }
            Uri uri = data.getData();
            if (!TextUtils.isEmpty(uri.getAuthority())) {
                //查询选择图片
                //外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口
              //  ContentResolver resolver = getContentResolver();

                Cursor cursor = getActivity().getContentResolver().query(uri,
                        new String[]{MediaStore.Images.Media.DATA}, null, null, null);
                //返回 没找到选择图片
                if (cursor == null) {
                    ToastUtils.showToast(MyApplication.getContext(), "没找到选择图片");
                    return;
                }
                //光标移动至开头 获取图片路径
                cursor.moveToFirst();
                pathImage = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                setImageView(pathImage, upLoadImage1);

            } else {
                ToastUtils.showToast(MyApplication.getContext(), "没找到文件");
            }
        } else if (resultCode == RESULT_OK && requestCode == CAPTURE_OPEN) {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + temppic);
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(Uri.fromFile(file), "image/*");
            intent.putExtra("crop", "true");
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("outputX", 600);
            intent.putExtra("outputY", 600);
            intent.putExtra("output", Uri.fromFile(file));
            intent.putExtra("return-data", true);
            this.startActivityForResult(intent, CAIJIAN);
            //裁剪
        } else if (requestCode == CAIJIAN) {
            pathImage = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + temppic;
            setImageView(pathImage, upLoadImage1);
       //     setImageViewFile(Uri.fromFile(file),upLoadImage1);
//            verifyStoragePermissions(getActivity());
//            BitmapFactory.Options option = new BitmapFactory.Options();
//            // 压缩图片:表示缩略图大小为原始图片大小的几分之一，1为原图
//            option.inSampleSize = 1;
//            // 根据图片的SDCard路径读出Bitmap
//            Bitmap bm = BitmapFactory.decodeFile(pathImage, option);
//            // 显示在图片控件上
//            upLoadImage1.setImageBitmap(bm);
///storage/emulated/0/f0030e35-78cd-4d93-b0ef-5eba08e75200.jpg
        }
      //  super.onActivityResult(requestCode, resultCode, data);
    }
    //shangchaun
        public void uploadImage(){
            String menuNames = menuName.getText().toString();
            String desc =imageDesc.getText().toString();
            if (menuNames == null ||menuNames.length()<1 ){
                ToastUtils.showToast(MyApplication.getContext(),"请填写菜名");
                return;
            }
            if (pathImage == null && pathImage.length()<1){
                ToastUtils.showToast(MyApplication.getContext(),"请上传图片");
                return;
            }

            UploadRetrofit uploadRetrofit = UploadRetrofit.getInstance();
            uploadRetrofit.clear();
            uploadRetrofit.addParameter("mpic",new File(pathImage));
            String cid ="32";
          // RetrofitAPI retrofitAPI =  retrofit.create(RetrofitAPI.class);
            Map<String, RequestBody> params = uploadRetrofit
                    .addParameter("mname",menuNames)
                    .addParameter("mdesc",desc)
                    .addParameter("cid",cid)
                    .addParameter("userId",userId)
                    .bulider();

           Call<UploadResult> call = uploadRetrofit.mRetrofitAPI.upload(params);
            call.enqueue(new Callback<UploadResult>() {
                @Override
                public void onResponse(Call<UploadResult> call, Response<UploadResult> response) {
                    if (response.body()!=null) {
                    //    ToastUtils.showToast(MyApplication.getContext(), "SUCCESS");
                        Intent intent = new Intent(MyApplication.getContext(), CooksActivity.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<UploadResult> call, Throwable t) {
                    Intent intent = new Intent(MyApplication.getContext(), CooksActivity.class);
                    startActivity(intent);
                    //ToastUtils.showToast(MyApplication.getContext(),"error");
                }
            });
    }


    private void verifyStoragePermissions(Activity activity) {
        int permissionWrite = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permissionWrite != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSION_EXTERNAL_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }


}




