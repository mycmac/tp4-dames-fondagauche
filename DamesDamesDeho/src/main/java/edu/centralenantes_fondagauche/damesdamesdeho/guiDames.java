package edu.centralenantes_fondagauche.damesdamesdeho;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class guiDames {
    private Jeu jeu;
    private JFrame frame;
    private JButton[][] buttons;

    public guiDames(Jeu jeu) {
        this.jeu = jeu;
        this.buttons = new JButton[10][10];
        initialiserInterface();
    }

    private void initialiserInterface() {
        frame = new JFrame("DamesDamesDeho");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(10, 10));

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setPreferredSize(new Dimension(50, 50));
                buttons[i][j].setBackground(java.awt.Color.GRAY);
                frame.add(buttons[i][j]);
            }
        }

        frame.pack();
        frame.setVisible(true);
        mettreAJourGUI();
    }

    public void mettreAJourGUI() {
        Plateau plateau = jeu.getPlateau();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JButton bouton = buttons[i][j];
                Case caseActuelle = new Case(i, j);
                Pion pion = plateau.getPion(caseActuelle);

                // Supprime tous les anciens ActionListeners
                for (var listener : bouton.getActionListeners()) {
                    bouton.removeActionListener(listener);
                }

                if (pion != null) {
                    // Afficher le pion (blanc ou noir)
                    bouton.setBackground(pion.isBlanc() ? java.awt.Color.WHITE : java.awt.Color.BLACK);
                    bouton.addActionListener(e -> gererClicPion(pion));
                } else {
                    // Afficher une case vide
                    bouton.setBackground(java.awt.Color.GRAY);
                }
            }
        }
    }


    private void gererClicPion(Pion p) {

        if (p.isBlanc() != jeu.joueurActifEstBlanc()) {
            System.out.println("Ce n'est pas votre pion !");
            return;
        }

        List<Case> casesDisponibles = jeu.getPlateau().calculerDeplacementsPossibles(p);
        for (Case c : casesDisponibles) {
            JButton boutonCase = buttons[c.getx()][c.gety()];
            boutonCase.setBackground(java.awt.Color.GREEN);
            boutonCase.addActionListener(e -> {
                jeu.getPlateau().deplacerPion(p, c);
                System.out.println("Le joueur "+ (jeu.joueurActifEstBlanc() ? "Blanc" : "Noir") + " se déplace");
                jeu.terminerTour();
                mettreAJourGUI();
            });
        }
    }
    
}
