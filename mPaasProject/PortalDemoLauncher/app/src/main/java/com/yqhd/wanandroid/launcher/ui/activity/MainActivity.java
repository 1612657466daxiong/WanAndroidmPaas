package com.yqhd.wanandroid.launcher.ui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;


import com.alipay.mobile.framework.app.ui.BaseActivity;
import com.yqhd.wanandroid.launcher.*;
import com.yqhd.wanandroid.launcher.ui.fragment.HomePageFragment;
import com.yqhd.wanandroid.launcher.ui.fragment.KnowledgeFragment;
import com.yqhd.wanandroid.launcher.ui.fragment.NavigationFragment;
import com.yqhd.wanandroid.launcher.ui.fragment.ProjectFragment;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;


/**
 * Created by mPaaS on 16/9/28.
 */
public class MainActivity extends BaseActivity {
    DrawerLayout  mDrawableLayout;
    Toolbar mToolbar;
    TextView mTitleTv;
    FloatingActionButton mFloatingActionButton;
//    BottomNavigationBar mBottomNavigationBar;

    NavigationFragment mNavigationFragment;
    KnowledgeFragment mKnowledgeFragment;
    ProjectFragment mProjectFragment;
    HomePageFragment mHomePageFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
        setDefaultFragment();
        initDrawerLayout();
    }

    private void initDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,mDrawableLayout,mToolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close){
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //获取mDrawerLayout中的第一个子布局，也就是布局中的RelativeLayout
                //获取抽屉的view
                View mContent = mDrawableLayout.getChildAt(0);
                float scale = 1 - slideOffset;
                float endScale = 0.8f + scale * 0.2f;
                float startScale = 1 - 0.3f * scale;

                //设置左边菜单滑动后的占据屏幕大小
                drawerView.setScaleX(startScale);
                drawerView.setScaleY(startScale);
                //设置菜单透明度
                drawerView.setAlpha(0.6f + 0.4f * (1 - scale));

                //设置内容界面水平和垂直方向偏转量
                //在滑动时内容界面的宽度为 屏幕宽度减去菜单界面所占宽度
                mContent.setTranslationX(drawerView.getMeasuredWidth() * (1 - scale));
                //设置内容界面操作无效（比如有button就会点击无效）
                mContent.invalidate();
                //设置右边菜单滑动后的占据屏幕大小
                mContent.setScaleX(endScale);
                mContent.setScaleY(endScale);
            }
        };
        toggle.syncState();
        mDrawableLayout.setDrawerListener(toggle);
    }

    private void setDefaultFragment() {
        FragmentManager fragmentManager =getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        mHomePageFragment = new HomePageFragment();
        transaction.replace(R.id.fragment_group, mHomePageFragment);
        transaction.commit();
    }

    private void initView() {
        mDrawableLayout = findViewById(R.id.drawer_layout);
        mToolbar = findViewById(R.id.common_toolbar);
//        mBottomNavigationBar = findViewById(R.id.bottom_mian_naviagtion_bar);
//        mBottomNavigationBar
//                .addItem(new BottomNavigationItem(R.drawable.icon_home_pager_not_selected,"首页"))
//                .addItem(new BottomNavigationItem(R.drawable.icon_knowledge_hierarchy_not_selected,"知识体系"))
//                .addItem(new BottomNavigationItem(R.drawable.icon_navigation_not_selected,"导航"))
//                .addItem(new BottomNavigationItem(R.drawable.icon_project_not_selected,"项目"))
//                .setFirstSelectedPosition(0)
//                .initialise();
//        mBottomNavigationBar.setTabSelectedListener(this);
    }

//
//    @Override
//    public void onTabSelected(int position) {
//        FragmentManager fm = this.getFragmentManager();
//        //开启事务
//        FragmentTransaction transaction = fm.beginTransaction();
//        switch (position){
//            case 0:
//                if (mHomePageFragment.equals(null)){
//                    mHomePageFragment = new HomePageFragment();
//                }
//                transaction.replace(R.id.fragment_group, mHomePageFragment);
//                break;
//            case 1:
//                if (mKnowledgeFragment.equals(null)){
//                    mKnowledgeFragment = new KnowledgeFragment();
//                }
//                transaction.replace(R.id.fragment_group, mKnowledgeFragment);
//                break;
//            case 2:
//                if (mNavigationFragment.equals(null)){
//                    mNavigationFragment = new NavigationFragment();
//                }
//                transaction.replace(R.id.fragment_group, mNavigationFragment);
//                break;
//            case 3:
//                if (mProjectFragment.equals(null)){
//                    mProjectFragment = new ProjectFragment();
//                }
//                transaction.replace(R.id.fragment_group, mProjectFragment);
//                break;
//            default:
//                break;
//        }
//        transaction.commit();
//    }
//
//    @Override
//    public void onTabUnselected(int position) {
//
//    }
//
//    @Override
//    public void onTabReselected(int position) {
//
//    }
}
