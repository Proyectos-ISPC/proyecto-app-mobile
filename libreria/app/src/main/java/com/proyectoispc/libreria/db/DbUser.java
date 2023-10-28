package com.proyectoispc.libreria.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.Nullable;
import android.database.Cursor;


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

    public boolean checkEmail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from t_user where email = ?", new String[] {email} );

        if(cursor.getCount() > 0){
            return true;
        } else return false;
    }

    public boolean checkEmailPassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from t_user where email = ? and password = ?", new String[] {email, password} );

        if(cursor.getCount() > 0){
            return true;
        } else {
            return false;}
    }

}
