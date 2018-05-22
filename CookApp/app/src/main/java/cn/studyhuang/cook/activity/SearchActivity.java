package cn.studyhuang.cook.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.arlib.floatingsearchview.FloatingSearchView;

import butterknife.BindView;
import cn.studyhuang.cook.R;
import cn.studyhuang.cook.fragment.CookFragment;


public class SearchActivity extends AppCompatActivity {



    private String query;



    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      //  mToolbar.setTitle("搜索结果");
        query = getIntent().getStringExtra("query");


      //  Toast.makeText(SearchActivity.this,"chaxun"+query,Toast.LENGTH_LONG).show();
//        mFloatingSearchView.setOnHomeActionClickListener(new FloatingSearchView.OnHomeActionClickListener() {
//            @Override
//            public void onHomeClicked() {
//                onBackPressed();
//            }
//        });
//        mFloatingSearchView.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
//            @Override
//            public void onActionMenuItemSelected(MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.id_action_search:
//                        doSearch(mFloatingSearchView.getQuery().toString());
//                        break;
//                }
//            }
//        });
        //搜索结果
        Fragment SearchResultFragment = CookFragment.newInstance(query);
        getSupportFragmentManager().beginTransaction().add(R.id.search_result, SearchResultFragment).commit();
    }

    private void doSearch(String query) {
        //搜索结果
        Fragment SearchResultFragment = CookFragment.newInstance(query);
        getSupportFragmentManager().beginTransaction().add(R.id.search_result, SearchResultFragment).commit();
    }
}
