package com.venomgamz.hotelv;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class infokamar extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ListView list;
    final Context p=this;

    DatabaseReference databaseReference;
    List<DHotel> hotels;
    public static String idhotel;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infokamar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        hotels=new ArrayList<DHotel>();
        databaseReference= FirebaseDatabase.getInstance().getReference("Hotel");
        list=(ListView)findViewById(R.id.listkamar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),inputmenu.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                LayoutInflater layoutInflater=LayoutInflater.from(p);
                View mView =layoutInflater.inflate(R.layout.activity_inputmenu,null);
                AlertDialog.Builder alertDialogBuilderUserInput=new AlertDialog.Builder(p);
                alertDialogBuilderUserInput.setView(mView);

                final DHotel hotel=hotels.get(i);
                idhotel=hotel.getId();
                final EditText nama=(EditText)mView.findViewById(R.id.inputkamar);
                final EditText harga_kamar=(EditText)mView.findViewById(R.id.inputharga);
                final EditText no_kamar=(EditText)mView.findViewById(R.id.inputnokamar);

                nama.setText(hotel.getNama());
                harga_kamar.setText(hotel.getHarga());
                no_kamar.setText(hotel.getNokamar());

                final Button update=(Button)mView.findViewById(R.id.save);
                update.setText("Delete");
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            databaseReference.child(idhotel).removeValue();
                            Intent intent=new Intent(getApplicationContext(),infokamar.class);
                            startActivity(intent);
                    }
                });

                alertDialogBuilderUserInput.setCancelable(false).setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String tnam=nama.getText().toString().trim();
                        String tharga=harga_kamar.getText().toString().trim();
                        String tno=no_kamar.getText().toString().trim();

                        if(!TextUtils.isEmpty(tnam) && !TextUtils.isEmpty(tharga) && !TextUtils.isEmpty(tno)){
                            databaseReference.child(idhotel).child("nama").setValue(tnam);
                            databaseReference.child(idhotel).child("harga").setValue(tharga);
                            databaseReference.child(idhotel).child("nokamar").setValue(tno);

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
                alertDialog.setTitle("Detil");
                alertDialog.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
                ListHotel adapteranggota=new ListHotel(infokamar.this,hotels,databaseReference);
                list.setAdapter(adapteranggota);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_input) {
            Intent intent = new Intent(getApplicationContext(),infokamar.class);
            startActivity(intent);
        } else if (id == R.id.nav_transaksi) {
            Intent intent = new Intent(getApplicationContext(),transaksi.class);
            startActivity(intent);
        } else if (id == R.id.nav_pesan) {
            Intent intent = new Intent(getApplicationContext(),laporan.class);
            startActivity(intent);
        }else if (id == R.id.nav_home) {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

