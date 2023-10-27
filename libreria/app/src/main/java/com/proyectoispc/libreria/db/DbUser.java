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

    public boolean checkName(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from t_user where name = ?", new String[] {name} );

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

    public Cursor getUserData(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from t_user where email = ? and password = ?", new String[] {email, password} );

        return cursor;
    }

    public Long updateName(String idUser, String name) {
        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put("name", name);
            id = db.update("name",values, "id = ?", new String[] { idUser });
        } catch (Exception e) {
            e.toString();
        }

        return id;
    }

    public Long updateEmail(String idUser, String email) {
        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put("email", email);
            id = db.update("email",values, "id = ?", new String[] { idUser });
        } catch (Exception e) {
            e.toString();
        }

        return id;
    }
}
