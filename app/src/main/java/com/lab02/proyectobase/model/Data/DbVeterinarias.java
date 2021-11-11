package com.lab02.proyectobase.model.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.lab02.proyectobase.model.Veterinarias;

import java.util.ArrayList;

public class DbVeterinarias extends DbHelper{
    Context context;

    public DbVeterinarias(@Nullable Context context) {
        super(context);
        this.context = context;
    }
    public long insertarVeterinaria(String nombre, String distrito, String ubicacion, String correo) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("distrito", distrito);
            values.put("ubicacion", ubicacion);
            values.put("correo", correo);
            id = db.insert(TABLE_VETERINARIAS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }
    public Veterinarias verVeterinaria(String correo) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Veterinarias veterinarias = null;
        Cursor cursorVeterinarias;

        cursorVeterinarias = db.rawQuery("SELECT * FROM " + TABLE_VETERINARIAS + " WHERE correo=  ? " + "LIMIT 1", new String[]{correo});

        if (cursorVeterinarias.moveToFirst()) {
            veterinarias = new Veterinarias();
            veterinarias.setId(cursorVeterinarias.getInt(0));
            veterinarias.setNombre(cursorVeterinarias.getString(1));
            veterinarias.setDistrito(cursorVeterinarias.getString(2));
            veterinarias.setUbicacion(cursorVeterinarias.getString(3));
            veterinarias.setCorreo(cursorVeterinarias.getString(4));
        }

        cursorVeterinarias.close();

        return veterinarias;
    }
    public ArrayList<Veterinarias> mostrarVeterinarias() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Veterinarias> listaVeterinarias = new ArrayList<>();
        Veterinarias veterinarias;
        Cursor cursorVeterinarias;

        cursorVeterinarias = db.rawQuery("SELECT * FROM " + TABLE_VETERINARIAS + " ORDER BY nombre ASC", null);

        if (cursorVeterinarias.moveToFirst()) {
            do {
                veterinarias = new Veterinarias();
                veterinarias.setId(cursorVeterinarias.getInt(0));
                veterinarias.setNombre(cursorVeterinarias.getString(1));
                veterinarias.setDistrito(cursorVeterinarias.getString(2));
                veterinarias.setUbicacion(cursorVeterinarias.getString(3));
                veterinarias.setCorreo(cursorVeterinarias.getString(4));
                listaVeterinarias.add(veterinarias);
            } while (cursorVeterinarias.moveToNext());
        }

        cursorVeterinarias.close();

        return listaVeterinarias;
    }
}
