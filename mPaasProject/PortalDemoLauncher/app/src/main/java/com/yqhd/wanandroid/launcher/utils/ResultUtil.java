package com.yqhd.wanandroid.launcher.utils;

import android.util.Log;

import com.google.gson.Gson;
import com.yqhd.wanandroid.launcher.api.I;
import com.yqhd.wanandroid.launcher.bean.Result;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author : xiongqiwei
 * Date : 2018/8/27
 * Project : PortalDemoLauncher
 */
public class ResultUtil {

    public static <T> Result getListResultFromJson(String jsonStr,Class<T> clazz){
        Result result = new Result();
        try {
            if(jsonStr==null || jsonStr.isEmpty() || jsonStr.length()<3)return null;
            JSONObject jsonObject = new JSONObject(jsonStr);
            if(!jsonObject.isNull("errorCode")) {
                result.setErrorCode(jsonObject.getInt("errorCode"));
            }else if(!jsonObject.isNull("errorMsg")){
                result.setErrorCode(jsonObject.getInt("errorMsg"));
            }
            if(!jsonObject.isNull("errorMsg")) {
                result.setErrorMsg(jsonObject.getString("errorMsg"));
            }else if(!jsonObject.isNull("errorMsg")){
                result.setErrorMsg(jsonObject.getString("errorMsg"));
            }
            if(!jsonObject.isNull("data")) {
                JSONArray array = jsonObject.getJSONArray("data");

                if (array != null) {

                    List<T> list = new ArrayList<T>();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonGroupAvatar = array.getJSONObject(i);

                        T ga = new Gson().fromJson(jsonGroupAvatar.toString(), clazz);

                        list.add(ga);
                    }
                    result.setData(list);

                    return result;
                }
            }else{
                JSONArray array = new JSONArray(jsonStr);
                if (array != null) {
                    List<T> list = new ArrayList<T>();
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonGroupAvatar = array.getJSONObject(i);
                        T ga = new Gson().fromJson(jsonGroupAvatar.toString(), clazz);
                        list.add(ga);
                    }
                    result.setData(list);
                    return result;
                }
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    public static <T> Result getResultFromJson(String jsonStr, Class<T> clazz){
        Result result = new Result();
        try {
            if(jsonStr==null || jsonStr.isEmpty() || jsonStr.length()<3)return null;
            JSONObject jsonObject = new JSONObject(jsonStr);
            if(!jsonObject.isNull("errorCode")) {
                result.setErrorCode(jsonObject.getInt("errorCode"));
            }else if(!jsonObject.isNull("errorCode")){
                result.setErrorCode(jsonObject.getInt("errorCode"));
            }
            if(!jsonObject.isNull("errorMsg")) {
                result.setErrorMsg(jsonObject.getString("errorMsg"));
            }else if(!jsonObject.isNull("errorMsg")){
                result.setErrorMsg(jsonObject.getString("errorMsg"));
            }
            if(!jsonObject.isNull("data")) {
                JSONObject jsonRetData = jsonObject.getJSONObject("data");
                if (jsonRetData != null) {

                    String date;
                    try {
                        date = URLDecoder.decode(jsonRetData.toString(), I.UTF_8);

                        T t = new Gson().fromJson(date, clazz);
                        result.setData(t);
                        return result;

                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                        T t = new Gson().fromJson(jsonRetData.toString(), clazz);
                        result.setData(t);
                        return result;
                    }
                }
            }else{
                if (jsonObject != null) {

                    String date;
                    try {
                        date = URLDecoder.decode(jsonObject.toString(), I.UTF_8);

                        T t = new Gson().fromJson(date, clazz);
                        result.setData(t);
                        return result;

                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                        T t = new Gson().fromJson(jsonObject.toString(), clazz);
                        result.setData(t);
                        return result;
                    }
                }
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }


    public static <T> ArrayList<T> array2List(T[] array) {
        List<T> list = Arrays.asList(array);
        ArrayList<T> arrayList = new ArrayList<>(list);
        return arrayList;
    }

}
