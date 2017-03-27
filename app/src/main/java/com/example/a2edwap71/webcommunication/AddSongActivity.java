package com.example.a2edwap71.webcommunication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 2edwap71 on 23/03/2017.
 */
public class AddSongActivity extends AppCompatActivity implements View.OnClickListener {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityadd);



        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);

    }

    public void onClick(View view){
        EditText songEditText = (EditText) findViewById(R.id.addsong);
        String song= songEditText.getText().toString();

        EditText artistEditText = (EditText)findViewById(R.id.addartist);
        String artist = artistEditText.getText().toString();

        EditText yearEditText = (EditText) findViewById(R.id.addyear);
        int year = Integer.parseInt(yearEditText.getText().toString());

        Song songobj = new Song(song, artist,year);

        new AddSongAsyncTask().execute(songobj);

    }
    class AddSongAsyncTask extends AsyncTask<Song,Void,String>{

        @Override
        protected String doInBackground(Song... songs) {
            Song song = songs[0];

            if(song !=null){
                HttpURLConnection conn = null;
                try{
                    URL urlobj = new URL("http://www.free-map.org.uk/course/mad/ws/addhit.php");
                    conn = (HttpURLConnection) urlobj.openConnection();

                    String postData = "songs=" + song.getTitle()
                            + "artist=" + song.getTitle()
                            + "year=" + song.getTitle();
                    conn.setDoOutput(true);
                    conn.setFixedLengthStreamingMode(postData.length());

                    OutputStream out = conn.getOutputStream();
                    out.write(postData.getBytes());

                    if (conn.getResponseCode() == 200){
                        InputStream in = conn.getInputStream();
                        BufferedReader br = new BufferedReader(new InputStreamReader(in));
                        String result = "", line;
                        while ((line = br.readLine()) != null)
                            result += line;
                        return result;
                    } else {
                        return "HTTP ERROR:" + conn.getResponseCode();

                    }


                                    }
                catch (IOException e){
                    return "Error" + e.getMessage();
                }
                finally {
                    if (conn != null)
                        conn.disconnect();

                }
            }
            return "Error:";
        }

    }
}
