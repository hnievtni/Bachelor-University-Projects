import javax.swing.*;
import java.io.IOException;

public class Model {
    Controller controller;
    View view;
    public Model(Controller controller) {
        this.controller=controller;
    }
    public void newUser() throws IOException, ClassNotFoundException {
        String response= (String) controller.getInputStream().readObject();
        switch (response){
            case "repetitive info" ->{
                JOptionPane.showMessageDialog(null,"There is already an account with this information.Please try again.");
                Client.signUp();
            }
            case "user saved" ->{
                JOptionPane.showMessageDialog(null,"Your account has been successfully registered");
                User user= (User) controller.getInputStream().readObject();
                this.view=new View(user,controller);
            }
        }
    }
    public void profile() throws IOException, ClassNotFoundException {
        User user1=(User) controller.getInputStream().readObject();
        view.setUser(user1);
        view.userInfoFrame();
    }
    public void editInfo() throws IOException, ClassNotFoundException {
        User user=(User) controller.getInputStream().readObject();
        view.setUser(user);
        view.userInfoFrame();
    }
    public void newMail() throws IOException, ClassNotFoundException {
        User user=(User) controller.getInputStream().readObject();

        view.setUser(user);
        view.setUserFrame();
    }
    public void signIn(String info) throws IOException, ClassNotFoundException {
        String str=(String) controller.getInputStream().readObject();
        if (str.equals("ok")){
            controller.getOutputStream().writeObject(info);
            controller.getOutputStream().flush();
            User user=(User) controller.getInputStream().readObject();

            if (user==null) {
                JOptionPane.showMessageDialog(null,"invalid username or password; Please try again.");
                Client.signIn();
            }
            else{
                view.setUser(user);
                view.setUserFrame();
            }
        }
    }
    public void readMail() throws IOException, ClassNotFoundException {
        User user=(User) controller.getInputStream().readObject();
        Message text=(Message) controller.getInputStream().readObject();
        view.setUser(user);
        view.displayMail(text,"inbox");
    }
    public void userPage() throws IOException, ClassNotFoundException {
        User user=(User) controller.getInputStream().readObject();
        view.setUser(user);
        view.setUserFrame();
    }
}
