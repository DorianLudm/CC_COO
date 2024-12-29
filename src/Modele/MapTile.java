package Modele;

public class MapTile {
    private MapObject background;
    private MapObject foreground;

    MapTile(MapObject background){
        this.background = background;
    }

    MapTile(MapObject background, MapObject foreground){
        this.background = background;
        this.foreground = foreground;
    }

    private boolean hasForeground(){
        return this.foreground != null;
    }

    public String getRepresentation(){
        if (this.hasForeground()){return this.foreground.getRepresentation();}
        else return this.background.getRepresentation();
    }

    public int getPosX(){
        return this.background.getPosX();
    }

    public int getPosY(){
        return this.background.getPosY();
    }

    public MapObject getBackground(){return this.background;}
    public MapObject getForeground(){return this.foreground;}

    public void setBackground(MapObject background){
        this.background = background;
    }

    public void setForeground(MapObject foreground){
        this.foreground = foreground;
    }

    public boolean isReachable(){
        if(this.hasForeground()){return false;}
        return this.background.isReachable();
    }

    public boolean isPickable(){
        if(this.hasForeground()){return this.foreground.isPickable();}
        return this.background.isPickable();
    }

    public void play(MapTile[][] map){
        if (this.hasForeground()){this.foreground.play(map);}
    }

    public void resetHasPlayed(){
        if (this.hasForeground()){this.foreground.resetHasPlayed();}
    }
}
