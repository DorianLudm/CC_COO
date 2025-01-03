package Modele;

public class Snake extends JunglePredator{
    Snake(int x, int y){
        this.posX = x;
        this.posY = y;
        this.detectionRadius = 1;
        this.moveRadius = 2;
        setBgColor("\u001B[43m");
        setFontColor("\u001B[30m");
        setRepresentation("E");
    }

    @Override
    public void play(MapTile[][] map){
        // reprendre code de ecureil junky pour double mouvement
        return;
    }

    @Override
    public void attacked(){
        return;
    }
}
