package com.example.act12;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Watcher extends AppCompatActivity {

    private ImageButton buttonBack;
    private TextView nombreView,descripcionView, puntosView;
    private DatabaseReference dbNombre, dbDescripcion, dbPuntos;
    private FirebaseAuth authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watcher);

        buttonBack = findViewById(R.id.back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();

                setResult(Activity.RESULT_OK, i);

                finish();
            }
        });

        authentication = FirebaseAuth.getInstance();

        nombreView = findViewById(R.id.nombreRetoWatcher);
        descripcionView = findViewById(R.id.descRetoWatcher);
        puntosView = findViewById(R.id.puntosRetoWatcher);
    }


    protected void onStart() {
        super.onStart();

        FirebaseUser user = authentication.getCurrentUser();

        FirebaseDatabase dbFirebase = FirebaseDatabase.getInstance();

        dbNombre = dbFirebase.getReference("nombreReto");

        dbNombre.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                nombreView.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Watcher.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        dbDescripcion = dbFirebase.getReference("descripcionReto");

        dbDescripcion.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                descripcionView.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Watcher.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        dbPuntos = dbFirebase.getReference("puntos");

        dbPuntos.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                puntosView.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Watcher.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void toChoose(View v){
            Intent i = new Intent(this, Register.class);
            startActivityForResult(i, 0);

    }

}