package com.airsoft44.bornes.bornesasf44;

import android.graphics.Color;
import android.widget.BaseAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by thibault on 21/11/2017.
 */

public class ConfigEquipe  {



    private String name;
    private String profil;
    Couleur couleur;

    public ConfigEquipe(Couleur couleur,String profil, String name) {
        this.name = name;
        this.profil = profil;
        this.couleur = couleur;
    }
    private final static String DEFAULT_PROFIL = "Metuentes igitur idem latrones Lycaoniam " +
            "magna parte campestrem cum se inpares nostris fore congressione stataria documentis" +
            " frequentibus scirent, tramitibus deviis petivere Pamphyliam diu quidem intactam" +
            " sed timore populationum et caedium, milite per omnia diffuso propinqua, magnis" +
            " undique praesidiis conmunitam.";


    public String getName() {
        return name;
    }

    public Couleur getCouleur() {
        return couleur;
    }
    public String getFirstLetter() {
        return name.toUpperCase().substring(0,1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConfigEquipe that = (ConfigEquipe) o;

        if (!name.equals(that.name)) return false;
        return couleur.equals(that.couleur);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + couleur.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ConfigEquipe{" +
                "name='" + name + '\'' +
                ", couleur='" + couleur + '\'' +
                '}';
    }
    public class Couleur {

        private int col;
        Couleur(int col) {
            this.col = col;
        }

        int getColor() {
            return col;
        }
    }
    public String getShortProfil() {
        int limit = 50;
        if (profil.length() < limit) {
            limit = profil.length();
        }
        return profil.substring(0,limit);
    }


    public static ArrayList<ConfigEquipe> initTeam() {
        ArrayList<ConfigEquipe> list = new ArrayList<ConfigEquipe>();

        String[] names = new String[]{
                "Equipe 1", "Equipe 2", "Equipe 3", "Equipe 4", "Equipe 5", "Equipe 6"
        };

        return list;
    }

}
