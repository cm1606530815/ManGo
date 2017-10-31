package com.qizu.ninegoshopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.qizu.ninegoshopping.bean.Login_bean;
import com.qizu.ninegoshopping.utils1.GsonObjectCallback;
import com.qizu.ninegoshopping.utils1.OkHttp3Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * 作者：${张忍} on 2017/10/20 10:34
 * 班级：Android1508A
 */

public class ZhuceActivity extends AppCompatActivity {

    @BindView(R.id.ed_user)
    EditText edUser;
    @BindView(R.id.ed_password)
    EditText edPassword;
    @BindView(R.id.zhuce_zhuce)
    Button zhuceZhuce;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhuce);
        ButterKnife.bind(this);
        zhuceZhuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                    shared = getSharedPreferences("config", MODE_PRIVATE);
//                    String savename = shared.getString("username", "");
//                    int savepassword = shared.getInt("password", 0);
                String name = edUser.getText().toString();
                String password = edPassword.getText().toString();
                Map map=new HashMap();
                map.put("mobile",name);
                map.put("password",password);
                OkHttp3Utils.doPost("http://120.27.23.105/user/reg", map, new GsonObjectCallback<Login_bean>() {

                    @Override
                    public void onUi(Login_bean login_bean) {
                        Toast.makeText(ZhuceActivity.this,login_bean.getMsg(),Toast.LENGTH_SHORT).show();
                        if(login_bean.getCode().equals("0")){
                            Intent intent = new Intent(ZhuceActivity.this,LoginActivity.class);
                            startActivity(intent);

                        }

                    }

                    @Override
                    public void onFailed(Call call, IOException e) {

                    }
                });

            }
        });
    }
}
