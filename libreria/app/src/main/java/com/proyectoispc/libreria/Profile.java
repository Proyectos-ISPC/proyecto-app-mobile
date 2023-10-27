package com.proyectoispc.libreria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profile extends AppCompatActivity {
    ImageButton backbutton;
    ImageButton carrito;
    LinearLayout inputNames;
    LinearLayout inputEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        backbutton = findViewById(R.id.imageButton6);
        carrito = findViewById(R.id.imageButton9);
        inputNames = findViewById(R.id.inputNames);
        inputEmail = findViewById(R.id.inputEmail);

        // Navegavilidad de los botones superiores
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
}