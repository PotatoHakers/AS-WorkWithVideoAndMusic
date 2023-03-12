package com.example.workwithaudioandvideo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        VideoView videoView = findViewById(R.id.videoView);

        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName()+"/" + R.raw.video));

        videoView.setMediaController(new MediaController(this)); videoView.start();
        videoView.requestFocus();


        Button button = findViewById(R.id.button_back);

        button.setOnClickListener(new View.OnClickListener()
        { @Override
        public void onClick(View v) {

            Intent intent = new Intent(VideoActivity.this, MainActivity.class);
            startActivity(intent);}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(VideoActivity.this); try {
            dialog.setMessage(getTitle().toString()+ " версия "+ getPackageManager().getPackageInfo(getPackageName(),0).versionName +
                    "\r\n\nПрограмма с примером выполнения диалогового окна\r\n\n Автор - Гержа Александр Геннадьевич, гр. 20ИТ-1");
        } catch (PackageManager.NameNotFoundException e) { e.printStackTrace();

        }
        dialog.setTitle("О программе"); dialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { dialog.dismiss();
            }
        });
        dialog.setIcon(R.mipmap.ic_launcher_round); AlertDialog alertDialog = dialog.create(); alertDialog.show();
        return super.onOptionsItemSelected(item);
    }
}