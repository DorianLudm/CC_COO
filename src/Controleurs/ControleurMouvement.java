package Controleurs;

import IHM.Ihm;
import Modele.Map;

public class ControleurMouvement {

    private Ihm ihm;
    private Map map;

    public ControleurMouvement(Ihm ihm) {
        this.ihm = ihm;
        this.map = new Map();
    }

    // --- Movement Functions --- //
    public void up_movement() {
        map.movePlayer("top");
        ihm.printValidationMouvement("Mouvement vers le haut");
    }

    public void down_movement() {
        map.movePlayer("bottom");
        ihm.printValidationMouvement("Mouvement vers le bas");
    }

    public void left_movement() {
        map.movePlayer("left");
        ihm.printValidationMouvement("Mouvement vers la gauche");
    }

    public void right_movement() {
        map.movePlayer("right");
        ihm.printValidationMouvement("Mouvement vers la droite");
    }

    
}
