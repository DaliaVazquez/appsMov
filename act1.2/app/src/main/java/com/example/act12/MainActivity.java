package com.example.act12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final int EXAMPLE_CODE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void button1Click(View v) {

        Intent i = new Intent(this, PerfilActivity.class);
        startActivityForResult(i, EXAMPLE_CODE);
    }
}