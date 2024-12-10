package Modele;

public abstract class MapObject{
    public final String ANSI_RESET = "\u001B[0m";
    private String bgColor;
    private String fontColor;
    private String representation;
    protected int posX;
    protected int posY;
    protected boolean reachable = false;
    protected boolean hasPlayed = false;

    public int getPosX() { return posX; }
    public int getPosY() { return posY; }

    public String getRepresentation(){
        return bgColor + fontColor + " " + representation + " " + ANSI_RESET;
    }

    public boolean isReachable(){ return reachable; }

    public void setBgColor(String bgColor){ this.bgColor = bgColor; }
    public void setFontColor(String fontColor){ this.fontColor = fontColor; }
    public void setRepresentation(String representation){ this.representation = representation; }

    public void play(MapObject[][] map){if(this.hasPlayed){return;} this.hasPlayed = true;}
    public abstract void attacked(MapObject[][] map);
    protected void setCoords(int x, int y){posX = x; posY = y;}
    public boolean hasPlayed(){return this.hasPlayed;}
    protected void resetHasPlayed(){this.hasPlayed = false;}
}