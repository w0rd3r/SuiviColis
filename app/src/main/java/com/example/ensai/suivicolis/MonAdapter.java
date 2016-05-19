package com.example.ensai.suivicolis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ensai.suivicolis.Core.Colis;

import java.util.List;
import java.util.Objects;

/**
 * Created by ensai on 10/05/16.
 */
public class MonAdapter extends BaseAdapter {

    List<Colis> colis;
    Context context;

    public MonAdapter(Context context, List<Colis> colis) {
        this.colis = colis;
        this.context = context;

    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = ((LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.item_colis, parent, false);
        } else {
            view = convertView;
        }

        final Colis colis = (Colis) getItem(position);
        TextView description = (TextView) view.findViewById(R.id.description);
        description.setText(colis.getDescription());
        TextView transporteur = (TextView) view.findViewById(R.id.transporteur);
        transporteur.setText(colis.getTransporteur().getNom());

        return view;
    }

    public long getItemId(int position) {
        return position;
    }

    public Object getItem(int position) {
        return colis.get(position);
    }

    public int getCount() {
        return colis.size();
    }


}

