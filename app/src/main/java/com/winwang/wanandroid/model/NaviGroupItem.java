package com.winwang.wanandroid.model;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class NaviGroupItem extends AbstractExpandableItem<NavigationListData> implements MultiItemEntity {

    public NaviGroupItem(String name) {
        this.name = name;
    }

    private String name;


    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
