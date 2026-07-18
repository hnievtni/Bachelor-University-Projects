import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Stack<Integer> counts = new Stack<>(); //stack for counts of the loop
        Stack<Integer> starts = new Stack<>(); //stack for starts of the loop
        System.out.println(printText(counts, starts, 0, input));
    }
    public static String printText(Stack<Integer> count, Stack<Integer> start, int index, String text) {
        switch (text.charAt(index)) {
            case '(' -> {
                if (count.isEmpty() || !intCheck(text.charAt(index - 1))) //if before () it doesn't have an int count
                    count.push(1);
                start.push(index); //adding a start point to stack
                return printText(count, start, index + 1, text);
            }
            case ')' -> {
                if (count.peek() > 1) {
                    count.push(count.pop() - 1); //count--;
                    return printText(count, start, start.peek() + 1, text);
                }
                else {
                    count.pop();
                    start.pop();
                    if (index < text.length() - 1)
                        return printText(count, start, index + 1, text);
                    else //if this is the last char of the text
                        return "";
                }
            }
            default -> {
                if (intCheck(text.charAt(index))) { //check if this char is an int
                    if (text.charAt(index + 1) == '(') {
                        count.push(text.charAt(index) - '0'); //adding a count to stack
                        return printText(count, start, index + 1, text);
                    }
                    else { //if it's a 2 digits number
                        count.push((text.charAt(index) - '0') * 10 + (text.charAt(index + 1) - '0'));
                        return printText(count, start, index + 2, text);
                    }
                }
                else if (index < text.length() - 1)
                    return text.charAt(index) + printText(count, start, index + 1, text);
                else
                    return String.valueOf(text.charAt(index));
            }
        }
    }
    public static boolean intCheck(char character) {
        return  (character == '0') || (character == '1') || (character == '2') || (character == '3') || (character == '4') ||
                (character == '5') || (character == '6') || (character == '7') || (character == '8') || (character == '9');
    }
}
