package com.proyectoispc.libreria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.proyectoispc.libreria.models.Book;
import com.proyectoispc.libreria.service.ShoppingCartService;

public class BookDetail extends AppCompatActivity {

    ImageButton backbutton, shoppingCartButton;

    Button addBookButton;

    ShoppingCartService shoppingCartService;

    String bookName, author, description;
    double price;
    int cover, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        shoppingCartService = ShoppingCartService.getInstance();


        backbutton = findViewById(R.id.backButton);
        shoppingCartButton = findViewById(R.id.shoppingCartButton);
        addBookButton = findViewById(R.id.addBook);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.catalogue);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        bookName = intent.getStringExtra("name");
        author = intent.getStringExtra("author");
        price = intent.getDoubleExtra("price", 0);
        description = intent.getStringExtra("description");
        cover = intent.getIntExtra("cover", 0);

        TextView textViewBookName = findViewById(R.id.textViewBookName);
        TextView textViewAuthor = findViewById(R.id.textViewAuthor);
        TextView textViewPrice = findViewById(R.id.textViewPrice);
        TextView textViewDescription = findViewById(R.id.textViewDescription);
        ImageView imageViewCover = findViewById(R.id.imageViewCover);


        textViewBookName.setText(bookName);
        textViewAuthor.setText(author);
        textViewPrice.setText("$ " + price);
        textViewDescription.setText(description);
        imageViewCover.setImageResource(cover);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.home) {
                    startActivity(new Intent(getApplicationContext(), Home.class));
                    overridePendingTransition(0, 0);
                    return true;
                }

                if (id == R.id.catalogue) {
                    startActivity(new Intent(getApplicationContext(), Catalogue.class));
                    overridePendingTransition(0, 0);
                    return true;
                }

                if (id == R.id.contact) {
                    startActivity(new Intent(getApplicationContext(), Contact.class));
                    overridePendingTransition(0, 0);
                    return true;
                }

                if (id == R.id.profile) {
                    startActivity(new Intent(getApplicationContext(), Profile.class));
                    overridePendingTransition(0, 0);
                    return true;
                }
                return false;
            }
        });

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        shoppingCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Carrito.class));
                overridePendingTransition(0, 0);
            }
        });

        addBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book(id, bookName, author, description, cover, price);
                shoppingCartService.addBook(book);
            }
        });

    }

}