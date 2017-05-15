package com.example.holmesk.recyclerviewtest.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.example.holmesk.recyclerviewtest.R;
import com.example.holmesk.recyclerviewtest.adapter.MyViewAdapter;
import com.example.holmesk.recyclerviewtest.utils.DividerGridItemDecoration;

import java.util.ArrayList;

public class GridActivity extends AppCompatActivity {

    private RecyclerView mygridrecyc;
    private ArrayList<String> mDatas;
    private MyViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        initData();
        initView();
        initEvent();
    }

    private void initView() {
        mygridrecyc = (RecyclerView) findViewById(R.id.mygridrecyc);
        mygridrecyc.setLayoutManager(new GridLayoutManager(this, 3));
        DividerGridItemDecoration itemDecoration = new DividerGridItemDecoration(this, 3);
        mygridrecyc.addItemDecoration(itemDecoration);
        adapter = new MyViewAdapter(this, mDatas);
        mygridrecyc.setAdapter(adapter);

    }

    private void initData() {

        mDatas = new ArrayList<String>();
        for (int i = 0; i < 101; i++) {
            mDatas.add("abcde" + i);
        }
    }

    private void initEvent() {

        adapter.setOnItemClickListener(new MyViewAdapter.OnMyItemClickListener() {
            @Override
            public void onMyItemClick(View view, int position) {
                ToastUtils.showShortToast("click" + position);

            }

            @Override
            public void onMyItemLongClick(View view, int position) {
                ToastUtils.showShortToast("longclick" + position);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
