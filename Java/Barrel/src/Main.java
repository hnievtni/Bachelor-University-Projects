import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        float container1 = scanner.nextFloat();
        float container2 = scanner.nextFloat();
        float container3 = scanner.nextFloat();

        int minimumSteps = minimumSteps(container1, container2, container3);
        System.out.println(minimumSteps);

    }
    public static int minimumSteps(float container1, float container2, float container3) {
        float sum = container1 + container2 + container3;
        float balance = sum / 3;

        //calculate the difference from the target for each container
        float diff1 = container1 - balance;
        float diff2 = container2 - balance;
        float diff3 = container3 - balance;

        //if already balanced
        if (diff1 == 0 && diff2 == 0 && diff3 == 0)
            return 0;

        int steps = 0;

        //move water between containers to balance
        if (diff1 > 0) {
            if (diff2 < 0) {
                float transfer = Math.min(diff1, -diff2);
                diff1 -= transfer; diff2 += transfer;
                steps++;
            }
            if (diff3 < 0) {
                float transfer = Math.min(diff1, -diff3);
                diff1 -= transfer; diff3 += transfer;
                steps++;
            }
        }
        if (diff2 > 0) {
            if (diff1 < 0) {
                float transfer = Math.min(diff2, -diff1);
                diff2 -= transfer; diff1 += transfer;
                steps++;
            }
            if (diff3 < 0) {
                float transfer = Math.min(diff2, -diff3);
                diff2 -= transfer; diff3 += transfer;
                steps++;
            }
        }
        if (diff3 > 0) {
            if (diff1 < 0) {
                float transfer = Math.min(diff3, -diff1);
                diff3 -= transfer; diff1 += transfer;
                steps++;
            }
            if (diff2 < 0) {
                float transfer = Math.min(diff3, -diff2);
                diff3 -= transfer; diff2 += transfer;
                steps++;
            }
        }

        return steps;
    }
}