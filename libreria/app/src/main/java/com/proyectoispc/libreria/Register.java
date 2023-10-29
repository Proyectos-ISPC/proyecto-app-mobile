package com.proyectoispc.libreria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputLayout;
import com.proyectoispc.libreria.db.DbHelper;
import com.proyectoispc.libreria.db.DbUser;

public class Register extends AppCompatActivity {

    Button btnCreate, btnLogin;

    TextInputLayout nameInputLayout, emailInputLayout, passwordInputLayout;
    EditText nameInput, emailInput, passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.nameInputLayout = findViewById(R.id.nameInput);
        this.emailInputLayout = findViewById(R.id.emailInput);
        this.passwordInputLayout = findViewById(R.id.passwordInput);

        this.btnCreate = findViewById(R.id.registerButton);
        this.btnLogin = findViewById(R.id.login);
        this.nameInput = nameInputLayout.getEditText();
        this.emailInput = emailInputLayout.getEditText();
        this.passwordInput = passwordInputLayout.getEditText();

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validateRegister()){
                  return;
                }

                DbUser dbUser = new DbUser(Register.this);
                long id = dbUser.insertUser(nameInput.getText().toString(), emailInput.getText().toString(), passwordInput.getText().toString());

                if(id > 0){
                    Toast.makeText(Register.this, "Registro exitoso", Toast.LENGTH_LONG).show();
                    redirectToLogin();
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToLogin();
            }
        });

    }

    private boolean validateRegister(){
        boolean isValidPassword = validatePassword();
        boolean isValidEmail =  validateEmail() ;
        boolean isValidName = validateName();

        boolean isValid = isValidName && isValidEmail && isValidPassword;

        return isValid;
    }

    private boolean validateName(){
        String name = this.nameInputLayout.getEditText().getText().toString().trim();

        if(name.isEmpty()){
            nameInputLayout.setError("El nombre es obligatorio");
            return false;
        }

        this.nameInputLayout.setError(null);
        return true;
    }


    private boolean validateEmail(){
        String email = this.emailInputLayout.getEditText().getText().toString().trim();

        if(email.isEmpty()){
            this.emailInputLayout.setError("El correo electronico es obligatorio");
            return false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            this.emailInputLayout.setError("Correo electronico invalido");
            return false;
        }

        this.emailInputLayout.setError(null);
        return true;
    }

    private boolean validatePassword(){
        String password = this.passwordInputLayout.getEditText().getText().toString().trim();

        if(password.isEmpty()){
            this.passwordInputLayout.setError("La contraseña es obligatoria");
            return false;
        }

        if(password.length() < 8){
            this.passwordInputLayout.setError("La contraseña es de al menos 8 caracteres");
            return false;
        }

        this.passwordInputLayout.setError(null);
        return true;
    }

    private void redirectToLogin(){
        startActivity(new Intent(getApplicationContext(),Login.class));
        overridePendingTransition(0,0);
    }
    
}