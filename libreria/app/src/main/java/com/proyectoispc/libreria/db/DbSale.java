package com.proyectoispc.libreria.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DbSale extends DbHelper {

    public DbSale(Context context) {
        super(context);
    }

    public Cursor getListSaleID(int userId) {
        Cursor cursor = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String query = "SELECT sale.id, total_cost, sale_date, status " +
                    "FROM sale " +
                    "INNER JOIN delivery " +
                    "ON sale.id = delivery.sale_id " +
                    "WHERE sale.user_id = " + userId ;
            cursor = db.rawQuery(query, null);


        } catch (Exception e) {
            Log.e("TAG", "Error en la consulta SQL: " + e.getMessage());
        }
        return cursor;
    }
}
