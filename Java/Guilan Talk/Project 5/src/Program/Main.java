package Program;

import Structure.*;
import UserPackage.City;

import java.util.Scanner;
import static java.lang.System.exit;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static HashMap users;

    public static DirectedGraph DGraph; //users follow graph --Directed Graph
    public static WeightedGraph WGraph; //cities graph --Weighted Graph
    public static DWGraph DWGraph; //users suggestions graph --Directed Weighted Graph

    public static Admin admin;

    public static void main(String[] args) {
        users = new HashMap( 50);
        DGraph = new DirectedGraph(50);
        WGraph = new WeightedGraph(12); //12 cities in total
        DWGraph = new DWGraph(50);
        admin = new Admin();

        menu();
    }
    public static void menu() {
        System.out.println("1. Sign Up\n2. Sign In\n3. Admin\n4. Exit");
        int input = scanner.nextInt();
        switch (input) {
            case 1 -> signUp();
            case 2 -> signIn();
            case 3 -> admin.homepage();
            case 4 -> exit(0);
            default -> {
                System.out.println("Wrong input! Please try again.");
                menu();
            }
        }
    }
    public static void signUp() {
        scanner.nextLine();
        System.out.println("For signing up you need to enter these information.");

        System.out.println("Username:");
        String username = scanner.nextLine();
        System.out.println("Biography:");
        String bio = scanner.nextLine();
        System.out.println("City:");
        String cityName = scanner.nextLine();

        cityName = cityName.toLowerCase();
        if (!WGraph.getCities().contains(cityName)) {
            System.out.println("There is no such city with this name in the map! Please try again.");
            menu();
        }
        if (username.contains(" ")) {
            System.out.println("Username shouldn't have any white space. Please try again.");
            menu();
        }
        username = username.toLowerCase();
        if (users.contains(username)) {
            System.out.println("This username has already been used! Please try again.");
            menu();
        }

        User user = new User(username, bio, cityName);
        City city = (City) WGraph.getCities().getValue(cityName);

        users.insert(username, user);

        city.addCitizen(user);
        DWGraph.addUser(user);
        DGraph.addUser(user);

        user.getHomePage().setSuggestions();
        user.getHomePage().display();
    }
    public static void signIn() {
        scanner.nextLine();
        System.out.println("For signing in your account please enter your username.");

        System.out.println("Username:");
        String username = scanner.nextLine();

        username = username.toLowerCase();
        if (!users.contains(username)) {
            System.out.println("There is no such account with this username! Please try again or sign up.");
            menu();
        }
        else {
            User user = getUser(username);
            user.getHomePage().display();
        }
    }

    public static boolean userValidity(String username) {
        return users.contains(username);
    }
    public static User getUser(String username) {
        return (User) users.getValue(username);
    }
}