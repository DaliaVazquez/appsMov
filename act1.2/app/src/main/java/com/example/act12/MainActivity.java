package com.example.act12;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private static final int EXAMPLE_CODE = 0;
    private FirebaseAuth mAuth;
    private DatabaseReference dbRef;
    private EditText login, password, value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login);
        password = findViewById(R.id.editTextTextPassword);
        mAuth = FirebaseAuth.getInstance();
    }

    public void toCuenta(View v) {
        Intent i = new Intent(this, CrearCuenta.class);
        startActivityForResult(i, EXAMPLE_CODE);
    }
    public void toChoose() {
        FirebaseUser user = mAuth.getCurrentUser();

        if(user != null)
        {
            Intent i = new Intent(this, Choose.class);
            startActivityForResult(i, EXAMPLE_CODE);
        }
    }

    public void login(View v){

        // logout to do login again
        mAuth.signOut();

        mAuth.signInWithEmailAndPassword(login.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            // do something on success
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(
                                    MainActivity.this,
                                    "Hey: " + user.getDisplayName(),
                                    Toast.LENGTH_SHORT).show();

                            // how to retrieve info from the user
                            // check if user is logged in
                            if(user != null){

                                String name = user.getDisplayName();
                                String email = user.getEmail();
                                Uri photoUrl = user.getPhotoUrl();

                                boolean verified = user.isEmailVerified();

                                String uid = user.getUid();
                                // String token = user.getIdToken();
                                //Toast.makeText(MainActivity.this, "USER IS LOGGED IN: " + uid, Toast.LENGTH_SHORT).show();
                            }
                            toChoose();

                        }else{

                            // do something on failure
                            Toast.makeText(
                                    MainActivity.this,
                                    "FAILURE: " + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }


}