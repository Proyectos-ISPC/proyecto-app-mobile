package com.proyectoispc.libreria.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;
public class DbSale extends DbHelper {
    Context context;
    public DbSale(@Nullable Context context){
        super(context);
        this.context = context;
    }

    public Long insertSale(int user_id, double total_cost, int total_quantity, String payment_type, String delivery_type, int bookId , String sale_date){
        long id_sale = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put("user_id", user_id);
            values.put("total_cost", total_cost);
            values.put("total_quantity", total_quantity);
            values.put("payment_type", payment_type);
            values.put("delivery_type", delivery_type);
            values.put("book_id", delivery_type);
            values.put("sale_date", sale_date);

            id_sale = db.insert(TABLE_SALE, null, values);
        } catch (Exception e) {
            e.toString();
        }
        return id_sale;
    }

}
