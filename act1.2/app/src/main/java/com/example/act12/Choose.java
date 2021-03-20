package com.example.act12;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Choose extends AppCompatActivity {

    private static final int EXAMPLE_CODE = 0;
    private FirebaseAuth mAuth;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        textView = findViewById(R.id.hola);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null){
            textView.setText("Hello "+user.getDisplayName());
        }


    }

    public void toWatcher(View v) {

        Intent i = new Intent(this, Watcher.class);
        startActivityForResult(i, EXAMPLE_CODE);
    }

    public void toPlayer(View v) {

        Intent i = new Intent(this, Player.class);
        startActivityForResult(i, EXAMPLE_CODE);
    }
    public void goBack(View v){
        mAuth.signOut();
        Intent i = new Intent();
        setResult(Activity.RESULT_OK, i);

        finish();
    }

}