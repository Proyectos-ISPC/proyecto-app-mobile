package com.proyectoispc.libreria.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;
public class DbPayment extends DbHelper {
    Context context;
    public DbPayment(@Nullable Context context){
        super(context);
        this.context = context;
    }

    public Long insertPayment(String name_card, Integer cvv, String card_number, String expiration_date, Integer sale_id){
        long id_payment = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put("name_card", name_card);
            values.put("cvv", cvv);
            values.put("card_number", card_number);
            values.put("expiration_date", expiration_date);
            values.put("sale_id", sale_id);

            id_payment = db.insert(TABLE_PAYMENT, null, values);
        } catch (Exception e) {
            e.toString();
        }
        return id_payment;
    }
}