package com.androidlover.asynctaskinternet;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ConnectInternetTask extends AsyncTask<String,Void,String> {

    Context ctx;


    @Override
    protected String doInBackground(String... strings) {
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

            BufferedReader MyBuff=new BufferedReader(new InputStreamReader(in));
            StringBuilder st=new StringBuilder();
            String line="";

            while((line=MyBuff.readLine())!=null){
                st.append(line+"  \n");
            }

            in.close();
            MyBuff.close();

            return st.toString();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        MainActivity.text.setText(s);
    }
}
