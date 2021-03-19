package com.example.act12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Choose extends AppCompatActivity {

    private static final int EXAMPLE_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
    }

    public void toWatcher(View v) {

        Intent i = new Intent(this, Watcher.class);
        startActivityForResult(i, EXAMPLE_CODE);
    }

    public void toPlayer(View v) {

        Intent i = new Intent(this, Player.class);
        startActivityForResult(i, EXAMPLE_CODE);
    }

}