package com.example.holmesk.recyclerviewtest.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.holmesk.recyclerviewtest.R;

public class RecycActivity extends AppCompatActivity implements View.OnClickListener {

    private Button list_but;
    private Button grid_but;
    private Button pu_but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyc);
        initView();
    }

    private void initView() {
        list_but = (Button) findViewById(R.id.list_but);
        grid_but = (Button) findViewById(R.id.grid_but);
        pu_but = (Button) findViewById(R.id.pu_but);

        list_but.setOnClickListener(this);
        grid_but.setOnClickListener(this);
        pu_but.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.list_but:
                startActivity(new Intent(RecycActivity.this, MainActivity.class));
                break;
            case R.id.grid_but:
                startActivity(new Intent(RecycActivity.this, GridActivity.class));
                break;
            case R.id.pu_but:
                startActivity(new Intent(RecycActivity.this, PuBuActivity.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
