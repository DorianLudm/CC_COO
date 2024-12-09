package Modele;

public class JungleEmptySpace extends EmptySpace{

    public JungleEmptySpace() {
        setBgColor("\u001B[43m");
        setFontColor("");
        setRepresentation(" ");
    }
    
    @Override
    public void attacked(MapObject[][] map){}
}
