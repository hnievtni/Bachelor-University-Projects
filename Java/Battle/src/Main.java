import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int fightersNum = scanner.nextInt(); //fighters number

        int[] heights = new int[fightersNum]; //list of heights
        for (int i = 0; i < fightersNum; i++)
            heights[i] = scanner.nextInt();

        System.out.println(calculateStrategicSum(heights));
    }

    public static long calculateStrategicSum(int[] heights) {
        int fightersCount = heights.length;
        int[][] indexedHeights = new int[fightersCount][2];

        //storing indexes and heights together
        for (int i = 0; i < fightersCount; i++) {
            indexedHeights[i][0] = i;
            indexedHeights[i][1] = heights[i];
        }

        long[] strategicCount = new long[fightersCount]; //array to store strategic counts for each fighter

        //perform merge sort and count strategic numbers
        mergeSortAndCount(indexedHeights, strategicCount, 0, fightersCount - 1);

        //calculate the total sum of strategic counts
        long totalStrategicSum = 0;
        for (long count : strategicCount)
            totalStrategicSum += count;

        return totalStrategicSum;
    }

    private static void mergeSortAndCount(int[][] array, long[] strategicCount, int start, int end) {
        if (start >= end)
            return;

        int mid = start + (end - start) / 2;

        //recursively sort and count for left and right halves
        mergeSortAndCount(array, strategicCount, start, mid);
        mergeSortAndCount(array, strategicCount, mid + 1, end);

        //merge the two halves and count strategic numbers
        mergeAndCount(array, strategicCount, start, mid, end);
    }

    private static void mergeAndCount(int[][] array, long[] strategicCount, int start, int mid, int end) {
        int[][] temp = new int[end - start + 1][2]; //temporary array for merging
        int i = start, j = mid + 1, k = 0;

        while (i <= mid && j <= end) {
            if (array[i][1] <= array[j][1])
                temp[k++] = array[i++];
            else {
                //all remaining elements in the left array can target the current right element
                strategicCount[array[j][0]] += (mid - i + 1);
                temp[k++] = array[j++];
            }
        }

        //copy remaining elements from left half
        while (i <= mid)
            temp[k++] = array[i++];
        //copy remaining elements from right half
        while (j <= end)
            temp[k++] = array[j++];
        //copy merged array back to original array
        System.arraycopy(temp, 0, array, start, temp.length);
    }
}
