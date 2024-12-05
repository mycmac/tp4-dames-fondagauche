package edu.centralenantes_fondagauche.damesdamesdeho;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Plateau {
    private Map<Case, Pion> cases;

    public Plateau() {
        cases = new HashMap<>();
        initialiserPions();
    }

    private void initialiserPions() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                if ((i + j) % 2 != 0) {
                    cases.put(new Case(i, j), new Pion(false, true, new Case(i, j)));
                }
            }
        }
        for (int i = 6; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ((i + j) % 2 != 0) {
                    cases.put(new Case(i, j), new Pion(false, false, new Case(i, j)));
                }
            }
        }
    }

    public Pion getPion(Case c) {
        return cases.get(c);
    }

    public void deplacerPion(Pion p, Case nouvelleCase) {
    // Vérifier si c'est une capture
    if (Math.abs(p.getCase().getx() - nouvelleCase.getx()) > 1) {
        // Calculer la direction du déplacement
        int directionX = (nouvelleCase.getx() - p.getCase().getx()) / Math.abs(nouvelleCase.getx() - p.getCase().getx());
        int directionY = (nouvelleCase.gety() - p.getCase().gety()) / Math.abs(nouvelleCase.gety() - p.getCase().gety());

        // Parcourir les cases intermédiaires pour détecter les pions capturés
        int x = p.getCase().getx() + directionX;
        int y = p.getCase().gety() + directionY;
        while (x != nouvelleCase.getx() && y != nouvelleCase.gety()) {
            Case interCase = new Case(x, y);
            if (cases.containsKey(interCase)) {
                // Supprimer le pion capturé
                cases.remove(interCase);
            }
            x += directionX;
            y += directionY;
        }
    }

    // Déplacer le pion vers la nouvelle case
    cases.remove(p.getCase());
    p.setCase(nouvelleCase);
    cases.put(nouvelleCase, p);
}


    public List<Case> calculerDeplacementsPossibles(Pion p) {
        List<Case> casesDisponibles = new ArrayList<>();
        int direction = p.isBlanc() ? 1 : -1;
        Case position = p.getCase();

        // Mouvements simples
        ajouterSiValide(casesDisponibles, position.getx() + direction, position.gety() - 1);
        ajouterSiValide(casesDisponibles, position.getx() + direction, position.gety() + 1);

        // Captures
        ajouterCapturesPossibles(casesDisponibles, p, direction);

        return casesDisponibles;
    }

    private void ajouterSiValide(List<Case> casesDisponibles, int x, int y) {
        if (x >= 0 && x < 10 && y >= 0 && y < 10) {
            Case c = new Case(x, y);
            if (!cases.containsKey(c)) {
                casesDisponibles.add(c);
            }
        }
    }

    private void ajouterCapturesPossibles(List<Case> casesDisponibles, Pion p, int directionX) {
        // Capture en bas à droite
        int x1 = p.getCase().getx() + directionX;
        int y1 = p.getCase().gety() + 1;  // Direction vers le bas pour la capture
        Case interCase = new Case(x1, y1);
        int x2 = p.getCase().getx() + 2 * directionX;
        int y2 = p.getCase().gety() + 2;  // Déplacement vers le bas
        Case destCase = new Case(x2, y2);

        testCaptures(casesDisponibles, p, x2, y2, interCase, destCase);

        // Capture en haut à droite
        x1 = p.getCase().getx() + directionX;
        y1 = p.getCase().gety() - 1;  // Direction vers le haut pour la capture
        interCase = new Case(x1, y1);
        x2 = p.getCase().getx() + 2 * directionX;
        y2 = p.getCase().gety() - 2;  // Déplacement vers le haut
        destCase = new Case(x2, y2);

        testCaptures(casesDisponibles, p, x2, y2, interCase, destCase);
    }


    private void testCaptures(List<Case> casesDisponibles, Pion p, int x2, int y2, Case interCase, Case destCase){
        // Vérification des conditions pour une capture
        if (x2 >= 0 && x2 < 10 && y2 >= 0 && y2 < 10 &&
            cases.containsKey(interCase) && // Case intermédiaire contient un pion
            cases.get(interCase).isBlanc() != p.isBlanc() && // Pion de l'adversaire
            !cases.containsKey(destCase)) { // Case destination vide

            // Ajouter la case destination aux déplacements possibles
            casesDisponibles.add(destCase);

            // Simuler la capture pour explorer les captures successives dans la même direction
            Pion pionCapture = cases.get(interCase); // Sauvegarde du pion capturé
            cases.remove(interCase); // Retirer temporairement le pion capturé
            Case ancienneCase = p.getCase(); // Sauvegarder la position actuelle

            // Déplacement temporaire du pion
            p.setCase(destCase);
            ajouterCapturesPossibles(casesDisponibles, p, x2); // Récursion dans la même direction

            // Restaurer l'état initial
            p.setCase(ancienneCase);
            cases.put(interCase, pionCapture);
        }
    }
}
