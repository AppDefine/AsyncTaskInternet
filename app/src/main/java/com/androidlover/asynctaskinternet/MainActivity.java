package com.androidlover.asynctaskinternet;

import androidx.appcompat.app.AppCompatActivity;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static TextView text;
    static ImageView image;

    ConnectInternetTask c1;
    DownloadImageTask d1;

    ConnectivityManager ConnM;
    NetworkInfo MyInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=(TextView)findViewById(R.id.textView);
        image=(ImageView)findViewById(R.id.imageView);

        ConnM=(ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        MyInfo=ConnM.getActiveNetworkInfo();
    }

    public void WebPage(View view) {
        c1=new ConnectInternetTask();
        c1.execute("https://www.google.com");
    }

    public void Image(View view) {
        if(MyInfo!=null&&MyInfo.isConnected()){
            d1=new DownloadImageTask();
            d1.execute("https://img.talkandroid.com/uploads/2012/02/Android-G-b_46.jpg");
        }
        else{
            Toast.makeText(MainActivity.this,"Internet Not Connected",Toast.LENGTH_SHORT).show();
        }

    }
}