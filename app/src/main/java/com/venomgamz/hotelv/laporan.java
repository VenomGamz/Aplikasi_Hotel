package com.venomgamz.hotelv;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class laporan extends AppCompatActivity {

    ListView list;
    final Context p=this;

    DatabaseReference databaseReference;
    List<DKasir> kasirs;
    public static String idkasir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan);

        kasirs=new ArrayList<DKasir>();
        databaseReference= FirebaseDatabase.getInstance().getReference("Transaksi");
        list=(ListView)findViewById(R.id.listkasir);


    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                kasirs.clear();
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    DKasir  kasir=postSnapshot.getValue(DKasir.class);
                    kasirs.add(kasir);
                }
                ListLaporan adapterkasir=new ListLaporan(laporan.this,kasirs,databaseReference);
                list.setAdapter(adapterkasir);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
