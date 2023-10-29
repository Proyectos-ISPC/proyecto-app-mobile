package com.proyectoispc.libreria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.proyectoispc.libreria.db.DbSale;
import com.proyectoispc.libreria.db.DbUser;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {
    ImageButton backbutton;
    ImageButton carrito;
    LinearLayout inputNames;
    LinearLayout inputEmail;
    TextView textName, textEmail;
    String id, email, name;
    TextInputLayout emailInputLayout, nameInputLayout;
    SharedPreferences sharedPreferences;
    EditText emailInput, nameInput;
    Button buttonNames, buttonEmail;
    DbUser dbUser;

    DbSale dbSale;

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

        // Conexion con BBDD
        dbUser = new DbUser(this);
        dbSale = new DbSale(this);

        // Carga de compras
        this.listAcordeon(Integer.parseInt(id));

        buttonNames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameInput.getText().toString().trim();

                if(!validateName()){
                    return;
                }

                boolean resp = dbUser.updateUserInfo(id, name, email);
                if (resp) {
                    Toast.makeText(Profile.this, "El nombre se actualizo correctamente.", Toast.LENGTH_LONG).show();
                    dbUser.updateUserPrefDataByID(id);
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

                boolean resp = dbUser.updateUserInfo(id, name, email);
                if (resp) {
                    Toast.makeText(Profile.this, "El correo se actualizo correctamente.", Toast.LENGTH_LONG).show();
                    dbUser.updateUserPrefDataByID(id);
                    fetchUserData();
                } else{
                    Toast.makeText(Profile.this, "En este momento no se puede actualizar el correo.", Toast.LENGTH_LONG).show();
                }

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
        this.id = this.sharedPreferences.getString(Login.KEY_ID, null);
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
    public void listAcordeon(int idUser) {
        Cursor cursor = dbSale.getListSaleID(idUser);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                LinearLayout accordionContainer = findViewById(R.id.accordionContainer);

                do {
                    // Obtener los valores de las columnas
                    int id = cursor.getInt(0);
                    Double total_cost = cursor.getDouble(1);
                    Long date = cursor.getLong(2);
                    String status = cursor.getString(3);

                    // Crear un CardView para el acordeón
                    CardView accordionCardView = new CardView(this);
                    accordionCardView.setLayoutParams(new CardView.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    ));

                    // Crear un LinearLayout vertical para el contenido del acordeón
                    LinearLayout accordionContentLayout = new LinearLayout(this);
                    accordionContentLayout.setLayoutParams(new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    ));
                    accordionContentLayout.setOrientation(LinearLayout.VERTICAL);

                    // Configurar el título del acordeón
                    TextView titleTextView = new TextView(this);
                    titleTextView.setText("ID: " + id + " - Date: " + date + " - Status: " + status);
                    accordionContentLayout.addView(titleTextView);

                    // Configurar el cuerpo del acordeón
                    TextView bodyTextView = new TextView(this);
                    bodyTextView.setText("Total Cost: " + total_cost);
                    accordionContentLayout.addView(bodyTextView);

                    // Agregar el contenido al CardView
                    accordionCardView.addView(accordionContentLayout);

                    // Agregar el CardView al contenedor
                    accordionContainer.addView(accordionCardView);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } else {
            // Informar que no hay productos comprados.
            LinearLayout accordionContainer = findViewById(R.id.accordionContainer);
            TextView voidTextView = new TextView(this);
            voidTextView.setText("No hay registros para mostrar");
            accordionContainer.addView(voidTextView);
        }
    }
}