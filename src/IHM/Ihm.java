package IHM;

import Controleurs.Controleur;
import Controleurs.ControleurMouvement;
import Modele.Map;

import java.util.Scanner;

public class Ihm{

    private ControleurMouvement ctlMouvement;

    public Ihm(){
        ctlMouvement = new ControleurMouvement(this);

        Scanner sc = new Scanner(System.in);
        String scInput = "";

        while(true){
            try {
                if (sc.hasNext()){
                    scInput = sc.next();
                }

                switch (scInput){
                    case "z":
                        ctlMouvement.up_movement();
                        break;
                    case "s":
                        ctlMouvement.down_movement();
                        break;
                    case "q":
                        ctlMouvement.left_movement();
                        break;
                    case "d":
                        ctlMouvement.right_movement();
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

    public void printMap(Map map){
        System.out.println(map);
    }

    public void printValidationMouvement(String s){
        System.out.println(s);
    }



    /// --- Main test --- ///
    public static void main(String[] args){
        Ihm ihm = new Ihm();
    }
}

