import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RequestHandler extends Thread implements Runnable {
    User user;
    Socket socket;
    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;

    public void setUser(User user) {
        this.user = user;
    }

    public Socket getSocket() {
        return socket;
    }

    public RequestHandler(Socket socket) throws IOException{
        this.socket=socket;
        this.inputStream= new ObjectInputStream(getSocket().getInputStream());
        this.outputStream = new ObjectOutputStream(getSocket().getOutputStream());
    }
    public void messageHandler() throws IOException, ClassNotFoundException {
        String message= (String) inputStream.readObject();
        User user1 = (User) inputStream.readObject();
        switch (message){
            case "new user" ->{
                User user2=Server.userValidity(user1);
                if (user2==null){
                    outputStream.writeObject("repetitive info");
                    outputStream.flush();
                }
                else {
                    setUser(user2);
                    outputStream.writeObject("user saved");
                    outputStream.writeObject(user2);
                    outputStream.flush();
                }
            }
            case "Change Profile Picture", "Edit Info" ->{
                setUser(user1);
                outputStream.writeObject(user1);
                outputStream.flush();
            }
            case "New Mail" ->{
                Message text= (Message) inputStream.readObject();
                for (String receiver: text.getReceivers()){
                    User user2=Server.findUser(receiver);
                    if (user2!=null){
                        user2.setReceivedMessages(text);
                    }
                }
                outputStream.writeObject(user1);
                outputStream.flush();
            }
            case "Sign In" ->{
                outputStream.writeObject("ok");
                outputStream.flush();

                String userInfo=(String) inputStream.readObject();
                String username= userInfo.split("/")[0];
                String pass=userInfo.split("/")[1];
                User user2=Server.signInValidity(username,pass);

                outputStream.writeObject(user2);
                outputStream.flush();
            }
            case "mark as read" ->{
                Message text=(Message) inputStream.readObject();
                User user2=Server.findUser(text.getSender());
                user2.getSentMessages().remove(text);
                user1.getReceivedMessages().remove(text);
                text.setRead(true);
                user2.setSentMessages(text);
                user1.setReceivedMessages(text);
                outputStream.writeObject(user1);
                outputStream.writeObject(text);
                outputStream.flush();
            }
            case "delete" ->{
                Message text= (Message) inputStream.readObject();
                User user2=Server.findUser(text.getSender());
                user2.getSentMessages().remove(text);
                user1.getReceivedMessages().remove(text);
                outputStream.writeObject(user1);
                outputStream.flush();
            }
        }
    }

    @Override
    public void run() {

        try {
        while (socket.isBound()){
            messageHandler();
        }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}