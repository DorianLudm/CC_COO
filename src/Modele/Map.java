package Modele;

public class Map{

    private MapObject[][] map;
    private Player player;

    /***/
    public Map(){
        player = new Player(0,0);
    }

    public Map(int x, int y){
        map = new MapObject[x][y];
        player = new Player(0,0);

        MapObject tile = new ForestEmptySpace();
        for(int i=0; i<x; i++){
            for(int j=0; j<y; j++){
                map[i][j] = tile;
            }
        }

        map[2][2] = new Acorn();
        map[25][4] = new ForestTree();
        map[3][2] = new ForestTree();
        map[3][4] = new ForestMushroom();
        map[6][6] = new Squirrel();
        map[0][0] = player;
    }

    /***/
    public void loadMap(String biome){}

    /***/
    public void generateMap(String biome){}

    /***/
    private void NPCturn(){
        for(MapObject[] obj1: this.map){
            for(MapObject obj2: obj1){
                Integer[] pos = new Integer[2];
                pos = obj2.play(map);
            }
        }
    }

    /***/
    public void movePlayer(String direction){
        int playerPosX = player.getPosX();
        int playerPosY = player.getPosY();

        switch(direction){
            case "top":
                if (map[playerPosY-1][playerPosX].isReachable()){
                    map[playerPosY-1][playerPosX] = player;
                    map[playerPosY][playerPosX] = new ForestEmptySpace();
                    player.moveTop();
                }
                break;
            case "bottom":
                if (map[playerPosY+1][playerPosX].isReachable()){
                    map[playerPosY+1][playerPosX] = player;
                    map[playerPosY][playerPosX] = new ForestEmptySpace();
                    player.moveBottom();
                }
                break;
            case "left":
                if (map[playerPosY][playerPosX-1].isReachable()){
                    map[playerPosY][playerPosX-1] = player;
                    map[playerPosY][playerPosX] = new ForestEmptySpace();
                    player.moveLeft();
                }
                break;
            case "right":
                if (map[playerPosY][playerPosX+1].isReachable()){
                    map[playerPosY][playerPosX+1] = player;
                    map[playerPosY][playerPosX] = new ForestEmptySpace();
                    player.moveRight();
                }
                break;
            default: // test
                System.out.println("error");
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
    public void playerInteract(){
        // implementation

        NPCturn();
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
