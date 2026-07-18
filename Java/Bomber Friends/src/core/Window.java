package core;

import core.panels.Panel;
import core.panels.children.GamePanel;
import core.panels.children.SettingPanel;
import core.panels.children.StartPanel;

import javax.swing.*;
import java.awt.*;
public class Window{
    JFrame frame;
    Panel panel;

    int rows, columns;
    int ghostNumber, skullNumber, fireSkullNumber;
    int lifeNumber, bombPower, bombRadius;

    public Window() {
        this.rows=23;
        this.columns=15;
        this.ghostNumber=3;
        this.skullNumber=2;
        this.fireSkullNumber=1;
        this.lifeNumber=3;
        this.bombPower=1;
        this.bombRadius=1;
    }

    public void avoidDisplaying(){
        this.frame.setVisible(false);
    }
    public void dispose(JFrame frame){
        frame.dispose();
    }
    public void display(Frame frame){
        frame.setVisible(true);
    }
    public void exit(){
        System.exit(0);
    }

    public void setPanel(String panelName){
        switch (panelName){
            case "StartPanel" -> this.panel=new StartPanel(1300, 700, this);
            case "GamePanel" -> this.panel=new GamePanel(1100,550,rows,columns, this);
            case "SettingPanel" -> this.panel=new SettingPanel(1300, 700, this);
        }
    }
    public JFrame setFrame() {
        ImageIcon icon = new ImageIcon("res/image/icon.jpg");

        this.frame=new JFrame();
        this.frame.setTitle("Bomber Friends");
        this.frame.setSize(1300, 700);
        this.frame.setIconImage(icon.getImage());
        this.frame.setLocationRelativeTo(null); //sets the frame window in the center of the screen
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setResizable(false);
        this.frame.getContentPane().setBackground(Color.BLACK);
        this.frame.add(panel);

        return frame;
    }
    public void setRows(int rows) {
        this.rows = rows;
    }
    public void setColumns(int columns) {
        this.columns = columns;
    }
    public void setGhostNumber(int ghostNumber) {
        this.ghostNumber = ghostNumber;
    }
    public void setSkullNumber(int skullNumber) {
        this.skullNumber = skullNumber;
    }
    public void setFireSkullNumber(int fireSkullNumber) {
        this.fireSkullNumber = fireSkullNumber;
    }
    public void setLifeNumber(int lifeNumber) {
        this.lifeNumber = lifeNumber;
    }
    public void setBombPower(int bombPower) {
        this.bombPower = bombPower;
    }
    public void setBombRadius(int bombRadius) {
        this.bombRadius = bombRadius;
    }
    public JFrame getFrame() {
        return frame;
    }
    public int getGhostNumber() {
        return ghostNumber;
    }
    public int getSkullNumber() {
        return skullNumber;
    }
    public int getFireSkullNumber() {
        return fireSkullNumber;
    }
    public int getLifeNumber() {
        return lifeNumber;
    }
    public int getBombPower() {
        return bombPower;
    }
    public int getBombRadius() {
        return bombRadius;
    }
}





















