package Modele;

public class Scorpion extends JunglePredator{
    Scorpion(int x, int y){
        this.posX = x;
        this.posY = y;
        this.detectionRadius = 1;
        this.moveRadius = 1;
        setBgColor("\u001B[44m");
        setFontColor("\u001B[32m");
        setRepresentation("S");
    }

    @Override
    public void play(MapTile[][] map){
        return;
    }

    @Override
    public void attacked(){
        return;
    }
}