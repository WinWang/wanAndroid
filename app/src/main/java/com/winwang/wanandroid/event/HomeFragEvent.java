package com.winwang.wanandroid.event;

import cn.droidlover.xdroidmvp.event.IBus;

/**
 * Created by admin on 2018/5/3.
 */

public class HomeFragEvent implements IBus.IEvent {

    public boolean isNight;

    public HomeFragEvent(boolean isNight) {
        this.isNight = isNight;
    }


    @Override
    public int getTag() {
        return 0;
    }

}
