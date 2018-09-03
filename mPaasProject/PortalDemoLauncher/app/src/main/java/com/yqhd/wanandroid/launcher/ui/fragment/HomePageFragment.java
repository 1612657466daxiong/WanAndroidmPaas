package com.yqhd.wanandroid.launcher.ui.fragment;


import android.app.ActivityOptions;

import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.alipay.mobile.framework.LauncherApplicationAgent;
import com.alipay.mobile.h5container.api.H5Bundle;
import com.alipay.mobile.h5container.api.H5Param;
import com.alipay.mobile.h5container.service.H5Service;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


import com.youth.banner.Banner;

import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.yqhd.wanandroid.launcher.R;
import com.yqhd.wanandroid.launcher.adapter.ArticleListAdapter;
import com.yqhd.wanandroid.launcher.app.Constants;
import com.yqhd.wanandroid.launcher.bean.BannerData;
import com.yqhd.wanandroid.launcher.bean.FeedArticleData;
import com.yqhd.wanandroid.launcher.bean.FeedListData;
import com.yqhd.wanandroid.launcher.bean.Result;
import com.yqhd.wanandroid.launcher.bean.User;
import com.yqhd.wanandroid.launcher.db.SharedPreferenceDao;
import com.yqhd.wanandroid.launcher.request.MPaasAPIUtils;
import com.yqhd.wanandroid.launcher.request.RequstDao;
import com.yqhd.wanandroid.launcher.request.bean.loginBean;
import com.yqhd.wanandroid.launcher.request.req.ArticleListPageJsonGetReq;
import com.yqhd.wanandroid.launcher.request.req.UserLoginPostReq;
import com.yqhd.wanandroid.launcher.ui.activity.LoginActivity;
import com.yqhd.wanandroid.launcher.ui.activity.WebActivity;
import com.yqhd.wanandroid.launcher.utils.GlideImageLoader;
import com.yqhd.wanandroid.launcher.utils.ResultUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment {
    private View view;
    private Banner mBanner;
    private RecyclerView mRecycleView;
    private List<String> mBannerTitleList;
    private List<String> mBannerUrlList;
    private List<FeedArticleData> mFeedArticleDataList;
    private ArticleListAdapter mAdapter;
    private SmartRefreshLayout mRefreshLayout;
    private int articlePosition;
    private boolean isRecreate;
    int pageNum =1;
    public HomePageFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      view = inflater.inflate(R.layout.fragment_home_page, container, false);
      initView();
      autoLogin();
      initBannerData();
      initFeedList();
      DownLoadFeedList(1,false);
      setRefresh();
      return view;
    }

    private void setRefresh() {
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageNum = 1;
                DownLoadFeedList(pageNum,false);
                refreshlayout.finishRefresh(1000);
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                pageNum++;
                DownLoadFeedList(pageNum,true);
                refreshlayout.finishLoadmore(1000);
            }
        });
    }

    private void autoLogin() {
        if (SharedPreferenceDao.getInstance(getActivity()).getUser()!=null){
            Autologin();
        }else {
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }
    }

    private void Autologin() {
        String username = SharedPreferenceDao.getInstance(getActivity()).getUser();
        String pwd = SharedPreferenceDao.getInstance(getActivity()).getPwd();
        UserLoginPostReq req = new UserLoginPostReq();
        req._requestBody = new loginBean(username,pwd);
        RequstDao.Login(getActivity(), req, new MPaasAPIUtils.OnCompleteListener<String>() {
            @Override
            public void onSuccess(String result) {
                if (isAdded()) {
                    Result resultFromJson = ResultUtil.getResultFromJson(result, User.class);
                    User user = (User) resultFromJson.getData();
                    if (user!=null){
                        //使用Snackbar失败
                        Toast.makeText(getActivity(), "自动登录成功", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void DownLoadFeedList(int page, final boolean isLoadmore) {
        ArticleListPageJsonGetReq req = new ArticleListPageJsonGetReq();
        req.page = page;
        RequstDao.GetFeedList(getActivity(), req, new MPaasAPIUtils.OnCompleteListener<String>() {
            @Override
            public void onSuccess(String result) {
                Result listResultFromJson = ResultUtil.getResultFromJson(result, FeedListData.class);
                FeedListData datas = (FeedListData) listResultFromJson.getData();
                if (isLoadmore)
                    mAdapter.addData(datas.getDatas());
                else
                    mAdapter.replaceData(datas.getDatas());
            }

            @Override
            public void onError(String error) {
                //数据错误显示
            }
        });
    }

    private void initFeedList() {
        mFeedArticleDataList = new ArrayList<>();
        mAdapter = new ArticleListAdapter(R.layout.item_feed_layout,mFeedArticleDataList);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startArticleDetailPager(view,position);
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    private void startArticleDetailPager(View view, int position) {
        if (mAdapter.getData().size()<=0 || mAdapter.getData().size()<position){
            return;
        }
        articlePosition = position;
        Intent intent = new Intent(getActivity(), WebActivity.class);
        intent.putExtra(Constants.ARTICLE_ID, mAdapter.getData().get(position).getId());
        intent.putExtra(Constants.ARTICLE_TITLE,  mAdapter.getData().get(position).getTitle());
        intent.putExtra(Constants.ARTICLE_LINK,  mAdapter.getData().get(position).getLink());
        intent.putExtra(Constants.IS_COLLECT, mAdapter.getData().get(position).isCollect());
        intent.putExtra(Constants.IS_COLLECT_PAGE, false);
        intent.putExtra(Constants.IS_COMMON_SITE, false);
        getActivity().startActivity(intent);
    }

    private void initBannerData() {
        RequstDao.GetBanner(getActivity(), new MPaasAPIUtils.OnCompleteListener<String>() {
            @Override
            public void onSuccess(String s) {
                Result result = ResultUtil.getListResultFromJson(s, BannerData.class);
                ArrayList<BannerData> bannerDataList = (ArrayList<BannerData>) result.getData();

                mBannerTitleList = new ArrayList<>();
                List<String> bannerImageList = new ArrayList<>();
                mBannerUrlList = new ArrayList<>();
                for (BannerData bannerData : bannerDataList) {
                    mBannerTitleList.add(bannerData.getTitle());
                    bannerImageList.add(bannerData.getImagePath());
                    mBannerUrlList.add(bannerData.getUrl());
                }
                //设置banner样式
                mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
                //设置图片加载器
                mBanner.setImageLoader(new GlideImageLoader());
                //设置图片集合
                mBanner.setImages(bannerImageList);
                //设置banner动画效果
                mBanner.setBannerAnimation(Transformer.DepthPage);
                //设置标题集合（当banner样式有显示title时）
                mBanner.setBannerTitles(mBannerTitleList);
                //设置自动轮播，默认为true
                mBanner.isAutoPlay(true);
                //设置轮播时间
                mBanner.setDelayTime(bannerDataList.size() * 400);
                //设置指示器位置（当banner模式中有指示器时）
                mBanner.setIndicatorGravity(BannerConfig.CENTER);

                mBanner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Intent intent = new Intent(getActivity(), WebActivity.class);
                        intent.putExtra(Constants.ARTICLE_ID, 0);
                        intent.putExtra(Constants.ARTICLE_TITLE,  mBannerTitleList.get(position));
                        intent.putExtra(Constants.ARTICLE_LINK, mBannerUrlList.get(position));
                        intent.putExtra(Constants.IS_COLLECT, false);
                        intent.putExtra(Constants.IS_COLLECT_PAGE, false);
                        intent.putExtra(Constants.IS_COMMON_SITE, true);
                        getActivity().startActivity(intent);
                    }
                });
                //banner设置方法全部调用完毕时最后调用
                mBanner.start();
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void initView() {
        mBanner = view.findViewById(R.id.head_banner);
        mRecycleView = view.findViewById(R.id.home_recyclerview);
        mRefreshLayout = view.findViewById(R.id.normal_view);
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mBanner != null) {
            mBanner.startAutoPlay();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mBanner != null) {
            mBanner.stopAutoPlay();
        }
    }


}
