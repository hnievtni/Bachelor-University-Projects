import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Controller{

    Socket socket;
    ObjectOutputStream outputStream;
    ObjectInputStream inputStream;

    Model model;

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }
    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    public Controller() throws IOException, ClassNotFoundException {
        this.socket=new Socket("localhost",9437);
        this.outputStream=new ObjectOutputStream(socket.getOutputStream());
        this.inputStream=new ObjectInputStream(socket.getInputStream());
        this.model=new Model(this);
    }

    public void messageHandler(User user,String message,Message text,String username,String pass) throws IOException, ClassNotFoundException {
        switch (message){
            case "new user" ->{
                outputStream.writeObject("new user");
                outputStream.writeObject(user);
                outputStream.flush();
                model.newUser();
            }
            case "Change Profile Picture" ->{
                outputStream.writeObject("Change Profile Picture");
                outputStream.writeObject(user);
                outputStream.flush();
                model.profile();
            }
            case "Edit Info" ->{
                outputStream.writeObject("Edit Info");
                outputStream.writeObject(user);
                outputStream.flush();
                model.editInfo();
            }
            case "New Mail" ->{
                outputStream.writeObject("New Mail");
                outputStream.writeObject(user);
                outputStream.writeObject(text);
                outputStream.flush();
                model.newMail();
            }
            case "Sign In" ->{
                outputStream.writeObject("Sign In");
                outputStream.writeObject(user);
                outputStream.flush();
                String userInfo=username+"/"+pass;
                model.signIn(userInfo);
            }
            case "mark as read" ->{
                outputStream.writeObject("mark as read");
                outputStream.writeObject(user);
                outputStream.writeObject(text);
                outputStream.flush();
                model.readMail();
            }
            case "delete" ->{
                outputStream.writeObject("delete");
                outputStream.writeObject(user);
                outputStream.writeObject(text);
                outputStream.flush();
                model.userPage();
            }
        }
    }

}
