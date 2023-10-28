package com.proyectoispc.libreria.db;

import android.content.Context;

import androidx.annotation.Nullable;

public class DbDelivery extends DbHelper {
    Context context;
    public DbDelivery(@Nullable Context context) {
        super(context);
        this.context = context;
    }
}
