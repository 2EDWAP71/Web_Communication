package com.example.a2edwap71.webcommunication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
                    //Connect to URL
                    URL urlobj = new URL("http://www.free-map.org.uk/course/mad/ws/addhit.php");
                    HttpURLConnection connection = (HttpURLConnection) urlobj.openConnection();


                    String postData = "song=" + song.getTitle()
                            + "&artist=" + song.getArtist()
                            + "&year=" + song.getYear();

                    // Set Output data
                    connection.setDoOutput(true);
                    connection.setFixedLengthStreamingMode(postData.length());

                    //Output data
                    OutputStream out = connection.getOutputStream();
                    out.write(postData.getBytes());

                    if (connection.getResponseCode() == 200){

                        //Returns successful message
                        InputStream in = connection.getInputStream();
                        BufferedReader br = new BufferedReader(new InputStreamReader(in));
                        String result = "", line;
                        while ((line = br.readLine()) != null)
                            result += line;
                        return result;
                    } else {
                        return "HTTP ERROR:" + connection.getResponseCode();

                    }


                                    }
                catch (IOException e){
                    return "Error" + e.getMessage();
                }

            }
            return "Error:";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            TextView resultsTextView= (TextView)findViewById(R.id.resultsText);
            resultsTextView.setText(s);

        }
    }

}
