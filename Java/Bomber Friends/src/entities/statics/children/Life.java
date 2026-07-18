package entities.statics.children;

import entities.statics.StaticEntity;

import javax.swing.*;

public class Life extends StaticEntity {
    public Life(int x, int y, int width, int height) {
        super(x, y, width, height);

        this.image= new ImageIcon("res/image/items/life.gif").getImage();
    }
}
