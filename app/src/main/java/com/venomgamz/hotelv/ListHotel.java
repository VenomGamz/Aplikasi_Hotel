package com.venomgamz.hotelv;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.util.List;

/**
 * Created by VENOM on 1/3/2018.
 */

public class ListHotel extends ArrayAdapter<DHotel> {
    private Activity context;
    private List<DHotel> hotels;
    DatabaseReference databaseReference;
    TextView nama,nomor;

    public ListHotel(@NonNull Activity context, List<DHotel> hotels,DatabaseReference databaseReference) {
        super(context, R.layout.listkamar,hotels);
        this.context=context;
        this.hotels=hotels;
    }

    public View getView(int pos, View view, ViewGroup parent){
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.listkamar,null,true);

        TextView txtname=(TextView)listViewItem.findViewById(R.id.namakamar);
        TextView txtnomor=(TextView)listViewItem.findViewById(R.id.nokamar);
        TextView txtharga=(TextView)listViewItem.findViewById(R.id.hargakamar);

        final DHotel hotel=hotels.get(pos);
        txtname.setText(hotel.getNama());
        txtnomor.setText(hotel.getNokamar());
        txtharga.setText(hotel.getHarga());
        return listViewItem;
    }

}
