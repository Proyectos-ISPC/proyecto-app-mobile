package com.proyectoispc.libreria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.proyectoispc.libreria.db.DbContact;
import com.proyectoispc.libreria.db.DbHelper;

public class Contact extends AppCompatActivity {

    EditText txtName, txtEmail, txtMessage;

    Button enviarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        txtName = findViewById(R.id.name);
        txtEmail = findViewById(R.id.email);
        txtMessage = findViewById(R.id.message);

        enviarButton = findViewById(R.id.enviarButton);

        enviarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper dbHelper = new DbHelper(Contact.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if (db != null) {
                    Toast.makeText(Contact.this, "Base de datos creada", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Contact.this, "Error al crear Base de Datos", Toast.LENGTH_LONG).show();
                }

                DbContact dbContact  = new DbContact(Contact.this);
                long id = dbContact.insertarContact(txtName.getText().toString(), txtEmail.getText().toString(), txtMessage.getText().toString());

                if (id > 0)  {
                    Toast.makeText(Contact.this, "Registro Guardado", Toast.LENGTH_LONG).show();
                    limpiar();
                } else {
                    Toast.makeText(Contact.this, "Error al guardar Registro", Toast.LENGTH_LONG).show();
                }
            }
        });

        BottomNavigationView bottomNavigationView=findViewById(R.id.nav_view);
        bottomNavigationView.setSelectedItemId(R.id.contact);

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
        txtName.setText("");
        txtEmail.setText("");
        txtMessage.setText("");
    }
}
