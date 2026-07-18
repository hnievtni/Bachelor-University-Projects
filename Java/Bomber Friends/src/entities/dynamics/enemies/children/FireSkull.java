package entities.dynamics.enemies.children;

import core.panels.children.GamePanel;
import entities.dynamics.enemies.Enemy;
import items.Bomb;

import javax.swing.*;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Timer;

public class FireSkull extends Enemy {
    int bombNumber;

    ArrayList<Bomb> bombs;

    Timer timer;

    public FireSkull(int x, int y, int width, int height, int speed, int life, int bombNumber, GamePanel panel) {
        super(x, y, width, height, speed, life, panel);

        this.bombNumber=bombNumber;
        this.bombs=new ArrayList<>();
        this.timer=new Timer();

        setImage();
        releaseTimer();
    }

    public void releaseBomb(){
        if (bombNumber > 0) {
            Bomb bomb = new Bomb(4, 5, 5, panel);

            bomb.setCoordinate(x, y, width, height);
            bomb.setReleased(true);
            bomb.setCoolDownMode(true);

            this.bombs.add(bomb);
            this.bombNumber--;
        }
    }
    public void releaseTimer(){
        TimerTask task=new TimerTask() {

            int count=bombNumber;
            @Override
            public void run() {
                if (count>0) {
                    releaseBomb();
                    count--;
                }
                else {
                    timer.cancel();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 5000,40* 1000L);
    }

    public int getBombNumber() {
        return bombNumber;
    }
    public ArrayList<Bomb> getBombs() {
        return bombs;
    }
    public Timer getTimer() {
        return timer;
    }

    @Override
    public void setImage() {
        setIdle(new ImageIcon("res/image/enemies/fire-skull/fire-skull.gif").getImage());
        setUp(new ImageIcon("res/image/enemies/fire-skull/fire-skull.gif").getImage());
        setDown(new ImageIcon("res/image/enemies/fire-skull/fire-skull.gif").getImage());
        setLeft(new ImageIcon("res/image/enemies/fire-skull/fire-skull.gif").getImage());
        setRight(new ImageIcon("res/image/enemies/fire-skull/fire-skull.gif").getImage());
    }
}
