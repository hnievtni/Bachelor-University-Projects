package entities.statics.children;

import entities.statics.StaticEntity;

import javax.swing.*;

public class GlassWindow extends StaticEntity {

    int damage;

    public GlassWindow(int x, int y, int width, int height) {
        super(x, y, width, height);

        this.damage=0;
        this.image= new ImageIcon("res/image/walls/glass-window.png").getImage();
    }

    public void setDamage(int hit) {
        this.damage = hit;
    }
    public int getDamage() {
        return damage;
    }
}
