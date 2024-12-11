package IHM;

import Controleurs.ControleurGestionnairePartie;
import Controleurs  .ControleurInteraction;
import Controleurs.ControleurMouvement;
import Modele.Map;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Ihm{

    private ControleurMouvement ctlMouvement;
    private ControleurInteraction ctlInteraction;
    private ControleurGestionnairePartie ctlGP;
    private Scanner sc;
    private Map map;

    public Ihm(){
        this.map = Map.getInstance();
        this.sc = new Scanner(System.in);  // Scanner utilisé par l'Ihm

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
            this.ctlGP = new ControleurGestionnairePartie(this, this.map,x,y, biome);
        }if(typeMapGen.equals("y")){
            String chemin = "./lib/carte.txt";
            ctlGP = new ControleurGestionnairePartie(this, this.map,chemin);
        }
        this.ctlInteraction = new ControleurInteraction(this, this.map);
        this.ctlMouvement = new ControleurMouvement(this, this.map);
        this.ctlGP.startGame();
    }

    /**/
    public void maj(String map){
        try {
            String scInputAction = "";
            String scInputDirection = "";
            System.out.println(map);
            System.out.println("Choisisez une action à effectuer :\n-" +
                    " 'pick' pour ramasser un objet\n" +
                    "- 'attack' pour attaquer un animal\n" +
                    "- 'drop pour lancer un item ou un animal\n" +
                    "- (z,q,s,d) pour vous déplacer");
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
                    case "s":
                        ctlInteraction.pickUp(2);
                        break;
                    case "d":
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
                    case "s":
                        ctlInteraction.attack(2);
                        break;
                    case "d":
                        ctlInteraction.attack(3);
                        break;
                }
            }
            else if (scInputAction.equals("drop")) {
                System.out.println("Quel item voulez-vous lancer ?");
                String scInputItem = sc.next();
                System.out.println("Dans quelle direction voulez-vous lancer un item ? (z,q,s,d)");
                scInputDirection = sc.next();
                switch (scInputDirection) {
                    case "z":
                        ctlInteraction.drop(0, scInputItem);
                        break;
                    case "q":
                        ctlInteraction.drop(1, scInputItem);
                        break;
                    case "s":
                        ctlInteraction.drop(2, scInputItem);
                        break;
                    case "d":
                        ctlInteraction.drop(3, scInputItem);
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
                default:
                    ctlGP.startGame();
                    break;
            }
        }
        catch (Exception e){
            //System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void closeGame(){
        this.sc.close();
    }
}

