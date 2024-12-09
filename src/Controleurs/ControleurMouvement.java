package Controleurs;

import IHM.Ihm;
import Modele.Map;

public class ControleurMouvement {

    private Ihm ihm;
    private Map map;

    public ControleurMouvement(Ihm ihm,String biome) {
        this.ihm = ihm;
        this.map = new Map(30,30,biome);
    }

    public Map getMap() { return map; }

    // --- Movement Functions --- //
    public void movement(String direction){
        map.movePlayer(direction);
    }
}
