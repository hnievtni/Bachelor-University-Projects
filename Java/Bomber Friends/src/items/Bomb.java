package items;

import core.panels.children.GamePanel;
import entities.Entity;
import entities.dynamics.DynamicEntity;
import entities.dynamics.enemies.Enemy;
import entities.dynamics.enemies.children.FireSkull;
import entities.dynamics.players.Player;
import entities.statics.StaticEntity;
import entities.statics.children.GlassWindow;
import entities.statics.children.SpookyDoor;

import javax.swing.*;
import java.awt.*;

public class Bomb{

    int x, y, width, height;
    int power, radius, coolDown;

    Boolean released, coolDownMode;

    Image bomb;

    GamePanel panel;

    public Bomb(int power, int radius, int coolDown, GamePanel panel) {
        this.power = power;
        this.radius = radius;
        this.coolDown = coolDown;
        this.panel=panel;
        this.released=false;
        this.coolDownMode=false;
        this.bomb=new ImageIcon("res/image/items/bomb.gif").getImage();
    }

    public Rectangle d_upBounds(){
        return new Rectangle(x+(width/4), y-(radius*height- (height/2)) , width/2, radius*height- (height/2));
    }
    public Rectangle d_downBounds(){
        return new Rectangle(x+(width/4), y+ height, width/2 , radius*height- (height/2));
    }
    public Rectangle d_leftBounds(){
        return new Rectangle(x-(radius*width-(width/2)), y+(height/4), radius*width-(width/2), height/2);
    }
    public Rectangle d_rightBounds(){
        return new Rectangle(x+width, y+(height/4) , radius*width-(width/2), height/2);
    }
    public Rectangle d_bounds(){
        return new Rectangle(x, y, width, height);
    }

    public Rectangle s_upBounds(){
        return new Rectangle(x+(width/4), y-(height/2) , width/2, height/2);
    }
    public Rectangle s_downBounds(){
        return new Rectangle(x+(width/4), y+ height, width/2 , height/2);
    }
    public Rectangle s_leftBounds(){
        return new Rectangle(x-(width/2), y+(height/4), width/2, height/2);
    }
    public Rectangle s_rightBounds(){
        return new Rectangle(x+width, y+(height/4) , width/2, height/2);
    }

    public void setCoordinate(int x, int y, int width, int height){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }
    public void draw(Graphics g) {
        g.drawImage(bomb,x, y, width, height, null);
    }
    public void explosion(){
        for (int i=0;i<panel.getWalls().size();i++){
            if (s_rightBounds().intersects(panel.getWalls().get(i).getBounds()) ||
                    s_leftBounds().intersects(panel.getWalls().get(i).getBounds()) ||
                    s_upBounds().intersects(panel.getWalls().get(i).getBounds()) ||
                    s_downBounds().intersects(panel.getWalls().get(i).getBounds())) {

                bombImpact(panel.getWalls().get(i));
            }
        }
        for (int i=0;i<panel.getEnemies().size();i++){
            if (d_rightBounds().intersects(panel.getEnemies().get(i).getBounds()) ||
                    d_leftBounds().intersects(panel.getEnemies().get(i).getBounds()) ||
                    d_upBounds().intersects(panel.getEnemies().get(i).getBounds()) ||
                    d_downBounds().intersects(panel.getEnemies().get(i).getBounds()) ||
                    d_bounds().intersects(panel.getEnemies().get(i).getBounds())) {

                bombImpact(panel.getEnemies().get(i));
            }
        }

        if (d_rightBounds().intersects(panel.getPlayer().getBounds()) ||
                d_leftBounds().intersects(panel.getPlayer().getBounds()) ||
                d_upBounds().intersects(panel.getPlayer().getBounds()) ||
                d_downBounds().intersects(panel.getPlayer().getBounds()) ||
                d_bounds().intersects(panel.getPlayer().getBounds())){

            bombImpact(panel.getPlayer());
        }
    }
    public void bombImpact(Entity entity){
        if (entity instanceof StaticEntity) {
            if (entity instanceof GlassWindow) {
                ((GlassWindow) entity).setDamage(power);
                if (((GlassWindow) entity).getDamage() >= 1){
                    panel.getWalls().remove(entity);
                }
            } else {
                if (entity instanceof SpookyDoor) {
                    ((SpookyDoor) entity).setDamage(power);
                    if (((SpookyDoor) entity).getDamage() >= 3) {
                        panel.getWalls().add(((SpookyDoor) entity).luckyBox());
                        panel.getWalls().remove(entity);
                    }
                }
            }
        }
        else if (entity instanceof  DynamicEntity){
            if (entity instanceof Player){
                ((Player) entity).setLife(((Player) entity).getLife() - 1);
            }
            if (entity instanceof Enemy){
                if (entity instanceof FireSkull) {
                    ((FireSkull) entity).getTimer().cancel();
                }
                panel.getEnemies().remove(entity);
            }
        }

    }

    public void setPower(int power) {
        this.power = power;
    }
    public void setRadius(int radius) {
        this.radius = radius;
    }
    public void setReleased(Boolean released) {
        this.released = released;
    }
    public void setCoolDownMode(Boolean coolDownBoolean) {
        this.coolDownMode = coolDownBoolean;

        if (!coolDownBoolean){
            explosion();
        }
    }
    public int getPower() {
        return power;
    }
    public int getRadius() {
        return radius;
    }
    public int getCoolDown() {
        return coolDown;
    }
    public Boolean getReleased() {
        return released;
    }
    public Boolean getCoolDownMode() {
        return coolDownMode;
    }
}






















