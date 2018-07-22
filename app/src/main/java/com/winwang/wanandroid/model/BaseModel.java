package com.winwang.wanandroid.model;

import cn.droidlover.xdroidmvp.net.IModel;

/**
 * Created by admin on 2018/4/23.
 */

public class BaseModel<T> implements IModel {

    private String errorMsg;
    private int errorCode;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private T data;


    @Override
    public boolean isNull() {
        return false;
    }

    @Override
    public boolean isAuthError() {
        return errorCode < 0;
    }

    @Override
    public boolean isBizError() {
        return false;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

    public String getMessage() {
        return errorMsg;
    }

    public void setMessage(String message) {
        this.errorMsg = message;
    }

    public int getResultCode() {
        return errorCode;
    }

    public void setResultCode(int resultCode) {
        this.errorCode = resultCode;
    }
}
