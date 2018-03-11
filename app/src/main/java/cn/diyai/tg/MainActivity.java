package cn.diyai.tg;


import java.util.Locale;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;

import cn.diyai.tg.contract.IActivityLifeCycle;
import cn.diyai.tg.contract.UserInfoContract;
import cn.diyai.tg.model.UserInfoModel;
import cn.diyai.tg.presenter.UserInfoActivityPresenter;
import cn.diyai.tg.view.AboutFragment;
import cn.diyai.tg.view.FeedbackFragment;
import cn.diyai.tg.view.FlagLibFragment;
import cn.diyai.tg.view.MyFragment;
import cn.diyai.tg.view.PlanetFragment;
import cn.diyai.tg.view.SettingFragment;
import cn.diyai.tg.view.StatisticsFragment;
import cn.diyai.tg.view.TimeLoggerFragment;


public class MainActivity extends AppCompatActivity implements UserInfoContract.IUserInfoActivity{
    // Activity逻辑层接口
    private UserInfoContract.IUserInfoActivityPresenter mIActivityPresenter;
    // 生命周期接口
    private IActivityLifeCycle mIActivityLifeCycle;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mPlanetTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle();
        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mPlanetTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }

        // 初始化逻辑
        new UserInfoActivityPresenter(this);
        mIActivityPresenter.start();

        // View映射onCreate生命周期到Presenter
        mIActivityLifeCycle.onCreate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
//        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
//        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
//        switch(item.getItemId()) {
//            case R.id.action_websearch:
//                // create intent to perform web search for this planet
//                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
//                intent.putExtra(SearchManager.QUERY, getSupportActionBar().getTitle());
//                // catch event that there's no activity to handle intent
//                if (intent.resolveActivity(getPackageManager()) != null) {
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
//                }
//                return true;
//            default:
                return super.onOptionsItemSelected(item);
//        }
    }





    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                //我的
                fragment = new MyFragment();
                break;

            case 1:
                //时间日志
                fragment = new TimeLoggerFragment();
                break;

            case 2:
                //标签库
                fragment = new FlagLibFragment();
                break;

            case 3:
                //统计
                fragment = new StatisticsFragment();
                break;

            case 4:
                //设置
                fragment = new SettingFragment();
                break;

            case 5:
                //反馈与建议
                fragment = new FeedbackFragment();
                break;

            case 6:
                //关于
                fragment = new AboutFragment();
                break;
            default:
                fragment = new PlanetFragment();
                break;


        }
        // update the main content by replacing fragments
        Bundle args = new Bundle();
        args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
        fragment.setArguments(args);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void setPresenter(UserInfoContract.IUserInfoActivityPresenter mIActivityPresenter) {
        this.mIActivityPresenter = mIActivityPresenter;
    }

    @Override
    public void setILifeCycle(IActivityLifeCycle mIActivityLifeCycle) {
        this.mIActivityLifeCycle = mIActivityLifeCycle;
    }

    @Override
    public void showLoading() {
        Toast.makeText(this, "正在加载", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dismissLoading() {
        Toast.makeText(this, "加载完成", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserInfo(UserInfoModel userInfoModel) {

    }

    @Override
    public String loadUserId() {
        return null;
    }

    @Override
    protected void onRestart() {
        mIActivityLifeCycle.onRestart();
        super.onRestart();
    }

    @Override
    protected void onStart() {
        mIActivityLifeCycle.onStart();
        super.onStart();
    }

    @Override
    protected void onResume() {
        mIActivityLifeCycle.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mIActivityLifeCycle.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        mIActivityLifeCycle.onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mIActivityLifeCycle.onDestroy();
        super.onDestroy();
    }
}