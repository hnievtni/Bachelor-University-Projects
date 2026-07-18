package core.panels.children;

import core.App;
import core.Window;
import entities.dynamics.enemies.Enemy;
import entities.dynamics.enemies.children.FireSkull;
import entities.dynamics.enemies.children.Ghost;
import entities.dynamics.enemies.children.Skull;
import entities.dynamics.players.Player;
import entities.handler.Handler;
import entities.statics.StaticEntity;
import core.panels.Panel;
import entities.statics.children.GlassWindow;
import entities.statics.children.Lava;
import entities.statics.children.SpookyDoor;
import entities.statics.children.StoneWall;
import items.Bomb;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
class EndGame extends KeyAdapter {

    GamePanel panel;
    public EndGame(GamePanel panel) {
        this.panel=panel;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            //ends the game and starts the new game
            panel.removeKeyListener(panel.handler);
            panel.getWindow().dispose(panel.getWindow().getFrame());
            App.newGame();
        }
    }
}
public class GamePanel extends Panel implements ActionListener {

    Image background, lifeImage, bombImage;

    public int rows,columns;

    public Vector<Integer> xAxis=new Vector<>();
    public Vector<Integer> yAxis=new Vector<>();

    HashMap<Integer, Integer> coordinate;
    ArrayList<HashMap<Integer, Integer>> availableCoordinates=new ArrayList<>();

    ArrayList<StaticEntity> walls=new ArrayList<>(); //all the blocks
    ArrayList<Enemy> enemies=new ArrayList<>(); //all the enemies

    Random random=new Random();
    Timer timer;

    Window window;
    Player player;
    Handler handler;

    String coolDownStr;

    public GamePanel(int width, int height, int rows, int columns, Window window) {
        super(width, height);

        this.rows=rows;
        this.columns=columns;
        this.window=window;
        this.coolDownStr=" ";

        setImages();
        setCoordinate(); //sets all the available coordinates
        setMap();

        this.player=new Player("kirito",xAxis.get(1),yAxis.get(1), width/rows, height/columns,
                7, window.getLifeNumber(), 50, this);
        this.handler=new Handler(player, this);
        addKeyListener(handler);

        this.timer=new Timer(1,this);
        timer.start();
    }

    public void setImages(){
        this.background=new ImageIcon("res/image/backgrounds/background-faded.jpg").getImage();
        this.lifeImage=new ImageIcon("res/image/items/life.gif").getImage();
        this.bombImage=new ImageIcon("res/image/items/bomb.gif").getImage();
    }
    public void setCoordinate(){
        for (int i=45;i<=1200;){
            this.xAxis.add(i);
            for (int j=25;j<=700;){
                this.yAxis.add(j);
                coordinate=new HashMap<>();
                coordinate.put(i,j);
                this.availableCoordinates.add(coordinate);
                j+=height/columns;
            }
            i+=width/rows;
        }
    }
    public void setMap(){
        //stone wall
        for (int i=0;i<rows;i++){
            setStoneWall(i, 0); //first row
            setStoneWall(i, columns-1); //last row

            for (int j=0;j<columns;j++) {
                setStoneWall(0, j); //first column
                setStoneWall(rows-1, j); //last column
            }

            setFlag1(0, 0);
            setFlag2(0, 1);
            setLamp(rows-1, 0);
            setGate(1, 0);
            setExit(rows-2, columns-1);
        }
        for (int i=2;i<rows-2;i+=2){
            for (int j=2;j<columns-2;j+=2){
                setStoneWall(i, j); //walls in between
            }
        }

        //players spot
        {
            coordinate = new HashMap<>();
            coordinate.put(xAxis.get(1),yAxis.get(1)); //first empty spot in the left
            availableCoordinates.remove(coordinate);
        }

        //glass window
        for (int count=0;count<0.16*(rows*columns);){
            //random spots
            if (setGlassWindow()){
                count++;
            }
        }

        //spooky door
        for (int count=0;count<0.06*rows*columns;){
            //random spots
            if (setSpookyDoor()){
                count++;
            }
        }

        //lava
        for (int count=0;count<0.03*rows*columns;){
            //random spots
            if (setLavaTile()){
                count++;
            }
        }

        //ghost
        for (int count=0;count<window.getGhostNumber();){
            //random spots
            if (setGhost()){
                count++;
            }
        }

        //skull
        for (int count=0;count<window.getSkullNumber();){
            //random spots
            if (setSkull()){
                count++;
            }
        }

        //fire skull
        for (int count=0;count<window.getFireSkullNumber();){
            //random spots
            if (setFireSkull()){
                count++;
            }
        }
    }
    public void setStoneWall(int i, int j){
        walls.add(new StoneWall(xAxis.get(i), yAxis.get(j), width / rows, height / columns, StoneWall.Wall.STONEWALL));{
            coordinate = new HashMap<>();
            coordinate.put(xAxis.get(i), yAxis.get(j));
            availableCoordinates.remove(coordinate);
        }
    }
    public void setFlag1(int i, int j){
        walls.add(new StoneWall(xAxis.get(i),yAxis.get(j), width / rows, height / columns, StoneWall.Wall.FLAG1));{
            coordinate = new HashMap<>();
            coordinate.put(xAxis.get(i),yAxis.get(j));
            availableCoordinates.remove(coordinate);
        }
    }
    public void setFlag2(int i, int j){
        walls.add(new StoneWall(xAxis.get(i),yAxis.get(j), width / rows, height / columns, StoneWall.Wall.FLAG2));{
            coordinate = new HashMap<>();
            coordinate.put(xAxis.get(i),yAxis.get(j));
            availableCoordinates.remove(coordinate);
        }
    }
    public void setLamp(int i, int j){
        walls.add(new StoneWall(xAxis.get(i),yAxis.get(j), width / rows, height / columns, StoneWall.Wall.LAMP));{
            coordinate = new HashMap<>();
            coordinate.put(xAxis.get(i),yAxis.get(j));
            availableCoordinates.remove(coordinate);
        }
    }
    public void setGate(int i, int j){
        walls.add(new StoneWall(xAxis.get(i),yAxis.get(j), width / rows, height / columns, StoneWall.Wall.GATE));{
            coordinate = new HashMap<>();
            coordinate.put(xAxis.get(i),yAxis.get(j));
            availableCoordinates.remove(coordinate);
        }
    }
    public void setExit(int i, int j){
        walls.add(new StoneWall(xAxis.get(i),yAxis.get(j), width / rows, height / columns, StoneWall.Wall.EXIT));{
            coordinate = new HashMap<>();
            coordinate.put(xAxis.get(i),yAxis.get(j));
            availableCoordinates.remove(coordinate);
        }
    }
    public Boolean setGlassWindow(){
        int randX=random.nextInt(rows-2)+1;
        int randY=random.nextInt(columns-2)+1;

        coordinate = new HashMap<>();
        coordinate.put(xAxis.get(randX),yAxis.get(randY));

        if (availableCoordinates.contains(coordinate)) {
            walls.add(new GlassWindow(xAxis.get(randX), yAxis.get(randY), width / rows, height / columns));
            availableCoordinates.remove(coordinate);
            return true;
        }
        else
            return false;
    }
    public Boolean setSpookyDoor(){
        int randX=random.nextInt(rows-2)+1;
        int randY=random.nextInt(columns-2)+1;

        coordinate = new HashMap<>();
        coordinate.put(xAxis.get(randX),yAxis.get(randY));

        if (availableCoordinates.contains(coordinate)) {
            walls.add(new SpookyDoor(xAxis.get(randX), yAxis.get(randY), width / rows, height / columns));
            availableCoordinates.remove(coordinate);
            return true;
        }
        else
            return false;
    }
    public Boolean setLavaTile(){
        int randX=random.nextInt(rows-2)+1;
        int randY=random.nextInt(columns-2)+1;

        coordinate = new HashMap<>();
        coordinate.put(xAxis.get(randX),yAxis.get(randY));

        Lava lava=new Lava(xAxis.get(randX), yAxis.get(randY), width / rows, height / columns);
        if (availableCoordinates.contains(coordinate)) {
            walls.add(lava);
            availableCoordinates.remove(coordinate);

            return true;
        }
        else
            return false;
    }
    public Boolean setGhost(){
        int randX=random.nextInt(((rows-2)-((rows/2)+1))+1)+(rows/2)+1;
        int randY=random.nextInt(columns-2)+1;

        coordinate = new HashMap<>();
        coordinate.put(xAxis.get(randX),yAxis.get(randY));

        if (availableCoordinates.contains(coordinate)) {
            enemies.add(new Ghost(xAxis.get(randX), yAxis.get(randY), width / rows, height / columns,3,1,this));
            availableCoordinates.remove(coordinate);
            return true;
        }
        else
            return false;
    }
    public Boolean setSkull(){
        int randX=random.nextInt(((rows-2)-((rows/2)+1))+1)+(rows/2)+1;
        int randY=random.nextInt(columns-2)+1;

        coordinate = new HashMap<>();
        coordinate.put(xAxis.get(randX),yAxis.get(randY));

        if (availableCoordinates.contains(coordinate)) {
            enemies.add(new Skull(xAxis.get(randX), yAxis.get(randY), width / rows, height / columns,1,3,this));
            availableCoordinates.remove(coordinate);
            return true;
        }
        else
            return false;
    }
    public Boolean setFireSkull(){
        int randX=random.nextInt(((rows-2)-((rows/2)+1))+1)+(rows/2)+1;
        int randY=random.nextInt(columns-2)+1;

        coordinate = new HashMap<>();
        coordinate.put(xAxis.get(randX),yAxis.get(randY));

        if (availableCoordinates.contains(coordinate)) {
            enemies.add(new FireSkull(xAxis.get(randX), yAxis.get(randY), width / rows, height / columns,2, 1, 3,this));
            availableCoordinates.remove(coordinate);
            return true;
        }
        else
            return false;
    }
    public void setCoolDownStr(String coolDownStr) {
        this.coolDownStr = coolDownStr;
    }
    public Window getWindow() {
        return window;
    }
    public ArrayList<StaticEntity> getWalls() {
        return walls;
    }
    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
    public Player getPlayer() {
        return player;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(background,0,0, 1300,700,null);

        for (int i=0;i<walls.size();i++){
            walls.get(i).draw(g);
        }
        for (int i=0;i<enemies.size();i++){
            enemies.get(i).draw(g);
            if (enemies.get(i) instanceof FireSkull){
                if (((FireSkull) enemies.get(i)).getBombNumber()>0 && ((FireSkull) enemies.get(i)).getBombs().size()>0){
                    for (Bomb bomb : ((FireSkull) enemies.get(i)).getBombs()) {
                        if (bomb.getReleased()) {
                            bomb.draw(g);
                        }
                    }
                }
            }
        }

        player.draw(g);
        if (player.getBombNumber()>=0) {
            for (int i=0;i<player.getBombs().size();i++){
                Bomb bomb=player.getBombs().get(i);
                if (bomb.getReleased()) {
                    bomb.draw(g);

                    java.util.Timer timer1=new java.util.Timer();
                    TimerTask task=new TimerTask() {

                        int count=bomb.getCoolDown();
                        @Override
                        public void run() {

                            if (count>0){
                                setCoolDownStr(count+" seconds left");
                                count--;
                            }
                            else {
                                setCoolDownStr(" ");
                                timer1.cancel();
                            }
                        }
                    };
                    timer1.schedule(task, 0,bomb.getCoolDown()* 1000L);
                }
            }
        }

        //player info
        g.drawImage(lifeImage, 100, 590, 50, 50, null);
        g.drawImage(bombImage, 200, 590, 50, 50, null);

        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 21));
        g.setColor(Color.WHITE);

        g.drawString(String.valueOf(player.getLife()), 115, 590);
        g.drawString(String.valueOf(player.getBombNumber()), 215, 590);
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));

        if (player.getBombs().size()>0) {
            g.drawString("Power: "+ player.getBombs().get(0).getPower(), 275, 600);
            g.drawString("Radius: "+ player.getBombs().get(0).getRadius(), 275, 625);
        }
        else if (player.getBombNumber() >=0 && player.getBombs().size() <= 0){
            g.drawString("Power: -", 275, 600);
            g.drawString("Radius: -", 275, 625);
        }

        g.setColor(Color.RED);
        g.drawString(coolDownStr, 460, 615);

        if (player.getLife()<=0 || enemies.isEmpty()){
            timer.stop();

            EndGame endGame=new EndGame(this);
            addKeyListener(endGame);

            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 0, 1300, 700);

            //shadow
            g.setColor(Color.BLACK);
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 110));
            if (player.getLife()<=0) {
                g.drawString("GAME OVER!", 304, 204);
            }
            if (enemies.isEmpty()){
                g.drawString("YOU WON!", 354, 204);
            }

            g.setFont(new Font(Font.SERIF, Font.BOLD, 35));
            g.drawString("START  NEW  GAME", 452,412);
            g.drawString("EXIT", 570,452);

            g.setFont(new Font(Font.SERIF, Font.BOLD, 35));

            //texts
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 110));
            if (player.getLife()<=0) {
                g.setColor(Color.red);
                g.drawString("GAME OVER!", 300, 200);
            }
            if (enemies.isEmpty()){
                g.setColor(Color.WHITE);
                g.drawString("YOU WON!", 350, 200);
            }

            g.setColor(Color.WHITE);
            g.setFont(new Font(Font.SERIF, Font.BOLD, 35));
            g.drawString("START  NEW  GAME", 450,410);

            g.setFont(new Font(Font.SERIF, Font.BOLD, 35));
            g.drawString(">", 420,410);
        }
        else if (player.getLife()>0 && !enemies.isEmpty()){
            repaint();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i=0;i<enemies.size();i++){
            if (player.getBounds().intersects(enemies.get(i).getBounds())){
                enemies.remove(enemies.get(i));
                player.setLife(player.getLife()-1);
            }
        }
    }
}