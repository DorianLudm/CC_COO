package Modele;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Map{
    private static Map instance;
    private MapTile[][] map;
    private Player player;
    private Factory factory;

    private Map(){}

    public MapTile[][] deepCopy() {
        if (map == null) { return null; }

        MapTile[][] newMap = new MapTile[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] != null) {
                    newMap[i][j] = map[i][j].copie();
                }
            }
        }
        return newMap;
    }

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
        map = new MapTile[x][y];
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                if(repartition[i][j] > 0.75){
                    map[i][j] = new MapTile(factory.instanciateDecoration(i, j));
                }else if(repartition[i][j] > 0.73){
                    map[i][j] = new MapTile(factory.instanciateEmptySpace(i, j), factory.instanciateAnimal(i, j));
                }else if(repartition[i][j]>0.71) {
                    map[i][j] = new MapTile(factory.instanciateFruit(i, j));
                }else if(repartition[i][j] > 0.59){
                    map[i][j] = new MapTile(factory.instanciateEmptySpace(i, j));
                }else if(repartition[i][j] > 0.5875){
                    map[i][j] = new MapTile(factory.instanciateEmptySpace(i, j), factory.instanciatePredator(i, j));
                }else if(repartition[i][j] < 0.56){
                    map[i][j] = new MapTile(factory.instanciateTree(i, j));
                }
                else {
                    map[i][j] = new MapTile(factory.instanciateMushroom(i, j));
                }
            }
        }
        player = Player.getInstance(x/2,y/2);
        map[player.getPosX()][player.getPosY()].setBackground(factory.instanciateEmptySpace(player.getPosX(), player.getPosY()));
        map[player.getPosX()][player.getPosY()].setForeground(player);
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
                    map = new MapTile[x][y];
                    System.out.println("x" + x + "y" + y);
                    for(int j = 0; j < x; j++){
                        for(int k = 0; k < y; k++){
                            // System.out.println("j" + j + "k" + k);
                            map[j][k] = new MapTile(factory.instanciateEmptySpace(j, k));
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
                            map[line-3][posy].setBackground(this.factory.instanciateTree(line-3,posy));
                            break;
                        case 'G':
                            if(this.factory instanceof ForestFactory){
                                map[line-3][posy].setBackground(this.factory.instanciateFruit(line-3,posy));
                            }
                            else{
                                map[line-3][posy].setBackground(this.factory.instanciateTree(line-3,posy));
                            }
                            break;
                        case 'E':
                        case 'M':
                            map[line-3][posy].setForeground(this.factory.instanciateAnimal(line-3,posy));
                            break;
                        case 'B':
                            if(this.factory instanceof ForestFactory){
                                map[line-3][posy].setBackground(this.factory.instanciateDecoration(line-3,posy));
                            }
                            else{
                                map[line-3][posy].setBackground(this.factory.instanciateFruit(line-3,posy));
                            }
                            break;
                        case 'X':
                            map[line-3][posy].setBackground(this.factory.instanciateDecoration(line-3,posy));
                            break;
                        case ' ':
                            map[line-3][posy].setBackground(this.factory.instanciateEmptySpace(line-3,posy));
                            break;
                        case 'C':
                            map[line-3][posy].setBackground(this.factory.instanciateMushroom(line-3,posy));
                            break;
                        case '2':
                            map[line-3][posy].setBackground(new RareRock(line-3, posy, 2));
                            break;
                        case '3':
                            map[line-3][posy].setBackground(new RareRock(line-3, posy, 3));
                            break;
                        case '@':
                            System.out.println(line-3 + " " + posy);
                            player=Player.getInstance(line-3,posy);
                            map[player.getPosX()][player.getPosY()].setForeground(player);
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
        for(MapTile[] obj1: this.map){
            for(MapTile obj2: obj1){
                obj2.play(map);
            }
        }
        for(MapTile[] obj1: this.map){
            for(MapTile obj2: obj1){
                obj2.resetHasPlayed();
            }
        }

        GameTurnInvocator.getInstance().push(new GameTurnCommand(this));
    }

    /***/
    public void movePlayer(int indDirection){
        int playerPosX = player.posX; // lignes
        int playerPosY = player.posY; // colonnes

        MapTile voisin = getSurroundings(playerPosX,playerPosY)[indDirection];

        if (voisin != null && voisin.isReachable()){

            map[voisin.getPosX()][voisin.getPosY()].setForeground(player);
            map[playerPosX][playerPosY].setForeground(null);
            /* map[playerPosX][playerPosY] factory.instanciateEmptySpace(playerPosX, playerPosY); */
            player.posX = voisin.getPosX();
            player.posY = voisin.getPosY();
        }

        NPCturn();
    }

    /***/
    public void playerFight(int indDirection){
        MapTile voisin = getSurroundings(player.posX,player.posY)[indDirection];
        voisin.getBackground().attacked();
        voisin.getForeground().attacked();
        NPCturn();
    }

    /***/
    public void playerPickUp(int indDirection){
        int playerPosX = player.getPosX();
        int playerPosY = player.getPosY();

        MapTile voisin = getSurroundings(playerPosX,playerPosY)[indDirection];

        if (voisin.isPickable()){
            player.addItem(voisin.getBackground());
            map[voisin.getPosX()][voisin.getPosY()].setBackground(factory.instanciateEmptySpace(voisin.getPosX(), voisin.getPosY()));
        }
        NPCturn();
    }

    public void playerDrop(int indDirection, String item){
        MapTile voisin = getSurroundings(player.posX,player.posY)[indDirection];

        if (voisin != null && voisin.isReachable()){
            if (player.removeItem(item) != null)
                if (item.equals("Banana") || item.equals("Acorn"))
                    map[voisin.getPosX()][voisin.getPosY()].setBackground(factory.instanciateFruit(voisin.getPosX(), voisin.getPosY()));
                else if (item.equals("Squirrel") || item.equals("Monkey")) {
                    map[voisin.getPosX()][voisin.getPosY()].setForeground(factory.instanciateAnimal(voisin.getPosX(), voisin.getPosY()));
                } else if (item.equals("ForestMushroom") || item.equals("JungleMushroom")) {
                    map[voisin.getPosX()][voisin.getPosY()].setBackground(factory.instanciateMushroom(voisin.getPosX(), voisin.getPosY()));
                } else if (item.equals("RareRock2")) { // possible modification en séparant la chaine en deux + automatisation pour tout n
                    map[voisin.getPosX()][voisin.getPosY()].setBackground(new RareRock(voisin.getPosX(), voisin.getPosY(), 2));
                } else if (item.equals("RareRock3")) {
                    map[voisin.getPosX()][voisin.getPosY()].setBackground(new RareRock(voisin.getPosX(), voisin.getPosY(), 3));
                }
        }

        NPCturn();
    }

    public void rewind(int rewindValue){
        if (player.removeItem("RareRock" + rewindValue) != null) {
            GameTurnInvocator gti = GameTurnInvocator.getInstance();
            GameTurnCommand gtc = gti.pop(); // comme la commande est sauvegardée au début du tour il faut faire un pop 'de plus'
            for (int i = 0; i < rewindValue; i++) {
                gtc = gti.pop();
            }
            map = gtc.getMap();
            map[gtc.getPlayerX()][gtc.getPlayerY()].setForeground(player);
            player.posX = gtc.getPlayerX();
            player.posY = gtc.getPlayerY();

            RareRock newRock = new RareRock(player.posX, player.posY, 0);
            newRock.setBgColor("\u001B[47m");

            player.addItem(newRock);
        }
    }

    public MapTile[] getSurroundings(int x, int y){
        MapTile[] res = new MapTile[4];
        res[0] = (x > 0) ? map[x - 1][y] : null;
        res[1] = (y > 0) ? map[x][y - 1] : null;
        res[2] = (x < map.length - 1) ? map[x + 1][y] : null;
        res[3] = (y < map[x].length - 1) ? map[x][y + 1] : null;
        return res;
    }

    public static java.util.Map<Integer, List<MapTile>> scanArea(MapTile[][] map, MapObject scanPosition, int scanRadius){
        java.util.Map<Integer, List<MapTile>> res = new HashMap<>();
        int predatorX = scanPosition.getPosX();
        int predatorY = scanPosition.getPosY();
        for (int i = (predatorX - scanRadius); i <= (predatorX + scanRadius); i++) {
            for (int j = (predatorY - scanRadius); j <= (predatorY + scanRadius); j++) {
                if (i >= 0 && j >= 0 && i < map.length && j < map[0].length) {
                    MapTile obj = map[i][j];
                    int deltaX = Math.abs(predatorX - obj.getPosX());
                    int deltaY = Math.abs(predatorY - obj.getPosY());
                    int distance = deltaX + deltaY;
                    if (res.containsKey(distance)) {
                        List<MapTile> list = res.get(distance);
                        list.add(obj);
                    } else {
                        List<MapTile> list = new ArrayList<>();
                        list.add(obj);
                        res.put(distance, list);
                    }
                }
            }
        }
        return res;
    }

    public static List<MapTile> findClosestEntityOfType(MapTile[][] map, MapObject scanPosition, int scanRadius, Class<?> preyType){
        java.util.Map<Integer, List<MapTile>> detection = Map.scanArea(map, scanPosition, scanRadius);
        List<MapTile> toEat = new ArrayList<>();
        for (java.util.Map.Entry<Integer, List<MapTile>> entry : detection.entrySet()){
            List<MapTile> tiles = entry.getValue();
            for(MapTile tile: tiles){
                if(preyType.isInstance(tile.getForeground()) && tile.getBackground() instanceof EmptySpace){
                    toEat.add(tile);
                }
            }
            if(toEat.size() > 0){break;}
        }
        return toEat;
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
