package entities.statics.children;

import entities.statics.StaticEntity;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SpookyDoor extends StaticEntity {

    int damage;

    Random random;

    public SpookyDoor(int x, int y, int width, int height) {
        super(x, y, width, height);

        this.damage=0;
        this.random=new Random();
        this.image = new ImageIcon("res/image/walls/spooky-door.png").getImage();
    }

    public StaticEntity luckyBox(){
        int randNumber=random.nextInt(3);

        StaticEntity entity=null;

        switch (randNumber){
            case 0 -> entity=new Life(x, y, width, height);
            case 1 -> entity=new BombPowerPotion(x, y, width, height);
            case 2 -> entity=new BombRadiusPotion(x, y, width, height);
        }
        return entity;
    }

    public void setDamage(int hit) {
        this.damage += hit;
    }
    public int getDamage() {
        return damage;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        int health=100-(33*damage);

        float green= (float) (health*2.55);
        float red=255-green;

        if (health>=0){
            g.setColor(new Color((int) red,(int) green,0));
            g.fillRect(x, y, width, 2);
        }
    }
}
