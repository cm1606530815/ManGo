package com.qizu.ninegoshopping.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.qizu.ninegoshopping.R;
import com.qizu.ninegoshopping.adapter.MyAdapter_left;
import com.qizu.ninegoshopping.adapter.MyAdapter_right;
import com.qizu.ninegoshopping.bean.DataleftBean;
import com.qizu.ninegoshopping.bean.DatarightBean;
import com.qizu.ninegoshopping.bean.Datebeanitem;
import com.qizu.ninegoshopping.utils1.API;
import com.qizu.ninegoshopping.utils1.GsonObjectCallback;
import com.qizu.ninegoshopping.utils1.OkHttp3Utils;

import java.io.IOException;

import okhttp3.Call;

/**
 * 作者：${九号} on 2017/10/20 08:43
 * 班级：Android1508A
 */

public class Fragment_clas extends Fragment {

    private RecyclerView rv_left, rv_right;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getContext(), R.layout.fragment_clas, null);

        //获取控件的方法
        rv_left = (RecyclerView)inflate.findViewById(R.id.type_rvleft);
        rv_right = (RecyclerView)inflate.findViewById(R.id.type_rvright);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        ///得到WindowManager
        WindowManager windowManager = getActivity().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        //得到屏幕宽
        int width = display.getWidth();
        //将RecyclerView宽设置为屏幕宽的1/5
        params.width = width * 1 / 5;
        rv_left.setLayoutParams(params);
        //得到RecyclerView布局管理器
        LinearLayoutManager leftLayoutManager = new LinearLayoutManager(getActivity());
        //RecyclerView设置布局管理器
        rv_left.setLayoutManager(leftLayoutManager);
        //得到RecyclerView布局管理器
        LinearLayoutManager rightLayoutManager = new LinearLayoutManager(getActivity());
        //RecyclerView设置布局管理器
        rv_right.setLayoutManager(rightLayoutManager);
        //获取后台数据，添加适配器
        getServerData();

        return inflate;
    }

    //获取后台数据的方法
    public void getServerData() {
        OkHttp3Utils.getInstance().doGet(API.TYPE_PATH, new GsonObjectCallback<DataleftBean>() {
            @Override
            public void onUi(final DataleftBean dataleftBean) {
                //适配器
                final MyAdapter_left myAdapter_left= new MyAdapter_left(getActivity(), dataleftBean.getDatas().getClass_list());
                rv_left.setAdapter(myAdapter_left);
                //第一个子条目显示其二级数据

                //子条目点击监听
                myAdapter_left.setRecycleViewItemClickListener(new MyAdapter_left.OnRecycleViewItemClickListener() {
                    @Override
                    public void recycleViewItemClickListener(int position, View view, RecyclerView.ViewHolder viewHolder) {
                        myAdapter_left.setTagPosition(position);
                        myAdapter_left.notifyDataSetChanged();
                        //请求二级数据
                        getServerTypeData(dataleftBean.getDatas().getClass_list().get(position),position);
                    }
                });
            }


            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }

    //获取网络数据的方法
    public static void getServerData(Context context, String url, final OnGetServerDateLisnter onGetServerDateLisnter) {
        OkHttp3Utils.getInstance().doGet(url, new GsonObjectCallback<Datebeanitem>() {
            @Override
            public void onUi(Datebeanitem datebeanitem) {
                onGetServerDateLisnter.getData(datebeanitem.getDatas().toString());
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });

    }
    public interface OnGetServerDateLisnter {
        void getData(String string);
    }
    //请求二级数据
    public void getServerTypeData(final DataleftBean.DatasBean.ClassListBean gc_id, final int position) {
        OkHttp3Utils.doGet(API.TYPE_PATH + "&gc_id=" + gc_id, new GsonObjectCallback<DatarightBean>() {
            @Override
            public void onUi(DatarightBean datarightBean) {
                MyAdapter_right myAdapter_right = new MyAdapter_right(getActivity(), datarightBean.getDatas().getClass_list());
                rv_right.setAdapter(myAdapter_right);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });


    }
}
