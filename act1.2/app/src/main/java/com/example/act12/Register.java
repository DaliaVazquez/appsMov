package com.example.act12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {


    private FirebaseAuth authentication;
    private DatabaseReference dbNombre, dbDescripcion, dbPuntos;
    private EditText nombre,descripcion, puntos;
    //private TextView nombreView,descripcionView, puntosView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nombre = findViewById(R.id.inputNombreReto);
        descripcion = findViewById(R.id.inputDescripcion);
        puntos = findViewById(R.id.inputPuntosReto);

        /*
        nombreView = findViewById(R.id.nombreReto);
        descripcionView = findViewById(R.id.descripcionReto);
        puntosView = findViewById(R.id.puntosReto);

         */

        authentication = FirebaseAuth.getInstance();
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
                //nombreView.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Register.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        dbDescripcion = dbFirebase.getReference("descripcionReto");

        dbDescripcion.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                //descripcionView.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Register.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        dbPuntos = dbFirebase.getReference("puntos");

        dbPuntos.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.getValue(String.class);
                //puntosView.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Register.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void saveValue(View v){

        Toast.makeText(this, "SAVING VALUE", Toast.LENGTH_SHORT).show();
        dbNombre.setValue(nombre.getText().toString());
        dbPuntos.setValue(puntos.getText().toString());
        dbDescripcion.setValue((descripcion.getText().toString()));
    }

    public void back(View v) {
        Intent i = new Intent();

        setResult(Activity.RESULT_OK, i);

        finish();
    }
}