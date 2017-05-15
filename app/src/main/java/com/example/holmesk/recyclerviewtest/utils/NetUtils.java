package com.example.holmesk.recyclerviewtest.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：holmes k
 * 时间：2017.05.10 14:04
 */


public class NetUtils {


    private OkHttpClient client;
    private Request request;
    private String result;
    private SetResult setResult;

    /**
     * 在这里直接设置连接超时，初始化OkHttpClient
     */
    private void init() {
        client = new OkHttpClient();
        client.newBuilder().connectTimeout(10, TimeUnit.SECONDS);
        client.newBuilder().readTimeout(10, TimeUnit.SECONDS);
        client.newBuilder().writeTimeout(10, TimeUnit.SECONDS);
    }

    public interface SetResult {
        void giveResult(String result);
    }

    public void getResult(SetResult setResult) {
        this.setResult = setResult;
    }


    /**
     * get请求同步方法
     */
    public void initSyncData(final String path) {
        init();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //--------------
                    request = new Request.Builder().url(path).build();
                    Response response = client.newCall(request).execute();
                    result = response.body().string();
                    setResult.giveResult(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


}
