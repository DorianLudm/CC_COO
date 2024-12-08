package Modele;

public abstract class MapObject{
    public final String ANSI_RESET = "\u001B[0m";
    private String bgColor;
    private String fontColor;
    private String representation;

    public String getRepresentation(){
        return bgColor + fontColor + " " + representation + " " + ANSI_RESET;
    }

    public void setBgColor(String bgColor){ this.bgColor = bgColor; }
    public void setFontColor(String fontColor){ this.fontColor = fontColor; }
    public void setRepresentation(String representation){ this.representation = representation; }

    public abstract void play(String map);
}