package edu.centralenantes_fondagauche.damesdamesdeho;

import java.util.List;

public class Jeu {
    private Plateau plateau;
    private int nbTour;

    public Jeu() {
        this.plateau = new Plateau();
        this.nbTour = 0;
    }

    public void commencerTour() {
        boolean joueurBlanc = nbTour % 2 == 0;
        System.out.println("Tour du joueur " + (joueurBlanc ? "Blanc" : "Noir"));
    }

    public void terminerTour() {
        nbTour++;
        System.out.println("Tour terminé. Prochain joueur : " + (nbTour % 2 == 0 ? "Blanc" : "Noir"));
    }

    public int getNbTour() {
        return nbTour;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public boolean joueurActifEstBlanc() {
        return nbTour % 2 == 0;
    }

}
