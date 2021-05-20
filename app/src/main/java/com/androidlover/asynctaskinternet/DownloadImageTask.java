package com.androidlover.asynctaskinternet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class DownloadImageTask extends AsyncTask<String,Void, Bitmap> {

    @Override
    protected Bitmap doInBackground(String... strings) {
        String s1=strings[0];
        InputStream in;

        try {
            URL MyUrl=new URL(s1);
            HttpsURLConnection MyConn= (HttpsURLConnection)MyUrl.openConnection();
            MyConn.setReadTimeout(10000);
            MyConn.setConnectTimeout(20000);
            MyConn.setRequestMethod("GET");
            MyConn.connect();

            in=MyConn.getInputStream();

          Bitmap bitmap= BitmapFactory.decodeStream(in);

            return bitmap;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        MainActivity.image.setImageBitmap(bitmap);
    }
}
