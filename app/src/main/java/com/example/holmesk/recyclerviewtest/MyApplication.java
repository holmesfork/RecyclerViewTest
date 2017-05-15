package com.example.holmesk.recyclerviewtest;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

/**
 * 初始化工程中的全局配置
 * <p>
 * 作者：holmes k
 * 时间：2017.05.11 18:45
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
