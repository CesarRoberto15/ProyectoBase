package com.lab02.proyectobase.model.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.lab02.proyectobase.model.Albergues;
import com.lab02.proyectobase.model.Veterinarias;

import java.util.ArrayList;

public class DbAlbergues extends DbHelper{
    Context context;

    public DbAlbergues(@Nullable Context context) {
        super(context);
        this.context = context;
    }
    public long insertarAlbergue(String nombre, String distrito, String latitud, String longitud, String celular, String correo) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("distrito", distrito);
            values.put("latitud", latitud);
            values.put("longitud", longitud);
            values.put("celular", celular);
            values.put("correo", correo);
            id = db.insert(TABLE_ALBERGUES, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }
    public Albergues verAlbergue(String correo) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Albergues albergues = null;
        Cursor cursorAlbergues;

        cursorAlbergues = db.rawQuery("SELECT * FROM " + TABLE_ALBERGUES + " WHERE correo=  ? " + "LIMIT 1", new String[]{correo});

        if (cursorAlbergues.moveToFirst()) {
            albergues = new Albergues();
            albergues.setId(cursorAlbergues.getInt(0));
            albergues.setNombre(cursorAlbergues.getString(1));
            albergues.setDistrito(cursorAlbergues.getString(2));
            albergues.setLatitud(cursorAlbergues.getString(3));
            albergues.setLongitud(cursorAlbergues.getString(4));
            albergues.setCelular(cursorAlbergues.getString(5));
            albergues.setCorreo(cursorAlbergues.getString(6));
        }

        cursorAlbergues.close();

        return albergues;
    }
    public Albergues verAlberguebyName(String nombre) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Albergues albergues = null;
        Cursor cursorAlbergues;

        cursorAlbergues = db.rawQuery("SELECT * FROM " + TABLE_ALBERGUES + " WHERE nombre=  ? " + "LIMIT 1", new String[]{nombre});

        if (cursorAlbergues.moveToFirst()) {
            albergues = new Albergues();
            albergues.setId(cursorAlbergues.getInt(0));
            albergues.setNombre(cursorAlbergues.getString(1));
            albergues.setDistrito(cursorAlbergues.getString(2));
            albergues.setLatitud(cursorAlbergues.getString(3));
            albergues.setLongitud(cursorAlbergues.getString(4));
            albergues.setCelular(cursorAlbergues.getString(5));
            albergues.setCorreo(cursorAlbergues.getString(6));
        }

        cursorAlbergues.close();

        return albergues;
    }
    public Albergues verAlbergueByID(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Albergues albergues = null;
        Cursor cursorAlbergues;

        cursorAlbergues = db.rawQuery("SELECT * FROM " + TABLE_ALBERGUES + " WHERE id=  ? " + "LIMIT 1", new String[]{""+id});

        if (cursorAlbergues.moveToFirst()) {
            albergues = new Albergues();
            albergues.setId(cursorAlbergues.getInt(0));
            albergues.setNombre(cursorAlbergues.getString(1));
            albergues.setDistrito(cursorAlbergues.getString(2));
            albergues.setLatitud(cursorAlbergues.getString(3));
            albergues.setLongitud(cursorAlbergues.getString(4));
            albergues.setCelular(cursorAlbergues.getString(5));
            albergues.setCorreo(cursorAlbergues.getString(6));
        }

        cursorAlbergues.close();

        return albergues;
    }
    public ArrayList<Albergues> mostrarAlbergues() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Albergues> listaAlbergues = new ArrayList<>();
        Albergues albergues;
        Cursor cursorAlbergues;

        cursorAlbergues = db.rawQuery("SELECT * FROM " + TABLE_ALBERGUES + " ORDER BY nombre ASC", null);

        if (cursorAlbergues.moveToFirst()) {
            do {
                albergues = new Albergues();
                albergues.setId(cursorAlbergues.getInt(0));
                albergues.setNombre(cursorAlbergues.getString(1));
                albergues.setDistrito(cursorAlbergues.getString(2));
                albergues.setLatitud(cursorAlbergues.getString(3));
                albergues.setLongitud(cursorAlbergues.getString(4));
                albergues.setCelular(cursorAlbergues.getString(5));
                albergues.setCorreo(cursorAlbergues.getString(6));
                listaAlbergues.add(albergues);
            } while (cursorAlbergues.moveToNext());
        }

        cursorAlbergues.close();

        return listaAlbergues;
    }
}
