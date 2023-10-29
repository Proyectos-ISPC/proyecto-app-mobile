package com.proyectoispc.libreria.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.Nullable;
import android.database.Cursor;

import com.proyectoispc.libreria.Login;


public class DbUser extends DbHelper {

    Context context;
    SharedPreferences sharedPreferences;

    public DbUser(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public Cursor getUserDataByID(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from t_user where id = ?", new String[] {id} );

        return cursor;
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
            values.put("account_status", true);

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

    public boolean updateUserInfo(String id, String name, String email, Boolean accountStatus){
        boolean updateSuccesfull;
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues userData = new ContentValues();

        userData.put("name", name);
        userData.put("email", email);
        userData.put("account_status", accountStatus);

        int cant = db.update("t_user", userData,"id='"+id+"'",null);
        db.close();

        updateSuccesfull = cant==1;

        return updateSuccesfull;
    }

    public void updateUserPrefDataByID(String id){
        Cursor userData = getUserDataByID(id);
        this.sharedPreferences = context.getSharedPreferences("user_pref", 0);

        if(userData.moveToFirst()){
            SharedPreferences.Editor editor = this.sharedPreferences.edit();
            editor.putString("name", userData.getString (3));
            editor.putString("email", userData.getString (1));
            editor.apply();
        }
    }


}
