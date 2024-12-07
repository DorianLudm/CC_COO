package Modele;

public abstract class MapObject{
    public final String ANSI_RESET = "\u001B[0m";
    private String bgColor;
    private String fontColor;
    private String representation;

    private int posX;
    private int posY;

    public String getRepresentation(){
        return bgColor + fontColor + " " + representation + " " + ANSI_RESET;
    }

    public void setBgColor(String bgColor){ this.bgColor = bgColor; }
    public void setFontColor(String fontColor){ this.fontColor = fontColor; }
    public void setRepresentation(String representation){ this.representation = representation; }

    public int getPosX(){return this.posX;}
    public int getPosY(){return this.posY;}

    public abstract void play(String map);
}