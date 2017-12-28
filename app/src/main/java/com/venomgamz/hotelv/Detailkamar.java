package com.venomgamz.hotelv;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Detailkamar extends AppCompatActivity {
    Dbkonn dbkonn;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailkamar);
        dbkonn = new Dbkonn(this);
        load();
    }
    public void load(){
        Cursor cursor = null;
        try {
            cursor = dbkonn.readAllKamar();
        } catch (Exception e) {
            Toast.makeText(this,"salah",Toast.LENGTH_LONG).show();
        }
        String[] from = new String[]{"nama", "jumlah", "total"};
        int[] to = new int[]{R.id.nama, R.id.jumlah,R.id.total  };
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(Detailkamar.this, R.layout.kamar, cursor, from, to);
        adapter.notifyDataSetChanged();
        list = (ListView) findViewById(R.id.detailkamar);
        list.setAdapter(adapter);
    }
}
