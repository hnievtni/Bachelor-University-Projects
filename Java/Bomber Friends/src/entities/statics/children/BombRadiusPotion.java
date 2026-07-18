package entities.statics.children;

import entities.statics.StaticEntity;

import javax.swing.*;

public class BombRadiusPotion extends StaticEntity {
    public BombRadiusPotion(int x, int y, int width, int height) {
        super(x, y, width, height);

        this.image= new ImageIcon("res/image/items/bombRadius-potion.gif").getImage();
    }
}
