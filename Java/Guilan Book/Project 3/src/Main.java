import java.util.Objects;
import java.util.Scanner;
import static java.lang.System.exit;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static Trie trie; //Trie class
    public static Categories categories; //Categories class

    public static void main(String[] args) {
        trie = new Trie(); //calling Trie constructor
        categories = new Categories(); //calling Categories constructor

        menu(); //calling menu
    }
    public static void menu() {
        System.out.println("1. Buy\n2. Sell\n3. Add category\n4. Add category to another category\n5. Delete category\n6. Delete book\n7. Display category\n8. Exit");
        int menuInput = Integer.parseInt(scanner.nextLine());

        switch (menuInput) {
            case 1 -> buy();
            case 2 -> sell();
            case 3 -> addCategory();
            case 4 -> addAtoB();
            case 5 -> deleteCategory();
            case 6 -> deleteBook();
            case 7 -> displayCategory();
            case 8 -> exit(0);
            default -> {
                System.out.println("Input is not valid. Please try again!");
                menu();
            }
        }
    } //menu display
    public static void buy() {
        System.out.println("1. Specific Category or Book\n2. All categories and books\n3. Previous menu");
        int input = Integer.parseInt(scanner.nextLine());

        switch (input) {
            case 1 -> categories.buy(categories.root);
            case 2 -> {
                categories.displayChildren(null); //when input is null it displays all the categories
                categories.buy((categories.root));
            }
            case 3 -> menu();
            default -> {
                System.out.println("Input is not valid. Please try again!");
                buy();
            }
        }
    } //buy a book
    public static void sell() {
        categories.sell();
    } //sell a book
    public static void addCategory() {
        System.out.println("Category Name: \nenter 0 for previous menu!");
        String categoryName = scanner.nextLine();
        if (categoryName.equals("0")) //previous menu
            menu();
        else
            categories.addCategory(categoryName);
    } //add category
    public static void addAtoB() {
        System.out.println("* for adding category A to category B enter their name.");

        System.out.println("Category A Name: \nenter 0 for previous menu!");
        String source = scanner.nextLine();
        if (source.equals("0")) //previous menu
            menu();
        else {
            System.out.println("Category B Name: \nenter 0 for previous menu!");
            String destination = scanner.nextLine();
            if (destination.equals("0")) //previous menu
                menu();
            else
                categories.addAtoB(source, destination);
        }
    } //add category A to category B
    public static void deleteCategory() {
        System.out.println("Category Name: \nenter 0 for previous menu!");
        String categoryName = scanner.nextLine();
        if (categoryName.equals("0")) //previous menu
            menu();
        else
            categories.deleteCategory(categoryName);
    } //delete category
    public static void deleteBook() {
        System.out.println("Book Name: .\nenter 0 for previous menu!");
        String bookName = scanner.nextLine();
        if (bookName.equals("0")) //previous menu
            menu();
        else
            categories.deleteBook(bookName);
    } //delete book
    public static void displayCategory() {
        System.out.println("Please write the category's name that you want.\n* for all the categories enter 1 and for previous menu enter 0.");
        String input = scanner.nextLine();

        if (Objects.equals(input, "1"))
            categories.displayChildren(null);
        else if (Objects.equals(input, "0"))
            menu();
        else
            categories.displayChildren(input);

        System.out.println();
        displayCategory();
    } //display category's children
}
