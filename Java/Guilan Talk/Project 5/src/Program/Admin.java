package Program;

import Structure.ArrayList;

public class Admin {

    public Admin() {}

    public void homepage() {
        Main.scanner.nextLine();
        System.out.println("1. Popular Users\n2. User Activity\n3. Strongly Connected Components\n4. Sign Out");
        int input = Main.scanner.nextInt();
        switch (input) {
            case 1 -> {
                displayPopularUsers();
                homepage();
            }
            case 2 -> {
                userActivity();
                homepage();
            }
            case 3 -> {
                displaySCC();
                homepage();
            }
            case 4 -> {
                Main.scanner.nextLine();
                Main.menu();
            }

            default -> {
                System.out.println("Wrong input! Please try again.");
                homepage();
            }
        }
    }
    public void displayPopularUsers() {
        ArrayList list = new ArrayList(50);
        for (int i = 0; i < Main.users.getValues().getLast() + 1; i++)
            list.add(Main.users.getValues().get(i));

        int count = 0;
        User popular;
        while (count < (list.getLast() + 1)) {
            int max = 0, maxIndex = 0;

            for (int i = 0; i < list.getLast() + 1; i++) {
                int popularity = ((User) list.get(i)).getPopularity();
                if (popularity > max) {
                    max = popularity;
                    maxIndex = i;
                }
            }

            popular = (User) list.get(maxIndex);
            System.out.println("@" + popular.getProfile().getUsername() + " - " + popular.getPopularity() + " popularity.");

            list.remove(popular);
            count++;
        }
    }
    public void userActivity() {
        Main.scanner.nextLine();
        System.out.println("Search user...");
        String username = Main.scanner.nextLine();
        if (username.contains("@")) {
            username = username.replace("@", "");
        }
        username = username.toLowerCase();

        if (Main.userValidity(username)) {
            User user = Main.getUser(username);
            for (int i = 0; i < user.getActivity().getLast() + 1; i++) {
                System.out.println((String) user.getActivity().get(i));
            }
        }
        else
            System.out.println("There is no such user with this username. Please try again.");
    }
    public void displaySCC() {

    }
}
