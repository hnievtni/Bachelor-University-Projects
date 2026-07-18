import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] markers = new int[n];
        for (int i = 0; i < n; i++)
            markers[i] = scanner.nextInt();

        int fewestMarker = findFewestMarker(n, markers);
        System.out.println(fewestMarker);
    }

    public static int findFewestMarker(int n, int[] markers) {
        int makerMax = 100;
        HashMap<Integer, Integer> markerCount = new HashMap<>(); //key: markers Color, Value: markers Count

        for (int i = 0; i < n; i++) {
            if (markerCount.containsKey(markers[i])) {
                int count = markerCount.get(markers[i]) + 1;
                markerCount.replace(markers[i], count);
            }
            else
                markerCount.put(markers[i], 1);
        }

        //find the minimum maker count and the color of that maker
        int min = Integer.MAX_VALUE;
        int markerMin = makerMax;
        for (int count: markerCount.values()) {
            if (count <= min)
                min = count;
        }
        for (int marker : markerCount.keySet()) {
            if (markerCount.get(marker) == min && marker < markerMin)
                markerMin = marker;
        }

        return markerMin;
    }
}