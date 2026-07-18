import java.io.Serializable;
import java.util.ArrayList;

class Message implements Serializable{
    String[] receivers; //usernames
    String sender; //username
    String subject;
    String email;
    String dateAndTime;
    Boolean read=false;

    public Message(String[] receiver, String sender, String subject, String email, String dateAndTime) {
        this.receivers = receiver;
        this.sender = sender;
        this.email = email;
        this.subject=subject;
        this.dateAndTime = dateAndTime;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public String[] getReceivers() {
        return receivers;
    }
    public String getSender() {
        return sender;
    }
    public String getSubject() {
        return subject;
    }
    public String getEmail() {
        return email;
    }
    public String getDateAndTime() {
        return dateAndTime;
    }
    public Boolean getRead() {
        return read;
    }

}

public class User implements Serializable {
    String name;
    String username;
    String email;
    String pass;
    String position;
    String profilePath;

    ArrayList<Message> receivedMessages=new ArrayList<>();
    ArrayList<Message> sentMessages=new ArrayList<>();

    public User(String name, String username, String email, String pass, String position, String profilePath){
        this.name=name;
        this.username = username;
        this.email = email;
        this.pass = pass;
        this.position = position;
        this.profilePath = profilePath;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPass(String pass) {
        this.pass = pass;
    }
    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }
    public void setSentMessages(Message sentMessage) {
        this.sentMessages.add(sentMessage);
    }
    public void setReceivedMessages(Message receivedMessages) {
        this.receivedMessages.add(receivedMessages);
    }

    public String getPass() {
        return pass;
    }
    public String getName() {
        if (this.position.equals("Doctor"))
            return "Dr."+name;
        else
            return "Pt."+name;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public String getProfilePath() {
        return profilePath;
    }
    public ArrayList<Message> getReceivedMessages() {
        return receivedMessages;
    }
    public ArrayList<Message> getSentMessages() {
        return sentMessages;
    }

}
