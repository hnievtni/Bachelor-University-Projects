package entities.statics;

import entities.Entity;

import java.awt.*;

public abstract class StaticEntity extends Entity {

    public Image image;

    public StaticEntity(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,width,height);
    }
    @Override
    public void draw(Graphics g){
        g.drawImage(image, x, y, width, height, null);
    }
}
