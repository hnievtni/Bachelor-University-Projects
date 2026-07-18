package UserPackage;

import Program.Main;
import Program.User;
import Structure.ArrayList;

public class Profile {
    User user;
    String username;
    String bio;

    ArrayList followers;
    ArrayList followings;

    City city;

    public Profile(User user, String username, String bio, String cityName) {
        this.user = user;
        this.username = username;
        this.bio = bio;
        this.city = (City) Main.WGraph.getCities().getValue(cityName);
        this.followers = new ArrayList(50);
        this.followings = new ArrayList(50);
    }

    public void display() {
        System.out.println("Username: @" + username);
        System.out.println("Biography: "+ bio);
        System.out.println("City: " + city.getName());
        System.out.println("Followers Count: " + (followers.getLast() + 1));
        System.out.println("Followings Count: " + (followings.getLast() + 1));

        System.out.println("1. Followers\n2. Followings\n3. Follow/Unfollow\n4. Previous Menu");
        int input = Main.scanner.nextInt();
        switch (input) {
            case 1 -> {
                displayFollowers();
                display();
            }
            case 2 -> {
                displayFollowings();
                display();
            }
            case 3 -> follow_Unfollow();
            case 4 -> user.getHomePage().homeDisplay();
            default -> {
                System.out.println("Wrong input! Please try again.");
                display();
            }
        }
    }

    public void displayFollowers() {
        System.out.println("Followers Count: " + (followers.getLast() + 1) + "\nFollowers List: ");
        for (int i = 0; i < followers.getLast() + 1; i++) {
            System.out.println("@" + ((User) followers.get(i)).getProfile().getUsername());
        }
    }
    public void displayFollowings() {
        System.out.println("Followings Count: " + (followings.getLast() + 1) + "\nFollowings List: ");
        for (int i = 0; i < followings.getLast() + 1; i++) {
            System.out.println("@" + ((User) followings.get(i)).getProfile().getUsername());
        }
    }

    public void follow_Unfollow() {
        System.out.println("1. Follow\n2. Unfollow\n3. Previous Menu");
        int input1 = Main.scanner.nextInt();
        switch (input1) {
            case 1 -> {
                Main.scanner.nextLine();
                System.out.println("Username: ");
                String username = Main.scanner.nextLine();
                this.user.follow(username);
                follow_Unfollow();
            }
            case 2 -> {
                Main.scanner.nextLine();
                System.out.println("Username: ");
                String username = Main.scanner.nextLine();
                this.user.unfollow(username);
                follow_Unfollow();
            }
            case 3 -> display();
            default -> {
                System.out.println("Wrong input! Please try again.");
                follow_Unfollow();
            }
        }
    }

    public User getUser() {
        return user;
    }
    public String getUsername() {
        return username;
    }
    public String getBio() {
        return bio;
    }
    public ArrayList getFollowers() {
        return followers;
    }
    public ArrayList getFollowings() {
        return followings;
    }
    public Object getCity() {
        return city;
    }
}
