import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] tiles = new int[n];
        for (int i = 0; i < n; i++)
            tiles[i] = scanner.nextInt();

        int minimumCost = findMinimumCost(n, m, tiles);
        System.out.println(minimumCost);

    }
    public static int findMinimumCost(int n, int m , int[] tiles) {
        int maxSquareSize = 100;
        int totalArea = 0;

        //calculate the total area of all tiles
        for (int tile: tiles)
            totalArea += (tile * tile);
        if (totalArea == m)
            return 0;

        //initialize a 2D array for cost for dynamic programming
        int[][] cost = new int[n + 1][m + 1];
        for (int[] row : cost)
            Arrays.fill(row, Integer.MAX_VALUE);
        cost[0][0] = 0;

        //finding the minimum cost
        for (int i = 1; i <= n ;i++) {
            for (int j = 0; j <= m; j++) {
                for (int b = 0; b <= maxSquareSize; b++) {
                    int areaChange = b * b;
                    if (j >= areaChange && cost[i - 1][j - areaChange] != Integer.MAX_VALUE) {
                        int costI = (tiles[i - 1] - b) * (tiles[i - 1] - b);
                        cost[i][j] = Math.min(cost[i][j], cost[i - 1][j - areaChange] + costI);
                    }
                }
            }
        }

        //return the minimum cost
        if (cost[n][m] == Integer.MAX_VALUE)
            return -1;
        else
            return cost[n][m];
    } //function to find the minimum cost to change tile sizes to achieve the desired total area
}