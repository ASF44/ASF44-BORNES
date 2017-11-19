package com.airsoft44.bornes.bornesasf44;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LaunchGame extends MainMenu {

    private Button boutonValider;
    private EditText etNbEquipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nb_equipes);

        etNbEquipes = findViewById(R.id.et_nombreEquipes);
        boutonValider = findViewById(R.id.btn_validerNbEquipes);
        boutonValider.setOnClickListener(clickBtnValider);
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
                    configureEquipes(nbEquipesInt);
                }
            }
        }
    };

    public void configureEquipes(int nbEquipes) {

    }
}
