package com.qizu.ninegoshopping;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hjm.bottomtabbar.BottomTabBar;
import com.qizu.ninegoshopping.fragments.Fragment_cart;
import com.qizu.ninegoshopping.fragments.Fragment_clas;
import com.qizu.ninegoshopping.fragments.Fragment_home;
import com.qizu.ninegoshopping.fragments.Fragment_user;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottomtabbar)
    BottomTabBar bottomtabbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().hide();//代码中  隐藏掉标题栏
        bottomtabbar = (BottomTabBar) findViewById(R.id.bottomtabbar);
        //底部导航栏
        bottomtabbar.init(getSupportFragmentManager())
//                .setImgSize(80, 80)
//                .setFontSize(24)
                .setChangeColor(Color.RED, Color.GRAY)
                .addTabItem("首页", R.mipmap.ic_nav_home_press, R.mipmap.ic_nav_home, Fragment_home.class)
                .addTabItem("分类", R.mipmap.ic_nav_class_press, R.mipmap.ic_nav_class, Fragment_clas.class)
                .addTabItem("购物车", R.mipmap.ic_nav_cart_press, R.mipmap.ic_nav_cart, Fragment_cart.class)
                .addTabItem("个人", R.mipmap.ic_nav_user_press , R.mipmap.ic_nav_user, Fragment_user.class)
                .isShowDivider(false);

    }
}
