package com.qizu.ninegoshopping.app;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * 作者：${九号} on 2017/10/20 09:26
 * 班级：Android1508A
 */

public class Myapp extends Application {

    public static Myapp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);
        mInstance = this;
    }

    public static Myapp getInstance(){
        return mInstance;
    }

}
