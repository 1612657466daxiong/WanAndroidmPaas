package com.yqhd.wanandroid.launcher.bean;

import java.util.List;

/**
 * Author : xiongqiwei
 * Date : 2018/8/9
 * Project : PortalDemoLauncher
 */
public class User {
    private List<Integer> collectIds;
    private String email;
    private String icon;
    private int id;
    private String password;
    private int type;
    private String username;
    public void setCollectIds(List<Integer> collectIds) {
        this.collectIds = collectIds;
    }
    public List<Integer> getCollectIds() {
        return collectIds;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getIcon() {
        return icon;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setType(int type) {
        this.type = type;
    }
    public int getType() {
        return type;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
}
