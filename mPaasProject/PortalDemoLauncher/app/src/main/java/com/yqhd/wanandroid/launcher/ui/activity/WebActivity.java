package com.yqhd.wanandroid.launcher.ui.activity;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;



import com.alipay.mobile.framework.LauncherApplicationAgent;
import com.alipay.mobile.framework.app.ui.BaseActivity;
import com.alipay.mobile.h5container.api.H5Bundle;
import com.alipay.mobile.h5container.api.H5Page;
import com.alipay.mobile.h5container.api.H5Param;
import com.alipay.mobile.h5container.service.H5Service;
import com.yqhd.wanandroid.launcher.R;
import com.yqhd.wanandroid.launcher.app.Constants;
import com.yqhd.wanandroid.launcher.utils.StatusBarUtil;

public class WebActivity extends BaseActivity {
    Toolbar mToolbar;
    FrameLayout mWebContent;
    private Bundle bundle;
    private int articleId;
    private String articleLink;
    private String title;

    private boolean isCollect;
    private boolean isCommonSite;
    private boolean isCollectPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        getData();
        initView();
        initData();
    }

    private void initData() {

        if (articleLink!=null){
            Bundle param = new Bundle();
            param.putString(H5Param.LONG_URL, articleLink);
            H5Bundle bundle = new H5Bundle();
            bundle.setParams(param);
            H5Service h5Service = LauncherApplicationAgent.getInstance().getMicroApplicationContext()
                    .findServiceByInterface(H5Service.class.getName());
// 获取到H5Page
            H5Page h5Page = h5Service.createPage(this, bundle);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,  LinearLayout.LayoutParams.MATCH_PARENT);
            lp.setMargins(20, 32, 20, 32);
// 添加view到父布局中
            mWebContent.addView(h5Page.getContentView(), lp);
        }
    }

    private void initView() {
        mWebContent = (FrameLayout) findViewById(R.id.article_detail_web_view);
        mToolbar = (Toolbar) findViewById(R.id.article_detail_toolbar);
        StatusBarUtil.immersive(this);
        StatusBarUtil.setPaddingSmart(this, mToolbar);
    }

    public void getData() {
        bundle = getIntent().getExtras();
        assert bundle != null;
        title = (String) bundle.get(Constants.ARTICLE_TITLE);
        articleLink = (String) bundle.get(Constants.ARTICLE_LINK);
        articleId = ((int) bundle.get(Constants.ARTICLE_ID));
        isCommonSite = ((boolean) bundle.get(Constants.IS_COMMON_SITE));
        isCollect = ((boolean) bundle.get(Constants.IS_COLLECT));
        isCollectPage = ((boolean) bundle.get(Constants.IS_COLLECT_PAGE));
    }
}
