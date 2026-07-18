package entities.dynamics.enemies.children;

import core.panels.children.GamePanel;
import entities.dynamics.enemies.Enemy;

import javax.swing.*;

public class Skull extends Enemy {

    public Skull(int x, int y, int width, int height, int speed, int life, GamePanel panel) {
        super(x, y, width, height, speed, life, panel);

        setImage();
    }

    @Override
    public void setImage() {
        setIdle(new ImageIcon("res/image/enemies/skull/skull.gif").getImage());
        setUp(new ImageIcon("res/image/enemies/skull/skull.gif").getImage());
        setDown(new ImageIcon("res/image/enemies/skull/skull.gif").getImage());
        setLeft(new ImageIcon("res/image/enemies/skull/skull.gif").getImage());
        setRight(new ImageIcon("res/image/enemies/skull/skull.gif").getImage());
    }
}
