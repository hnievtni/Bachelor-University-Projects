package core.panels.children;

import core.panels.Panel;
import core.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class Handler extends KeyAdapter {

    StartPanel panel;

    public Handler(StartPanel panel) {
        this.panel=panel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        int keyCode=e.getKeyCode();

        switch (keyCode){
            case KeyEvent.VK_W ->{
                panel.setCommandNumber(panel.getCommandNumber()-1);

                if (panel.getCommandNumber() < 1){
                    panel.setCommandNumber(3);
                }
            }
            case KeyEvent.VK_S ->{
                panel.setCommandNumber(panel.getCommandNumber()+1);

                if (panel.getCommandNumber() > 3) {
                    panel.setCommandNumber(1);
                }
            }
            case KeyEvent.VK_ENTER -> {
                switch (panel.getCommandNumber()){
                    case 1 ->{
                        panel.getWindow().avoidDisplaying();
                        panel.getWindow().setPanel("GamePanel");
                        panel.getWindow().display(panel.getWindow().setFrame());
                    }
                    case 2 ->{
                        panel.getWindow().avoidDisplaying();
                        panel.getWindow().setPanel("SettingPanel");
                        panel.getWindow().display(panel.getWindow().setFrame());
                    }
                    case 3 -> panel.getWindow().exit();
                }
            }
        }
    }
}
public class StartPanel extends Panel {

    Image background, skull;

    int commandNumber;

    Window window;

    public StartPanel(int width, int height, Window window) {
        super(width, height);

        this.commandNumber=1;
        this.window=window;

        setImage();

        Handler handler=new Handler(this);
        addKeyListener(handler);
    }

    public void setImage(){
        this.background=new ImageIcon("res/image/backgrounds/background-faded.jpg").getImage();
        this.skull=new ImageIcon("res/image/ui/skull.png").getImage();
    }
    public void setCommandNumber(int commandNumber) {
        this.commandNumber = commandNumber;
    }
    public int getCommandNumber() {
        return commandNumber;
    }
    public Window getWindow() {
        return window;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(background,0,0, width,height,null);
        g.drawImage(skull,235,0, 800,300,null);

        g.setFont(new Font(Font.SERIF, Font.BOLD, 50));

        //shadows
        g.setColor(Color.DARK_GRAY);

        g.setFont(new Font(Font.SERIF, Font.BOLD, 50));
        g.drawString("BOMBER FRIENDS", 411,324);

        g.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        g.drawString("SPOOKY  EDITION", 552, 357);

        g.setFont(new Font(Font.SERIF, Font.PLAIN, 25));
        g.drawString("START  NEW  GAME", 520,512);
        g.drawString("SETTING", 583,552);
        g.drawString("EXIT", 600,592);

        g.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        switch (commandNumber){
            case 1 -> g.drawString(">", 485,512);
            case 2 -> g.drawString(">", 548,552);
            case 3 -> g.drawString(">", 565,592);
        }

        //texts
        g.setColor(Color.WHITE);

        g.setFont(new Font(Font.SERIF, Font.BOLD, 50));
        g.drawString("BOMBER FRIENDS", 407,320);

        g.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        g.drawString("SPOOKY  EDITION", 550,355);

        g.setFont(new Font(Font.SERIF, Font.PLAIN, 25));
        g.drawString("START  NEW  GAME", 518,510);
        g.drawString("SETTING", 581,550);
        g.drawString("EXIT", 598,590);

        g.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        switch (commandNumber){
            case 1 -> g.drawString(">", 483,510);
            case 2 -> g.drawString(">", 546,550);
            case 3 -> g.drawString(">", 563,590);
        }

        repaint();
    }
}