import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javax.swing.*;
import java.io.*;
import java.lang.reflect.Type;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    static ArrayList<User> users=new ArrayList<>();
    static Gson gson=new Gson();
    static String usersList=gson.toJson(users);

    public static void setUsers(User user) {
        Server.users.add(user);
        usersList=gson.toJson(users);
        File dataBase= new File("Data Base.txt");
        try {
            FileWriter writer = new FileWriter(dataBase,true);
            writer.write(usersList);
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERROR!");
        }
    }

    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(9437);

        while (true) {
            Socket socket = serverSocket.accept();
            Thread thread = new RequestHandler(socket);
            thread.start();
        }

    }
    public static User findUser(String name){
        User user1=null;

        Type usersType=new TypeToken<ArrayList<User>>(){}.getType();
        ArrayList<User> users1=gson.fromJson(usersList,usersType);

        for (User user : users1) {
            if (user.getUsername().equals(name)) {
                user1 = user;
                break;
            }
        }
        return user1;
    }
    public static User userValidity(User user){
        if (usersList.contains(user.getUsername()))
            return null;
        else {
            setUsers(user);
            return user;
        }
    }
    public static User signInValidity(String username,String pass){
        User user1=null;

        Type usersType=new TypeToken<ArrayList<User>>(){}.getType();
        ArrayList<User> users1=gson.fromJson(usersList,usersType);

        for (User user : users1) {
            if (user.getUsername().equals(username)&&user.getPass().equals(pass)) {
                    user1=user;
            }
        }

        return user1;
    }
}