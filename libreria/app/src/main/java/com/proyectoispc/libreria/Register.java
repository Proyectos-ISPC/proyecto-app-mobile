package com.proyectoispc.libreria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputLayout;
import com.proyectoispc.libreria.db.DbHelper;
import com.proyectoispc.libreria.db.DbUser;

public class Register extends AppCompatActivity {

    Button btnCreate;
    EditText nameInput, emailInput, passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextInputLayout nameInputLayout = findViewById(R.id.nameInput);
        TextInputLayout emailInputLayout = findViewById(R.id.emailInput);
        TextInputLayout passwordInputLayout = findViewById(R.id.passwordInput);

        btnCreate = findViewById(R.id.registerButton);
        nameInput = nameInputLayout.getEditText();
        emailInput = emailInputLayout.getEditText();
        passwordInput = passwordInputLayout.getEditText();

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbUser dbUser = new DbUser(Register.this);
                long id = dbUser.insertUser(nameInput.getText().toString(), emailInput.getText().toString(), passwordInput.getText().toString());

                if(id > 0){
                    Toast.makeText(Register.this, "Registro exitoso", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),Home.class));
                    overridePendingTransition(0,0);
                }
            }
        });

    }
    
}