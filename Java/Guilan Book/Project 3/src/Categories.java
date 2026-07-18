import java.util.Objects;
import java.util.Scanner;

class Node {
    String name;
    Node root; //category's root
    int index; //category's index in itself root's categories list

    //children
    Node[] categories; //subcategories
    BookNode[] books; //books
    int subCounter;
    int bookCounter;

    public Node() {
        //initializing
        categories = new Node[25];
        books = new BookNode[30];
        subCounter = 0;
        bookCounter = 0;
    }
}
class BookNode {
    String name;
    String author;
    String category;
    String status; //{"unSold", "Sold"}
    int price;
    int index; //book's index in itself root's books list
}

class Categories {
    public Scanner scanner = new Scanner(System.in);
    public final Node root; //root

    public Categories() {
        root = new Node();
    } //constructor to initialize the Categories with a root node

    public void buy(Node root) {
        System.out.println("\n1. Category\n2. Book\n3. Previous menu");
        int input = Integer.parseInt(scanner.nextLine());

        switch (input) {
            case 1 -> {
                System.out.println("Write the category's name");
                String categoryName = scanner.nextLine();
                Node category = searchCategory(categoryName, root);
                if (category != null) {
                    displayChildren(category.name);
                    buy(category);
                }
                else {
                    System.out.println("This category does not exist please try again!");
                    Main.buy();
                }
            }
            case 2 -> {
                System.out.println("Write the book's name");
                String bookName = scanner.nextLine();
                BookNode book = searchBook(bookName, root);
                if (book != null) {
                    System.out.println("Book's name: " + book.name);
                    System.out.println("Book's author: " + book.author);
                    System.out.println("Book's category: " + book.category);
                    System.out.println("Book's price: " + book.price);
                    System.out.println("Book's status: " + book.status);

                    System.out.println("\nDo you want to buy this book?\n1. Yes\n2. No");
                    int input1 = Integer.parseInt(scanner.nextLine());
                    if (input1 == 1 && Objects.equals(book.status, "unSold")) {
                        book.status = "Sold";
                        System.out.println("Your purchase is done.");
                        Main.buy();
                    }
                    else if (input1 == 1 && Objects.equals(book.status, "Sold")) {
                        System.out.println("This book is already sold out.");
                        buy(root);
                    }
                    else
                        Main.buy();
                }
                else {
                    System.out.println("This book does not exist please try again!");
                    buy(root);
                }
            }
            case 3 -> Main.buy();
            default -> {
                System.out.println("Input is not valid. Please try again!");
                buy(root);
            }
        }
    } //buy a book
    public void sell() {
        System.out.println("1. Add book\n2. Already existing book\n3. Previous menu");
        int input = Integer.parseInt(scanner.nextLine());
         switch (input) {
             case 1 -> {
                 System.out.println("Please enter your book information.");
                 System.out.println("Books Name: ");

                 if (sellHelper())
                     System.out.println("Please write down one of this choices or write down your book's name.");
                 else
                     System.out.println("no suggestions!! please write down your book's name.");

                 String name = scanner.nextLine();
                 System.out.println("Authors Name: ");
                 String author = scanner.nextLine();
                 System.out.println("Books Category: ");
                 String category = scanner.nextLine();
                 System.out.println("Price: ");
                 int price = Integer.parseInt(scanner.nextLine());

                 addBook(name, author, category, price);
                 sell();
             }
             case 2 -> {
                 System.out.println("Write the book's name");
                 String bookName = scanner.nextLine();
                 BookNode book = searchBook(bookName, root);
                 if (book != null) {
                     System.out.println("Book's name: " + book.name);
                     System.out.println("Book's author: " + book.author);
                     System.out.println("Book's category: " + book.category);
                     System.out.println("Book's price: " + book.price);
                     System.out.println("Book's status: " + book.status);

                     System.out.println("\nDo you want to sell this book?\n1. Yes\n2. No");
                     int input1 = Integer.parseInt(scanner.nextLine());
                     if (input1 == 1 && Objects.equals(book.status, "unSold")) {
                         book.status = "Sold";
                         System.out.println("Your book is successfully sold.");
                         Main.sell();
                     }
                     else if (input1 == 1 && Objects.equals(book.status, "Sold")) {
                         System.out.println("This book is already sold out.");
                         sell();
                     }
                     else
                         Main.sell();
                 }
                 else {
                     System.out.println("This book does not exist please try again!");
                     sell();
                 }
             }
             case 3 -> Main.menu();
             default -> {
                 System.out.println("Input is not valid. Please try again!");
                 sell();
             }
         }

    } //sell a book
    private boolean sellHelper() {
        String name = scanner.nextLine();
        String[] suggestions = Main.trie.autocomplete(name);
        boolean bool = false;

        if (suggestions != null) {
            for (String suggestion : suggestions) {
                if (suggestion != null) {
                    bool = true;
                    System.out.println(suggestion);
                }
            }
        }
        return bool;
    } //display autocomplete for the book's name

    public void addCategory(String name) {
        name = name.toLowerCase();
        Node newCategory = new Node(); //creating a new category
        newCategory.name = name;

        if (!categoryDuplicateCheck(newCategory, root)) { //if the category doesn't already exist
            addCategoryHelper(newCategory, root);
            System.out.println("Category was successfully added to the list.");
            Main.menu();
        }
        else {
            System.out.println("This category already exists!");
            Main.addCategory();
        }
    } //add category to the root
    public void addAtoB(String sourceStr, String destinationStr) {
        sourceStr = sourceStr.toLowerCase();
        destinationStr = destinationStr.toLowerCase();

        Node destination = searchCategory(destinationStr, root); //destination category
        Node source = new Node(); //creating source category
        source.name = sourceStr;

        if (destination != null && !categoryDuplicateCheck(source, root)) { //if destination exists and source doesn't already exist
            addCategoryHelper(source, destination);
            System.out.println("Category was successfully added to the destination category.");
            Main.menu();
        }
        else {
            if (destination == null) //if destination doesn't exist
                System.out.println("The destination category does not exist!");
            else if (categoryDuplicateCheck(source, root)) //if source already exists
                System.out.println("This category already exists!");
            Main.addAtoB();
        }

    } //add category A to the category B
    private void addCategoryHelper(Node newCategory, Node root) {
        if (root.subCounter < root.categories.length) //if array is not full
            root.categories[root.subCounter] = newCategory; //adding the book the roots subCategories list
        else {
            //increasing roots subcategories array length by 1
            Node[] copy = new Node[root.categories.length + 1];
            int index = 0;
            for (Node node: root.categories) { //copying all the nodes into copy array
                copy[index] = node;
                index++;
            }
            copy[copy.length - 1] = newCategory; //last element is the new category
            root.categories = copy;
        }
        newCategory.root = root; //register new category's root
        newCategory.index = root.subCounter; //register new category's index
        root.subCounter++;
    } //adding new category as a subcategory to the root

    public void addBook(String name, String author, String categoryName, int price) {
        name = name.toLowerCase();
        author = author.toLowerCase();
        categoryName = categoryName.toLowerCase();

        Node destination = searchCategory(categoryName, root); //destination category

        BookNode book = new BookNode(); //creating a new book
        book.name = name;
        book.author = author;
        book.status = "unSold";
        book.price = price;

        if (destination!= null && !bookDuplicateCheck(book, root)) { //if destination exists and book doesn't already exist
            book.category = destination.name;
            addBookHelper(book, destination); //add book to the destinations book array
            Main.trie.insert(book.name); //add books name to the trie
            System.out.println("Your book successfully was added to the list for sell.");
        }
        else if (destination == null) //if destination doesn't exist
            System.out.println("This category does not exist!");
        else if (bookDuplicateCheck(book, root)) //if the book already exist
            System.out.println("This book already exists!");
    } //add a book to the category
    private void addBookHelper(BookNode newBook, Node root) {
        if (root.bookCounter <root.books.length) //if array is not full
            root.books[root.bookCounter] = newBook;
        else {
            //increasing roots books array length by 1
            BookNode[] copy = new BookNode[root.books.length + 1];
            int index = 0;
            for (BookNode book: root.books) { //copying all the nodes into copy array
                copy[index] = book;
                index++;
            }
            copy[copy.length - 1] = newBook; //last element is the new category
            root.books = copy;
        }
        newBook.index = root.bookCounter; //register new book's index
        root.bookCounter++;
    } //adding new book to the root

    public void deleteCategory(String categoryName) {
        categoryName = categoryName.toLowerCase();

        Node category = searchCategory(categoryName, root);
        Node root; //category's root
        int index; //category's index

        if (category != null) { //if category exists
            root = category.root;
            index = category.index;

            Node[] temp = new Node[root.categories.length]; //temp array for deleting the category
            for (int i = 0; i < root.subCounter; i++) {
                if (i < index)
                    temp[i] = root.categories[i];
                if (i >= index) //from the index and above we shift left the array
                    temp[i] = root.categories[i + 1];
            }
            root.categories = temp;
            root.subCounter--; //decreasing the subCounter by 1

            System.out.println("Category was successfully deleted.");
            Main.menu();
        }
        else {
            System.out.println("There is no such category with this name.");
            Main.deleteCategory();
        }

    } //delete the category
    public void deleteBook(String bookName) {
        bookName = bookName.toLowerCase();

        BookNode book = searchBook(bookName, root);
        Node category ; //category's root
        int index; //category's index

        if (book != null) { //if category exists
            category = searchCategory(book.category, root);
            index = book.index;

            BookNode[] temp = new BookNode[category.books.length]; //temp array for deleting the category
            for (int i = 0; i < category.bookCounter; i++) {
                if (i < index)
                    temp[i] = category.books[i];
                if (i >= index) //from the index and above we shift left the array
                    temp[i] = category.books[i + 1];
            }
            category.books = temp;
            category.bookCounter--; //decreasing the subCounter by 1

            System.out.println("Book was successfully deleted.");
            Main.menu();
        }
        else {
            System.out.println("There is no such book with this name.");
            Main.deleteBook();
        }
    } //delete a book in a category

    public void displayChildren(String rootName) {
        Node currentRoot;
        if (rootName == null) //all the categories and books
            currentRoot = root;
        else {
            rootName = rootName.toLowerCase();
            currentRoot = searchCategory(rootName, root);
        }

        assert currentRoot != null;
        for (Node category: currentRoot.categories) { //each subcategory
            if (category != null)
                System.out.println(category.name);
            else //if loop reached null nodes break the loop
                break;
        }
        for (BookNode book: currentRoot.books) { //each book
            if (book != null)
                System.out.println(book.name + ",\tstatus: " + book.status);
            else //if loop reached null nodes break the loop
                break;
        }
    } //display all the children of the category

    private Node searchCategory(String name, Node root) {
        name = name.toLowerCase();

        Node found = null;
        if (Objects.equals(root.name, name)) //checks the roots name
            found = root;
        else {
            for (Node node : root.categories) { //for each node that is a subcategory to the root
                if (node != null) {
                    found = searchCategory(name, node); //check the subcategories of the node
                    if (found != null) //if we found a match break the loop
                        break;
                }
                else //if we reach the null element break the loop
                    break;
            }
        }
        return found;
    } //search for a category based on a name
    private BookNode searchBook(String name, Node root) {
        name = name.toLowerCase();

        BookNode found = null;
        for (BookNode bookNode : root.books) { //for each book that is a child of the root
            if (bookNode != null) {
                if (Objects.equals(bookNode.name, name)) {
                    found = bookNode;
                    break; //if we found a match break the loop
                }
            }
            else { //after reaching to the first null book we start checking other books
                for (Node node : root.categories) { //for each category that is in books array
                    if (node != null) {
                        found = searchBook(name, node); //check the books of the node
                        if (found != null) //if we found a match break the loop
                            break;
                    }
                    else
                        break;
                }
            }
        }
        return found;
    } //search for a book based on a name

    private boolean categoryDuplicateCheck(Node category, Node root) {
        boolean dup = false;
        if (Objects.equals(root.name, category.name)) //checks the roots name
            dup = true;
        else {
            for (Node node : root.categories) { //for each node that is a subcategory to the root
                if (node != null) {
                    dup = categoryDuplicateCheck(category, node); //check the subcategories of the node
                    if (dup) //if dup is true it means that we have a duplicate, so we stop the for loop
                        break;
                }
                else
                     break;
            }
        }
        return dup;
    } //checks if there is already a category as the one we are adding
    private boolean bookDuplicateCheck(BookNode book, Node root) {
        boolean dup = false;
        for (BookNode bookNode : root.books) { //for each book that is a child of the root
            if (bookNode != null) {
                if (Objects.equals(bookNode.name, book.name)) {
                    dup = true;
                    break; //after finding the first dup we break the loop
                }
            }
            else { //after reaching to the first null book we start checking other categories
                for (Node node : root.categories) { //for each category that is in books array
                    if (node != null) {
                        dup = bookDuplicateCheck(book, node); //check the books of the node
                        if (dup) //if dup is true it means that we have a duplicate, so we stop the for loop
                            break;
                    }
                    else
                        break;
                }
            }
        }
        return dup;
    } //checks if there is already a book as the one we are adding
}