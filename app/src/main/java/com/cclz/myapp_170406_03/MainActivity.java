package com.cclz.myapp_170406_03;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    InputStream inputStream;
    Handler handler;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.textView);
        handler=new Handler();
        new Thread(){
            @Override
            public void run(){
                super.run();
                try{
                    URL url=new URL("https://udn.com/rssfeed/news/1");
                    HttpURLConnection conn =(HttpURLConnection)url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.connect();
                    inputStream=conn.getInputStream();

                    BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));
                    final StringBuilder sb=new StringBuilder();
                    String str;
                    while((str=br.readLine()) != null){
                        sb.append(str);
                    }
                    Log.d("MYNELog", sb.toString());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            tv.setText(sb.toString());
                        }
                    });

                }catch (MalformedURLException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
