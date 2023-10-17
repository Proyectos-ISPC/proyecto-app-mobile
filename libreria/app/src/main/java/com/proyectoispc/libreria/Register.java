package com.proyectoispc.libreria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.proyectoispc.libreria.db.DbHelper;

public class Register extends AppCompatActivity {

    Button btnCreate;
    EditText txtName, txtEmail, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnCreate = findViewById(R.id.registerButton);

    }

    public void register(View view) {
        startActivity(new Intent(getApplicationContext(), Home.class));
        overridePendingTransition(0, 0);
    }

}