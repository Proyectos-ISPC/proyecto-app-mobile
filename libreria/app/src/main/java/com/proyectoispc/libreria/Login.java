package com.proyectoispc.libreria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.proyectoispc.libreria.db.DbUser;

public class Login extends AppCompatActivity {

    Button btnLogin;
    EditText emailInput, passwordInput;
    DbUser dbUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextInputLayout emailInputLayout = findViewById(R.id.emailInput);
        TextInputLayout nameInputLayout = findViewById(R.id.passwordInput);
        btnLogin = findViewById(R.id.signInButton);
        dbUser = new DbUser(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

    }

    public void login() {


        String email = emailInput.getText().toString();
        String password = passwordInput.getText().toString();

        boolean isAuthenticated = dbUser.checkUserCredentials(email, password);

        if (isAuthenticated) {
            Intent intent = new Intent(this, Home.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "El usuario y/o la contraseña son incorrectas", Toast.LENGTH_SHORT).show();
        }
    }
    }
