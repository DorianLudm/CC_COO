package Modele;

public class Player extends MapObject{

    public Player() {
        setBgColor("\u001B[47m");
        setFontColor("\u001B[35m");
        setRepresentation("@");
    }

    @Override
    public void play(String map) {}
}
