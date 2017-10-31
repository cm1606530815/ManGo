package com.qizu.ninegoshopping.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.qizu.ninegoshopping.LoginActivity;
import com.qizu.ninegoshopping.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 作者：${九号} on 2017/10/20 08:43
 * 班级：Android1508A
 */

public class Fragment_user extends Fragment {

    @BindView(R.id.img)
    ImageView img;
    Unbinder unbinder;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getContext(), R.layout.fragment_user, null);

        unbinder = ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.img)
    public void onViewClicked() {

        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);

    }
}
