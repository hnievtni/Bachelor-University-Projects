package entities.statics.children;

import entities.statics.StaticEntity;

import javax.swing.*;

public class BombPowerPotion extends StaticEntity {
    public BombPowerPotion(int x, int y, int width, int height) {
        super(x, y, width, height);

        this.image=new ImageIcon("res/image/items/bombPower-potion.gif").getImage();
    }
}
