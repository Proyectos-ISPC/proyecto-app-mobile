package com.proyectoispc.libreria.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class DbContact extends DbHelper{

    Context context;

    public DbContact(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarContact(String name_contact, String email_contact, String message_contact) {

        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("name_contact", name_contact);
            values.put("email_contact",email_contact);
            values.put("message_contact", message_contact);

            id = db.insert(TABLE_CONTACT, null, values);

        } catch (Exception ex) {
            ex.toString();
        }
        return id;
    }

}
