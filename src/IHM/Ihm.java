package IHM;

import Controleurs.ControleurMouvement;
import Modele.Map;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Ihm{

    private ControleurMouvement ctlMouvement;
    private Map map;

    public Ihm(){
        Scanner sc = new Scanner(System.in);  // Scanner utilisé par l'Ihm

        while(true) {
            String typeMapGen = null;
            System.out.println("Voulez vous charger une map depuis un fichier ? y/n");
            if(sc.hasNext()){
                typeMapGen = sc.next();
            }
            if(typeMapGen.equals("n")){
                List<String> valideBiome = new ArrayList<>();  // Liste des biomes existants
                valideBiome.add("jungle");
                valideBiome.add("forest"); // rajouter ici les biomes implémentés ultérieurement
                String biome;  // Variable de stockage du biome souhaité
                while (true) { // Vérifie que le nom de biome est correct parmi les biomes implémentés.
                    String demande = "La carte va donc être généré. Choisissez votre biome parmi : ";
                    for (String biomeName : valideBiome) {  // construction de la demande de biome en fonction de ceux implémenté.
                        demande += biomeName + " ";
                    }
                    System.out.println(demande);
                    if (sc.hasNext()) {
                        biome = sc.next().toLowerCase();
                        if (valideBiome.contains(biome)) {
                            break;  // Biome valide, on sort de la boucle.
                        } else {
                            System.out.println("Nom de biome incorrect.");
                        }
                    }
                }
                ctlMouvement = new ControleurMouvement(this, biome);
                break;
            }if(typeMapGen.equals("y")){
                break;
            }
        }


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

