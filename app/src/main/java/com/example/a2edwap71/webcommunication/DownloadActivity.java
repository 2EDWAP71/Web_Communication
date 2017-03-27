package com.example.a2edwap71.webcommunication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by 2edwap71 on 23/03/2017.
 */
public class DownloadActivity extends AppCompatActivity implements View.OnClickListener {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.downloadact);
        Button go = (Button) findViewById(R.id.btn1);


       Button downloadButton = (Button) findViewById(R.id.btndownload);
       downloadButton.setOnClickListener(this);

       Button addSongButton = (Button) findViewById(R.id.btnadd);
        addSongButton.setOnClickListener(this);


    }

    public void onClick (View view){
        if (view.getId() == R.id.btndownload){
            startActivity(new Intent(this, MainActivity.class));

        }
        else if (view.getId() == R.id.btnadd){
            startActivity(new Intent(this, AddSongActivity.class));
        }



    }
}
