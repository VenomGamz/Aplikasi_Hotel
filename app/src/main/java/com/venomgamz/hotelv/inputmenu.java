package com.venomgamz.hotelv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class inputmenu extends AppCompatActivity {

    EditText nama,harga_kamar,no_kamar;
    Button save;
    Dbkonn dbkonn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputmenu);
        nama=(EditText)findViewById(R.id.inputkamar);
        harga_kamar=(EditText)findViewById(R.id.inputharga);
        no_kamar=(EditText)findViewById(R.id.inputnokamar);
        save=(Button)findViewById(R.id.save);
        dbkonn=new Dbkonn(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbkonn.insertDataMenu(nama.getText().toString(),Integer.parseInt(harga_kamar.getText().toString()),Integer.parseInt(no_kamar.getText().toString()));
                Intent intent = new Intent(getApplicationContext(),infokamar.class);
                startActivity(intent);
            }
        });
    }
}