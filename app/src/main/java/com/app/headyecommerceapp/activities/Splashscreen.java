package com.app.headyecommerceapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.app.headyecommerceapp.R;

public class Splashscreen extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        new countDownStart(3000, 1000).start();
    }

    public class countDownStart extends CountDownTimer {
        public countDownStart(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
        }

        @Override
        public void onFinish() {
            Intent intent;
            intent = new Intent(Splashscreen.this, LandingPageActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
