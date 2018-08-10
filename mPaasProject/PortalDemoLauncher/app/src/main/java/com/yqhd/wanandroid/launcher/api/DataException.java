package com.yqhd.wanandroid.launcher.api;

import java.io.IOException;

/**
 * Author:Doraemon_xqw
 * Time:17.11.8
 * FileName:DataException
 * Project:WAIproject
 * Package:infoex.cn.wai.retrofit
 * Company:YawooAI
 */
public class DataException extends IOException {
    private String msg;
    private int status;

    public DataException(String msg, int status){
        this.msg= msg;
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DataException{" +
                "msg='" + msg + '\'' +
                ", status=" + status +
                '}';
    }
}
