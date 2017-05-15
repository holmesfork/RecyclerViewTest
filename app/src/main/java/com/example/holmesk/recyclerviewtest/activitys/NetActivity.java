package com.example.holmesk.recyclerviewtest.activitys;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.example.holmesk.recyclerviewtest.R;
import com.example.holmesk.recyclerviewtest.utils.OkHttpManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetActivity extends AppCompatActivity implements View.OnClickListener {

    private Button tb_get;
    private Button yb_get;
    private Button form;
    private Button download;
    private Button tb_post;
    private Button yb_post;
    private TextView mytv;
    private OkHttpClient client;
    private String result;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                String result = (String) msg.obj;
                mytv.setText(result);
                ToastUtils.showShortToast("同步get请求成功");
            }
        }
    };


    /**
     * 在这里直接设置连接超时，初始化OkHttpClient
     */
    private void init() {
        client = new OkHttpClient();
        client.newBuilder().connectTimeout(10, TimeUnit.SECONDS);
        client.newBuilder().readTimeout(10, TimeUnit.SECONDS);
        client.newBuilder().writeTimeout(10, TimeUnit.SECONDS);
    }

    private Request request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        init();
        initView();
    }

    private void initView() {
        tb_get = (Button) findViewById(R.id.tb_get);
        yb_get = (Button) findViewById(R.id.yb_get);
        form = (Button) findViewById(R.id.form);
        download = (Button) findViewById(R.id.download);
        tb_post = (Button) findViewById(R.id.tb_post);
        yb_post = (Button) findViewById(R.id.yb_post);
        mytv = (TextView) findViewById(R.id.mytv);

        tb_get.setOnClickListener(this);
        yb_get.setOnClickListener(this);
        form.setOnClickListener(this);
        download.setOnClickListener(this);
        tb_post.setOnClickListener(this);
        yb_post.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tb_get:
                tbGetData();//同步get请求
                break;
            case R.id.yb_get:
                ybGetData();//异步get请求
                break;
            case R.id.form:

                break;
            case R.id.download:

                break;
            case R.id.tb_post:

                break;
            case R.id.yb_post:

                break;
        }
    }

    private void tbGetData() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = OkHttpManager.getSyncString("http://result.eolinker.com/Kn5mQ5bc3f37907192691b123841b88fd19ed7dfdb7d55c?uri=shuju");
                Message msg = new Message();
                msg.obj = result;
                msg.what = 1;
                handler.sendMessage(msg);
            }
        }).start();

    }

    /**
     * 异步请求
     */
    private void ybGetData() {

        String path = "http://result.eolinker.com/Kn5mQ5bc3f37907192691b123841b88fd19ed7dfdb7d55c?uri=shuju";
        OkHttpManager.getAsync(path, new OkHttpManager.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {
                ToastUtils.showShortToast("请求失败");
            }

            @Override
            public void requestSuccess(String result) throws Exception {
                mytv.setText(result);
                ToastUtils.showShortToast("异步请求成功");
            }
        });

    }

    /**
     * 表单提交
     */
    private void initPost() {

        String url = "http://112.124.22.238:8081/course_api/banner/query";

        FormBody formBody = new FormBody.Builder()
                .add("type", "1")
                .build();
        request = new Request.Builder().url(url)
                .post(formBody).build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mytv.setText(result);
                                Toast.makeText(NetActivity.this, "表单提交成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        }).start();

    }

    /**
     * 文件下载地址
     */
    private void downLoadFile() {
        String url = "http://www.0551fangchan.com/images/keupload/20120917171535_49309.jpg";
        request = new Request.Builder().url(url).build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                //把请求成功的response转为字节流
                InputStream inputStream = response.body().byteStream();

                /**
                 * 在这里要加上权限   在mainfests文件中
                 * <uses-permission android:name="android.permission.INTERNET"/>
                 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
                 */

                //在这里用到了文件输出流
                FileOutputStream fileOutputStream = new FileOutputStream(new File("/sdcard/logo.jpg"));
                //定义一个字节数组
                byte[] buffer = new byte[2048];
                int len = 0;
                while ((len = inputStream.read(buffer)) != -1) {
                    //写出到文件
                    fileOutputStream.write(buffer, 0, len);
                }
                Toast.makeText(NetActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
                //关闭输出流
                fileOutputStream.flush();
                Log.d("wuyinlei", "文件下载成功...");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }


}
