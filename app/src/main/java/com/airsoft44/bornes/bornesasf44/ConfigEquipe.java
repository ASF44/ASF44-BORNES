package com.airsoft44.bornes.bornesasf44;

import android.widget.BaseAdapter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by thibault on 21/11/2017.
 */

public class ConfigEquipe  {


    private int nbEquipe;
    private int id;
    private int tempsCaptureGagner;
    private int tempsCaptureChangeEquipe;
    private boolean buzzer;


    public ConfigEquipe(int id, int nbEquipe, int tempsCaptureGagner, int tempsCaptureChangeEquipe, boolean buzzer) {
        this.id = id;
        this.nbEquipe = nbEquipe;
        this.tempsCaptureGagner = tempsCaptureGagner;
        this.tempsCaptureChangeEquipe = tempsCaptureChangeEquipe;
        this.buzzer = buzzer;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbEquipe() {
        return nbEquipe;
    }

    public void setNbEquipe(int nbEquipe) {
        this.nbEquipe = nbEquipe;
    }

    public int getTempsCaptureGagner() {
        return tempsCaptureGagner;
    }

    public void setTempsCaptureGagner(int tempsCaptureGagner) {
        this.tempsCaptureGagner = tempsCaptureGagner;
    }

    public int getTempsCaptureChangeEquipe() {
        return tempsCaptureChangeEquipe;
    }

    public void setTempsCaptureChangeEquipe(int tempsCaptureChangeEquipe) {
        this.tempsCaptureChangeEquipe = tempsCaptureChangeEquipe;
    }

    public boolean isBuzzer() {
        return buzzer;
    }

    public void setBuzzer(boolean buzzer) {
        this.buzzer = buzzer;
    }

    @Override
    public String toString() {
        return "ConfigEquipe{" +
                "nbEquipe=" + nbEquipe +
                ", id=" + id +
                ", tempsCaptureGagner=" + tempsCaptureGagner +
                ", tempsCaptureChangeEquipe=" + tempsCaptureChangeEquipe +
                ", buzzer=" + buzzer +
                '}';
    }
}
