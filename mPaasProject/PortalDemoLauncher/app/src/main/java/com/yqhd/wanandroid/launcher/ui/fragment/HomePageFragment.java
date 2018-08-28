package com.yqhd.wanandroid.launcher.ui.fragment;


import android.app.Fragment;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.yqhd.wanandroid.launcher.R;
import com.yqhd.wanandroid.launcher.adapter.ArticleListAdapter;
import com.yqhd.wanandroid.launcher.bean.BannerData;
import com.yqhd.wanandroid.launcher.bean.FeedArticleData;
import com.yqhd.wanandroid.launcher.bean.FeedListData;
import com.yqhd.wanandroid.launcher.bean.Result;
import com.yqhd.wanandroid.launcher.request.MPaasAPIUtils;
import com.yqhd.wanandroid.launcher.request.RequstDao;
import com.yqhd.wanandroid.launcher.request.req.ArticleListPageJsonGetReq;
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
    private int articlePosition;
    private boolean isRecreate;
    public HomePageFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      view = inflater.inflate(R.layout.fragment_home_page, container, false);
      initView();
      initBannerData();
      initFeedList();
      DownLoadFeedList(1);
      return view;
    }

    private void DownLoadFeedList(int page) {
        ArticleListPageJsonGetReq req = new ArticleListPageJsonGetReq();
        req.page = page;
        RequstDao.GetFeedList(getActivity(), req, new MPaasAPIUtils.OnCompleteListener<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("feedList",result);
                Result listResultFromJson = ResultUtil.getResultFromJson(result, FeedListData.class);
                FeedListData datas = (FeedListData) listResultFromJson.getData();
                mAdapter.addData(datas.getDatas());
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void initFeedList() {
        mFeedArticleDataList = new ArrayList<>();
        mAdapter = new ArticleListAdapter(R.layout.item_feed_layout,mFeedArticleDataList);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setAdapter(mAdapter);
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

//                mBanner.setOnBannerListener(i -> JudgeUtils.startArticleDetailActivity(getActivity(), null,
//                        0, mBannerTitleList.get(i), mBannerUrlList.get(i),
//                        false, false, true));
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
    }


}
