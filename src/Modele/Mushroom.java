package Modele;

import java.util.Random;

public abstract class Mushroom extends MapObject{
    protected boolean rollAttribute(){
        Random rd = new Random();
        return 1 == rd.nextInt(2);
    }

    public abstract boolean isWeirdMushroom();

    protected void initColor(){
        if(this.isWeirdMushroom()){
            this.setFontColor("\u001B[30m");
            this.setBgColor("\u001B[46m");
        }
        else{
            this.setFontColor("\u001B[34m");
            this.setBgColor("\u001B[47m");
        }
    }
}
