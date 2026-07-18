package entities.dynamics.enemies.children;

import core.panels.children.GamePanel;
import entities.dynamics.enemies.Enemy;

import javax.swing.*;

public class Ghost extends Enemy {

    public Ghost(int x, int y, int width, int height, int speed, int life, GamePanel panel) {
        super(x, y, width, height, speed, life, panel);

        setImage();
    }

    @Override
    public void setImage() {
        setIdle(new ImageIcon("res/image/enemies/ghost/demon-idle.gif").getImage());
        setUp(new ImageIcon("res/image/enemies/ghost/demon-idle.gif").getImage());
        setDown(new ImageIcon("res/image/enemies/ghost/demon-idle.gif").getImage());
        setLeft(new ImageIcon("res/image/enemies/ghost/demon-idle.gif").getImage());
        setRight(new ImageIcon("res/image/enemies/ghost/demon-idle.gif").getImage());
    }
}
