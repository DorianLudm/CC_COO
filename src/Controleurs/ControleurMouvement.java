package Controleurs;

import IHM.Ihm;
import Modele.Map;

public class ControleurMouvement {

    private Ihm ihm;
    private Map map;

    public ControleurMouvement(Ihm ihm, Map map,int tailleX,int tailleY, String biome) {
        this.ihm = ihm;
        this.map = map;
        map.Init(tailleX, tailleY, biome);
    }
    public ControleurMouvement(Ihm ihm, Map map,String chemin) {
        this.ihm = ihm;
        this.map = map;
        map.initFromTxt(chemin);
    }

    // --- Movement Functions --- //
    public void movement(int indDirection){
        map.movePlayer(indDirection);
    }
}
