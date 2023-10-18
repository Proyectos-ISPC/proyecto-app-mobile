package com.proyectoispc.libreria.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbUser extends DbHelper {

    Context context;

    public DbUser(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public Long insertUser(String name, String email, String password) {
        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put("name", name);
            values.put("email", email);
            values.put("password", password);

            id = db.insert(TABLE_USER, null, values);
        } catch (Exception e) {
            e.toString();
        }

        return id;


    }
}
