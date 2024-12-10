package IHM;

import Controleurs  .ControleurInteraction;
import Controleurs.ControleurMouvement;
import Modele.Map;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Ihm{

    private ControleurMouvement ctlMouvement;
    private ControleurInteraction ctlInteraction;
    private Map map;

    public Ihm(){
        this.map = Map.getInstance();
        Scanner sc = new Scanner(System.in);  // Scanner utilisé par l'Ihm

        while(true) {
            String typeMapGen = null;
            System.out.println("Voulez vous charger une map depuis un fichier ? y/n");
            if(sc.hasNext()){
                typeMapGen = sc.next();
            }
            if(typeMapGen.equals("n")){
                int x =0;int y=0;
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
                while(true){
                    System.out.println("Quelle dimension de carte : longueur largeur");
                    if(sc.hasNextInt()) {
                        x = sc.nextInt();
                    }if(sc.hasNextInt()) {
                        y = sc.nextInt();
                    }
                    if(x > 0&& y > 0 ){
                        break;
                    }
                }
                ctlMouvement = new ControleurMouvement(this, this.map,x,y, biome);
                ctlInteraction = new ControleurInteraction(this, this.map);
                break;
            }if(typeMapGen.equals("y")){
                String chemin = "./lib/carte.txt";
                ctlMouvement = new ControleurMouvement(this, this.map,chemin);
                ctlInteraction = new ControleurInteraction(this, this.map);
                break;
            }
        }

        String scInputAction = "";
        String scInputDirection = "";

        while(true){
            try {
                System.out.println(map);
                System.out.println("Choisisez une action à effectuer :\n- 'pick' pour ramasser un objet\n- 'attack' pour attaquer un animal\n- (z,q,s,d) pour vous déplacer");
                if (sc.hasNext()){
                    scInputAction = sc.next();
                }

                if (scInputAction.equals("pick")){
                    System.out.println("Dans quelle direction voulez-vous ramasser un objet ? (z,q,s,d)");
                    scInputDirection = sc.next();
                    switch (scInputDirection) {
                        case "z":
                            ctlInteraction.pickUp(0);
                            break;
                        case "q":
                            ctlInteraction.pickUp(1);
                            break;
                        case "d":
                            ctlInteraction.pickUp(2);
                            break;
                        case "s":
                            ctlInteraction.pickUp(3);
                            break;
                    }
                }
                else if (scInputAction.equals("attack")) {
                    System.out.println("Dans quelle direction voulez-vous attaquer? (z,q,s,d)");
                    scInputDirection = sc.next();
                    switch (scInputDirection) {
                        case "z":
                            ctlInteraction.attack(0);
                            break;
                        case "q":
                            ctlInteraction.attack(1);
                            break;
                        case "d":
                            ctlInteraction.attack(2);
                            break;
                        case "s":
                            ctlInteraction.attack(3);
                            break;
                    }
                }
                switch (scInputAction) {
                    case "z":
                        ctlMouvement.movement(0);
                        break;
                    case "q":
                        ctlMouvement.movement(1);
                        break;
                    case "s":
                        ctlMouvement.movement(2);
                        break;
                    case "d":
                        ctlMouvement.movement(3);
                        break;
                }

            }
            catch (Exception e){
                //System.out.println(e.getMessage());
                e.printStackTrace();
                break;
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

