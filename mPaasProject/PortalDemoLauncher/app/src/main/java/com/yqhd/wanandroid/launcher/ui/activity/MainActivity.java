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
import com.yqhd.wanandroid.launcher.utils.StatusBarUtil;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


/**
 * Created by mPaaS on 16/9/28.
 */
public class MainActivity extends BaseActivity {
    DrawerLayout  mDrawableLayout;
    Toolbar mToolbar;
    TextView mTitleTv;
    FloatingActionButton mFloatingActionButton;

    RadioGroup rdgTab;
    RadioButton rbtnHome,rbtnKnowledge,rbtnNavigation,rbtnProject;


    NavigationFragment mNavigationFragment;
    KnowledgeFragment mKnowledgeFragment;
    ProjectFragment mProjectFragment;
    HomePageFragment mHomePageFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
        initToolbar();
        initListener();
        setDefaultFragment();
        initDrawerLayout();
    }

    private void initToolbar() {
        StatusBarUtil.setStatusColor(getWindow(), ContextCompat.getColor(this, R.color.textInputHintColor), 1f);
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
        mTitleTv.setText(getString(R.string.home_pager));
        rbtnHome.setChecked(true);
    }

    private void initView() {
        mDrawableLayout = findViewById(R.id.drawer_layout);
        mToolbar = findViewById(R.id.common_toolbar);
        rdgTab = findViewById(R.id.rbg_mian);
        mTitleTv = findViewById(R.id.common_toolbar_title_tv);
        rbtnHome = findViewById(R.id.rbtn_home_pager);
    }

    private void initListener() {
        rdgTab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                FragmentTransaction transaction =getFragmentManager().beginTransaction();
                hideAllFragment(transaction);
                switch (i){
                    case R.id.rbtn_home_pager:
                        if(mHomePageFragment==null){
                            mHomePageFragment = new HomePageFragment();
                            transaction.add(R.id.fragment_group,mHomePageFragment);
                        }else{
                            transaction.show(mHomePageFragment);
                        }
                        mTitleTv.setText(getString(R.string.home_pager));
                        break;
                    case R.id.rbtn_knoweldge:
                        if(mKnowledgeFragment==null){
                            mKnowledgeFragment = new KnowledgeFragment();
                            transaction.add(R.id.fragment_group,mKnowledgeFragment);
                        }else{
                            transaction.show(mKnowledgeFragment);
                        }
                        mTitleTv.setText(getString(R.string.knowledge_hierarchy));
                        break;
                    case R.id.rbtn_navigation:
                        if(mNavigationFragment==null){
                            mNavigationFragment = new NavigationFragment();
                            transaction.add(R.id.fragment_group,mNavigationFragment);
                        }else{
                            transaction.show(mNavigationFragment);
                        }
                        mTitleTv.setText(getString(R.string.navigation));
                        break;
                    case R.id.rbtn_project:
                        if(mProjectFragment==null){
                            mProjectFragment = new ProjectFragment();
                            transaction.add(R.id.fragment_group,mProjectFragment);
                        }else{
                            transaction.show(mProjectFragment);
                        }
                        mTitleTv.setText(getString(R.string.project));
                        break;
                }
                transaction.commit();
            }
        });
    }


    public void hideAllFragment(FragmentTransaction transaction) {
        if (mHomePageFragment != null) {
            transaction.hide(mHomePageFragment);
        }
        if (mKnowledgeFragment != null) {
            transaction.hide(mKnowledgeFragment);
        }
        if (mNavigationFragment != null) {
            transaction.hide(mNavigationFragment);
        }
        if (mProjectFragment != null) {
            transaction.hide(mProjectFragment);
        }
    }
}
