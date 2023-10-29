package com.proyectoispc.libreria;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

public class CheckboxVirtual extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago);

        CheckBox checkBoxPagoVirtual = findViewById(R.id.checkBoxPagoVirtual);
        EditText nombreCompleto = findViewById(R.id.editTextNombreCompleto);
        EditText numeroTarjeta = findViewById(R.id.editTextNumeroTarjeta);
        EditText fechaExpiracion = findViewById(R.id.editTextFechaExpiracion);
        EditText codigoSeguridad = findViewById(R.id.editTextCodigoSeguridad);

        checkBoxPagoVirtual.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    nombreCompleto.setVisibility(View.VISIBLE);
                    numeroTarjeta.setVisibility(View.VISIBLE);
                    fechaExpiracion.setVisibility(View.VISIBLE);
                    codigoSeguridad.setVisibility(View.VISIBLE);
                    enableForm(true);
                } else {
                    nombreCompleto.setVisibility(View.GONE);
                    numeroTarjeta.setVisibility(View.GONE);
                    fechaExpiracion.setVisibility(View.GONE);
                    codigoSeguridad.setVisibility(View.GONE);
                    enableForm(false);
                }
            }
        });
    }

    // Método para habilitar o deshabilitar los campos del formulario
    private void enableForm(boolean enabled) {
        EditText nombreCompleto = findViewById(R.id.editTextNombreCompleto);
        EditText numeroTarjeta = findViewById(R.id.editTextNumeroTarjeta);
        EditText fechaExpiracion = findViewById(R.id.editTextFechaExpiracion);
        EditText codigoSeguridad = findViewById(R.id.editTextCodigoSeguridad);

        nombreCompleto.setEnabled(enabled);
        numeroTarjeta.setEnabled(enabled);
        fechaExpiracion.setEnabled(enabled);
        codigoSeguridad.setEnabled(enabled);
    }
}

