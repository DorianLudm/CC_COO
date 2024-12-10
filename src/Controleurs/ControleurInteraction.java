package Controleurs;

import IHM.Ihm;
import Modele.Map;

public class ControleurInteraction {

    private Ihm ihm;
    private Map map;

    public ControleurInteraction(Ihm ihm, Map map) {
        this.ihm = ihm;
        this.map = map;
    }

    public void attack(int indDirection){
        map.playerFight(indDirection);
        ihm.maj(map.toString());
    }

    public void pickUp(int indDirection){
        map.playerPickUp(indDirection);
        ihm.maj(map.toString());
    }

}
