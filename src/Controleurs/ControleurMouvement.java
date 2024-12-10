package Controleurs;

import IHM.Ihm;
import Modele.Map;

public class ControleurMouvement {

    private Ihm ihm;
    private Map map;

    public ControleurMouvement(Ihm ihm, Map map, String biome) {
        this.ihm = ihm;
        this.map = map;
        map.Init(30, 30, biome);
    }

    // --- Movement Functions --- //
    public void movement(String direction){
        map.movePlayer(direction);
    }
}
