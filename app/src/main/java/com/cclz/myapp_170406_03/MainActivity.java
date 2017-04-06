package com.cclz.myapp_170406_03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    InputStream inputStream;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(){
            @Override
            public void run(){
                super.run();
                try{
                    URL url=new URL("http://www.google.com");
                    HttpURLConnection conn =(HttpURLConnection)url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.connect();
                    inputStream=conn.getInputStream();

                    BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder sb=new StringBuilder();
                    String str;
                    while((str=br.readLine()) != null){
                        sb.append(str);
                    }
                    Log.d("MYNELog", sb.toString());

                }catch (MalformedURLException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
