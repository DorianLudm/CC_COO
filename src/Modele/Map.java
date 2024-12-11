package Modele;

import java.io.FileReader;
import java.io.IOException;
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
                    map[i][j] = factory.instanciateEmptySpace(i, j);
                }else if(repartition[i][j] < 0.56){
                    map[i][j] = factory.instanciateTree(i, j);
                }
                else {
                    map[i][j] = factory.instanciateMushroom(i, j);
                }
            }
        }
        player = Player.getInstance(x/2,y/2);
        map[player.getPosX()][player.getPosY()] = player;
    }

    public void initFromTxt(String path){
        try (FileReader fileReader = new FileReader(path)) {
            int line = 0;int x =0;int y = 0;int posy = 0;
            int caractere;
            while ((caractere = fileReader.read()) != -1) {
                char c = (char) caractere;
                if(line == 0){
                    switch(c){
                        case 'F':
                            this.factory = new ForestFactory();
                            break;
                        case 'J':
                            this.factory = new JungleFactory();
                            break;
                    }
                }else if(line == 1 && caractere >= '0'){
                    x = x * 10 + caractere - '0';
                }else if(line == 2 && caractere >= '0') {
                    y = y * 10 + caractere - '0';
                }
                if(c==10 && line == 2){
                    map = new MapObject[x][y];
                    System.out.println("x" + x + "y" + y);
                    for(int j = 0; j < x; j++){
                        for(int k = 0; k < y; k++){
                            map[j][k] = factory.instanciateEmptySpace(j, k);
                        }
                    }
                }if(c == 10){
                    line++;posy = 0;
                    continue;
                }else if(c == 13){
                    continue;
                }else{
                    switch(c){
                        case 'A':
                            map[line-3][posy] = this.factory.instanciateTree(line-3,posy);
                            break;
                        case 'G':
                            map[line-3][posy] = this.factory instanceof ForestFactory ? this.factory.instanciateFruit(line-3,posy) : this.factory.instanciateTree(line-3,posy);
                            break;
                        case 'E':
                        case 'M':
                            map[line-3][posy] = this.factory.instanciateAnimal(line-3,posy);
                            break;
                        case 'B':
                            map[line-3][posy] = this.factory instanceof ForestFactory ? this.factory.instanciateDecoration(line-3,posy) : this.factory.instanciateFruit(line-3,posy);
                            break;
                        case 'X':
                            map[line-3][posy] = this.factory.instanciateDecoration(line-3,posy);
                            break;
                        case ' ':
                            map[line-3][posy] = this.factory.instanciateEmptySpace(line-3,posy);
                            break;
                        case 'C':
                            map[line-3][posy] = this.factory.instanciateMushroom(line-3,posy);
                            break;
                        case '@':
                            System.out.println(line-3 + " " + posy);
                            player=Player.getInstance(line-3,posy);
                            map[player.getPosX()][player.getPosY()] = player;
                            break;
                    }
                }
                posy++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        if (voisin != null && voisin.isReachable()){

            map[voisin.posX][voisin.posY] = player;
            map[playerPosX][playerPosY] = factory.instanciateEmptySpace(playerPosX, playerPosY);
            player.posX = voisin.posX;
            player.posY = voisin.posY;
        }

        NPCturn();
    }

    /***/
    public void playerFight(int indDirection){
        MapObject voisin = getSurroudings(player.posX,player.posY)[indDirection];
        voisin.attacked();

        NPCturn();
    }

    /***/
    public void playerPickUp(int indDirection){
        int playerPosX = player.getPosX();
        int playerPosY = player.getPosY();

        MapObject voisin = getSurroudings(playerPosX,playerPosY)[indDirection];

        if (voisin.isPickable()){
            player.addItem(voisin);
            map[voisin.posX][voisin.posY] = factory.instanciateEmptySpace(voisin.posX, voisin.posY);
        }
        NPCturn();
    }

    public void playerDrop(int indDirection, String item){
        MapObject voisin = getSurroudings(player.posX,player.posY)[indDirection];

        if (voisin != null && voisin.isReachable()){
            if (player.removeItem(item) != null)
                if (item.equals("Banana") || item.equals("Acorn"))
                    map[voisin.posX][voisin.posY] = factory.instanciateFruit(voisin.posX, voisin.posY);
                else if (item.equals("Squirrel") || item.equals("Monkey")) {
                    map[voisin.posX][voisin.posY] = factory.instanciateAnimal(voisin.posX, voisin.posY);
                } else if (item.equals("ForestMushroom") || item.equals("JungleMushroom")) {
                    map[voisin.posX][voisin.posY] = factory.instanciateMushroom(voisin.posX, voisin.posY);
                }
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

        int hauteur = map.length;
        int largeur = map[0].length;

        // Calcul des indices de début et de fin
        int startX = Math.max(0, player.getPosX() - 15 / 2);
        int startY = Math.max(0, player.getPosY() - 20 / 2);
        int endX = Math.min(hauteur, startX + 15);
        int endY = Math.min(largeur, startY + 20);

        // Ajustement si la fin dépasse les bords
        startX = Math.max(0, endX - 15);
        startY = Math.max(0, endY - 20);

        for (int i = startX; i < endX; i++) {
            for (int j = startY; j < endY; j++) {
                s += map[i][j].getRepresentation();
            }
            s += "\n";
        }
        s += player.toString();
        return s;
    }
}
