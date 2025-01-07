package IHM;

import Controleurs.ControleurGestionnairePartie;
import Controleurs  .ControleurInteraction;
import Controleurs.ControleurMouvement;
import Modele.GameTurnCommand;
import Modele.GameTurnInvocator;
import Modele.Map;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Ihm{

    private ControleurMouvement ctlMouvement;
    private ControleurInteraction ctlInteraction;
    private ControleurGestionnairePartie ctlGP;
    private GameTurnInvocator gameTurnInvocator;
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
                if (sc.hasNextInt()) {
                    x = sc.nextInt();
                    if (sc.hasNextInt()) {
                        y = sc.nextInt();
                        if (x > 0 && y > 0) {
                            break;
                        } else {
                            System.out.println("Les dimensions doivent être des entiers positifs.");
                        }
                    } else {
                        System.out.println("Veuillez entrer deux entiers.");
                        sc.next(); // Utile pour que le scanner ne prenne pas le print en entrée
                    }
                } else {
                    System.out.println("Veuillez entrer deux entiers.");
                    sc.next();
                }
            }
            this.ctlGP = new ControleurGestionnairePartie(this, this.map,x,y, biome);
        }if(typeMapGen.equals("y")){
            System.out.println("Avec ou sans prédateur ? y/n");
            String preda = null;
            String chemin = null;
            if(sc.hasNext()){
                preda = sc.next();
            }
            if(preda.equals("n")){
                chemin = "./lib/carte_base.txt";
            }else{
                chemin = "./lib/carte_preda.txt";
            }
            ctlGP = new ControleurGestionnairePartie(this, this.map,chemin);
        }
        this.ctlInteraction = new ControleurInteraction(this, this.map);
        this.ctlMouvement = new ControleurMouvement(this, this.map);
        this.gameTurnInvocator = GameTurnInvocator.getInstance();
        gameTurnInvocator.push(new GameTurnCommand(this.map));

        this.ctlGP.startGame();
    }

    /**/
    public void maj(String map) {
        try {
            String scInputAction = "";
            System.out.println(map);
            System.out.println("Choisisez une action à effectuer :\n-" +
                    " 'pick' pour ramasser un objet\n" +
                    "- 'attack' pour attaquer un animal\n" +
                    "- 'drop' pour lancer un item ou un animal\n" +
                    "- 'rewind' pour revenir dans le temps\n" +
                    "- 'quit' pour arrêter la partie\n" +
                    "- (z,q,s,d) pour vous déplacer");
    
            // Vérifier l'entrée pour l'action
            while (true) {
                if (sc.hasNext()) {
                    scInputAction = sc.next();
                    break;
                } else {
                    System.out.println("Entrée invalide. Veuillez choisir une action :");
                    sc.next(); // Vider le tampon du scanner pour éviter une boucle infinie
                }
            }
    
            switch (scInputAction) {
                case "pick":
                    handlePickAction();
                    break;
                case "attack":
                    handleAttackAction();
                    break;
                case "drop":
                    handleDropAction();
                    break;
                case "rewind":
                    handleRewindAction();
                    break;
                case "quit":
                    ctlGP.stopGame();
                    break;
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
                    System.out.println("Action invalide. Veuillez choisir une action valide.");
                    maj(map);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void handlePickAction() {
        System.out.println("Dans quelle direction voulez-vous ramasser un objet ? (z,q,s,d)");
        String scInputDirection = getValidDirection();
        if (scInputDirection == null) return;
    
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
    
    private void handleAttackAction() {
        System.out.println("Dans quelle direction voulez-vous attaquer? (z,q,s,d)");
        String scInputDirection = getValidDirection();
        if (scInputDirection == null) return;
    
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
    
    private void handleDropAction() {
        System.out.println("Quel item voulez-vous lancer ?");
        String scInputItem = sc.next();
        System.out.println("Dans quelle direction voulez-vous lancer un item ? (z,q,s,d)");
        String scInputDirection = getValidDirection();
        if (scInputDirection == null) return;
    
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

    private void handleRewindAction() {
        System.out.println("De combien de tours voulez-vous remonter le temps ?");
        String scInputRewindValue = sc.next();
        if (scInputRewindValue == null) return;
        switch (scInputRewindValue) {
            case "2":
                ctlInteraction.rewind(2);
                break;
            case "3":
                ctlInteraction.rewind(3);
                break;
        }
    }
    
    private String getValidDirection() {
        while (true) {
            if (sc.hasNext()) {
                String direction = sc.next();
                if (direction.matches("[zqsd]")) {
                    return direction;
                } else {
                    System.out.println("Direction invalide. Veuillez entrer (z,q,s,d) :");
                }
            } else {
                System.out.println("Entrée invalide. Veuillez entrer (z,q,s,d) :");
                sc.next(); // Vider le tampon du scanner pour éviter une boucle infinie
            }
        }
    }
    
    public void closeGame() {
        System.out.println("Merci d'avoir joué!");
        this.sc.close();
    }
}
