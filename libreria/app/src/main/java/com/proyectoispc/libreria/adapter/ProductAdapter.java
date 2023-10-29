package com.proyectoispc.libreria.adapter;


import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proyectoispc.libreria.BookDetail;
import com.proyectoispc.libreria.R;
import com.proyectoispc.libreria.models.Book;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Book> products;
    private Activity activity;

    public ProductAdapter(Activity activity, List<Book> products) {
        this.activity = activity;
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Book product = products.get(position);

        String img = product.getCover();
        int resourceId = holder.itemView.getResources().getIdentifier(img, "drawable", holder.itemView.getContext().getPackageName());

        holder.imageViewProduct.setImageResource(resourceId);

        // Asigna los datos a la vista (por ejemplo, a TextViews)
        holder.nameTextView.setText(product.getName());
        holder.authorTextView.setText(product.getAuthor());
        holder.priceTextView.setText("$" + String.valueOf(product.getPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book selectedBook = products.get(holder.getAdapterPosition());

                Intent intent = new Intent(activity, BookDetail.class);
                intent.putExtra("id", selectedBook.getId());
                intent.putExtra("name", selectedBook.getName());
                intent.putExtra("author", selectedBook.getAuthor());
                intent.putExtra("description", selectedBook.getDescription());
                intent.putExtra("cover", resourceId);
                intent.putExtra("price", selectedBook.getPrice());
                activity.startActivity(intent);;
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView authorTextView;
        public TextView priceTextView;
        public ImageView imageViewProduct;

        public ViewHolder(View view) {
            super(view);
            nameTextView = view.findViewById(R.id.textViewBookName);
            authorTextView = view.findViewById(R.id.textViewBookAuthor);
            priceTextView = view.findViewById(R.id.textViewBookPrice);
            imageViewProduct = view.findViewById(R.id.imageViewProduct);
        }
    }
}