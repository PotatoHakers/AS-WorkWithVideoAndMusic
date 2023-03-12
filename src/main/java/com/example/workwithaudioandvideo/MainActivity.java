package com.example.workwithaudioandvideo;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.workwithaudioandvideo.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.buttonListen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Your should hear a sound", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.you   );
                mediaPlayer.start();

            }
        });

        Button button = findViewById(R.id.button_go);

        button.setOnClickListener(new View.OnClickListener()
        { @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, VideoActivity.class);
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

        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this); try {
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

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}