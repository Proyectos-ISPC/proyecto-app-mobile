package com.proyectoispc.libreria.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "library.db";
    public static final String TABLE_USER = "t_user";
    public static final String TABLE_SALE = "t_sale";
    public static final String TABLE_DELIVERY = "t_delivery";


    private static final String ID = " id INTEGER PRIMARY KEY AUTOINCREMENT,";
    private static final String EMAIL = " email TEXT NOT NULL,";
    private static final String PASSWORD = " password TEXT NOT NULL,";
    private static final String NAME = " name TEXT NOT NULL,";
    private static final String LAST_NAME = " last_name TEXT,";
    private static final String DNI = " dni TEXT,";
    private static final String ADDRESS = " address TEXT,";
    private static final String PHONE = " phone TEXT,";

//Atributos tabla SALE
    private static final String ID_SALE = "id_sale INTEGER PRIMARY KEY AUTOINCREMENT,";
    private static final String USER_ID = "user_id INTEGER,";
    private static final String TOTAL_COST = "total_cost DECIMAL(10,2) NULL,";
    private static final String TOTAL_QUANTITY = "total_quantity INTEGER NULL";
    private static final String PAYMENT_TYPE = "payment_type VARCHAR(20) NULL";
    private static final String DELIVERY_TYPE = "delivery_type VARCHAR(20) NULL";
    private static final String SALE_DATE = "sale_date date null";

    //Atributos tabla DELIVERY

    private static final String ID_DELIVERY = "id_delivery INTEGER PRIMARY KEY AUTOINCREMENT,";
    private static final String MAIL = "email VARCHAR(20) NOT NULL,";
    private static final String TELEPHONE = "phone VARCHAR(20) NOT NULL,";
    private static final String STREET = "street VARCHAR(20) NULL,";
    private static final String POSTALCODE = "postalcode VARCHAR(20) NULL,";
    private static final String LOCATION = "location VARCHAR(20) NULL,";
    private static final String PROVINCE = "province VARCHAR(20) NULL,";

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
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_SALE);
        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_DELIVERY);
        onCreate(sqLiteDatabase);
    }


    private void createUserTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_USER + " (" +
                ID + EMAIL + PASSWORD + NAME + LAST_NAME + DNI + ADDRESS + " phone TEXT)");
    }

    private void createSaleTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_SALE + " (" +
                ID_SALE + USER_ID + TOTAL_COST + TOTAL_QUANTITY + PAYMENT_TYPE + DELIVERY_TYPE + SALE_DATE + ", FOREIGN KEY(user_id) REFERENCES " + TABLE_USER + "(id))");
    }

    private void createDeliveryTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_DELIVERY + " (" +
                ID_DELIVERY + MAIL + TELEPHONE + STREET + POSTALCODE + LOCATION + PROVINCE + ", FOREIGN KEY(sale_id) REFERENCES " + TABLE_SALE + "(ID_SALE))");
    }
}
