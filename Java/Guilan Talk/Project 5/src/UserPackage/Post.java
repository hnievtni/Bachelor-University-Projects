package UserPackage;

import Program.*;
import Structure.ArrayList;

public class Post {
    String title;
    String text;
    ArrayList hashtags;

    User author;
    ArrayList likers;

    int likes; //MAX: authors follower count

    public Post(String title, String text, User author) {
        this.title = title;
        this.text = text;
        this.author = author;

        this.hashtags = new ArrayList(5);
        this.likers = new ArrayList(50);

        this.likes = 0;
    }

    public void displayPost() {
        System.out.println("Title: " + title);
        System.out.println("Author: @" + author.getProfile().getUsername());
        System.out.println("Text: " + text);

        for (int i = 0; i < hashtags.getLast() + 1; i++)
            System.out.println((String) hashtags.get(i));

        System.out.println("Likes: " + likes);
        for (int i = 0; i < likers.getLast() + 1; i++)
            System.out.println("@" + ((User) likers.get(i)).getProfile().getUsername());
    }
    public void insertHashtags(String hashtag) {
        if (!hashtag.contains("#")) {
            hashtag = "#" + hashtag;
            hashtag = hashtag.toLowerCase();
        }
        this.hashtags.add(hashtag);
    }
    public void addLiker(User user) {
        this.likes++;
        this.likers.add(user);
        this.author.addNotification("@" + user.getProfile().getUsername() + " Liked your post.");
        this.author.setPopularity(author.getPopularity() + 1);
    }

    public String getTitle() {
        return title;
    }
    public String getText() {
        return text;
    }
    public ArrayList getHashtags() {
        return hashtags;
    }
    public Object getAuthor() {
        return author;
    }
    public ArrayList getLikers() {
        return likers;
    }
    public int getLikes() {
        return likes;
    }
}
