package com.venomgamz.hotelv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class inputmenu extends AppCompatActivity {

    EditText nama,harga_kamar,no_kamar;
    Button save;
    Dbkonn dbkonn;
    DatabaseReference databaseReference;
    List<DHotel> hotels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputmenu);

        nama=(EditText)findViewById(R.id.inputkamar);
        harga_kamar=(EditText)findViewById(R.id.inputharga);
        no_kamar=(EditText)findViewById(R.id.inputnokamar);
        save=(Button)findViewById(R.id.save);

        databaseReference= FirebaseDatabase.getInstance().getReference("Hotel");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tnam=nama.getText().toString().trim();
                String tharga=harga_kamar.getText().toString().trim();
                String tno=no_kamar.getText().toString().trim();

                if(!TextUtils.isEmpty(tnam) && !TextUtils.isEmpty(tharga) && !TextUtils.isEmpty(tno)){
                    String id=databaseReference.push().getKey();
                    DHotel dHotel=new DHotel(id,tnam,tharga,tno);
                    databaseReference.child(id).setValue(dHotel);

                    Intent intent=new Intent(getApplicationContext(),infokamar.class);
                    startActivity(intent);
                }
   /*            dbkonn.insertDataMenu(nama.getText().toString(),Integer.parseInt(harga_kamar.getText().toString()),Integer.parseInt(no_kamar.getText().toString()));
                Intent intent = new Intent(getApplicationContext(),infokamar.class);
                startActivity(intent);*/
            }
        });
    }
}