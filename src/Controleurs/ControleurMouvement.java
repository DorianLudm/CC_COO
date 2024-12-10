package Controleurs;

import IHM.Ihm;
import Modele.Map;

public class ControleurMouvement {

    private Ihm ihm;
    private Map map;

    public ControleurMouvement(Ihm ihm, Map map) {
        this.ihm = ihm;
        this.map = map;
    }

    // --- Movement Functions --- //
    public void movement(int indDirection){
        map.movePlayer(indDirection);
        ihm.maj(map.toString());
    }
}
