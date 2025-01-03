package Modele;

public abstract class MapObject implements Cloneable{
    public final String ANSI_RESET = "\u001B[0m";
    private String bgColor;
    private String fontColor;
    private String representation;
    protected int posX;
    protected int posY;
    protected boolean reachable = false;
    protected boolean pickable = false;
    protected boolean hasPlayed = false;

    public int getPosX() { return posX; }
    public int getPosY() { return posY; }

    public String getRepresentation(){
        return bgColor + fontColor + " " + representation + " " + ANSI_RESET;
    }

    public boolean isReachable(){ return reachable; }
    public boolean isPickable(){ return pickable; }

    public void setBgColor(String bgColor){ this.bgColor = bgColor; }
    public void setFontColor(String fontColor){ this.fontColor = fontColor;}
    public void setRepresentation(String representation){ this.representation = representation; }

    public void play(MapTile[][] map){if(this.hasPlayed){return;} this.hasPlayed = true;}
    public abstract void attacked();
    protected void setCoords(int x, int y){posX = x; posY = y;}
    public boolean hasPlayed(){return this.hasPlayed;}
    public void resetHasPlayed(){this.hasPlayed = false;}

    @Override
    public MapObject clone(){
        try {
            return (MapObject) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Clonage non supporté", e);
        }
    }
}