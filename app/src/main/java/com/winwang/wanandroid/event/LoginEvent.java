package com.winwang.wanandroid.event;

import com.winwang.wanandroid.model.LoginData;

import cn.droidlover.xdroidmvp.event.IBus;

public class LoginEvent implements IBus.IEvent {


    public LoginEvent(LoginData data) {
        this.data = data;
    }

    public LoginData data;

    @Override
    public int getTag() {
        return 11;
    }
}
