package com.example.myapplicationquiz;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    EditText email, password, password1;
    Button bregister;
    FirebaseAuth MyAuthentification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        FirebaseApp.initializeApp(getApplicationContext());
        setContentView(R.layout.activity_register);
        email = findViewById(R.id.etMail);
        password = findViewById(R.id.etPassword);
        password1 = findViewById(R.id.etPassword1);
        bregister = findViewById(R.id.bRegister);
        MyAuthentification = FirebaseAuth.getInstance();
        bregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = email.getText().toString();
                String pass = password.getText().toString();
                String pass1 = password1.getText().toString();
                if (TextUtils.isEmpty(mail) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(pass1)) {
                    Toast.makeText(Register.this, "Veuillez saisir les champs obligatoires",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pass.length() < 6) {
                    Toast.makeText(Register.this, "Nombre de caractéres insuffisant",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!pass.equals(pass1)) {
                    Toast.makeText(Register.this, "Vérification invalide",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                SigneUp(mail, pass);
            }
        });
    }

    private void SigneUp(String mail, String password) {
        Log.d("RegisterActivity", "Attempting to sign up user.");
        MyAuthentification.createUserWithEmailAndPassword(mail, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(Register.this, "Enregistrement réussi !",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(Register.this, "Enregistrement échoué" + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}





