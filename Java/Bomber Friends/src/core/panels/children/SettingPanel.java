package core.panels.children;

import core.Window;
import core.panels.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class SettingHandler extends KeyAdapter {

    SettingPanel panel;

    public SettingHandler(SettingPanel panel) {
        this.panel=panel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        int keyCode=e.getKeyCode();

        switch (keyCode){
            case KeyEvent.VK_W ->{
                panel.setCommandRow(panel.getCommandRow() - 1);
                if(panel.getCommandRow() < 1){
                    panel.setCommandRow(3);
                }
            }
            case KeyEvent.VK_S ->{
                panel.setCommandRow(panel.getCommandRow() + 1);
                if(panel.getCommandRow() > 3){
                    panel.setCommandRow(1);
                }
            }
            case KeyEvent.VK_A -> {
                panel.setCommandColumn(panel.getCommandColumn() - 1);
                if (panel.getCommandColumn() < 1){
                    panel.setCommandColumn(5);
                }
                panel.setCommandRow(1);
            }
            case KeyEvent.VK_D -> {
                panel.setCommandColumn(panel.getCommandColumn() + 1);
                if (panel.getCommandColumn() > 5){
                    panel.setCommandColumn(1);
                }
                panel.setCommandRow(1);
            }
            case KeyEvent.VK_ENTER -> {
                switch (panel.getCommandColumn()){
                    case 1 ->{
                        switch (panel.getCommandRow()){
                            case 1 -> panel.setDimension(0,true);
                            case 2 -> panel.setDimension(1,true);
                            case 3 -> panel.setDimension(2,true);
                        }
                    }
                    case 2 ->{
                        switch (panel.getCommandRow()){
                            case 1 -> panel.setLife(0,true);
                            case 2 -> panel.setLife(1,true);
                            case 3 -> panel.setLife(2,true);
                        }
                    }
                    case 3 ->{
                        switch (panel.getCommandRow()){
                            case 1 -> panel.setEnemy(0,true);
                            case 2 -> panel.setEnemy(1,true);
                            case 3 -> panel.setEnemy(2,true);
                        }
                    }
                    case 4 ->{
                        switch (panel.getCommandRow()){
                            case 1 -> panel.setPower(0,true);
                            case 2 -> panel.setPower(1,true);
                            case 3 -> panel.setPower(2,true);
                        }
                    }
                    case 5 ->{
                        switch (panel.getCommandRow()){
                            case 1 -> panel.setRadius(0,true);
                            case 2 -> panel.setRadius(1,true);
                            case 3 -> panel.setRadius(2,true);
                        }
                    }
                }
            }
            case KeyEvent.VK_ESCAPE -> panel.setSaveData(true);
        }
    }
}
public class SettingPanel extends Panel {

    Boolean[] dimension = new Boolean[3];
    Boolean[] life = new Boolean[3];
    Boolean[] enemy = new Boolean[3];
    Boolean[] power = new Boolean[3];
    Boolean[] radius = new Boolean[3];

    Boolean saveData;

    int commandRow, commandColumn;

    Image background;

    Window window;

    public SettingPanel(int width, int height, Window window) {
        super(width, height);

        for (int i=0;i<3;i++){
            this.dimension[i]=false;
            this.life[i]=false;
            this.enemy[i]=false;
            this.power[i]=false;
            this.radius[i]=false;
        }

        this.saveData=false;
        this.commandRow=1;
        this.commandColumn=1;
        this.window=window;
        this.background=new ImageIcon("res/image/backgrounds/background-faded.jpg").getImage();

        SettingHandler handler=new SettingHandler(this);
        addKeyListener(handler);
    }

    public void setSaveData(Boolean saveData) {
        this.saveData = saveData;
    }
    public void setCommandRow(int commandRow) {
        this.commandRow = commandRow;
    }
    public void setCommandColumn(int commandColumn) {
        this.commandColumn = commandColumn;
    }
    public void setDimension(int index, Boolean value) {
        this.dimension[index]=value;
    }
    public void setLife(int index, Boolean value) {
        this.life[index]=value;
    }
    public void setEnemy(int index, Boolean value) {
        this.enemy[index]=value;
    }
    public void setPower(int index, Boolean value) {
        this.power[index]=value;
    }
    public void setRadius(int index, Boolean value) {
        this.radius[index]=value;
    }
    public int getCommandRow() {
        return commandRow;
    }
    public int getCommandColumn() {
        return commandColumn;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(background,0,0, width,height,null);

        g.setColor(new Color(175, 5, 5, 85));
        g.fillRoundRect(50, 130, 200, 450, 40, 40);
        g.fillRoundRect(300, 130, 200, 450, 40, 40);
        g.fillRoundRect(550, 130, 200, 450, 40, 40);
        g.fillRoundRect(800, 130, 200, 450, 40, 40);
        g.fillRoundRect(1050, 130, 200, 450, 40, 40);

        g.setColor(Color.WHITE);
        g.drawRoundRect(45, 135,200, 440, 40, 40);
        g.drawRoundRect(295, 135,200, 440, 40, 40);
        g.drawRoundRect(545, 135,200, 440, 40, 40);
        g.drawRoundRect(795, 135,200, 440, 40, 40);
        g.drawRoundRect(1045, 135,200, 440, 40, 40);

        //title
        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        g.drawString("Game Dimension", 78, 160);
        g.drawString("Life Number", 338, 160);
        g.drawString("Enemy Number", 583, 160);
        g.drawString("Bomb Power", 843, 160);
        g.drawString("Bomb Radius", 1088, 160);

        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));

        //game dimension
        {
            g.drawString("Columns  *  Rows", 88, 260);
            g.drawString("23   *   15", 80, 300);
            g.drawString("21   *   13", 80, 340);
            g.drawString("19   *   11", 80, 380);

            g.drawRect(200, 290,10, 10);
            g.drawRect(200, 330, 10, 10);
            g.drawRect(200, 370, 10, 10);

            if (dimension[0]){
                g.fillRect(200, 290,10, 10);
            }
            else {
                if (dimension[1]) {
                    g.fillRect(200, 330, 10, 10);
                }
                else if (dimension[2]){
                    g.fillRect(200, 370, 10, 10);

                }
            }
        }

        //life number
        {
            g.drawString("3 lives", 330, 300);
            g.drawString("5 lives", 330, 340);
            g.drawString("7 lives", 330, 380);

            g.drawRect(450, 290,10, 10);
            g.drawRect(450, 330, 10, 10);
            g.drawRect(450, 370, 10, 10);

            if (life[0]){
                g.fillRect(450, 290,10, 10);
            }
            else {
                if (life[1]) {
                    g.fillRect(450, 330, 10, 10);
                }
                else if (life[2]){
                    g.fillRect(450, 370, 10, 10);

                }
            }
        }

        //enemy number
        {
            g.drawString("Ghost - Skull - Fire Skull", 570, 260);
            g.drawString("3  -  2  -  1", 580, 300);
            g.drawString("4  -  3  -  2", 580, 340);
            g.drawString("5  -  4  -  3", 580, 380);

            g.drawRect(700, 290,10, 10);
            g.drawRect(700, 330, 10, 10);
            g.drawRect(700, 370, 10, 10);

            if (enemy[0]){
                g.fillRect(700, 290,10, 10);
            }
            else {
                if (enemy[1]) {
                    g.fillRect(700, 330, 10, 10);
                }
                else if (enemy[2]){
                    g.fillRect(700, 370, 10, 10);

                }
            }
        }

        //bomb power
        {
            g.drawString("1x", 830, 300);
            g.drawString("2x", 830, 340);
            g.drawString("3x", 830, 380);

            g.drawRect(950, 290,10, 10);
            g.drawRect(950, 330, 10, 10);
            g.drawRect(950, 370, 10, 10);

            if (power[0]){
                g.fillRect(950, 290,10, 10);
            }
            else {
                if (power[1]) {
                    g.fillRect(950, 330, 10, 10);
                }
                else if (power[2]){
                    g.fillRect(950, 370, 10, 10);

                }
            }
        }

        //bomb radius
        {
            g.drawString("1x", 1080, 300);
            g.drawString("2x", 1080, 340);
            g.drawString("3x", 1080, 380);

            g.drawRect(1200, 290,10, 10);
            g.drawRect(1200, 330, 10, 10);
            g.drawRect(1200, 370, 10, 10);

            if (radius[0]){
                g.fillRect(1200, 290,10, 10);
            }
            else {
                if (radius[1]) {
                    g.fillRect(1200, 330, 10, 10);
                }
                else if (radius[2]){
                    g.fillRect(1200, 370, 10, 10);

                }
            }
        }

        //cursor
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        switch (commandColumn){
            case 1 ->{
                switch (commandRow){
                    case 1 -> g.drawString(">",60, 300);
                    case 2 -> g.drawString(">",60, 340);
                    case 3 -> g.drawString(">",60, 380);
                }
            }
            case 2 ->{
                switch (commandRow){
                    case 1 -> g.drawString(">",310, 300);
                    case 2 -> g.drawString(">",310, 340);
                    case 3 -> g.drawString(">",310, 380);
                }
            }
            case 3 ->{
                switch (commandRow){
                    case 1 -> g.drawString(">",560, 300);
                    case 2 -> g.drawString(">",560, 340);
                    case 3 -> g.drawString(">",560, 380);
                }
            }
            case 4 ->{
                switch (commandRow){
                    case 1 -> g.drawString(">",810, 300);
                    case 2 -> g.drawString(">",810, 340);
                    case 3 -> g.drawString(">",810, 380);
                }
            }
            case 5 ->{
                switch (commandRow){
                    case 1 -> g.drawString(">",1060, 300);
                    case 2 -> g.drawString(">",1060, 340);
                    case 3 -> g.drawString(">",1060, 380);
                }
            }
        }

        if (!saveData){
            repaint();
        }
        else{
            //dimension
            if (dimension[0]){
                window.setRows(23);
                window.setColumns(15);
            }
            else {
                if (dimension[1]) {
                    window.setRows(21);
                    window.setColumns(13);
                }
                else if (dimension[2]){
                    window.setRows(19);
                    window.setColumns(11);
                }
            }

            //life
            if (life[0]){
                window.setLifeNumber(3);
            }
            else {
                if (life[1]) {
                    window.setLifeNumber(5);
                }
                else if (life[2]){
                    window.setLifeNumber(7);
                }
            }

            //enemy
            if (enemy[0]){
                window.setGhostNumber(3);
                window.setSkullNumber(2);
                window.setFireSkullNumber(1);
            }
            else {
                if (enemy[1]) {
                    window.setGhostNumber(4);
                    window.setSkullNumber(3);
                    window.setFireSkullNumber(2);
                }
                else if (enemy[2]){
                    window.setGhostNumber(5);
                    window.setSkullNumber(4);
                    window.setFireSkullNumber(3);
                }
            }

            //power
            if (power[0]){
                window.setBombPower(1);
            }
            else {
                if (power[1]) {
                    window.setBombPower(2);
                }
                else if (power[2]){
                    window.setBombPower(3);
                }
            }

            //radius
            if (radius[0]){
                window.setBombRadius(1);
            }
            else {
                if (radius[1]) {
                    window.setBombRadius(2);
                }
                else if (radius[2]){
                    window.setBombRadius(3);
                }
            }

            window.avoidDisplaying();
            window.setPanel("StartPanel");
            window.display(window.setFrame());
        }
    }
}



































