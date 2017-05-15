package com.example.holmesk.recyclerviewtest.activitys;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.holmesk.recyclerviewtest.R;

public class TabActivity extends AppCompatActivity {

    private ViewPager myvp;
    private String[] mTitle = new String[20];
    private String[] mData = new String[20];
    private TabLayout mytab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        initView();
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            mTitle[i] = "title" + i;
            mData[i] = "data" + i;
        }
    }

    private void initView() {
        mytab = (TabLayout) findViewById(R.id.myTab);
        myvp = (ViewPager) findViewById(R.id.myvp);
        initData();
        myvp.setAdapter(mAdapter);
        mytab.setTabMode(TabLayout.MODE_SCROLLABLE);
        mytab.setupWithViewPager(myvp);
    }

    private PagerAdapter mAdapter = new PagerAdapter() {
        @Override
        public CharSequence getPageTitle(int position) {

            return mTitle[position];
        }

        @Override
        public int getCount() {
            return mData.length;
        }

        @Override
        public Object instantiateItem(View container, int position) {

            TextView tv = new TextView(TabActivity.this);
            tv.setTextSize(30.f);
            tv.setText(mData[position]);
            ((ViewPager) container).addView(tv);
            return tv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}



