package com.proyectoispc.libreria.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "library.db";
    public static final String TABLE_USER = "t_user";


    private static final String ID = " id INTEGER PRIMARY KEY AUTOINCREMENT,";
    private static final String EMAIL = " email TEXT,";
    private static final String PASSWORD = " password TEXT,";
    private static final String NAME = " name TEXT,";
    private static final String LAST_NAME = " last_name TEXT,";
    private static final String DNI = " dni TEXT,";
    private static final String ADDRESS = " address TEXT,";
    private static final String PHONE = " phone TEXT,";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createUserTable(sqLiteDatabase);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_USER);
        onCreate(sqLiteDatabase);
    }


    private void createUserTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_USER + " (" +
                ID + EMAIL + PASSWORD + NAME + LAST_NAME + DNI + ADDRESS + " phone TEXT)");
    }
}
