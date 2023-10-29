package com.proyectoispc.libreria.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbSale extends DbHelper {

    public DbSale(Context context) {
        super(context);
    }

    public Cursor getListSaleID(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT id, total_cost, sale_date, status " +
                "FROM sale " +
                "INNER JOIN delivery " +
                "ON sale.id = delivery.sale_id " +
                "WHERE sale.id_user = '" + userId + "'";

        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }
}
