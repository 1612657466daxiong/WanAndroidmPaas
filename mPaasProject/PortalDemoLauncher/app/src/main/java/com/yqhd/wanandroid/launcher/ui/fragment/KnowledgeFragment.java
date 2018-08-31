package com.yqhd.wanandroid.launcher.ui.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alipay.mobile.framework.app.fragment.BaseFragment;
import com.alipay.mobile.framework.app.ui.BaseActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yqhd.wanandroid.launcher.R;
import com.yqhd.wanandroid.launcher.adapter.KnowledgeHierarchyAdapter;
import com.yqhd.wanandroid.launcher.bean.KnowledgeHierarchyData;
import com.yqhd.wanandroid.launcher.bean.Result;
import com.yqhd.wanandroid.launcher.request.MPaasAPIUtils;
import com.yqhd.wanandroid.launcher.request.RequstDao;
import com.yqhd.wanandroid.launcher.utils.ResultUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class KnowledgeFragment extends Fragment {
    View view;

    SmartRefreshLayout mRefreshlayout;
    RecyclerView mRecyclerView;
    List<KnowledgeHierarchyData> mKnowledgeHierarchyDataList;
    KnowledgeHierarchyAdapter mAdapter;
    int page =1;

    public KnowledgeFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_knowledge, container, false);
        initView();
        initRecyclerView();
        initData(1);
        setRefresh();
        return view;
    }

    private void setRefresh() {
        mRefreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

            }
        });
        mRefreshlayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

            }
        });
    }

    private void initRecyclerView() {
        mAdapter = new KnowledgeHierarchyAdapter(R.layout.item_knowledgehierarchy_layout,mKnowledgeHierarchyDataList);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
    }

    private void initData(int page) {
        RequstDao.GetKnowledge(getActivity(), new MPaasAPIUtils.OnCompleteListener<String>() {
            @Override
            public void onSuccess(String result) {
                Result listResultFromJson = ResultUtil.getListResultFromJson(result, KnowledgeHierarchyData.class);
                ArrayList<KnowledgeHierarchyData> listData = (ArrayList<KnowledgeHierarchyData>) listResultFromJson.getData();
                mAdapter.replaceData(listData);
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void initView() {
        mRefreshlayout = view.findViewById(R.id.normal_view);
        mRecyclerView = view.findViewById(R.id.knowledge_recycler_view);
    }

}
