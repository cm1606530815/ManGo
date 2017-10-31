package com.qizu.ninegoshopping;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 * 作者：${九号} on 2017/10/20 20:42
 * 班级：Android1508A
 * 欢迎界面
 */

public class WelcomeActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //两秒钟进入MainActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //启动MainActivity主页面，这段代码是在主线程执行
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                //关闭当前页面（结束WelcomeActivity）
                finish();
            }
        },2000);
    }
}
