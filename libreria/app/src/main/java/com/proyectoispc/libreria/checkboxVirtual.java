package com.proyectoispc.libreria.ui;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class CheckboxVirtualActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago);

        CheckBox checkBoxPagoVirtual = findViewById(R.id.checkBoxPagoVirtual);
        LinearLayout formularioPagoVirtual = findViewById(R.id.formularioPagoVirtual);

        checkBoxPagoVirtual.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    formularioPagoVirtual.setVisibility(View.VISIBLE);
                    enableForm(true);
                } else {
                    formularioPagoVirtual.setVisibility(View.GONE);
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

