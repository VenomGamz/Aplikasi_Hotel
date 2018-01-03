package com.venomgamz.hotelv;

import android.app.Activity;
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

public class ListLaporan extends ArrayAdapter<DKasir> {
    private Activity context;
    private List<DKasir> kasirs;
    DatabaseReference databaseReference;
    TextView nama,nomor,lama;

    public ListLaporan(@NonNull Activity context, List<DKasir> kasirs, DatabaseReference databaseReference) {
        super(context, R.layout.listkasir,kasirs);
        this.context=context;
        this.kasirs=kasirs;
    }

    public View getView(int pos, View view, ViewGroup parent){
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.listkasir,null,true);

        TextView txtname=(TextView)listViewItem.findViewById(R.id.txtkasir1);
        TextView txtnomor=(TextView)listViewItem.findViewById(R.id.txtkasir2);
        TextView txtlama=(TextView)listViewItem.findViewById(R.id.txtkasir3);

        final DKasir kasir=kasirs.get(pos);
        txtname.setText(kasir.getNama());
        txtnomor.setText(kasir.getNokamar());
        txtlama.setText(kasir.getLama());
        return listViewItem;
    }
}
