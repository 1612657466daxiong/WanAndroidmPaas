package com.yqhd.wanandroid.launcher.bean;

/**
 * Author : xiongqiwei
 * Date : 2018/8/9
 * Project : PortalDemoLauncher
 */
public class Result<T>{
        private T data;
        private int errorCode;
        private String errorMsg;
        public void setData(T data) {
            this.data = data;
        }
        public T getData() {
            return data;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }
        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }
        public String getErrorMsg() {
            return errorMsg;
        }
}
