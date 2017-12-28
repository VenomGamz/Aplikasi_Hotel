package com.venomgamz.hotelv;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by VENOM on 12/28/2017.
 */

public class Dbkonn extends SQLiteOpenHelper {
    static final private String Db_NAME = "Hotel";
    static final private String ID = "_id";
    static final private int Db_VER = 6;
    //Deklarasi Nama tabel
    static final private String TB_Kamar = "Kamar";//tabel nama kamar
    static final private String CREATE_TB_Kamar = "create table " + TB_Kamar + "(_id integer primary key autoincrement,nama_kamar text,harga_kamar integer,no_kamar integer);";
    static final private String TB_Pelanggan = "pelanggan";//tabel pelanggan
    static final private String CREATE_TB_Pelanggan = "create table " + TB_Pelanggan + "(_id integer primary key autoincrement,nama text,alamat text,no_hp text);";//tabel pelanggan
    static final private String TB_Kasir = "Kasir";//tabel Kasir
    static final private String CREATE_TB_Kasir = "create table " + TB_Kasir + "(_id integer primary key autoincrement,nama text,harga integer,jumlah integer,total integer);";//tabel kasir

    Context mycontext;
    SQLiteDatabase myDb;
    public Dbkonn(Context context) {
        super(context, Db_NAME, null, Db_VER);
        mycontext = context;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists "+TB_Kamar);
        db.execSQL("drop table if exists "+TB_Pelanggan);
        db.execSQL("drop table if exists "+TB_Kasir);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TB_Kamar);
        db.execSQL(CREATE_TB_Pelanggan);
        db.execSQL(CREATE_TB_Kasir);
        Log.i("Database","Table Created");
    }
    public void insertDataMenu(String p1, int p2, int p3) {
        myDb = getWritableDatabase();
        myDb.execSQL("insert into " + TB_Kamar + " (nama_kamar,harga_kamar,no_kamar) values('" + p1 + "','" + p2 + "','" + p3 + "');");
        Toast.makeText(mycontext, "Data Saved", Toast.LENGTH_LONG).show();
    }

    public Cursor readAllMenu() {
        myDb = getWritableDatabase();
        String[] columns = new String[]{"_id", "nama_kamar", "harga_kamar","no_kamar"};
        Cursor c = myDb.query(TB_Kamar, columns, null, null, null, null, ID + " asc");
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }
    public Cursor selectedMenu(long id) {
        myDb=getWritableDatabase();
        String[] columns = new String[]{"_id","nama_kamar", "harga_kamar","no_kamar"};
        Cursor c = myDb.query(TB_Kamar, columns, ID + "=" + id, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public void update(long id, String p1,int p2, int p3) {
        myDb=getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nama_kamar", p1);
        values.put("harga_kamar",p2);
        values.put("no_kamar",p3);
        myDb.update(TB_Kamar, values, ID + "=" + id, null);
        close();
    }

    public void deleteMenu(long id) {
        myDb=getWritableDatabase();
        myDb.delete(TB_Kamar, ID + "=" + id, null);
        myDb.close();
    }
    public void insertDataKamar(String p1, int p2, int p3, int p4) {
        myDb = getWritableDatabase();
        myDb.execSQL("insert into " + TB_Kasir + " (nama,harga,jumlah,total) values('" + p1 + "','" + p2 + "','" + p3 + "','" + p4 + "');");
        Toast.makeText(mycontext, "Data Saved", Toast.LENGTH_LONG).show();
    }

    public Cursor readAllKamar() {
        myDb = getWritableDatabase();
        String[] columns = new String[]{"_id", "nama", "harga", "jumlah", "total"};
        Cursor c = myDb.query(TB_Kasir, columns, null, null, null, null, ID + " asc");
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }
}
