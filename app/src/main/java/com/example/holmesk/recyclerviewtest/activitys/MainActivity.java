package com.example.holmesk.recyclerviewtest.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.example.holmesk.recyclerviewtest.R;
import com.example.holmesk.recyclerviewtest.adapter.MyViewAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> mDatas;
    private RecyclerView myrecyc;
    private MyViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        initEvent();
        //////
        //shangzhemingwangbadan
        //菜逼南
        //97623
        //德玛西亚
        init();
    }

    private void init() {
    }

    private void initData() {

        mDatas = new ArrayList<String>();
        for (int i = 0; i < 101; i++) {
            mDatas.add("abcde" + i);
        }
    }


    private void initView() {
        myrecyc = (RecyclerView) findViewById(R.id.myrecyc);
        myrecyc.setLayoutManager(new LinearLayoutManager(this));
        myrecyc.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter = new MyViewAdapter(this, mDatas);
        myrecyc.setAdapter(adapter);
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
