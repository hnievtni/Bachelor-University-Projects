import java.util.*;

public class Main {
    public static List<int[]> brokenKeys; //list of broken keys we want to add to keyboard
    public static int count; //count od keyboard we need to break

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        brokenKeys = new ArrayList<>();
        count = 0;

        int rows = scanner.nextInt();
        int columns = scanner.nextInt();
        int brokenCount = scanner.nextInt(); //number of broken keys

        int[][] keys = new int[rows][columns];
        for (int[] row : keys)
            Arrays.fill(row, 1); //initialize all keys with 1

        for (int i = 0; i < brokenCount; i++) {
            int row = scanner.nextInt();
            int column = scanner.nextInt();
            keys[row - 1][column - 1] = 0; //assign 0 to broken keys
        }

        int size = rows * columns;
        checkBrokenKeys(0, 0, size, brokenCount, keys);
    }
    public static void checkBrokenKeys(int row, int column, int size, int brokenCount, int[][]keys) {
        if (brokenCount % 2 != 0) //if it's already odd number
            printOutput();
        else if (brokenCount >= size) //if it's not possible
            System.out.println(-1);
        else {
            if (row < keys.length) {
                if (column < keys[row].length) {
                    if (keys[row][column] == 1) {
                        count++;
                        brokenCount++;
                        keys[row][column] = 0; //breaking the key
                        brokenKeys.add(new int[]{row + 1, column + 1}); //adding to the list
                    }
                    checkBrokenKeys(row, column + 1, size, brokenCount, keys); //start from that node
                }
                else {
                    checkBrokenKeys(row + 1, 0, size, brokenCount, keys); //start from that node
                }
            }
        }
    }
    public static void printOutput() {
        System.out.println(count);
        for (int i = 0; i < count; i++)
            System.out.println(brokenKeys.get(i)[0] + " " + brokenKeys.get(0)[1]);
    }
}