import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(reverse(input));
    }
    public static String reverse(String text){
        if (text == null || text.length() <= 1)
            return text;
        else {
            return text.charAt(text.length() - 1) + reverse(text.substring(0, text.length() - 1));
        }
    }
}