package com.proyectoispc.libreria.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "library.db";

    // El nombre para la tabla de user
    public static final String TABLE_USER = "t_user";

    // El nombre para la tabla de contacto
    public static final String TABLE_CONTACT = "t_contact";


    // Atributos para la tabla de user
    private static final String ID = " id INTEGER PRIMARY KEY AUTOINCREMENT,";
    private static final String EMAIL = " email TEXT NOT NULL,";
    private static final String PASSWORD = " password TEXT NOT NULL,";
    private static final String NAME = " name TEXT NOT NULL,";
    private static final String LAST_NAME = " last_name TEXT,";
    private static final String DNI = " dni TEXT,";
    private static final String ADDRESS = " address TEXT,";
    private static final String PHONE = " phone TEXT,";

    // Atributos para la tabla de contacto

    private static final String ID_CONTACT = " id_contact INTEGER PRIMARY KEY AUTOINCREMENT,";
    private static final String MESSAGE_CONTACT = " message_contact TEXT NOT NULL,";




    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createUserTable(sqLiteDatabase);
        createContactTable(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_USER);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_CONTACT);
        onCreate(sqLiteDatabase);
    }

    private void createUserTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_USER + " (" +
                ID + EMAIL + PASSWORD + NAME + LAST_NAME + DNI + ADDRESS + " phone TEXT)");
    }
    private void createContactTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_CONTACT + " (" +
                ID_CONTACT + " INTEGER PRIMARY KEY, " +
                MESSAGE_CONTACT + " TEXT" +
                ")");
    }



}