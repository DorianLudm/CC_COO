package Modele;

public abstract class TxtConverter {
    Factory facto;
    TxtConverter(Factory factory){
        facto = factory;
    }
    public abstract MapObject generate(char letter, int x, int y);
}
