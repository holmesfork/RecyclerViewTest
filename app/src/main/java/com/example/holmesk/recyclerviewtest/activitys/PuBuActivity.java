package com.example.holmesk.recyclerviewtest.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.example.holmesk.recyclerviewtest.R;
import com.example.holmesk.recyclerviewtest.adapter.StaggeredHomeAdapter;

import java.util.ArrayList;

public class PuBuActivity extends AppCompatActivity {

    private RecyclerView mypbrecyc;
    private ArrayList<String> mDatas;
    private StaggeredHomeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pu_bu);
        initData();
        initView();
    }

    private void initView() {
        mypbrecyc = (RecyclerView) findViewById(R.id.mypbrecyc);
        mypbrecyc.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        adapter = new StaggeredHomeAdapter(this, mDatas);
        mypbrecyc.setAdapter(adapter);

        mypbrecyc.setItemAnimator(new DefaultItemAnimator());

        initEvent();

    }

    private void initData() {

        mDatas = new ArrayList<String>();
        for (int i = 0; i < 101; i++) {
            mDatas.add("abcde" + i);
        }
    }

    private void initEvent() {
        adapter.setOnItemClickLitener(new StaggeredHomeAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                ToastUtils.showShortToast("click" + position);
            }

            @Override
            public void onItemLongClick(View view, int position) {
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
