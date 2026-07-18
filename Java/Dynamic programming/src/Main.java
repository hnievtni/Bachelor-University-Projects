import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int input = scanner.nextInt();
        int[] output = countSetBits(input + 1);

        System.out.print("[");
        for (int index = 0; index < output.length; index++) {
            System.out.print(output[index]);
            if (index < output.length - 1)
                System.out.print(",");
        }
        System.out.print("]");
    }
    public static int[] countSetBits(int length) {
        int[] result = new int[length];
        result[0] = 0;
        for (int index = 1; index < result.length; index++)
            result[index] = result[index >> 1] + (index & 1);
        //each number is calculated based on the number of set bits in its right-shifted value and the value of its least significant bit
        return result;
    }
}