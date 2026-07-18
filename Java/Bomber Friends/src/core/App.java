package core;

public class App {
    public static void main(String[] args) {
        newGame();
    }
    public static void newGame(){
        //new game window and initializations
        Window window=new Window();
        window.setPanel("StartPanel");
        window.display(window.setFrame());
    }
}
