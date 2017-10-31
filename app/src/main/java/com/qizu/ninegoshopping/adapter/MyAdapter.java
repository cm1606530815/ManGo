package com.qizu.ninegoshopping.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qizu.ninegoshopping.R;
import com.qizu.ninegoshopping.bean.Bean_sy;
import com.qizu.ninegoshopping.utils1.ImageLoderll;

import java.util.List;

/**
 * 作者：${九号} on 2017/10/20 09:30
 * 班级：Android1508A
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VoewHoder> {

    private Context context;
    // private List<Bean_sy.DataBean.SubjectsBean.GoodsListBeanX> goodsList;
    private List<Bean_sy.DataBean.DefaultGoodsListBean> defaultGoodsList;
    private LayoutInflater inflater;

    public MyAdapter(Context context, List<Bean_sy.DataBean.DefaultGoodsListBean> defaultGoodsList) {
        this.context = context;
        this.defaultGoodsList = defaultGoodsList;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public VoewHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item,parent,false);
        VoewHoder holder=new VoewHoder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(VoewHoder holder, int position) {
        ImageLoderll.setimage(defaultGoodsList.get(position).getGoods_img(),context,holder.img);
        holder.tv1.setText(defaultGoodsList.get(position).getGoods_name());
        holder.tv2.setText(defaultGoodsList.get(position).getEfficacy());
    }


    @Override
    public int getItemCount() {
        return defaultGoodsList.size();
    }

    class VoewHoder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tv1;
        TextView tv2;

        public VoewHoder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            tv1 = (TextView) itemView.findViewById(R.id.tv1);
            tv2 = (TextView) itemView.findViewById(R.id.tv2);

        }
    }
}
