package Modele;

public class Fox extends FoerstPredator{
    Fox(int x, int y){
        this.posX = x;
        this.posY = y;
        this.detectionRadius = 1;
        this.moveRadius = 1;
        this.setFontColor("\u001B[30m");
        this.setBgColor("\u001B[48m");
        this.setRepresentation("R");
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
