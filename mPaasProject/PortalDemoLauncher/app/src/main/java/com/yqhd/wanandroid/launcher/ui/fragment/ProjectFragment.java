package com.yqhd.wanandroid.launcher.ui.fragment;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alipay.mobile.framework.app.fragment.BaseFragment;
import com.flyco.tablayout.SlidingTabLayout;

import com.yqhd.wanandroid.launcher.R;
import com.yqhd.wanandroid.launcher.app.Constants;
import com.yqhd.wanandroid.launcher.bean.ProjectClassifyData;
import com.yqhd.wanandroid.launcher.bean.Result;
import com.yqhd.wanandroid.launcher.request.MPaasAPIUtils;
import com.yqhd.wanandroid.launcher.request.RequstDao;
import com.yqhd.wanandroid.launcher.utils.ResultUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends Fragment {
    View view;
    SlidingTabLayout mTabLayout;
    ViewPager mViewPager;


    private List<ProjectClassifyData> mData;
    private List<Fragment> mFragments = new ArrayList<>();
    private int currentPage;

    public ProjectFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_project, container, false);
        initView();
//        initViewPagerAndTab();
        initData();
        return view;
    }

    private void initData() {
        RequstDao.GetProjectTypes(getActivity(), new MPaasAPIUtils.OnCompleteListener<String>() {
            @Override
            public void onSuccess(String result) {
                Result listResultFromJson = ResultUtil.getListResultFromJson(result, ProjectClassifyData.class);
                ArrayList<ProjectClassifyData> listDatas = (ArrayList<ProjectClassifyData>) listResultFromJson.getData();
                mData = listDatas;
                initViewPagerAndTab();
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void initViewPagerAndTab() {
        for (ProjectClassifyData data:mData){
            ProjectLIstragment projectLIstragment =ProjectLIstragment.getInstance(data.getId(),null);
            mFragments.add(projectLIstragment);
        }
        mViewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mFragments.get(i);
            }

            @Override
            public int getCount() {
                return mData == null? 0 : mData.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mData.get(position).getName();
            }
        });


        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                currentPage = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mTabLayout.setViewPager(mViewPager);
        mViewPager.setCurrentItem(0);
    }

    private void initView() {
        mTabLayout = view.findViewById(R.id.project_tab_layout);
        mViewPager = view.findViewById(R.id.project_viewpager);

    }

}
