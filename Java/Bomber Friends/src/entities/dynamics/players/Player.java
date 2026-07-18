package entities.dynamics.players;

import core.panels.children.GamePanel;
import entities.dynamics.DynamicEntity;
import items.Bomb;

import javax.swing.*;
import java.util.ArrayList;

public class Player extends DynamicEntity {

    String name;

    public int bombNumber;

    ArrayList<Bomb> bombs;

    GamePanel panel;

    public Player(String name, int x, int y, int width, int height, int speed, int life, int bombNumber, GamePanel panel) {
        super(x, y, width, height, speed, life);

        this.name=name;
        this.life=life;
        this.bombNumber=bombNumber;
        this.panel=panel;
        this.bombs=new ArrayList<>();

        setImage();
    }

    public Boolean checkCoolDown(){
        Boolean result=false;
        for (Bomb bomb:bombs){
            result= bomb.getCoolDownMode();
        }
        return result;
    }
    public void addBomb(Bomb bomb) {
        this.bombs.add(bomb);
    }

    public void setBombNumber(int bombNumber) {
        this.bombNumber = bombNumber;
    }
    public int getBombNumber() {
        return bombNumber;
    }
    public ArrayList<Bomb> getBombs() {
        return bombs;
    }

    @Override
    public void setImage(){
        switch (name){
            case "ninja-girl" ->{
                setIdle(new ImageIcon("res/image/players/ninja-girl/idle.gif").getImage());
                setUp(new ImageIcon("res/image/players/ninja-girl/up.gif").getImage());
                setDown(new ImageIcon("res/image/players/ninja-girl/down.gif").getImage());
                setLeft(new ImageIcon("res/image/players/ninja-girl/left.gif").getImage());
                setRight(new ImageIcon("res/image/players/ninja-girl/right.gif").getImage());
            }
            case "ninja-boy" ->{
                setIdle(new ImageIcon("res/image/players/ninja-boy/idle.gif").getImage());
                setUp(new ImageIcon("res/image/players/ninja-boy/up.gif").getImage());
                setDown(new ImageIcon("res/image/players/ninja-boy/down.gif").getImage());
                setLeft(new ImageIcon("res/image/players/ninja-boy/left.gif").getImage());
                setRight(new ImageIcon("res/image/players/ninja-boy/right.gif").getImage());
            }
            case "kid" ->{
                setIdle(new ImageIcon("res/image/players/kid/idle.png").getImage());
                setUp(new ImageIcon("res/image/players/kid/up.gif").getImage());
                setDown(new ImageIcon("res/image/players/kid/down.gif").getImage());
                setLeft(new ImageIcon("res/image/players/kid/left.gif").getImage());
                setRight(new ImageIcon("res/image/players/kid/right.gif").getImage());
            }
            case "shadow" ->{
                setIdle(new ImageIcon("res/image/players/shadow/idle.gif").getImage());
                setUp(new ImageIcon("res/image/players/shadow/up.gif").getImage());
                setDown(new ImageIcon("res/image/players/shadow/down.gif").getImage());
                setLeft(new ImageIcon("res/image/players/shadow/left.gif").getImage());
                setRight(new ImageIcon("res/image/players/shadow/right.gif").getImage());
            }
            case "kirito" ->{
                setIdle(new ImageIcon("res/image/players/kirito/idle.gif").getImage());
                setUp(new ImageIcon("res/image/players/kirito/up.gif").getImage());
                setDown(new ImageIcon("res/image/players/kirito/down.gif").getImage());
                setLeft(new ImageIcon("res/image/players/kirito/left.gif").getImage());
                setRight(new ImageIcon("res/image/players/kirito/right.gif").getImage());
            }
        }
    }
}
