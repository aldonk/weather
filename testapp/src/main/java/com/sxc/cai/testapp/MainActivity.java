package com.sxc.cai.testapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.sxc.cai.testapp.R.*;

public class MainActivity extends Activity implements View.OnClickListener{

    public static final int SHOW_RESPONSE = 0;
    private Button sendRequest;
    private TextView responseText;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_RESPONSE:
                    String response = (String) msg.obj;
// 在这里进行UI操作，将结果显示到界面上
                    responseText.setText(response);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        sendRequest = (Button) findViewById(id.send_request);
        responseText = (TextView) findViewById(id.response);
        sendRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        SharedPreferences pref = getSharedPreferences("default",
                MODE_PRIVATE);
        String name = pref.getString("city_selected", "");
        Log.d("MainActivity", "name is " + name);

        /*if (v.getId() == R.id.send_request) {
            sendRequestWithHttpURLConnection();
        }*/
    }
    private void sendRequestWithHttpURLConnection() {
// 开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL("http://www.weather.com.cn/adat/cityinfo/101010100.html");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
// 下面对获取到的输入流进行读取
                    BufferedReader reader = new BufferedReader(new
                            InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    Message message = new Message();
                    message.what = SHOW_RESPONSE;
// 将服务器返回的结果存放到Message中
                    message.obj = response.toString();
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

}