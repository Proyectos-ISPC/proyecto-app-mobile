package com.proyectoispc.libreria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.proyectoispc.libreria.db.DbUser;

public class Login extends AppCompatActivity {

    static final String USER_PREF_NAME = "user_pref";
    static final String KEY_NAME = "name";
    static final String KEY_EMAIL = "email";
    static final String KEY_ID = "id";
    SharedPreferences sharedPreferences;
    Button btnLogin, btnRegister;
    TextInputLayout emailInputLayout, passwordInputLayout;
    EditText emailInput, passwordInput;
    DbUser dbUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.sharedPreferences = getSharedPreferences(USER_PREF_NAME, MODE_PRIVATE);

        this.emailInputLayout = findViewById(R.id.emailInput);
        this.passwordInputLayout = findViewById(R.id.passwordInput);
        emailInput = emailInputLayout.getEditText();
        passwordInput = passwordInputLayout.getEditText();

        btnLogin = findViewById(R.id.signInButton);
        btnRegister = findViewById(R.id.register);
        dbUser = new DbUser(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();

                if(!validateInputs()){
                    return;
                }

                if (dbUser.checkEmailPassword(email, password)){
                    fillUserPrefData(email, password);
                    Toast.makeText(Login.this, "Login exitoso", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), Home.class);
                    startActivity(intent);
                } else{
                    Toast.makeText(Login.this, "Las credenciales no son validas", Toast.LENGTH_LONG).show();

                }

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToRegister();
            }
        });

    }

    public void redirectToRegister(){
        startActivity(new Intent(getApplicationContext(),Register.class));
        overridePendingTransition(0,0);
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

    public boolean validateInputs(){
        boolean isEmailValid = validateEmail();
        boolean isPasswordValid = validatePassword();
        return isEmailValid && isPasswordValid;
    }

    private void fillUserPrefData(String email, String password){
        Cursor userData = dbUser.getUserData(email, password);

        if(userData.moveToFirst()){
            SharedPreferences.Editor editor = this.sharedPreferences.edit();
            editor.putInt(KEY_ID, userData.getInt(0));
            editor.putString(KEY_NAME, userData.getString (3));
            editor.putString(KEY_EMAIL, userData.getString (1));
            editor.apply();
        }
    }
}
