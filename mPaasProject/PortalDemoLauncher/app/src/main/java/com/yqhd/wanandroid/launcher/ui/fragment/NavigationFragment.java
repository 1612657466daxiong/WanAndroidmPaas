package com.yqhd.wanandroid.launcher.ui.fragment;


import android.app.Fragment;
import android.os.Bundle;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alipay.mobile.framework.app.fragment.BaseFragment;
import com.yqhd.wanandroid.launcher.R;
import com.yqhd.wanandroid.launcher.adapter.NavigationAdapter;
import com.yqhd.wanandroid.launcher.bean.NavigationListData;
import com.yqhd.wanandroid.launcher.bean.Result;
import com.yqhd.wanandroid.launcher.request.MPaasAPIUtils;
import com.yqhd.wanandroid.launcher.request.RequstDao;
import com.yqhd.wanandroid.launcher.utils.ResultUtil;

import java.util.ArrayList;

import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationFragment extends Fragment {
    View view;
    VerticalTabLayout mTabLayout;
    View mDivider;
    RecyclerView mRecyclerView;

    LinearLayoutManager mManager;
    boolean needScroll;
    int index;
    boolean isClickTab;

    public NavigationFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_navigation, container, false);
        initView();
        initData();
        return view;
    }

    private void initData() {
        RequstDao.GetNavigation(getActivity(), new MPaasAPIUtils.OnCompleteListener<String>() {
            @Override
            public void onSuccess(String result) {
                Result listResultFromJson = ResultUtil.getListResultFromJson(result, NavigationListData.class);
                ArrayList<NavigationListData> listDatas  = (ArrayList<NavigationListData>) listResultFromJson.getData();
                showNavigationListData(listDatas);
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void showNavigationListData(final ArrayList<NavigationListData> listDatas) {
        mTabLayout.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return listDatas ==null?0:listDatas.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                return new TabView.TabTitle.Builder()
                        .setContent(listDatas.get(position).getName())
                        .setTextColor(ContextCompat.getColor(getActivity(), R.color.shallow_green),
                                ContextCompat.getColor(getActivity(), R.color.shallow_grey))
                        .build();
            }

            @Override
            public int getBackground(int position) {
                return -1;
            }
        });
        initRecyclerView(listDatas);
        leftRightLinkage();

    }



    private void initRecyclerView(ArrayList<NavigationListData> listDatas) {
        NavigationAdapter adapter = new NavigationAdapter(R.layout.item_navigation, listDatas,getActivity());
        mRecyclerView.setAdapter(adapter);
        mManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.setHasFixedSize(true);
    }

    private void initView() {
        mTabLayout = view.findViewById(R.id.navigation_tab_layout);
        mRecyclerView = view.findViewById(R.id.navigation_RecyclerView);
    }


    /**
     * Left tabLayout and right recyclerView linkage
     */
    private void leftRightLinkage() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (needScroll && (newState == RecyclerView.SCROLL_STATE_IDLE)) {
                    scrollRecyclerView();
                }
                rightLinkageLeft(newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (needScroll) {
                    scrollRecyclerView();
                }
            }
        });

        mTabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tabView, int i) {
                isClickTab = true;
                selectTag(i);
            }

            @Override
            public void onTabReselected(TabView tabView, int i) {
            }
        });
    }

    private void selectTag(int i) {
        index = i;
        mRecyclerView.stopScroll();
        smoothScrollToPosition(i);
    }


    private void smoothScrollToPosition(int currentPosition) {
        int firstPosition = mManager.findFirstVisibleItemPosition();
        int lastPosition = mManager.findLastVisibleItemPosition();
        if (currentPosition <= firstPosition) {
            mRecyclerView.smoothScrollToPosition(currentPosition);
        } else if (currentPosition <= lastPosition) {
            int top = mRecyclerView.getChildAt(currentPosition - firstPosition).getTop();
            mRecyclerView.smoothScrollBy(0, top);
        } else {
            mRecyclerView.smoothScrollToPosition(currentPosition);
            needScroll = true;
        }
    }


    private void scrollRecyclerView() {
        needScroll = false;
        int indexDistance = index - mManager.findFirstVisibleItemPosition();
        if (indexDistance >= 0 && indexDistance < mRecyclerView.getChildCount()) {
            int top = mRecyclerView.getChildAt(indexDistance).getTop();
            mRecyclerView.smoothScrollBy(0, top);
        }
    }

    /**
     * Right recyclerView linkage left tabLayout
     * SCROLL_STATE_IDLE just call once
     *
     * @param newState RecyclerView new scroll state
     */
    private void rightLinkageLeft(int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            if (isClickTab) {
                isClickTab = false;
                return;
            }
            int firstPosition = mManager.findFirstVisibleItemPosition();
            if (index != firstPosition) {
                index = firstPosition;
                setChecked(index);
            }
        }
    }

    /**
     * Smooth right to select the position of the left tab
     *
     * @param position checked position
     */
    private void setChecked(int position) {
        if (isClickTab) {
            isClickTab = false;
        } else {
            if (mTabLayout == null) {
                return;
            }
            mTabLayout.setTabSelected(index);
        }
        index = position;
    }

}
