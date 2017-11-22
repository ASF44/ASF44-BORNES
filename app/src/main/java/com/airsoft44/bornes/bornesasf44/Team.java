package com.airsoft44.bornes.bornesasf44;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;



public class Team extends AppCompatActivity {

    ListView myListView;
    TextView tv;
    Button button_plus, btn_moins , button_lancer;
    ConfigEquipe c;
    ConfigEquipe selectedTeam;
    ArrayAdapter<ConfigEquipe>  myAdapter;
    ArrayList<ConfigEquipe> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_list);

        loadElements();
        loadList();
        loadEvents();


    }
    private void loadElements(){

        myListView =  findViewById(R.id.listView);
        tv =  findViewById(R.id.textView_selectP);
        button_plus =  findViewById(R.id.button_plus);
        btn_moins =  findViewById(R.id.button_moins);
        button_lancer =  findViewById(R.id.lancer);

    }
    private void loadList(){

        list = c.initTeam();
        myAdapter = new MyArrayAdapter ( Team.this , list );
        myListView.setAdapter(myAdapter);
    }
    private void loadEvents(){



        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ConfigEquipe selected = ((ConfigEquipe)adapterView.getItemAtPosition(i));
                select(selected);

            }
        });
        btn_moins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myAdapter.remove(selectedTeam);
                tv.setText("");
                selectedTeam=null;
                btn_moins.setEnabled(false);

            }
        });
        button_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Team.this,AddTeam.class);
                startActivityForResult(i,0);
            }
        });
        button_lancer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Team.this,LaunchGame.class);
                startActivityForResult(i,0);

            }
        });

    }
    private void select(ConfigEquipe selected){

        tv.setText(selected.getName());
        selectedTeam = selected;
        btn_moins.setEnabled(true);

    }
}
