package com.proyectoispc.libreria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.proyectoispc.libreria.db.DbUser;

public class Profile extends AppCompatActivity {
    ImageButton backbutton, carrito, deleteButton ;
    LinearLayout inputNames;
    LinearLayout inputEmail;
    TextView textName, textEmail;
    String email, name;
    int id;
    Boolean account_status;
    TextInputLayout emailInputLayout, nameInputLayout;
    SharedPreferences sharedPreferences;
    EditText emailInput, nameInput;
    Button buttonNames, buttonEmail;
    DbUser dbUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Barra superior
        backbutton = findViewById(R.id.imageButton6);
        carrito = findViewById(R.id.imageButton9);

        // Se setea dinamicamente el nombre y el email
        this.textEmail = findViewById(R.id.email);
        this.textName = findViewById(R.id.name);
        this.fetchUserData();

        // Inputs y botones
        inputNames = findViewById(R.id.inputNames);
        inputEmail = findViewById(R.id.inputEmail);
        this.emailInputLayout = findViewById(R.id.inputMail);
        this.nameInputLayout = findViewById(R.id.inputName);
        emailInput = emailInputLayout.getEditText();
        nameInput = nameInputLayout.getEditText();
        buttonNames = findViewById(R.id.buttonNames);
        buttonEmail = findViewById(R.id.buttonEmail);
        deleteButton = findViewById(R.id.delete);

        // Conexion con BBDD
        dbUser = new DbUser(this);

        buttonNames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameInput.getText().toString().trim();

                if(!validateName()){
                    return;
                }

                boolean resp = dbUser.updateUserInfo(String.valueOf(id), name, email, account_status);
                if (resp) {
                    Toast.makeText(Profile.this, "El nombre se actualizo correctamente.", Toast.LENGTH_LONG).show();
                    dbUser.updateUserPrefDataByID(String.valueOf(id));
                    fetchUserData();
                }
                else {
                    Toast.makeText(Profile.this, "En este momento no se puede actualizar el nombre.", Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailInput.getText().toString().trim();

                if(!validateEmail()){
                    return;
                }

                boolean resp = dbUser.updateUserInfo(String.valueOf(id), name, email, account_status);
                if (resp) {
                    Toast.makeText(Profile.this, "El correo se actualizo correctamente.", Toast.LENGTH_LONG).show();
                    dbUser.updateUserPrefDataByID(String.valueOf(id));
                    fetchUserData();
                } else{
                    Toast.makeText(Profile.this, "En este momento no se puede actualizar el correo.", Toast.LENGTH_LONG).show();
                }

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showConfirmationDialog();
            }
        });



        // Navegabilidad de los botones superiores
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        carrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Carrito.class));
                overridePendingTransition(0,0);
            }
        });
        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.nav_view);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.profile);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id == R.id.home){
                    startActivity(new Intent(getApplicationContext(),Home.class));
                    overridePendingTransition(0,0);
                    return true;
                }

                if(id == R.id.catalogue){
                    startActivity(new Intent(getApplicationContext(),Catalogue.class));
                    overridePendingTransition(0,0);
                    return true;
                }

                if(id == R.id.contact){
                    startActivity(new Intent(getApplicationContext(),Contact.class));
                    overridePendingTransition(0,0);
                    return true;
                }

                if(id == R.id.profile){
                    return true;
                }

                return false;
            }
        });

    }

    public void fetchUserData(){
        this.sharedPreferences = getSharedPreferences(Login.USER_PREF_NAME, MODE_PRIVATE);
        this.name = this.sharedPreferences.getString(Login.KEY_NAME, null);
        this.email = this.sharedPreferences.getString(Login.KEY_EMAIL, null);
        this.textEmail.setText(this.email);
        this.textName.setText(this.name);
        this.id = this.sharedPreferences.getInt(Login.KEY_ID, 0);
    }

    public void expandirInputsName(View view) {
        int v = (inputNames.getVisibility()== View.GONE)? view.VISIBLE: view.GONE;
        TransitionManager.beginDelayedTransition(inputNames, new AutoTransition());
        inputNames.setVisibility(v);
    }

    public void expandirInputEmail(View view) {
        int v = (inputEmail.getVisibility()== View.GONE)? view.VISIBLE: view.GONE;
        TransitionManager.beginDelayedTransition(inputEmail, new AutoTransition());
        inputEmail.setVisibility(v);
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

    private boolean validateName(){
        String name = this.nameInputLayout.getEditText().getText().toString().trim();

        if(name.isEmpty()){
            this.nameInputLayout.setError("La contraseña es obligatoria");
            return false;
        }

        if(name.length() < 3 || name.matches(".*\\d.*")){
            this.nameInputLayout.setError("Ingrese un apellido valido.");
            return false;
        }

        this.nameInputLayout.setError(null);
        return true;
    }

    public void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Profile.this);
        builder.setTitle("Confirmación");
        builder.setMessage("¿Estás seguro de que deseas eliminar tu cuenta?");

        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean success = dbUser.updateUserInfo(String.valueOf(id), name, email, false);
                if (success) {
                    Toast.makeText(Profile.this, "Tu cuenta ha sido deshabilitada.", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    startActivity(new Intent(getApplicationContext(), Register.class));
                    overridePendingTransition(0, 0);
                } else {
                    Toast.makeText(Profile.this, "No se pudo deshabilitar la cuenta en este momento.", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}