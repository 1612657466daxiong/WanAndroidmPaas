package com.yqhd.wanandroid.launcher.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yqhd.wanandroid.launcher.R;
import com.yqhd.wanandroid.launcher.adapter.ProjectListAdapter;
import com.yqhd.wanandroid.launcher.app.Constants;
import com.yqhd.wanandroid.launcher.bean.FeedArticleData;
import com.yqhd.wanandroid.launcher.bean.ProjectListData;
import com.yqhd.wanandroid.launcher.bean.Result;
import com.yqhd.wanandroid.launcher.request.MPaasAPIUtils;
import com.yqhd.wanandroid.launcher.request.RequstDao;
import com.yqhd.wanandroid.launcher.request.req.ProjectListReq;
import com.yqhd.wanandroid.launcher.ui.activity.WebActivity;
import com.yqhd.wanandroid.launcher.utils.ResultUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectLIstragment extends Fragment {
    View view;
    SmartRefreshLayout mRefreshLayout;
    RecyclerView mRecyclerView;
    private List<FeedArticleData> mDatas;
    private ProjectListAdapter mAdapter;
    private boolean isRefresh = true;
    private int mCurrentPage;
    private int cid;

    public ProjectLIstragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_project_listragment, container, false);
        initView();
        setRefresh();
        initDataFormTap();
        initData(mCurrentPage,cid);
        return  view;
    }

    private void setRefresh() {
        mCurrentPage = 1;
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mCurrentPage = 1;
                isRefresh = true;
                initData(mCurrentPage,cid);
                refreshlayout.finishRefresh(1000);
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mCurrentPage++;
                isRefresh = false;
                initData(mCurrentPage,cid);
                refreshlayout.finishLoadmore(1000);
            }
        });
    }

    private void initData(int page,int cid) {
        ProjectListReq req = new ProjectListReq();
        req.type = page;
        req.cid = cid;
        RequstDao.GetProjectList(getActivity(), req, new MPaasAPIUtils.OnCompleteListener<String>() {
            @Override
            public void onSuccess(String result) {
                Result resultFromJson = ResultUtil.getResultFromJson(result, ProjectListData.class);
                if (resultFromJson!=null){
                    ProjectListData data = (ProjectListData) resultFromJson.getData();
                    if (data!=null){
                        mDatas = data.getDatas();
                        if (isRefresh) {
                            mAdapter.replaceData(mDatas);
                        } else {
                            if (mDatas.size() > 0) {
                                mAdapter.addData(mDatas);
                            } else {
//                        CommonUtils.showMessage(_mActivity, getString(R.string.load_more_no_data));
                                Toast.makeText(getActivity(), "没有更多的数据了", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
            }

            @Override
            public void onError(String error) {

            }
        });
    }

    private void initDataFormTap() {
        Bundle arguments = getArguments();
        cid = arguments.getInt(Constants.ARG_PARAM1);
        initRecyclerView();
    }

    private void initRecyclerView() {
        mDatas = new ArrayList<>();
        mAdapter = new ProjectListAdapter(R.layout.item_project_list,mDatas);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startProjectPage(position);
            }
        });
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
    }

    private void startProjectPage(int position) {
        if (mAdapter.getData().size()<=0 ||mAdapter.getData().size()<=position){
            return;
        }

        Intent intent = new Intent(getActivity(), WebActivity.class);
        intent.putExtra(Constants.ARTICLE_ID, mAdapter.getData().get(position).getId());
        intent.putExtra(Constants.ARTICLE_TITLE,  mAdapter.getData().get(position).getTitle().trim());
        intent.putExtra(Constants.ARTICLE_LINK,  mAdapter.getData().get(position).getLink().trim());
        intent.putExtra(Constants.IS_COLLECT, mAdapter.getData().get(position).isCollect());
        intent.putExtra(Constants.IS_COLLECT_PAGE, false);
        intent.putExtra(Constants.IS_COMMON_SITE, true);
        getActivity().startActivity(intent);
    }

    private void initView() {
        mRefreshLayout = view.findViewById(R.id.normal_view);
        mRecyclerView = view.findViewById(R.id.project_list_recycler_view);
    }

    public static ProjectLIstragment getInstance(int param1, String param2) {
        ProjectLIstragment fragment = new ProjectLIstragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_PARAM1, param1);
        args.putString(Constants.ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

}
