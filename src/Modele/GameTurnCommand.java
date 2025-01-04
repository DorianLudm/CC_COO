package Modele;

import java.util.LinkedList;
import java.util.Queue;

public class GameTurnCommand {

    private MapTile[][] map;
    private int playerX;
    private int playerY;

    public GameTurnCommand(Map map) {
        this.map = map.deepCopy();
        this.playerX = Player.getInstance().posX;
        this.playerY = Player.getInstance().posY;
    }

    public MapTile[][] getMap() {
        return map;
    }

    public int getPlayerX() { return playerX; }
    public int getPlayerY() { return playerY; }

    public void setMap(MapTile[][] map) {
        this.map = map;
    }
}
