package com.yqhd.wanandroid.launcher.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alipay.mobile.common.rpc.RpcException;
import com.alipay.mobile.common.rpc.RpcInvokeContext;
import com.alipay.mobile.framework.LauncherApplicationAgent;
import com.alipay.mobile.framework.MicroApplicationContext;
import com.alipay.mobile.framework.service.common.RpcService;
import com.yqhd.wanandroid.launcher.R;
import com.yqhd.wanandroid.launcher.base.BaseActivity;
import com.yqhd.wanandroid.launcher.bean.User;
import com.yqhd.wanandroid.launcher.contract.LoginContract;
import com.yqhd.wanandroid.launcher.presenter.LoginPresenter;
import com.yqhd.wanandroid.launcher.request.Yqhd_wanAndroid_01Client;
import com.yqhd.wanandroid.launcher.request.loginBean;
import com.yqhd.wanandroid.launcher.request.UserLoginPostReq;


/**
 * Created by mPaaS on 16/9/28.
 */
public class MainActivity extends BaseActivity implements LoginContract.View{
    private EditText mEdUsername,mEdPassword;
    private Button mBtnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
//        LoginRequst();
    }

    private void initView() {
       mEdUsername = (EditText) findViewById(R.id.ed_username);
       mEdPassword = (EditText) findViewById(R.id.ed_password);
       mBtnLogin = (Button) findViewById(R.id.btn_login);
       mBtnLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               LoginRequst();
           }
       });
    }

    private void LoginRequst() {
        //这里不能在主线程里面做rpc?  文档也没说嘛？？问题好多啊 我都要弃坑了
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    MicroApplicationContext microAppContext = LauncherApplicationAgent.getInstance()
//                            .getMicroApplicationContext();
//                    //2. 获取RpcService
//                    RpcService rpcService = microAppContext
//                            .findServiceByInterface(RpcService.class.getName());
//                    Yqhd_wanAndroid_01Client client = rpcService.getRpcProxy(Yqhd_wanAndroid_01Client.class);
//                    RpcInvokeContext rpcInvokeContext = rpcService.getRpcInvokeContext(client);
//                    //设置超时时间,单位ms
//                    rpcInvokeContext.setTimeout(60000);
//                    UserLoginPostReq userLoginPostReq = new UserLoginPostReq();
//                    userLoginPostReq.username = "doraemon";
//                    userLoginPostReq.password = "123456789";
//                    userLoginPostReq._requestBody = new loginBean("doraemon","123456789");
//                    String s = client.userLoginPost(userLoginPostReq);
//                    Log.e("1", "返回结果："+s);
//                }catch (RpcException ex){
//                    int errorCode = ex.getCode();
//                    if (ex.isClientError()) {
//                        //客户端网络错误
//                        Log.e("2", "客户端网络错误："+errorCode);
////                        Toast.makeText(MainActivity.this, "客户端网络错误："+errorCode, Toast.LENGTH_SHORT).show();
//                    } else {
//                        //服务端错误,获取服务端返回的错误描述
//                        String msg = ex.getMsg();
//                        Log.e("3", "服务端错误："+msg);
//                    }
//                }
//            }
//        }).start();

        new LoginPresenter(this,this).Login("doraemon","123456789");
    }


    @Override
    public void showLoadDialog() {

    }

    @Override
    public void dissmissLoad() {

    }

    @Override
    public void showDataSuccess(User data) {
        Toast.makeText(this,"loginSucess", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDataFail(int code, String msg) {
        Toast.makeText(this,"loginfail", Toast.LENGTH_SHORT).show();
    }
}
