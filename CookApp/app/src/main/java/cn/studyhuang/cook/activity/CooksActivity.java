package cn.studyhuang.cook.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import cn.studyhuang.cook.MyApplication;
import cn.studyhuang.cook.R;
import cn.studyhuang.cook.fragment.AddCookFragment;
import cn.studyhuang.cook.fragment.IndexFragment;
import cn.studyhuang.cook.fragment.MenuListFragment;
import cn.studyhuang.cook.fragment.MineFragment;
import cn.studyhuang.cook.fragment.MyFoodFragment;
import cn.studyhuang.cook.utils.Constant;
import cn.studyhuang.cook.utils.SPUtils;

public class CooksActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private IndexFragment mIndexFragment;
    private SearchView mSearchView;
    private VideoView video1;
    MediaController mediaco;
    private TextView eName;
    private TextView mEmail;
    private AppCompatButton mAppCompatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooks);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAppCompatButton = (AppCompatButton) findViewById(R.id.login_btn);

        //   mImageView =(ImageView) findViewById(R.id.imageView2);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.draw_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_cook);
        //设置侧滑菜单的值
        View header=navView.getHeaderView(0);
        eName=(TextView) header.findViewById(R.id.user_name);
        mEmail=(TextView) header.findViewById(R.id.user_email);
        final SharedPreferences sharedPreferences = getSharedPreferences("data",Context.MODE_PRIVATE);
         if (!(sharedPreferences.getString("username","").length()<0||
                 sharedPreferences.getString("username","").equals(""))){
             eName.setText(sharedPreferences.getString("username","姓名"));
             mEmail.setText(sharedPreferences.getString("email","邮箱"));
             mAppCompatButton.setVisibility(View.INVISIBLE);
         }else{
             eName.setText("");
             mEmail.setText("");
         }

        //设置首页视频播放
        video1 = (VideoView) findViewById(R.id.videoView2);
        mediaco = new MediaController(this);
        Uri uri = Uri.parse(Constant.VIDEOURL);
        //VideoView与MediaController进行关联
        //video1.setVideoPath(file.getAbsolutePath());
        video1.setMediaController(mediaco);
        video1.setVideoURI(uri);
        mediaco.setMediaPlayer(video1);

        //让VideiView获取焦点
        video1.requestFocus();
        video1.start();
        //     }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        //   navView.setCheckedItem(R.id.index);//默认选中


            mAppCompatButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(CooksActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            });

//        //设置菜单的点击事件
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.content,new MenuListFragment())
//                .addToBackStack(null).commit();
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                //   Log.e("cookactivity","item" +item.getItemId()+"dcs ");
                switch (item.getItemId()) {
                    case R.id.index://主页
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.content, new MenuListFragment())
                                .addToBackStack(null).commit();

                        break;
                    case R.id.my_home://个人中心
                        if(SPUtils.sharedPreferences ==null){
                            SPUtils.gotoLogin(MyApplication.getContext());
                        }
                        String userIds= (String) sharedPreferences.getString("userId","");
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.content,  MineFragment.newInstance(userIds))
                                .addToBackStack(null).commit();
                        break;
                    case R.id.my_foods://发布的菜谱
                        SharedPreferences sharedPreferences = getSharedPreferences("data",Context.MODE_PRIVATE);
                        if (sharedPreferences == null) {
                            Intent login = new Intent(CooksActivity.this, LoginActivity.class);
                            startActivity(login);
                            break;
                        }else {
                            String userId= (String) sharedPreferences.getString("userId","");
                            if (userId != null && userId.length()>0){
                                //查询本账号自己发布的菜谱

                                getSupportFragmentManager()
                                        .beginTransaction()
                                        .replace(R.id.content,  MyFoodFragment.newInstance(userId))
                                        .addToBackStack(null).commit();
                                break;
                            }
                        }
                    case R.id.category://分类
                        Intent user = new Intent(CooksActivity.this, CategoryActivity.class);
                        startActivity(user);
                        break;
                    case R.id.add_menus://添加菜谱
                        //如果登录状态可以添加菜谱
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.content,new AddCookFragment())
                                .addToBackStack(null).commit();
                        break;
                    case R.id.exit_cook:
                        SharedPreferences sp =getSharedPreferences("data",Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.clear();
                        editor.commit();
                        Intent intent = new Intent(MyApplication.getContext(),MenusActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        Intent login = new Intent(CooksActivity.this, LoginActivity.class);
                        startActivity(login);
                        break;
                }

                mDrawerLayout.closeDrawers();
                return true;
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu, menu);
     //   MenuItem searchItem = menu.findItem(R.id.menu_search);

        //通过MenuItem得到SearchView
     //  mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchMenuItem = menu.findItem(R.id.menus_search);
        SearchView searchView = null;
        if (searchMenuItem != null) {
            searchView = (SearchView) searchMenuItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(CooksActivity.this.getComponentName()));
        }
        SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setHintTextColor(Color.WHITE);
        searchAutoComplete.setTextColor(Color.WHITE);
        searchAutoComplete.setHint("搜美食");
        //设置监听事件
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Toast.makeText(MainActivity.this,"你查询了"+query,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CooksActivity.this, SearchActivity.class);
                intent.putExtra("query", query);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Toast.makeText(CooksActivity.this,"你输入了"+newText,Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        //设置搜索框样式
        View searchplate = (View) searchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
        //searchplate.setBackgroundResource(R.drawable.ic_search_white_24dp);

        ImageView searchCloseIcon = (ImageView) searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        searchCloseIcon.setImageResource(R.drawable.ic_close_white_24dp);

        ImageView voiceIcon = (ImageView) searchView.findViewById(android.support.v7.appcompat.R.id.search_voice_btn);
        voiceIcon.setImageResource(R.drawable.ic_settings_voice_white_24dp);

        //searchView.setSubmitButtonEnabled(true); // to enable submit button

        ImageView b = (ImageView) searchView.findViewById(android.support.v7.appcompat.R.id.search_button);
        b.setImageResource(R.drawable.ic_search_white_24dp); //to change submit button icon

        ImageView searchIcon = (ImageView) searchView.findViewById(android.support.v7.appcompat.R.id.search_mag_icon);
        searchIcon.setImageResource(R.drawable.ic_search_white_24dp);

//        switch (currentTab) {
//            case 0:
//                MenuItem miMap = menu.findItem(R.id.id_action_map);
//                miMap.setVisible(false);
//                break;
//            case 1:
//                MenuItem miSearch = menu.findItem(R.id.id_action_search);
//                miSearch.setVisible(false);
//                break;
//            case 2:
//                MenuItem mi1 = menu.findItem(R.id.id_action_map);
//                mi1.setVisible(false);
//                MenuItem mi2 = menu.findItem(R.id.id_action_search);
//                mi2.setVisible(false);
//            case 3:
//                MenuItem mi3 = menu.findItem(R.id.id_action_map);
//                mi3.setVisible(false);
//                MenuItem mi4 = menu.findItem(R.id.id_action_search);
//                mi4.setVisible(false);
//                break;
//            default:
//                break;
//        }

        return true;
    }




}



