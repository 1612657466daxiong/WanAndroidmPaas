package com.yqhd.wanandroid.launcher.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : xiongqiwei
 * Date : 2018/8/28
 * Project : PortalDemoLauncher
 */
public class FeedListData {
    int curPage;
    List<FeedArticleData> datas;
    int offset;
    boolean over;
    int pageCount;
    int size;
    int total;

    public FeedListData() {
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public List<FeedArticleData> getDatas() {
        return datas;
    }

    public void setDatas(List<FeedArticleData> datas) {
        this.datas = datas;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
