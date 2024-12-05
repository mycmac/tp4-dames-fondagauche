/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.centralenantes_fondagauche.damesdamesdeho;

/**
 *
 * @author benja
 */
public class Pion {
    private boolean estDame;
    private boolean blanc;
    private Case c;

    public Pion() {
        this.estDame = false;
        this.blanc = false;
        this.c = new Case(0,0);
    }

    public Pion(boolean estDame, boolean blanc, Case c) {
        this.estDame = estDame;
        this.blanc = blanc;
        this.c = c;
    }

    public boolean isBlanc() {
        return blanc;
    }

    public boolean isEstDame() {
        return estDame;
    }

    public Case getCase() {
        return c;
    }
    
    public void setBlanc(boolean blanc) {
        this.blanc = blanc;
    }

    public void setEstDame(boolean estDame) {
        this.estDame = estDame;
    }

    public void setCase(Case c) {
        this.c = c;
    }
    
    public void avancer(){
        
    }
    
    public void manger(Pion p){
        
    }
    
}
