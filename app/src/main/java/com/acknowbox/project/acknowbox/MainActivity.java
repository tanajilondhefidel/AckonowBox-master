package com.acknowbox.project.acknowbox;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.acknowbox.project.acknowbox.Utils.Util;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        startTimer();



    }
    public void startTimer(){
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SharedPreferences prefernce = MainActivity.this.getSharedPreferences("AcknowBox", 0);
                if (prefernce.getString(Util.SESSION_ID, "").equalsIgnoreCase("")) {
                    Intent intent = new Intent(MainActivity.this, Login_Activity.class);
                    MainActivity.this.startActivity(intent);
                } else {
                    startActivity(new Intent(MainActivity.this, Login_Activity.class));

                    MainActivity.this.finish();
                }
            }
        }, 5 * 1000);
    }

}
