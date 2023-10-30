package com.proyectoispc.libreria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.proyectoispc.libreria.adapter.ProductAdapter;
import com.proyectoispc.libreria.db.DbBook;
import com.proyectoispc.libreria.models.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Home extends AppCompatActivity {

    DbBook dbBook;
    ImageButton backbutton, shoppingCartButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dbBook = new DbBook(this);

        backbutton = findViewById(R.id.backButton);
        shoppingCartButton = findViewById(R.id.shoppingCartButton);

        RecyclerView recyclerView = findViewById(R.id.recomendedBooks);
        List<Book> recomendedBooks = getRecomendedBooks();
        ProductAdapter adapter = new ProductAdapter(this ,recomendedBooks);
        recyclerView.setAdapter(adapter);

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.nav_view);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id == R.id.home){
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
                    startActivity(new Intent(getApplicationContext(), Profile.class));
                    overridePendingTransition(0,0);
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
                overridePendingTransition(0,0);
            }
        });
    }

    public void onClickBook(){
        startActivity(new Intent(getApplicationContext(), BookDetail.class));
        overridePendingTransition(0,0);
    }

    public List<Book >getRecomendedBooks(){
        List<Book> recomendedBooks = new ArrayList<>();

        Cursor booksData = dbBook.getBooks();

        while (booksData.moveToNext()) {
            int id = booksData.getInt(0);
            String name = booksData.getString(1);
            String author = booksData.getString(2);
            String description = booksData.getString(3);
            String cover = booksData.getString(4);
            double price = booksData.getDouble(5);
            String tag = booksData.getString(6);

            Book book = new Book(id, name, author, description, cover, price, tag);
            recomendedBooks.add(book);
        }

        recomendedBooks = recomendedBooks.stream()
                .filter(book -> "recomended".equals(book.getTag()))
                .collect(Collectors.toList());

        return recomendedBooks;
    }

}