package IHM;

import Controleurs.ControleurMouvement;
import Modele.Map;

import java.util.Scanner;

public class Ihm{

    private ControleurMouvement ctlMouvement;
    private Map map;

    public Ihm(){
        Scanner sc = new Scanner(System.in);
        String biome = "";
        if (sc.hasNext()){
            biome = sc.next().toLowerCase();
        }
        ctlMouvement = new ControleurMouvement(this, biome);
        map = ctlMouvement.getMap();

        String scInput = "";

        while(true){
            try {
                System.out.println(map);

                if (sc.hasNext()){
                    scInput = sc.next();
                }

                switch (scInput){
                    case "z":
                        ctlMouvement.movement("top");
                        break;
                    case "s":
                        ctlMouvement.movement("bottom");
                        break;
                    case "q":
                        ctlMouvement.movement("left");
                        break;
                    case "d":
                        ctlMouvement.movement("right");
                        break;
                }
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    /**/
    public void maj(Map map){
    }


    // --- INTERACTIONS APPLICATION -> USER --- //

    public void printValidationMouvement(String s){
        System.out.println(s);
    }



    /// --- Main test --- ///
    public static void main(String[] args){
        Ihm ihm = new Ihm();
    }
}

