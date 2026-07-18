package UserPackage;

import Program.Main;
import Program.User;
import Structure.HashMap;

public class Direct{
    User user;
    HashMap groups;
    HashMap dms;

    public Direct(User user) {
        this.user = user;
        this.groups = new HashMap(10);
        this.dms = new HashMap(20);
    }

    public void chatHandler() {
        Main.scanner.nextLine();
        System.out.println("1. Direct Messages\n2. Groups\n3. Previous Menu");
        int input = Main.scanner.nextInt();
        switch (input) {
            case 1 -> {
                Main.scanner.nextLine();
                System.out.println("Username: ");
                String username = Main.scanner.nextLine();
                if (username.contains("@")) {
                    username = username.replace("@", "");
                }
                username = username.toLowerCase();

                if (!Main.userValidity(username))
                    System.out.println("There is no such user with this username. Please try again.");
                else {
                    if (dms.getKeys().contains(username)) { //if we already chat
                        DM dm = (DM) dms.getValue(username);
                        dm.chat();
                    }
                    else {
                        User friend = Main.getUser(username);
                        DM newDm = new DM(user, friend);
                        this.dms.insert(username, newDm);
                        friend.getDirect().getDms().insert(user.getProfile().getUsername(), newDm);
                        newDm.chat();
                    }
                }
                chatHandler();
            }
            case 2 -> {
                Main.scanner.nextLine();
                System.out.println("Group Name: ");
                String name = Main.scanner.nextLine();
                name = name.toLowerCase();
                if (groups.getKeys().contains(name)) {
                    Group group = (Group) groups.getValue(name);
                    group.chat(user);
                }
                else {
                    System.out.println("Are you sure you want to create a group with this name?\n1. Yes\n2. No");
                    int input1 = Main.scanner.nextInt();
                    if (input1 == 1) {
                        Group newGroup = createGroup(name);
                        this.groups.insert(name, newGroup);
                        for (int i = 0; i < newGroup.getMembers().getLast() + 1; i++) {
                            User member = (User) newGroup.getMembers().get(i);
                            member.getDirect().getGroups().insert(name, newGroup);
                        }
                        Main.scanner.nextLine();
                        newGroup.chat(user);
                    }
                }
                chatHandler();
            }
            case 3 -> user.getHomePage().directDisplay();
            default -> {
                System.out.println("Wrong input! Please try again.");
                chatHandler();
            }
        }
    }
    public void chatHistoryHandler() {
        System.out.println("1. Direct Messages\n2. Groups\n3. Previous Menu");
        int input = Main.scanner.nextInt();
        switch (input) {
            case 1 -> {
                Main.scanner.nextLine();
                System.out.println("Username: ");
                String username = Main.scanner.nextLine();
                if (username.contains("@")) {
                    username = username.replace("@", "");
                }
                username = username.toLowerCase();

                if (!Main.userValidity(username))
                    System.out.println("There is no such user with this username. Please try again.");
                else {
                    if (dms.getKeys().contains(username)) { //if we already chat
                        DM dm = (DM) dms.getValue(username);
                        dm.displayChatHistory();
                    }
                    else
                        System.out.println("You haven't chat with this user.");
                }
                chatHistoryHandler();
            }
            case 2 -> {
                Main.scanner.nextLine();
                System.out.println("Group Name: ");
                String name = Main.scanner.nextLine();
                name = name.toLowerCase();
                if (groups.getKeys().contains(name)) {
                    Group group = (Group) groups.getValue(name);
                    group.displayChatHistory();
                }
                else
                    System.out.println("You have no such group with this name.");
                chatHistoryHandler();
            }
            case 3 -> user.getHomePage().directDisplay();
            default -> {
                System.out.println("Wrong input! Please try again.");
                chatHistoryHandler();
            }
        }
    }
    public Group createGroup(String name) {
        Main.scanner.nextLine();
        Group newGroup = new Group(user, name);
        while (true) {
            System.out.println("Do you want to add a member now?\n1. Yes\n2. No");
            int input2 = Main.scanner.nextInt();
            if (input2 == 1) {
                Main.scanner.nextLine();
                System.out.println("Username: ");
                String username = Main.scanner.nextLine();
                if (!Main.userValidity(username)) {
                    System.out.println("There is no such user with this username. Please try again.");
                    Main.scanner.nextLine();
                    chatHandler();
                }
                else
                    newGroup.addMember(Main.getUser(username));
            }
            else
                break;
        }
        return newGroup;
    }

    public void addDM(String username) {
        username = username.toLowerCase();
        if (Main.userValidity(username)) {
            User user = Main.getUser(username);
            DM dm = new DM(user, user);
            this.dms.insert(username, dm);
            user.getDirect().getDms().insert(user.getProfile().getUsername(), dm);
        }
        else
            System.out.println("There is no such user with this username. Please try again.");
    }
    public void deleteDM(String username) {
        username = username.toLowerCase();
        if (Main.userValidity(username)) {
            this.dms.remove(dms.getValue(username));
        }
        else
            System.out.println("There is no such user with this username. Please try again.");
    }

    public void addGroup(String name) {
        name = name.toLowerCase();
        if (groups.contains(name))
            System.out.println("You have already a group with this name. Please try again.");
        else {
            Group group = new Group(user, name);
            this.groups.insert(name, group);
        }
    }
    public void addGroupMember(String name , String username) {
        name = name.toLowerCase();
        username = username.toLowerCase();
        if (groups.contains(name)) {
            Group group = (Group) groups.getValue(name);
            if (Main.userValidity(username)) {
                User user = Main.getUser(username);
                group.addMember(user);
                user.getDirect().getGroups().insert(name, group);
            }
            else
                System.out.println("There is no such user with this username. Please try again.");
        }
        else
            System.out.println("There is no group with this name. Please try again.");
    }
    public void deleteGroup(String name) {
        name = name.toLowerCase();
        if (groups.contains(name))
            this.groups.remove(groups.getValue(name));
        else
            System.out.println("There is no group with this name. Please try again.");
    }

    public User getUser() {
        return user;
    }
    public HashMap getDms() {
        return dms;
    }
    public HashMap getGroups() {
        return groups;
    }
}
