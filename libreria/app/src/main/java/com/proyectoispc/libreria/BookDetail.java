package com.proyectoispc.libreria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.proyectoispc.libreria.adapter.ProductAdapter;
import com.proyectoispc.libreria.db.DbBook;
import com.proyectoispc.libreria.models.Book;
import com.proyectoispc.libreria.service.ShoppingCartService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookDetail extends AppCompatActivity {

    DbBook dbBook;

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

        dbBook = new DbBook(this);


        backbutton = findViewById(R.id.backButton);
        shoppingCartButton = findViewById(R.id.shoppingCartButton);
        addBookButton = findViewById(R.id.addBook);

        RecyclerView recyclerView = findViewById(R.id.sugestedBooks);
        List<Book> sugestedBooks = getSugestedBooks();
        ProductAdapter adapter = new ProductAdapter(this ,sugestedBooks);
        recyclerView.setAdapter(adapter);

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

        addBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BookDetail.this, "Agregaste " + bookName + " al carrito", Toast.LENGTH_LONG).show();
                Book book = new Book(id, bookName, author, description, cover, price);
                shoppingCartService.addBook(book);
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

    public List<Book > getSugestedBooks(){
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
                .filter(book -> "sugested".equals(book.getTag()))
                .collect(Collectors.toList());

        return recomendedBooks;
    }

}