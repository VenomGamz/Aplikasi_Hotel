package com.venomgamz.hotelv;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class transaksi extends AppCompatActivity {
    ListView list;
    final Context p=this;

    DatabaseReference databaseReference,dRef;
    List<DHotel> hotels;
    public static String idhotel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        hotels=new ArrayList<DHotel>();
        databaseReference= FirebaseDatabase.getInstance().getReference("Hotel");
        dRef= FirebaseDatabase.getInstance().getReference("Transaksi");
        list=(ListView)findViewById(R.id.listmenu);


      list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              LayoutInflater layoutInflater=LayoutInflater.from(p);
              View mView =layoutInflater.inflate(R.layout.pesanan,null);
              AlertDialog.Builder alertDialogBuilderUserInput=new AlertDialog.Builder(p);
              alertDialogBuilderUserInput.setView(mView);

              final DHotel hotel=hotels.get(i);
              final String no=hotel.getNokamar();
              final String harga=hotel.getHarga();
              final EditText editnama=(EditText)mView.findViewById(R.id.edttr1);
              final EditText editnoktp=(EditText)mView.findViewById(R.id.edttr2);
              final EditText editlama=(EditText)mView.findViewById(R.id.edttr3);
              alertDialogBuilderUserInput.setCancelable(false).setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      String nama=editnama.getText().toString().trim();
                      String ktp=editnoktp.getText().toString().trim();
                      String lama=editlama.getText().toString().trim();
                      int hasil=Integer.parseInt(harga)*Integer.parseInt(lama);
                      String total=Integer.toString(hasil);

                      if(!TextUtils.isEmpty(nama) && !TextUtils.isEmpty(ktp) && !TextUtils.isEmpty(lama)){
                          String id=dRef.push().getKey();
                          DKasir kasir=new DKasir(id,no,harga,lama,total,nama,ktp);
                          dRef.child(id).setValue(kasir);
                          Intent intent=new Intent(getApplicationContext(),infokamar.class);
                          startActivity(intent);
                      }

                  }
              })
                      .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialog, int which) {
                              dialog.cancel();
                          }
                      });
              AlertDialog alertDialog=alertDialogBuilderUserInput.create();
              alertDialog.setTitle("Input Data Anggota");
              alertDialog.show();
          }
      });
    }
    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                hotels.clear();
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    DHotel  anggota=postSnapshot.getValue(DHotel.class);
                    hotels.add(anggota);
                }
                ListHotel adapteranggota=new ListHotel(transaksi.this,hotels,databaseReference);
                list.setAdapter(adapteranggota);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}