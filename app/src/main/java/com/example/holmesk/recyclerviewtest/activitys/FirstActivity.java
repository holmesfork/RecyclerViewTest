package com.example.holmesk.recyclerviewtest.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.holmesk.recyclerviewtest.R;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    private Button recyc_but;
    private Button ok_but;
    private Button tab_but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initView();
    }

    private void initView() {

        recyc_but = (Button) findViewById(R.id.recyc_but);
        recyc_but.setOnClickListener(this);
        ok_but = (Button) findViewById(R.id.ok_but);
        ok_but.setOnClickListener(this);
        tab_but = (Button) findViewById(R.id.tab_but);
        tab_but.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recyc_but:
                startActivity(new Intent(FirstActivity.this, RecycActivity.class));
                break;
            case R.id.ok_but:
                startActivity(new Intent(FirstActivity.this, NetActivity.class));
                break;
            case R.id.tab_but:
                startActivity(new Intent(FirstActivity.this, TabActivity.class));
                break;
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
