package com.proyectoispc.libreria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class activity_micuenta extends AppCompatActivity {
    ImageButton backbutton;

    ImageButton carrito;
    TextView cuerpo;
    LinearLayout itemacordeon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_micuenta);
        backbutton = findViewById(R.id.imageButton6);
        carrito = findViewById(R.id.imageButton9);
        cuerpo = findViewById(R.id.cuerpo1);
        itemacordeon = findViewById(R.id.itemacordeon);
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

    }

    public void expandir(View view) {
        int v = (cuerpo.getVisibility()== View.GONE)? view.VISIBLE: view.GONE;
        TransitionManager.beginDelayedTransition(itemacordeon, new AutoTransition());
        cuerpo.setVisibility(v);
    }
}