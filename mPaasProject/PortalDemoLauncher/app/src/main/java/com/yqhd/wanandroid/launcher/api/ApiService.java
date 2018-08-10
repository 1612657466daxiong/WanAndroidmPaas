package com.yqhd.wanandroid.launcher.api;


import com.yqhd.wanandroid.launcher.bean.Result;
import com.yqhd.wanandroid.launcher.bean.User;

import java.util.ArrayList;
import java.util.Map;


import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;



/**
 * Created by ibm on 2017/9/12. 熊启尾
 */
public interface ApiService {


    /**
     * 登录
     * @param username 用户名
     * @param pwd 密码
     * @return 接口
     */
    @FormUrlEncoded
    @POST(I.LOGIN_REGISTER.LOGIN)
    Observable<Result<User>> Login(@Field(I.LOGIN_REGISTER.USERNAME) String username, @Field(I.LOGIN_REGISTER.PASSWORD) String pwd);

}
