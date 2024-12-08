package Modele;

public class ForestEmptySpace extends EmptySpace{

    public ForestEmptySpace() {
        setBgColor("\u001B[42m");
        setFontColor("");
        setRepresentation(" ");
        reachable = true;
    }

    public void play(String map){}
}
