package entities.dynamics.enemies;

import core.panels.children.GamePanel;
import entities.dynamics.DynamicEntity;
import entities.handler.Handler;
import entities.statics.StaticEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;

public abstract class Enemy extends DynamicEntity implements ActionListener {

    public int[] possibleKeys;

    Random random;
    Timer timer;

    Handler handler;
    public GamePanel panel;

    public Enemy(int x, int y, int width, int height, int speed, int life, GamePanel panel) {
        super(x, y, width, height, speed, life);

        this.possibleKeys= new int[]{KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_ENTER};
        this.random=new Random();
        this.timer=new Timer(2,this);
        this.panel=panel;
        this.handler=new Handler(this, panel);
        addKeyListener(handler);

        timer.start();
    }

    public void randMovements() throws AWTException {
        int rand=random.nextInt(4);
        int keyCode=possibleKeys[rand];

        switch (keyCode) {
            case KeyEvent.VK_W -> {
                for (int i=0;i<panel.getWalls().size();i++){
                    StaticEntity wall=panel.getWalls().get(i);
                    if (handler.collisionCheck(wall)) {
                        if (getY() <= wall.getY() + wall.getHeight() &&
                                getY() + getHeight() >= wall.getY() + wall.getHeight() &&
                                getX() + getWidth() >= wall.getX()
                                && getX() <= wall.getX() + wall.getWidth()) {
                            return;
                        }
                    }
                }
                move(DynamicEntity.Direction.UP);
            }
            case KeyEvent.VK_S -> {
                for (int i=0;i<panel.getWalls().size();i++){
                    StaticEntity wall=panel.getWalls().get(i);
                    if (handler.collisionCheck(wall)) {
                        if (getY() + getHeight() >= wall.getY() &&
                                getY() <= wall.getY() && getX() + getWidth() >= wall.getX()
                                && getX() <= wall.getX() + wall.getWidth()) {
                            return;
                        }
                    }
                }
                move(DynamicEntity.Direction.DOWN);
            }
            case KeyEvent.VK_A -> {
                for (int i=0;i<panel.getWalls().size();i++){
                    StaticEntity wall=panel.getWalls().get(i);
                    if (handler.collisionCheck(wall)) {
                        if (getX() <= wall.getX() + wall.getWidth() &&
                                getX() + getWidth() >= wall.getX() + wall.getWidth() &&
                                getY() + getHeight() >= wall.getY() && getY() <= wall.getY()) {
                            return;
                        }
                    }
                }
                move(DynamicEntity.Direction.LEFT);
            }
            case KeyEvent.VK_D -> {
                for (int i=0;i<panel.getWalls().size();i++){
                    StaticEntity wall=panel.getWalls().get(i);
                    if (handler.collisionCheck(wall)){
                        if (getX() + getWidth() >= wall.getX() &&
                                getX() <= wall.getX() && getY() + getHeight() >= wall.getY() &&
                                getY() <= wall.getY()) {
                            return;
                        }
                    }
                }
                move(DynamicEntity.Direction.RIGHT);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            randMovements();
        } catch (AWTException ex) {
            ex.printStackTrace();
        }
    }
}
