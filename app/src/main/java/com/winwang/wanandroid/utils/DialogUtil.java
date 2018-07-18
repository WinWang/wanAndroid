package com.winwang.wanandroid.utils;

import android.content.Context;

import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import cn.droidlover.xdroidmvp.log.XLog;

/**
 * Created by admin on 2018/3/23.
 */

public class DialogUtil {


    /**
     * DialogUtil实例
     */
    private static QMUITipDialog tipDialog;


    public static void showDialog(Context context) {
        tipDialog = new QMUITipDialog.Builder(context)
                .setTipWord("加载中")
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .create();
        tipDialog.show();
        tipDialog.setCancelable(true);
    }


    public static void cancleDialog() {
        if (tipDialog != null) {
            try {
                tipDialog.dismiss(); //防止context导致错误
            } catch (Exception e) {
                tipDialog = null;
                XLog.e("rxDialog", "Dialog错误");
            }
        }
    }


}
