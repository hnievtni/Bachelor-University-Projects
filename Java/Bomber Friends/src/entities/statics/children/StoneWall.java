package entities.statics.children;

import entities.statics.StaticEntity;

import javax.swing.*;

public class StoneWall extends StaticEntity {

    public enum Wall{
        STONEWALL, GATE, EXIT, FLAG1, FLAG2, LAMP
    }

    public StoneWall(int x, int y, int width, int height, Wall wall) {
        super(x, y, width, height);

        setImage(wall);
    }

    public void setImage(Wall wall) {
        switch (wall){
            case STONEWALL -> this.image= new ImageIcon("res/image/walls/stone-wall.png").getImage();
            case GATE -> this.image = new ImageIcon("res/image/walls/gate.png").getImage();
            case EXIT -> this.image = new ImageIcon("res/image/walls/exit.png").getImage();
            case FLAG1 -> this.image = new ImageIcon("res/image/walls/flag1.png").getImage();
            case FLAG2 -> this.image = new ImageIcon("res/image/walls/flag2.png").getImage();
            case LAMP -> this.image = new ImageIcon("res/image/walls/lamp.png").getImage();
        }
    }
}
