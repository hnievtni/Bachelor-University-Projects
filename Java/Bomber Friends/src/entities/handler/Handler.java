package entities.handler;

import core.panels.children.GamePanel;
import entities.dynamics.DynamicEntity;
import entities.dynamics.players.Player;
import entities.statics.StaticEntity;
import entities.statics.children.*;
import items.Bomb;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.TimerTask;

public class Handler extends KeyAdapter {

    DynamicEntity dynamic;
    GamePanel panel;

    public Handler(DynamicEntity dynamic, GamePanel panel) {
        this.dynamic = dynamic;
        this.panel=panel;
    }

    public Boolean collisionCheck(StaticEntity entity){
        Boolean collision= dynamic.getBounds().intersects(entity.getBounds());

        if(collision){
            if (entity instanceof Lava && dynamic instanceof Player){
                dynamic.setLife(dynamic.getLife()-1);
                panel.getWalls().remove(entity);
            }
            if (entity instanceof BombPowerPotion && dynamic instanceof Player){
                for (Bomb bomb:((Player) dynamic).getBombs()){
                    bomb.setRadius(bomb.getRadius() + 2);
                }
                panel.getWalls().remove(entity);
            }
            if (entity instanceof BombRadiusPotion && dynamic instanceof Player){
                for (Bomb bomb:((Player) dynamic).getBombs()){
                    bomb.setPower(bomb.getPower() + 2);
                }
                panel.getWalls().remove(entity);
            }
            if (entity instanceof Life && dynamic instanceof Player){
                dynamic.setLife((dynamic.getLife() + 1));
                panel.getWalls().remove(entity);
            }
        }

        return collision;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
        dynamic.setCurrentState(DynamicEntity.Direction.IDLE);

        if (dynamic instanceof Player) {
            if (e.getKeyCode()==KeyEvent.VK_ENTER){
                for (int i=0;i<((Player) dynamic).getBombs().size();i++){
                    Bomb bomb=((Player) dynamic).getBombs().get(i);
                    if (bomb.getReleased()){
                        bomb.setCoolDownMode(true);

                        java.util.Timer timer=new java.util.Timer();
                        TimerTask task=new TimerTask() {
                            @Override
                            public void run() {
                                bomb.setCoolDownMode(false);
                                bomb.setReleased(false);
                            }
                        };
                        timer.schedule(task, bomb.getCoolDown()* 1000L);
                    }
                }
            }
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode=e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_W -> {
                for (int i=0;i<panel.getWalls().size();i++){
                    StaticEntity wall=panel.getWalls().get(i);
                    if (collisionCheck(wall)) {
                        if (dynamic.getY() <= wall.getY() + wall.getHeight() &&
                                dynamic.getY() + dynamic.getHeight() >= wall.getY() + wall.getHeight() &&
                                dynamic.getX() + dynamic.getWidth() >= wall.getX()
                                && dynamic.getX() <= wall.getX() + wall.getWidth()) {
                            return;
                        }
                    }
                }
                dynamic.move(DynamicEntity.Direction.UP);
            }
            case KeyEvent.VK_S -> {
                for (int i=0;i<panel.getWalls().size();i++){
                    StaticEntity wall=panel.getWalls().get(i);
                    if (collisionCheck(wall)) {
                        if (dynamic.getY() + dynamic.getHeight() >= wall.getY() &&
                                dynamic.getY() <= wall.getY() && dynamic.getX() + dynamic.getWidth() >= wall.getX()
                                && dynamic.getX() <= wall.getX() + wall.getWidth()) {
                            return;
                        }
                    }
                }
                dynamic.move(DynamicEntity.Direction.DOWN);
            }
            case KeyEvent.VK_A -> {
                for (int i=0;i<panel.getWalls().size();i++){
                    StaticEntity wall=panel.getWalls().get(i);
                    if (collisionCheck(wall)) {
                        if (dynamic.getX() <= wall.getX() + wall.getWidth() &&
                                dynamic.getX() + dynamic.getWidth() >= wall.getX() + wall.getWidth() &&
                                dynamic.getY() + dynamic.getHeight() >= wall.getY() && dynamic.getY() <= wall.getY()) {
                            return;
                        }
                    }
                }
                dynamic.move(DynamicEntity.Direction.LEFT);
            }
            case KeyEvent.VK_D -> {
                for (int i=0;i<panel.getWalls().size();i++){
                    StaticEntity wall=panel.getWalls().get(i);
                    if (collisionCheck(wall)) {
                        if (dynamic.getX() + dynamic.getWidth() >= wall.getX() &&
                                dynamic.getX() <= wall.getX() && dynamic.getY() + dynamic.getHeight() >= wall.getY() &&
                                dynamic.getY() <= wall.getY()) {
                            return;
                        }
                    }
                }
                dynamic.move(DynamicEntity.Direction.RIGHT);
            }
            case KeyEvent.VK_ENTER ->{
                if (dynamic instanceof Player) {
                    if (((Player) dynamic).getBombNumber() > 0) {
                        if (!((Player) dynamic).checkCoolDown()) {

                            Bomb bomb = new Bomb(panel.getWindow().getBombPower(),
                                    panel.getWindow().getBombRadius(), 2, panel);

                            bomb.setCoordinate(dynamic.getX(), dynamic.getY(), dynamic.getWidth(), dynamic.getHeight());
                            bomb.setReleased(true);

                            ((Player) dynamic).addBomb(bomb);
                            ((Player) dynamic).setBombNumber(((Player) dynamic).getBombNumber() - 1);
                        }
                    }
                }
            }
        }
        super.keyPressed(e);
    }
}






























