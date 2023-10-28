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
    private static final String EMAIL = " email TEXT NOT NULL,";
    private static final String PASSWORD = " password TEXT NOT NULL,";
    private static final String NAME = " name TEXT NOT NULL,";
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
        createSaleTable(sqLiteDatabase);
        createDeliveryTable(sqLiteDatabase);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_USER);
        sqLiteDatabase.execSQL("DROP TABLE delivery");
        sqLiteDatabase.execSQL("DROP TABLE sale");
        onCreate(sqLiteDatabase);
        createSaleTable(sqLiteDatabase);
        createDeliveryTable(sqLiteDatabase);
    }


    private void createUserTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_USER + " (" +
                ID + EMAIL + PASSWORD + NAME + LAST_NAME + DNI + ADDRESS + " phone TEXT)");
    }

    private void createSaleTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE sale (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "total_cost DECIMAL(10,2) NOT NULL, " +
                        "total_quantity INTEGER NOT NULL, " +
                        "payment_type VARCHAR(20) NOT NULL, " +
                        "delivery_type VARCHAR(20) NOT NULL, " +
                        "sale_date DATE, user_id INTEGER, " +
                        "user_id INTEGER NOT NULL," +
                        "FOREIGN KEY (user_id) REFERENCES sale(id)" +
                        "ON DELETE CASCADE)");
    }

    private void createDeliveryTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE delivery(" +
                        "id PRIMARY KEY AUTOINCREMENT, " +
                        "street VARCHAR(40) NOT NULL, " +
                        "number INTEGER NOT NULL, " +
                        "postalcode VARCCHAR(10) NOT NULL, " +
                        "status VARCHAR(20) NOT NULL, " +
                        "sale_id INTEGER NOT NULL, " +
                        "fullname VARCHAR(100) NOT NULL, " +
                        "tel VARCHAR(45) NOT NULL, " +
                        "email VARCHAR(60) NOT NULL, " +
                        "FOREIGN KEY (sale_id) REFERENCES sale(id)" +
                        "ON DELETE CASCADE)");
    }
}
