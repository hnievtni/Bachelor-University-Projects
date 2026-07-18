package UserPackage;

import Program.Main;
import Program.User;
import Structure.ArrayList;

public class Group {
    String name;

    ArrayList members;
    ArrayList chatHistory; //FIFO


    public Group(User user, String name) {
        this.name = name;
        this.members = new ArrayList(10);
        this.chatHistory = new ArrayList(100);
    }

    public void addMember(User user) {
        if (!members.contains(user))
            this.members.add(user);
    }
    public void removeMember(User user) {
        if (members.contains(user))
            this.members.remove(user);
    }

    public void chat(User user) {
        System.out.println("Type...");
        String text = Main.scanner.nextLine();
        this.chatHistory.add("@" + user.getProfile().getUsername() + ": " + text);

        for (int i = 0; i < members.getLast() + 1; i++) {
            ((User) members.get(i)).addNotification("@" + user.getProfile().username + " sent a new massage in " + name + " group.");
        }

        System.out.println("Do you want to send another message?\n1. Yes\n2. No");
        int input = Main.scanner.nextInt();
        if (input == 1) {
            Main.scanner.nextLine();
            chat(user);
        }
    }
    public void displayChatHistory() {
        for (int i = 0; i < chatHistory.getLast() + 1; i++) {
            System.out.println((String) chatHistory.get(i));
        }
    }

    public String getName() {
        return name;
    }
    public ArrayList getMembers() {
        return members;
    }
    public ArrayList getChatHistory() {
        return chatHistory;
    }
}
