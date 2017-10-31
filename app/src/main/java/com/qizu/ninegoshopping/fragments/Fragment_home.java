package com.qizu.ninegoshopping.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.qizu.ninegoshopping.Main2Activity;
import com.qizu.ninegoshopping.R;
import com.qizu.ninegoshopping.adapter.MyAdapter;
import com.qizu.ninegoshopping.bean.Bean_sy;
import com.qizu.ninegoshopping.utils1.GsonObjectCallback;
import com.qizu.ninegoshopping.utils1.NetWorkUtils;
import com.qizu.ninegoshopping.utils1.OkHttp3Utils;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.youth.banner.Banner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;


public class Fragment_home extends Fragment {
    
    @BindView(R.id.zxing)
    ImageView zxing;
    @BindView(R.id.edittext)
    EditText edittext;

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.duanxin)
    ImageView duanxin;
    //接口
    private String url = "http://result.eolinker.com/umIPmfS6c83237d9c70c7c9510c9b0f97171a308d13b611?uri=homepage";
    //viewpage的地址
    private String[] url_bunner = new String[4];
    private List<Bean_sy.DataBean.SubjectsBean> subjects;
    private List<Bean_sy.DataBean.SubjectsBean.GoodsListBeanX> goodsList;
    private List<Bean_sy.DataBean.DefaultGoodsListBean> defaultGoodsList;
    //数据集合
    private List<Bean_sy.DataBean.Ad1Bean> ad1 = new ArrayList<>();

    private int REQUEST_CODE = 1;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getContext(), R.layout.fragment_home, null);

        unbinder = ButterKnife.bind(this, inflate);
        boolean netWorkAvailable = NetWorkUtils.isNetWorkAvailable(getContext());
        if (!netWorkAvailable) {
            Toast.makeText(getContext(), "联网：" + netWorkAvailable, Toast.LENGTH_SHORT).show();
        } else {
            OkHttp3Utils.getInstance().doGet(url, new GsonObjectCallback<Bean_sy>() {

                @Override
                public void onUi(Bean_sy bean) {

                    ad1 = bean.getData().getAd1();
                    Log.d("图片---------", ad1.get(0).getImage());
                    for (int i = 0; i < ad1.size(); i++) {
                        String str = ad1.get(i).getImage();
                        url_bunner[i] = str;
                    }
                    banner.setImages(url_bunner);
                    banner.setBannerStyle(Banner.CIRCLE_INDICATOR);
                    banner.setDelayTime(3000);
                    subjects = bean.getData().getSubjects();
                    defaultGoodsList = bean.getData().getDefaultGoodsList();
                    recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recycler.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
                    recycler.setAdapter(new MyAdapter(getActivity(),defaultGoodsList));
                }

                @Override
                public void onFailed(Call call, IOException e) {

                }
            });
        }

        return inflate;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.zxing, R.id.edittext})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zxing:
                Intent intent = new Intent(getContext(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.edittext:
                Intent intent1 = new Intent(getContext(), Main2Activity.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getContext(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getContext(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
