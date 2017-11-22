package com.airsoft44.bornes.bornesasf44;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.airsoft44.bornes.bornesasf44.R;
import com.airsoft44.bornes.bornesasf44.Team;

public class AddTeam extends Activity {

    RadioGroup rd;
    EditText txtEdit;
    Button btnOk;
    RadioButton rdC1, rdC2,rdC3,rdC4,rdC5,rdC6;
    String couleur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        loadElements();
        loadEvents();
    }

    public void loadElements(){


        txtEdit = (EditText) findViewById(R.id.editText);
        btnOk = (Button) findViewById(R.id.btn_validerNbEquipes);

        rdC1 = (RadioButton) findViewById(R.id.radioButton);
        rdC2 = (RadioButton) findViewById(R.id.radioButton2);
        rdC3 = (RadioButton) findViewById(R.id.radioButton3);
        rdC4 = (RadioButton) findViewById(R.id.radioButton4);
        rdC5 = (RadioButton) findViewById(R.id.radioButton5);
        rdC6 = (RadioButton) findViewById(R.id.radioButton6);


    }

    public void loadEvents(){

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = "";
                name = txtEdit.getText().toString();

                if(name.equals("")){
                    Toast.makeText(getApplicationContext(),"Entrer un nom svp", Toast.LENGTH_SHORT).show();
                }else{
                    Team t = null;

                    Intent i = new Intent();
                    i.putExtra("name",name);
                    i.putExtra("couleur",couleur);
                    setResult(Activity.RESULT_OK,i);
                    finish();

                }


            }
        });

        rdC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                couleur = "";
            }
        });

        rdC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                couleur = "";
            }
        });

        rdC3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                couleur = "";
            }
        });
        rdC4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                couleur = "";
            }
        });
        rdC5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                couleur = "";
            }
        });
        rdC6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                couleur = "";
            }
        });
    }

}
