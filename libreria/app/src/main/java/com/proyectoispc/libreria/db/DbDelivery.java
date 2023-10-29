package com.proyectoispc.libreria.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;
public class DbDelivery extends DbHelper {
    Context context;
    public DbDelivery(@Nullable Context context){
        super(context);
        this.context = context;
    }

    public Long insertDelivery(String name, String mail, String telephone, String street, String postalcode, String location, String province, Integer sale_id){
        long id_delivery = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put("name", name);
            values.put("mail", mail);
            values.put("telephone", telephone);
            values.put("street", street);
            values.put("postalcode", postalcode);
            values.put("location", location);
            values.put("province", province);
            values.put("sale_id", sale_id);

            id_delivery = db.insert(TABLE_DELIVERY, null, values);
        } catch (Exception e) {
            e.toString();
        }
        return id_delivery;
    }
}
