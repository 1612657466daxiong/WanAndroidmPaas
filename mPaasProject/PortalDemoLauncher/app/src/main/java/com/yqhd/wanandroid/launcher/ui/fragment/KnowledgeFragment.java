package com.yqhd.wanandroid.launcher.ui.fragment;


import android.app.Fragment;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alipay.mobile.framework.app.fragment.BaseFragment;
import com.alipay.mobile.framework.app.ui.BaseActivity;
import com.yqhd.wanandroid.launcher.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class KnowledgeFragment extends Fragment {


    public KnowledgeFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_knowledge, container, false);
    }

}
