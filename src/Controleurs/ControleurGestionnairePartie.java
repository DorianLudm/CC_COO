package Controleurs;

import IHM.Ihm;
import Modele.Map;

public class ControleurGestionnairePartie {

    private Ihm ihm;
    private Map map;

    public ControleurGestionnairePartie(Ihm ihm, Map map,int tailleX,int tailleY, String biome) {
        this.ihm = ihm;
        this.map = map;
        map.Init(tailleX, tailleY, biome);
    }
    
    public ControleurGestionnairePartie(Ihm ihm, Map map,String chemin) {
        this.ihm = ihm;
        this.map = map;
        map.initFromTxt(chemin);
    }

    public void startGame(){
        ihm.maj(map.toString());
    }

    public void stopGame(){
        ihm.closeGame();
    }
}
