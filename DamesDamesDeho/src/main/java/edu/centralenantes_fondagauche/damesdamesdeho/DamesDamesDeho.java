package edu.centralenantes_fondagauche.damesdamesdeho;

import javax.swing.SwingUtilities;

public class DamesDamesDeho {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Jeu partie = new Jeu();
            guiDames guiDames = new guiDames(partie);
            partie.commencerTour();
        });
    }
}
