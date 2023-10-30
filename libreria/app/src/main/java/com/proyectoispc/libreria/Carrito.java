package com.proyectoispc.libreria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.proyectoispc.libreria.adapter.ProductAdapter;
import com.proyectoispc.libreria.adapter.ProductCardAdapter;
import com.proyectoispc.libreria.models.Book;
import com.proyectoispc.libreria.models.SelectedBook;
import com.proyectoispc.libreria.service.ShoppingCartService;

import java.util.List;

public class Carrito extends AppCompatActivity {

    private ShoppingCartService shoppingCartService;
    double totalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);
        shoppingCartService = ShoppingCartService.getInstance();


        RecyclerView recyclerView = findViewById(R.id.selectedBooksCard);
        List<SelectedBook> selectedBooks = shoppingCartService.getBooks();

        this.totalAmount = this.shoppingCartService.getTotalAmount();
        TextView totalAmountText = findViewById(R.id.totalCompra);
        totalAmountText.setText("" + totalAmount);

        ProductCardAdapter adapter = new ProductCardAdapter(this ,selectedBooks);
        recyclerView.setAdapter(adapter);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.nav_view);
        ImageView flechaAtras = findViewById(R.id.backButton);
        flechaAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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
                    startActivity(new Intent(getApplicationContext(), Contact.class));
                    overridePendingTransition(0,0);
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

    public void iniciarCompra(View view) {
        Intent intent = new Intent(this, Checkout.class);
        startActivity(intent);
    }
}