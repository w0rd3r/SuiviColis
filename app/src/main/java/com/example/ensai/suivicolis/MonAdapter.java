package com.example.ensai.suivicolis;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.ensai.suivicolis.Core.Colis;

import java.util.List;
import java.util.Objects;

/**
 * Created by ensai on 10/05/16.
 */
public class MonAdapter extends BaseAdapter {

    List<Colis> colis;


public View getView (int position, View convertView, ViewGroup parent) {

}
    public long getItemId (int position){
    return position;
    }
    public Object getItem (int position){
        return colis.get(position);
    }
    public int getCount(){
        return colis.size();
    }


