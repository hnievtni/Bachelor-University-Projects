package UserPackage;

import Program.*;
import Structure.ArrayList;

public class DM{
    User user;
    User friend; //user that you are talking to

    ArrayList chatHistory; //FIFO

    public DM(User user, User friend) {
        this.user = user;
        this.friend = friend;
        this.chatHistory = new ArrayList(100);
    }

    public void chat() {
        System.out.println("Type...");
        String text = Main.scanner.nextLine();
        this.chatHistory.add("@" + user.getProfile().getUsername() + ": " + text);

        this.friend.addNotification("You have a new massage from @" + user.getProfile().username);

        System.out.println("Do you want to send another message?\n1. Yes\n2. No");
        int input = Main.scanner.nextInt();
        if (input == 1) {
            Main.scanner.nextLine();
            chat();
        }
    }
    public void displayChatHistory() {
        for (int i = 0; i < chatHistory.getLast() + 1; i++) {
            System.out.println((String) chatHistory.get(i));
        }
    }

    public User getUser() {
        return user;
    }
    public User getFriend() {
        return friend;
    }
    public ArrayList getChatHistory() {
        return chatHistory;
    }
}
