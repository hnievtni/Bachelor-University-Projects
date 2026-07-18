package core.panels;

import javax.swing.*;
public abstract class Panel extends JPanel {
    public int width, height;

    public Panel(int width, int height) {

        this.width=width;
        this.height=height;

        setBounds(0,0,1200,550);
        setOpaque(false);
        setFocusable(true);
    }
}
























