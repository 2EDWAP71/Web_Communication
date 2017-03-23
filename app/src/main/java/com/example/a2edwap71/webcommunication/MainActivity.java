package com.example.a2edwap71.webcommunication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.AsyncTask;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    class MyTask extends AsyncTask<String,Void,String>
    {
        public String doInBackground(String...arguments) {
            HttpURLConnection conn = null;
            String url = arguments[0];
            String artist = arguments[1];

            if (url == null || url.isEmpty() ||
                    artist == null || artist.isEmpty()){
                return "Error";
            }

            try {
                URL urlobj = new URL( url + "?artist=" + artist);
                conn = (HttpURLConnection) urlobj.openConnection();
                InputStream in = conn.getInputStream();
                if (conn.getResponseCode() == 200) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    String result = "", line;
                    while ((line = br.readLine()) != null)
                        result += line;
                    return result;
                } else {
                    return "HTTP ERROR:" + conn.getResponseCode();

                }
            } catch (IOException e) {
                return e.toString();
            } finally {
                if (conn != null)
                    conn.disconnect();

            }
        }
            public void onPostExecute (String result)
    {
        TextView et1 = (TextView) findViewById(R.id.et2);
        et1.setText(result);

    }

        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button go = (Button) findViewById(R.id.btn1);
        go.setOnClickListener(this);

    }
    public void onClick(View v){

        EditText artistEditText = (EditText) findViewById(R.id.et1);
        String artist = artistEditText.getText().toString();

        EditText urlEditText =(EditText) findViewById(R.id.et3);
        String url = urlEditText.getText().toString();
        MyTask t = new MyTask();
        t.execute(url,artist);
    }
}
