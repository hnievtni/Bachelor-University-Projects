package entities.dynamics;

import entities.Entity;

import java.awt.*;

public abstract class DynamicEntity extends Entity{

    public enum Direction{
        IDLE,UP,DOWN,LEFT,RIGHT
    }
    public Direction currentState;

    public Image idle;
    public Image up;
    public Image down;
    public Image left;
    public Image right;

    public int life, speed;

    public DynamicEntity(int x, int y, int width, int height, int speed, int life) {
        super(x, y, width, height);

        this.currentState=Direction.IDLE;
        this.life=life;
        this.speed=speed;
    }

    public void move(Direction direction){
        switch(direction){
            case UP ->{
                setY(y-=speed);
                this.currentState=Direction.UP;
            }
            case DOWN -> {
                setY(y+=speed);
                this.currentState=Direction.DOWN;
            }
            case LEFT -> {
                setX(x-=speed);
                this.currentState=Direction.LEFT;
            }
            case RIGHT -> {
                setX(x+=speed);
                this.currentState=Direction.RIGHT;
            }
        }
    }
    public void draw(Graphics g) {
        switch (currentState){
            case IDLE -> g.drawImage(idle, x, y, width, height, null);
            case UP -> g.drawImage(up, x, y, width, height, null);
            case DOWN -> g.drawImage(down, x, y, width, height, null);
            case LEFT -> g.drawImage(left, x, y, width, height, null);
            case RIGHT -> g.drawImage(right, x, y, width, height, null);
        }
    }

    public abstract void setImage();
    public void setCurrentState(Direction currentState) {
        this.currentState = currentState;
    }
    public void setLife(int life) {
        this.life = life;
    }
    public void setIdle(Image idle) {
        this.idle = idle;
    }
    public void setUp(Image up) {
        this.up = up;
    }
    public void setDown(Image down) {
        this.down = down;
    }
    public void setLeft(Image left) {
        this.left = left;
    }
    public void setRight(Image right) {
        this.right = right;
    }
    public int getLife() {
        return life;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x+width/4,y+height/4,width/2,height/2);
    }
}
