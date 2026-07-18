package Program;

import Structure.ArrayList;
import UserPackage.*;

public class User {
    Profile profile;
    HomePage homePage;
    Direct direct;

    ArrayList notifications; //LIFO
    ArrayList activity;

    int popularity;

    public User(String username, String bio, String cityName) {
        this.profile = new  Profile(this, username, bio, cityName);
        this.homePage = new HomePage(this);
        this.direct = new Direct(this);
        this.notifications = new ArrayList(10);
        this.activity = new ArrayList(20);
        this.popularity = 0;
    }

    public void follow(String username) {
        if (Main.userValidity(username)) {
            User user = Main.getUser(username);
            if (!profile.getFollowings().contains(user)) { //if we already don't follow them
                this.profile.getFollowings().add(user);
                this.homePage.addSuggestion(user); //add suggestions based on user's followings
                user.getProfile().getFollowers().add(this);
                for (int i = 0; i < user.getHomePage().getPosts().getLast() + 1; i++) {
                    this.homePage.addFeed((Post) user.getHomePage().getPosts().get(i));
                }

                if (homePage.getSuggestions().contains(user))
                    this.homePage.removeSuggestion(user);

                System.out.println("You have successfully followed @" + username);
                this.activity.add("Followed " + "@" + username + ".");
                user.addNotification("@" + profile.getUsername() + " has followed you!");
            }
        }
        else
            System.out.println("There is no such account with this username. Please try again.");
    }
    public void unfollow(String username) {
        if (Main.userValidity(username)) {
            User user = Main.getUser(username);
            if (profile.getFollowings().contains(user)) { //if we follow them
                this.profile.getFollowings().remove(user);
                user.getProfile().getFollowers().remove(this);

                System.out.println("You have successfully unfollowed @" + username);
                this.activity.add("Unfollowed " + "@" + username + ".");
                user.addNotification("@" + profile.getUsername() + " has unfollowed you!");
            }
        }
        else
            System.out.println("There is no such account with this username! Please try again.");
    }

    public void addNotification(String notif) {
        this.notifications.add(notif);
    }
    public void displayNotifications() {
        for (int i = notifications.getLast(); i >= 0; i--) {
            System.out.println(notifications.get(i));
        }
        this.notifications = new ArrayList(10);
    }

    public void post() {
        Main.scanner.nextLine();
        System.out.println("Title: ");
        String title = Main.scanner.nextLine();
        System.out.println("Text: ");
        String text = Main.scanner.nextLine();

        Post post = new Post(title, text, this);
        this.homePage.getPosts().add(post);
        addHashtags(post);
        this.activity.add("Posted a new post with title: " + title + ".");

        for (int i = 0; i < profile.getFollowers().getLast() + 1; i++) {
            User follower = (User) profile.getFollowers().get(i);
            follower.addNotification("@" + profile.getUsername() + "posted a new post. Check it out!");
            follower.getHomePage().addFeed(post);
        }
    }

    public void addHashtags(Post post) {
        System.out.println("Do you want to add hashtags?\n1. Yes\n2. No");
        int input = Main.scanner.nextInt();
        if (input == 1) {
            Main.scanner.nextLine();
            System.out.println("Hashtag: ");
            String hashtag = Main.scanner.nextLine();
            post.insertHashtags(hashtag);
            addHashtags(post);
        }
    }

    public void signOut() {
        System.out.println("Are you sure you want to sign out of your account?\n1. Yes\n2. No");
        int input = Main.scanner.nextInt();
        switch (input) {
            case 1 -> Main.menu();
            case 2 -> homePage.display();
            default -> {
                System.out.println("Wrong input! Please try again.");
                signOut();
            }
        }
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public Profile getProfile() {
        return profile;
    }
    public HomePage getHomePage() {
        return homePage;
    }
    public Direct getDirect() {
        return direct;
    }
    public ArrayList getNotifications() {
        return notifications;
    }
    public ArrayList getActivity() {
        return activity;
    }
    public int getPopularity() {
        return popularity;
    }
}
