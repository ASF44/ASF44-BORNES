package com.airsoft44.bornes.bornesasf44;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LaunchGame extends MainMenu {

    private Button boutonValider,boutonLancer;
    private EditText etNbEquipes,ettpsCaptureTotal,ettpsChgtEquipe;
    private TextView rappelNbEquipes;
    private CheckBox cbBuzzer;

    private int nbEquipes = 0;
    private int tpsTotalCapture = 0;
    private int tpsChgtEquipe = 0;
    private int buzzer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nb_equipes);

        etNbEquipes = findViewById(R.id.et_nombreEquipes);
        boutonValider = findViewById(R.id.btn_validerNbEquipes);
        boutonValider.setOnClickListener(clickBtnValider);
    }

    public void configureEquipes() {
        setContentView(R.layout.config_partie);

        rappelNbEquipes = findViewById(R.id.tv_rappelNbEquipes);
        boutonLancer = findViewById(R.id.btn_lancer);
        ettpsCaptureTotal = findViewById(R.id.et_tpsCaptureTotal);
        ettpsChgtEquipe = findViewById(R.id.et_tpsCaptureChgtEquipe);
        cbBuzzer = findViewById(R.id.cb_buzzer);

        rappelNbEquipes.append(String.valueOf(nbEquipes));

        boutonLancer.setOnClickListener(clickBtnLancer);

    }

    View.OnClickListener clickBtnValider = new View.OnClickListener() {
        public void onClick(View v) {

            String nbEquipesStr = etNbEquipes.getText().toString();

            if (nbEquipesStr.matches("")) { // Si l'user n'a pas entré de nombre
                Toast.makeText(getApplicationContext(),"Merci de saisir un nombre d'équipe", Toast.LENGTH_LONG).show();
                return;
            } else { // Si l'user a entré un nombre
                int nbEquipesInt = Integer.parseInt(nbEquipesStr);

                if(nbEquipesInt > 6 || nbEquipesInt <= 0 ){ // Si le nombre d'équipe entré est supérieur à 6 ou =< 0
                    Toast.makeText(getApplicationContext(),"Merci de saisir un nombre d'équipe compris entre 1 et 6", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    nbEquipes = nbEquipesInt;
                    configureEquipes();
                }
            }
        }
    };

    View.OnClickListener clickBtnLancer = new View.OnClickListener() {
        public void onClick(View v) {

            String tpsTotalCaptureStr = ettpsCaptureTotal.getText().toString();
            String tpsChgtEquipeStr = ettpsChgtEquipe.getText().toString();

            if (tpsTotalCaptureStr.matches("") || tpsChgtEquipeStr.matches("")) { // Si l'user n'a pas entré de nombre
                Toast.makeText(getApplicationContext(),"Merci de remplir les champs des temps", Toast.LENGTH_LONG).show();
                return;
            } else { // Si l'user a entré un nombre
                int tpsTotalCaptureInt = Integer.parseInt(tpsTotalCaptureStr);
                int tpsChgtEquipeInt = Integer.parseInt(tpsChgtEquipeStr);

                if(tpsTotalCaptureInt <= 0 || tpsChgtEquipeInt <= 0 ){ // Si le nombre d'équipe entré est =< 0
                    Toast.makeText(getApplicationContext(),"Merci de saisir des nombres positifs", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    tpsTotalCapture = tpsTotalCaptureInt;
                    tpsChgtEquipe = tpsChgtEquipeInt;

                    if(cbBuzzer.isChecked()) {
                        buzzer = 1;
                    } else {
                        buzzer = 0;
                    }

                    lancerPartie(tpsTotalCapture,tpsChgtEquipe,buzzer);
                }
            }
        }
    };



}
