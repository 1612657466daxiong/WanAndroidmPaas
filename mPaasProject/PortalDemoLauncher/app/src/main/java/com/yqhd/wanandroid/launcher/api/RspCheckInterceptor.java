package com.yqhd.wanandroid.launcher.api;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;


import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Author:Doraemon_xwq
 * Time:17.9.28
 * FileName:RspCheckInterceptor
 * Project:WAI
 * Package:infoex.cn.wai.retrofit
 * Company:YawooAI
 */
public class RspCheckInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        try {
            ResponseBody rspBody = response.body();
            JSONObject jsonObject = new JSONObject(InterceptorUtils.getRspData(rspBody));
            int status = jsonObject.getInt("status");
            String msg =jsonObject.getString("msg");
//            Log.e("status",status+"");
//            switch (status){
//                case I.STATUS_CODE.USER_LOGIN_IS_INVALID://登录失效
//                    throw new DataException(msg,status);
//                case I.STATUS_CODE.PASSWORD_ERROR://密码错误
//                    throw new DataException(msg,status);
//                case I.STATUS_CODE.USER_ISNULL://用户不存在
//                    throw new DataException(msg,status);
//                case I.STATUS_CODE.USER_IS_FROZEN://账户已冻结
//                    throw new DataException(msg,status);
//                case I.STATUS_CODE.INSERT_FAIL://添加失败
//                    throw new DataException(msg,status);
//                case I.STATUS_CODE.UPDATE_FAIL:
//                    throw new DataException(msg,status);
//                case I.STATUS_CODE.IS_USE_BY_CODE_221:
//                    throw new DataException(msg,status);
//                case I.STATUS_CODE.IS_USE_BY_CODE_251:
//                    throw new DataException(msg,status);
//                case I.STATUS_CODE.IS_USE_BY_CODE_252:
//                    throw new DataException(msg,status);
//                case I.STATUS_CODE.IS_USE_BY_CODE_253:
//                    throw new DataException(msg,status);
//                case I.STATUS_CODE.IS_USE_BY_CODE_254:
//                    throw new DataException(msg,status);
//                case I.STATUS_CODE.IS_USE_BY_CODE_255:
//                    throw new DataException(msg,status);
//            }
        } catch (JSONException e) {
            Log.e("JsonException",e.toString());
            throw new DataException("数据获取异常",-1);
        }catch (Exception e){
            if (e instanceof IOException){
                Log.e("IOException",e.toString());
                throw new DataException(e.getMessage(),-1);
            }else if (e instanceof ConnectException){
                Log.e("ConnectException",e.toString());
                throw new DataException(e.getMessage(),-1);
            }else if (e instanceof SocketTimeoutException){
                throw new DataException("连接超时",-1);
            }else {
                throw new DataException(e.getMessage(),-1);
            }
        }
        return response;
    }
}
