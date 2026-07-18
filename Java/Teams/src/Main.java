import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int stuCount = scanner.nextInt();
        int[] teams = new int[stuCount];
        for (int i = 0; i < stuCount; i++) {
            teams[i] = scanner.nextInt();
        }

        //store the positions of members for each team
        Map<Integer, List<Integer>> teamPositions = new HashMap<>();
        for (int i = 0; i < stuCount; i++) {
            teamPositions.computeIfAbsent(teams[i], k -> new ArrayList<>()).add(i);
        }

        //initial value for the minimum k
        int minK = stuCount;

        //find the maximum gap between consecutive positions
        for (List<Integer> positions : teamPositions.values()) {

            int maxGap = 0;
            for (int i = 1; i < positions.size(); i++) {
                maxGap = Math.max(maxGap, positions.get(i) - positions.get(i - 1));
            }

            //gap between the last position and the end of the queue
            maxGap = Math.max(maxGap, stuCount - positions.get(positions.size() - 1));

            //gap between the first position and the start of the queue
            maxGap = Math.max(maxGap, positions.get(0) + 1);

            //update the minimum value of k
            minK = Math.min(minK, maxGap);
        }

        System.out.println(minK);
    }
}
