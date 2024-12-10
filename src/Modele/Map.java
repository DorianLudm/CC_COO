package Modele;

import java.util.Random;

public class Map{
    private static Map instance;
    private MapObject[][] map;
    private Player player;
    private Factory factory;

    private Map(){}

    public void Init(int x, int y, String biome){ // map générer.
        switch(biome){
            case "forest" :
                this.factory = new ForestFactory();
                break;
            case "jungle" :
                this.factory = new JungleFactory();
                break;
        }
        double[][] repartition = generateWave(x,y);
        map = new MapObject[x][y];
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                if(repartition[i][j] > 0.75){
                    map[i][j] = factory.instanciateDecoration(i, j);
                }else if(repartition[i][j] > 0.73){
                    map[i][j] = factory.instanciateAnimal(i, j);
                }else if(repartition[i][j]>0.71) {
                    map[i][j] = factory.instanciateFruit(i, j);
                }else if(repartition[i][j] > 0.58){
                    map[i][j] = factory.instanciatEmptySpace(i, j);
                }else if(repartition[i][j] < 0.56){
                    map[i][j] = factory.instanciateTree(i, j);
                }
                else {
                    map[i][j] = factory.instanciateMushroom(i, j);
                }
            }
        }
        player = Player.getInstance(5,5);
        map[player.getPosX()][player.getPosY()] = player;
    }
    /***/

    public static Map getInstance(){
        if(instance == null){
            instance = new Map();
        }
        return instance;
    }

    public Factory getFactory(){
        return this.factory;
    }

    private double[][] generateWave(int x, int y) {
        double[][] result = new double[x][y];
        Random rand = new Random();
        for (int nbpt = 0; nbpt < 40; nbpt++) {
            int irand = rand.nextInt(x);
            int jrand = rand.nextInt(y);
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    double distance = Math.sqrt(Math.pow(i - irand, 2) + Math.pow(j - jrand, 2));
                    result[i][j] += 0.025 * Math.abs(Math.sin(distance));
                }
            }
        }
        return result;
    }
    /***/

    public Map(int x, int y,String biome, String fichier){
        // map[2][2] = new Acorn();
        // map[25][4] = new ForestTree();
        // map[3][2] = new ForestTree();
        // map[3][4] = new ForestMushroom();
        // map[6][6] = new Squirrel();
        // map[0][0] = player;
    }

    /***/
    public void loadMap(String biome){}

    /***/
    public void generateMap(String biome){

    }

    /***/
    private void NPCturn(){
        for(MapObject[] obj1: this.map){
            for(MapObject obj2: obj1){
                obj2.play(map);
            }
        }
        for(MapObject[] obj1: this.map){
            for(MapObject obj2: obj1){
                obj2.resetHasPlayed();
            }
        }
    }

    /***/
    public void movePlayer(int indDirection){
        int playerPosX = player.posX; // lignes
        int playerPosY = player.posY; // colonnes

        MapObject voisin = getSurroudings(playerPosX,playerPosY)[indDirection];

        if (voisin.isReachable()){

            map[voisin.posX][voisin.posY] = player;
            map[playerPosX][playerPosY] = factory.instanciatEmptySpace(playerPosX, playerPosY);
            player.posX = voisin.posX;
            player.posY = voisin.posY;
        }

        NPCturn();
    }

    /***/
    public void playerFight(){
        // implementation
        // int x_attack, y_attack
        // map[x_attack][y_attack].

        NPCturn();
    }

    /***/
    public void playerPickUp(int indDirection){
        int playerPosX = player.getPosX();
        int playerPosY = player.getPosY();

        MapObject voisin = getSurroudings(playerPosX,playerPosY)[indDirection];

        if (voisin.isPickable()){
            player.addItem(voisin);
            map[voisin.posX][voisin.posY] = factory.instanciatEmptySpace(voisin.posX, voisin.posY);
        }
        NPCturn();
    }

    public MapObject[] getSurroudings(int x, int y){
        MapObject[] res = new MapObject[4];
        res[0] = (x > 0) ? map[x - 1][y] : null;
        res[1] = (y > 0) ? map[x][y - 1] : null;
        res[2] = (x < map.length - 1) ? map[x + 1][y] : null;
        res[3] = (y < map[x].length - 1) ? map[x][y + 1] : null;
        return res;
    }

    @Override
    public String toString() {
        String s = "";

        int largeur = map.length;
        int hauteur = map[0].length;

        // Calcul des indices de début et de fin
        int startX = Math.max(0, player.getPosX() - 20 / 2);
        int startY = Math.max(0, player.getPosY() - 15 / 2);
        int endX = Math.min(largeur, startX + 20);
        int endY = Math.min(hauteur, startY + 15);

        // Ajustement si la fin dépasse les bords
        startX = Math.max(0, endX - 20);
        startY = Math.max(0, endY - 15);

        for (int i = startY; i < endY; i++) {
            for (int j = startX; j < endX; j++) {
                s += map[i][j].getRepresentation();
            }
            s += "\n";
        }
        return s;
    }
}
