package com.devz.app;

import android.content.Intent;
import android.os.Bundle;

import com.develouz.view.FlatSplash;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity implements FlatSplash.Callback {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        FlatSplash view = findViewById(R.id.splash_screen);
        view.data().setCallback(this);
    }

    @Override
    public void onEula(String title, String url) {
        Intent intent = new Intent(this, PolicyBrowser.class);
        intent.putExtra("TITLE", title);
        intent.putExtra("URL", url);
        startActivity(intent);
    }

    @Override
    public void onPrivacy(String title, String url) {
        Intent intent = new Intent(this, PolicyBrowser.class);
        intent.putExtra("TITLE", title);
        intent.putExtra("URL", url);
        startActivity(intent);
    }

    @Override
    public void onAgree() {

    }

    @Override
    public void onConsent(boolean personalizedAds) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
