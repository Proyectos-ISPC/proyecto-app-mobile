package com.proyectoispc.libreria.adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.proyectoispc.libreria.R;
import com.proyectoispc.libreria.models.Book;
import com.proyectoispc.libreria.models.SelectedBook;

import java.util.List;

public class ProductCardAdapter extends RecyclerView.Adapter<ProductCardAdapter.ViewHolder> {
    private List<SelectedBook> products;
    private Activity activity;

    public ProductCardAdapter(Activity activity, List<SelectedBook> products) {
        this.activity = activity;
        this.products = products;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SelectedBook product = products.get(position);


        int img = product.getBook().getCoverID();
        String name = product.getBook().getName();
        String author = product.getBook().getAuthor();
        int cuantity = product.getCuantity();
        double price = product.getBook().getPrice();

        holder.cardImageProduct.setImageResource(img);

        holder.cardBookName.setText(name);
        holder.cardBookAuthor.setText(author);
        holder.cardTotalCuantity.setText("" + cuantity);
        holder.cardBookPrice.setText("$ " + price);

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView cardBookName;
        public TextView cardBookAuthor;
        public TextView cardBookPrice;
        public TextView cardTotalCuantity;
        public ImageView cardImageProduct;

        public ViewHolder(View view) {
            super(view);
            cardBookName = view.findViewById(R.id.cardBookName);
            cardBookAuthor = view.findViewById(R.id.cardBookAuthor);
            cardBookPrice = view.findViewById(R.id.cardBookPrice);
            cardImageProduct = view.findViewById(R.id.cardImageProduct);
            cardTotalCuantity = view.findViewById(R.id.cardTotalCuantity);
        }
    }
}