package entities.statics.children;

import entities.statics.StaticEntity;

import javax.swing.*;

public class Lava extends StaticEntity {

    public Lava(int x, int y, int width, int height) {
        super(x, y, width, height);

        this.image= new ImageIcon("res/image/items/lava.png").getImage();
    }
}
