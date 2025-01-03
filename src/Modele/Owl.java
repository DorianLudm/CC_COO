package Modele;

public class Owl extends FoerstPredator{
    private int stunned;

    Owl(int x, int y){
        this.posX = x;
        this.posY = y;
        this.detectionRadius = 3;
        this.moveRadius = 1;
        setBgColor("\u001B[45m");
        setFontColor("\u001B[37m");
        setRepresentation("H");
    }

    @Override
    public void play(MapTile[][] map){
        if(hasPlayed){return;}
        if(stunned > 1){stunned--; return;}
        if(stunned == 1){stunned--; setBgColor("\u001B[45m");}

        //if attacks fails
        setBgColor("\u001B[44m");
        return;
    }

    @Override
    public void attacked(){
        return;
    }
}
