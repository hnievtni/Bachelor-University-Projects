import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        printText(input);
    }
    public static void printText(String text) {
        Stack<Integer> count = new Stack<>(); //stack for counts of the loop
        Stack<Integer> start = new Stack<>(); //stack for starts of the loop
        int index = 0;
        while (index < text.length()) {
            switch (text.charAt(index)) {
                case '(' -> {
                    if (count.isEmpty() || !intCheck(text.charAt(index - 1))) //if before () it doesn't have an int count
                        count.push(1);
                    start.push(index); //adding a start point to stack
                    index++;
                }
                case ')' -> {
                    if (count.peek() > 1) {
                        count.push(count.pop() - 1); //count--;
                        index = start.peek() + 1;
                    }
                    else {
                        count.pop();
                        start.pop();
                        index++;
                    }
                }
                default -> {
                    if (intCheck(text.charAt(index))) { //check if this char is an int
                        if (text.charAt(index + 1) == '(') {
                            count.push(text.charAt(index) - '0'); //adding a count to stack
                            index++;
                        }
                        else { //if it's a 2 digits number
                            count.push((text.charAt(index) - '0') * 10 + (text.charAt(index + 1) - '0'));
                            index += 2;
                        }
                    }
                    else {
                        System.out.print(text.charAt(index));
                        index++;
                    }
                }
            }
        }
    }
    public static boolean intCheck(char character) {
        return (character == '0') || (character == '1') || (character == '2') || (character == '3') || (character == '4') ||
                (character == '5') || (character == '6') || (character == '7') || (character == '8') || (character == '9');
    }
}