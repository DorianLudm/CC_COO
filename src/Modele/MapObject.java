package Modele;

public abstract class MapObject{
    private String color;
    private String representation;
    private int posX;
    private int posY;

    public String getRepresentation(){
        String finalString = representation + color;
        return finalString;
    }

    public int getPosX(){return this.posX;}
    public int getPosY(){return this.posY;}

    public abstract void play(String map);
}