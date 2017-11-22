package com.airsoft44.bornes.bornesasf44;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Thibault on 22/11/2017.
 */
public class MyArrayAdapter extends ArrayAdapter< ConfigEquipe > {
    public MyArrayAdapter (Context context , List< ConfigEquipe > myList ) {
        super ( context ,
                R.layout.activity_my_array_adapter,
                R.id.lblName ,
                myList );

    }
    @Override
    public View getView (int position , View convertView , ViewGroup parent ) {
        View view = super.getView ( position , convertView , parent );
        TextView txtLetter =  view.findViewById(R.id.lblLetter);
        TextView txtName =  view.findViewById(R.id.lblName);
        TextView txtDesc =  view.findViewById(R.id.lblDesc);
        TextView txtColor =  view.findViewById(R.id.lblColor);

        txtLetter.setText(getItem(position).getFirstLetter());
        txtName.setText(getItem(position).getName());
        txtDesc.setText(getItem(position).getShortProfil());
        txtColor.setBackgroundColor(getItem(position).getCouleur().getColor());


        return view ;
    }
}