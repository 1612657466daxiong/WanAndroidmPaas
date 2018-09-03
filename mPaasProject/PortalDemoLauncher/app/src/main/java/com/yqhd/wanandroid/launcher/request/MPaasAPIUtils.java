package com.yqhd.wanandroid.launcher.request;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.alipay.mobile.common.rpc.RpcInvokeContext;
import com.alipay.mobile.framework.LauncherApplicationAgent;
import com.alipay.mobile.framework.MicroApplicationContext;
import com.alipay.mobile.framework.service.common.RpcService;
import com.alipay.mobile.framework.service.common.TaskScheduleService;
import com.google.gson.Gson;
import com.yqhd.wanandroid.launcher.request.req.ArticleListPageJsonGetReq;
import com.yqhd.wanandroid.launcher.request.req.ProjectListReq;
import com.yqhd.wanandroid.launcher.request.req.UserLoginPostReq;
import com.yqhd.wanandroid.launcher.request.req.UserRegisterPostReq;

import java.util.logging.LogRecord;

/**
 * Author : xiongqiwei
 * Date : 2018/8/15
 * Project : PortalDemoLauncher
 */
public class MPaasAPIUtils<T> {
    private static Yqhd_wanAndroid_01Client mYqClient;
    private RpcInvokeContext rpcInvokeContext;
    private MicroApplicationContext microAppContext;
    private RpcService rpcService;
    private TaskScheduleService taskServie;

    public static final int RESULT_SUCCESS = 0;
    public static final int RESULT_ERROR = 1;
    public static final int DOWNLOAD_START=2;
    public static final int DOWNLOADING=3;
    public static final int DOWNLOAD_FINISH=4;

    private Handler mHandler;

    Class<T> mClazz;

    public interface OnCompleteListener<T>{
        void onSuccess(T result);
        void onError(String error);
    }
    private OnCompleteListener<T> mListener;

    public MPaasAPIUtils(Context context){
        if(mYqClient ==null){
            synchronized (MPaasAPIUtils.class){
                if (mYqClient== null){
                    microAppContext = LauncherApplicationAgent.getInstance()
                            .getMicroApplicationContext();
                    //2. 获取RpcService
                    rpcService = microAppContext
                            .findServiceByInterface(RpcService.class.getName());
                    taskServie = microAppContext.findServiceByInterface(TaskScheduleService.class.getName());
                    mYqClient = rpcService.getRpcProxy(Yqhd_wanAndroid_01Client.class);
                }
            }
        }
        initHandler();
    }

    private void initHandler() {
        mHandler = new Handler( LauncherApplicationAgent.getInstance().getApplicationContext().getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case RESULT_ERROR:
                        mListener.onError(msg.obj==null?msg.toString():msg.obj.toString());
                        break;
                    case RESULT_SUCCESS:
                        T result = (T) msg.obj;
                        mListener.onSuccess(result);
                        break;
                }
            }
        };
    }

    public Yqhd_wanAndroid_01Client getmYqClient(){
        return mYqClient;
    }

    public MPaasAPIUtils<T> targetClass(Class<T> clazz){
        mClazz = clazz;
        return this;
    }

    public MPaasAPIUtils setTimeOut(int time){
        if (rpcService!=null){
            rpcInvokeContext = rpcService.getRpcInvokeContext(mYqClient);
            //设置超时时间,单位ms
            rpcInvokeContext.setTimeout(time);
        }
        return this;
    }

    int functionType;
    Object params;
    public MPaasAPIUtils functionType(int type,Object req){
        functionType = type;
        params = req;
        return  this;
    }

    public MPaasAPIUtils monitor(OnCompleteListener<T> listener){
        if (listener!=null){
            mListener = listener;
        }

        if (microAppContext==null){
            microAppContext = LauncherApplicationAgent.getInstance().getMicroApplicationContext();
        }
        if (taskServie==null){
            taskServie = microAppContext.findServiceByInterface(TaskScheduleService.class.getName());
        }
        taskServie.parallelExecute(new Runnable() {
            @Override
            public void run() {
                try {
                    toWay();
                }catch (Exception e){
                    Message msg = Message.obtain();
                    msg.what = RESULT_ERROR;
                    msg.obj = e.getMessage();
                    mHandler.sendMessage(msg);
                }
            }
        });
        return  this;
    }

    private void toWay() {
        String s=null;
        switch (functionType){
            case ReqestType.LOGIN://API 添加则在这里添加分支
                s =mYqClient.userLoginPost((UserLoginPostReq) params);
                break;
            case ReqestType.REGISTER://注册
                s= mYqClient.userRegisterPost((UserRegisterPostReq) params);
                break;
            case ReqestType.BANNER://首页banner
                s=mYqClient.bannerJsonGet();
                break;
            case ReqestType.GET_HOME_FEED_LIST://首页文章列表数据
                s=mYqClient.articleListPageJsonGet((ArticleListPageJsonGetReq) params);
                break;
            case ReqestType.GET_TREE_JSON://获取知识体系
                s=mYqClient.treeJsonGet();
                break;
            case ReqestType.GET_NAVIGATION:
                s = mYqClient.naviJsonGet();
                break;
            case ReqestType.GET_PROJECTTYPES:
                s=mYqClient.projectTreeJsonGet();
                break;
            case ReqestType.GET_PROJECT_LIST:
                s=mYqClient.projectListTypeJsonCidCidGet((ProjectListReq) params);
                break;
        }
        if (mClazz.equals(String.class)){
            Message msg = Message.obtain();
            msg.what = RESULT_SUCCESS;
            msg.obj = s;
            mHandler.sendMessage(msg);
        }else {
            Gson gson = new Gson();
            T value = gson.fromJson(s,mClazz);
            Message msg = Message.obtain();
            msg.what = RESULT_SUCCESS;
            msg.obj = value;
            mHandler.sendMessage(msg);
        }
    }


}
