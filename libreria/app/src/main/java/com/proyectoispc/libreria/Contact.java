package com.proyectoispc.libreria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.proyectoispc.libreria.db.DbContact;

public class Contact extends AppCompatActivity {

    Button enviarMensajeButton;
    EditText nameInput; // Declare nameInput here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.nav_view);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.contact);

        ImageView imagenFlecha = findViewById(R.id.imagenFlecha);
        ImageView imagenCarrito = findViewById(R.id.imagenCarrito);
        enviarMensajeButton = findViewById(R.id.enviarMensajeButton);
        TextInputLayout messageLayout = findViewById(R.id.messageLayout);
        nameInput = messageLayout.getEditText(); // Initialize nameInput here

        enviarMensajeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbContact dbContact  = new DbContact(Contact.this);
                long id = dbContact.insertarContact(nameInput.getText().toString());

                if (id > 0)  {
                    Toast.makeText(Contact.this, "Registro Guardado", Toast.LENGTH_LONG).show();
                    limpiar();
                } else {
                    Toast.makeText(Contact.this, "Error al guardar Registro", Toast.LENGTH_LONG).show();
                }
            }
        });
        imagenFlecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        imagenCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Carrito.class));
                overridePendingTransition(0,0);
            }
        });


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
                    return true;
                }

                if(id == R.id.profile){
                    startActivity(new Intent(getApplicationContext(),Profile.class));
                    overridePendingTransition(0,0);
                    return true;
                }

                return false;
            }
        });

    }
    private void limpiar() {
        nameInput.setText("");
    }
}
