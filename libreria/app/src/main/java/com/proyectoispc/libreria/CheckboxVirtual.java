package com.proyectoispc.libreria;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.proyectoispc.libreria.db.DbSale;
import com.proyectoispc.libreria.db.DbUser;
import com.proyectoispc.libreria.service.ShoppingCartService;

import java.util.Date;


public class CheckboxVirtual extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    int userId;

    ShoppingCartService shoppingCartService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago);

        this.sharedPreferences = getSharedPreferences(Login.USER_PREF_NAME, MODE_PRIVATE);
        this.userId = this.sharedPreferences.getInt(Login.KEY_ID, 0);

        shoppingCartService = ShoppingCartService.getInstance();

        CheckBox checkBoxPagoVirtual = findViewById(R.id.checkBoxPagoVirtual);
        EditText nombreCompleto = findViewById(R.id.editTextNombreCompleto);
        EditText numeroTarjeta = findViewById(R.id.editTextNumeroTarjeta);
        EditText fechaExpiracion = findViewById(R.id.editTextFechaExpiracion);
        EditText codigoSeguridad = findViewById(R.id.editTextCodigoSeguridad);

        ImageButton imagenFlecha = findViewById(R.id.backButton);
        ImageButton imagenCarrito = findViewById(R.id.shoppingCartButton);
        Button botonAtras = findViewById(R.id.botonAtras);

        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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

        // Método de validación de datos
        Button btnConfirmacion = findViewById(R.id.botonConfirmar);
        btnConfirmacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreCompletoStr = nombreCompleto.getText().toString();
                String numeroTarjetaStr = numeroTarjeta.getText().toString();
                String fechaExpiracionStr = fechaExpiracion.getText().toString();
                String codigoSeguridadStr = codigoSeguridad.getText().toString();

                // Validaciones de campos vacíos
                if (nombreCompletoStr.isEmpty() || numeroTarjetaStr.isEmpty() || fechaExpiracionStr.isEmpty() || codigoSeguridadStr.isEmpty()) {
                    Toast.makeText(CheckboxVirtual.this, "Todos los campos son requeridos.", Toast.LENGTH_SHORT).show();
                    return; // Termina la ejecución del método si hay campos vacíos.
                }

                // Validaciones adicionales
                if (!nombreCompletoStr.matches("[a-zA-Z]+")) {
                    nombreCompleto.setError("El nombre debe contener solo letras.");
                    return;
                }

                if (!numeroTarjetaStr.matches("\\d{16}")) {
                    numeroTarjeta.setError("Ingrese un número de tarjeta válido de 16 dígitos.");
                    return;
                }

                if (!codigoSeguridadStr.matches("\\d{3}")) {
                    codigoSeguridad.setError("Ingrese un código de seguridad válido de 3 dígitos.");
                    return;
                }

                if (!fechaExpiracionStr.matches("\\d{2}/\\d{2}")) {
                    fechaExpiracion.setError("Ingrese una fecha de expiración válida (MM/YY).");
                    return;
                }

                DbSale dbSale = new DbSale(CheckboxVirtual.this);
                dbSale.insertSale(userId, shoppingCartService.getTotalAmount(), shoppingCartService.getTotalQuantity(), "virtual", "mail", shoppingCartService.getBookId(), new Date().toString());

                startActivity(new Intent(getApplicationContext(), ConfirmacionCompra.class));
                overridePendingTransition(0,0);
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


