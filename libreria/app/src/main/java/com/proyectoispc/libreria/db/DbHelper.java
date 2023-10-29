package com.proyectoispc.libreria.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "library.db";
    public static final String TABLE_USER = "t_user";
    public static final String TABLE_BOOK = "t_book";


    private static final String ID = " id INTEGER PRIMARY KEY AUTOINCREMENT,";
    private static final String EMAIL = " email TEXT NOT NULL,";
    private static final String PASSWORD = " password TEXT NOT NULL,";
    private static final String NAME = " name TEXT NOT NULL,";
    private static final String LAST_NAME = " last_name TEXT,";
    private static final String DNI = " dni TEXT,";
    private static final String ADDRESS = " address TEXT,";
    private static final String PHONE = " phone TEXT,";
    Context context_;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context_ = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createUserTable(sqLiteDatabase);
        createBookTable(sqLiteDatabase);

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

    private void createBookTable(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_BOOK + " (" +
                ID + " name TEXT,"+ " author TEXT," + " description TEXT," +
                " cover TEXT," + " price DOUBLE," + " tag TEXT)");

        fillBookTable(sqLiteDatabase);
    }

     private void fillBookTable(SQLiteDatabase db) {
            List<ContentValues> values = getContentValues(db);
         for (ContentValues element : values) {
             db.insert(TABLE_BOOK, null, element);
         }
    }

     private List<ContentValues> getContentValues(SQLiteDatabase db){
        long id = 0;
        List<ContentValues> values = new ArrayList<ContentValues>();

        ContentValues book = new ContentValues();
        book.put("name", "Todo por volverte a ver");
        book.put("author", "Florencia Vercellone");
        book.put("description", "Ana vuelve a la Argentina después de 31 años. Ese regreso despierta memorias guardadas bajo llave, recuerdos de una militancia y un amor");
        book.put("cover", "todo_por_volver");
        book.put("price", 13000);
        book.put("tag", "recomended");
        values.add(book);

        book = new ContentValues();
        book.put("name", "Sanar la herida");
        book.put("author", "Claudia Luchetti");
        book.put("description", "A través de técnicas y procedimientos específicos, Claudia Luchetti -decodificadora- nos invita a desarmar los códigos alfabéticos y numéricos de nuestros nombres y la fecha de nacimiento ");
        book.put("cover", "todo_por_volver");
        book.put("price", 10000);
        book.put("tag", "recomended");
        values.add(book);

        book = new ContentValues();
        book.put("name", "Buscando a Dorothy");
        book.put("author", "Elizabeth Letts");
        book.put("description", "Nuestra Dorothy no es de carne y hueso. Está hecha de papel y lápiz, de palabras y frases. Pero tiene una cualidad que ningún niño de carne y hueso puede tener: jamás crecerá. Jamás envejecerá. Siempre estará con nosotros.");
        book.put("cover", "todo_por_volver");
        book.put("price", 13000);
        book.put("tag", "recomended");
        values.add(book);

        book = new ContentValues();
        book.put("name", "Sigue mi voz");
        book.put("author", "Ariana Godoy");
        book.put("description", "Todos conocemos el amor pasional que te nubla la razón, el amor a  primera vista que te vacía el estómago y el amor platónico que te llena el corazón de fantasía y admiración.");
        book.put("cover", "todo_por_volver");
        book.put("price", 9000);
        book.put("tag", "sugested");
        values.add(book);

        book = new ContentValues();
        book.put("name", "La teoria de lo perfecto");
        book.put("author", "Sophie Gonzalez");
        book.put("description", "Cuando Brougham la atrapa recolectando las cartas del mítico casillero 89, en el que Darcy opera su negocio secreto de consejos románticos, surge el chantaje: o ella lo ayuda a recuperar a su exnovia o la delatará.");
        book.put("cover", "todo_por_volver");
        book.put("price", 11000);
        book.put("tag", "sugested");
        values.add(book);

        book = new ContentValues();
        book.put("name", "Heartstopper");
        book.put("author", "Alice Oseman");
        book.put("description", "Charlie y Nick van al mismo colegio; aunque nunca se habían cruzado hasta el día en que los hacen sentarse juntos en su grupo de estudio. Muy pronto se vuelven amigos y más pronto aún Charlie");
        book.put("cover", "todo_por_volver");
        book.put("price", 9000);
        book.put("tag", "sugested");
        values.add(book);

        return values;
    }
}
