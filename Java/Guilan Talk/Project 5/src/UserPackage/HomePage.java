package UserPackage;
import Program.Main;
import Program.User;
import Structure.ArrayList;

import java.util.Objects;


public class HomePage {
    User user;
    ArrayList suggestions;
    ArrayList usersNearby;
    ArrayList posts;
    ArrayList feed;

    public HomePage(User user) {
        this.user = user;
        this.suggestions = new ArrayList(50);
        this.usersNearby = new ArrayList(50);
        this.posts = new ArrayList(50);
        this.feed = new ArrayList(50);
    }

    public void display() {
        System.out.println("1. Feed\n2. Home\n3. Direct\n4. Suggestions\n5. People Nearby\n6. Search User\n7. Search Hashtag\n8. Sign Out");
        int input = Main.scanner.nextInt();
        switch (input) {
            case 1 -> {
                feedDisplay();
                display();
            }
            case 2 -> {
                homeDisplay();
                display();
            }
            case 3 -> directDisplay();
            case 4 -> {
                suggestionDisplay();
                display();
            }
            case 5 -> {
                peopleNearby();
                display();
            }
            case 6 -> {
                searchUser();
                display();
            }
            case 7 -> {
                searchHashtag();
                display();
            }
            case 8 -> user.signOut();
            default -> {
                System.out.println("Wrong input! Please try again.");
                display();
            }
        }
    }

    public void feedDisplay() {
        for (int i = 0; i < feed.getLast() + 1; i++) {
            Post post = (Post) feed.get(i);
            post.displayPost();
            Main.scanner.nextLine();
            System.out.println("Do you want to like the post?\n1. Yes\n2. No");
            int input = Main.scanner.nextInt();
            if (input == 1 && !post.getLikers().contains(user)) {
                post.addLiker(user);
                user.getActivity().add("Liked a post by @" + post.getAuthor() + " with title: " + post.getTitle() + ".");
            }
            else if (post.getLikers().contains(user)) {
                System.out.println("Ypu have already liked this post!");
            }
        }
    }
    public void homeDisplay() {
        Main.scanner.nextLine();
        System.out.println("1. Profile\n2. Posts\n3. Notifications\n4. New Post\n5. Previous Menu");
        int input = Main.scanner.nextInt();
        switch (input) {
            case 1 -> user.getProfile().display();
            case 2 -> {
                postsDisplay();
                homeDisplay();
            }
            case 3 -> {
                this.user.displayNotifications();
                homeDisplay();
            }
            case 4 -> {
                this.user.post();
                homeDisplay();
            }
            case 5 -> display();
            default -> {
                System.out.println("Wrong input! Please try again.");
                homeDisplay();
            }
        }
    }
    public void directDisplay() {
        Direct direct = user.getDirect();

        System.out.println("Direct Massages: ");
        for (int i = 0; i < direct.getDms().getKeys().getLast() + 1; i++)
            System.out.println("@" + direct.getDms().getKeys().get(i));
        System.out.println("Groups: ");
        for (int i = 0; i < direct.getGroups().getKeys().getLast() + 1; i++)
            System.out.println(direct.getGroups().getKeys().get(i));

        Main.scanner.nextLine();
        System.out.println("1. Chat\n2. Display chat history\n3. Previous Menu");
        int input = Main.scanner.nextInt();
        switch (input) {
            case 1 -> direct.chatHandler();
            case 2 -> direct.chatHistoryHandler();
            case 3 -> display();
            default -> {
                System.out.println("Wrong input! Please try again.");
                directDisplay();
            }
        }
    }
    public void postsDisplay() {
        for (int i = 0; i < posts.getLast() + 1; i++) {
            ((Post) posts.get(i)).displayPost();
        }
    }
    public void suggestionDisplay() {
        for (int i = 0; i < suggestions.getLast() + 1; i++)
            System.out.println("@" + ((User) suggestions.get(i)).getProfile().getUsername());
    }
    public void peopleNearby() {
        setUsersNearby();
        for (int i = 0; i < usersNearby.getLast() + 1; i++) {
            if (!Objects.equals(usersNearby.get(i), user)) { //except us
                int distance = Main.WGraph.getEdge(user.getProfile().getCity(), ((User) usersNearby.get(i)).getProfile().getCity());
                System.out.println("@" + (((User) usersNearby.get(i))).getProfile().getUsername() + " - " + distance + "KM away");
            }
        }
    }
    public void searchUser() {
        Main.scanner.nextLine();
        System.out.println("Search user...");
        String username = Main.scanner.nextLine();
        if (username.contains("@")) {
            username = username.replace("@", "");
        }
        username = username.toLowerCase();

        if (!Main.userValidity(username)) {
            System.out.println("There is no such user with this username. Please try again.");
            display();
        }
        else {
            User user = Main.getUser(username);
            System.out.println("Username: @" + user.getProfile().getUsername());
            System.out.println("Biography: "+ user.getProfile().getBio());
            System.out.println("City: " + ((City) user.getProfile().getCity()).getName());
            System.out.println("Followers Count: " + (user.getProfile().getFollowers().getLast() + 1));
            System.out.println("Followings Count: " + (user.getProfile().getFollowings().getLast() + 1));
        }
    }
    public void searchHashtag() {
        Main.scanner.nextLine();
        System.out.println("Search hashtag...");
        String hashtag = Main.scanner.nextLine();
        if (!hashtag.contains("#")) {
            hashtag = "#" + hashtag;
        }
        hashtag = hashtag.toLowerCase();

        for (int i = 0; i < Main.users.getValues().getLast() + 1; i++) {
            User user = (User) Main.users.getValues().get(i);
            for (int j = 0; j < user.getHomePage().getPosts().getLast() + 1; j++) {
                Post post = (Post) user.getHomePage().getPosts().get(j);
                if (post.getHashtags().contains(hashtag))
                    post.displayPost();
            }
        }
    }

    public void addFeed(Post post) {
        this.feed.add(post);
    }
    public void addSuggestion(User friend) {
        for (int i = 0; i < friend.getProfile().getFollowings().getLast() + 1; i++) {
            User following = (User) friend.getProfile().getFollowings().get(i);
            if (!user.getProfile().getFollowings().contains(following)) {
                Main.DWGraph.addSuggestion(user, following, mutualFriends(user, following));
            }
        }
    }
    public void removeSuggestion(User suggestion) {
        Main.DWGraph.removeSuggestion(user, suggestion);
    }
    public int mutualFriends(User x, User y) {
        int count = 0;
        for (int i = 0; i < x.getProfile().getFollowings().getLast() + 1; i++) {
            User following = (User) x.getProfile().getFollowings().get(i);
            if (y.getProfile().getFollowings().contains(following))
                count++;
        }
        return count;
    }

    public void setSuggestions() {
        this.suggestions = Main.DWGraph.getSuggestions(user);
    }
    public void setUsersNearby() {
        City hometown = (City) user.getProfile().getCity();
        for (int i = 0; i < hometown.getCitizens().getLast() + 1; i++) {
            usersNearby.add(hometown.getCitizens().get(i));
        }
        for (int i = 0; i < hometown.getAdjList().getLast() + 1; i++) {
            City city = (City) hometown.getAdjList().get(i);
            for (int j = 0; j < city.getCitizens().getLast() + 1; j++) {
                usersNearby.add(city.getCitizens().get(j));
            }
        }
    }


    public User getUser() {
        return user;
    }
    public ArrayList getSuggestions() {
        return suggestions;
    }
    public ArrayList getUsersNearby() {
        return usersNearby;
    }
    public ArrayList getPosts() {
        return posts;
    }
    public ArrayList getFeed() {
        return feed;
    }
}
