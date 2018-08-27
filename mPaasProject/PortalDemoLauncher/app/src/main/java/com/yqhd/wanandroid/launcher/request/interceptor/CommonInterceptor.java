package com.yqhd.wanandroid.launcher.request.interceptor;

import android.util.Log;

import com.alipay.mobile.common.rpc.RpcException;
import com.alipay.mobile.common.rpc.RpcInterceptor;
import com.alipay.mobile.common.rpc.RpcInvocationHandler;
import com.alipay.mobile.common.rpc.RpcInvokeContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * Author : xiongqiwei
 * Date : 2018/8/24
 * Project : PortalDemoLauncher
 */
public class CommonInterceptor implements RpcInterceptor {
    /**
     * 前置拦截：发送 RPC 之前回调。
     * @param proxy  RPC 代理对象。
     * @param clazz  rpcface 模型类，通过 clazz 参数可以判断当前调用的是哪个 RPC 模型类
     * @param method 当前 RPC 调用的方法。
     * @throws RpcException
     * @return true 表示继续向下执行，false 表示中断当前请求，抛出 RpcException，错误码：9。
     */
    @Override
    public boolean preHandle(Object proxy, ThreadLocal<Object> threadLocal, byte[] bytes, Class<?> clazz, Method method, Object[] objects, Annotation annotation, ThreadLocal<Map<String, Object>> threadLocal1) throws RpcException {
        RpcInvocationHandler rpcInvocationHandler = (RpcInvocationHandler) Proxy.getInvocationHandler(proxy);
        RpcInvokeContext rpcInvokeContext = rpcInvocationHandler.getRpcInvokeContext();
        return true;
    }

    @Override
    public boolean postHandle(Object proxy, ThreadLocal<Object> threadLocal, byte[] bytes, Class<?> aClass, Method method, Object[] objects, Annotation annotation) throws RpcException {

        RpcInvocationHandler rpcInvocationHandler = (RpcInvocationHandler) Proxy.getInvocationHandler(proxy);
        RpcInvokeContext rpcInvokeContext = rpcInvocationHandler.getRpcInvokeContext();
        Map<String,String> map = rpcInvokeContext.getResponseHeaders();


        return true;
    }

    @Override
    public boolean exceptionHandle(Object o, ThreadLocal<Object> threadLocal, byte[] bytes, Class<?> aClass, Method method, Object[] objects, RpcException e, Annotation annotation) throws RpcException {

        return true;
    }
}
