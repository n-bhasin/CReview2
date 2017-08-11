package com.remake.creview;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class SplashActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharedPreferences = getSharedPreferences("activate",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        handler.sendEmptyMessageDelayed(420,3000);

    }

    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 420){
                if(sharedPreferences.contains("keyId")){
                    Intent intent = new Intent(SplashActivity.this,ServiceMainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Intent intent = new Intent(SplashActivity.this,ActivationActivity.class);
                    startActivity(intent);
                    finish();
                }

            }

        }
    };

}
