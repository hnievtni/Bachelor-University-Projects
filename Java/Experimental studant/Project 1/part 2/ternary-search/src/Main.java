import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        ArrayList<Integer> array = createArray(random);
        int key = randomKey(random);
        System.out.println("Array -> " + array);
        Collections.sort(array);
        System.out.println("Sorted array -> " + array);
        System.out.println("Array length -> " + array.size());
        System.out.println("Key -> " +key);
        int index = ternarySearch(array, key, 0, array.size() - 1, 0);
        if (index != -1) //key is found
            System.out.println("Element is found at index: " + index);
        else //key is not found
            System.out.println("Element is not found!");
    }
    public static int ternarySearch(ArrayList<Integer> array, int key, int left, int right, int operationCounter) {
        if (right >= left) {
            int mid1 = left + (right - left) / 3; //first mid
            int mid2 = right - (right - left) / 3; //second mid
            operationCounter++; //for comparing the key to mid1
            if (array.get(mid1) == key) {
                System.out.println("Operation counter -> " + operationCounter);
                return mid1; //key is found
            }
            operationCounter++; //for comparing the key to mid2
            if (array.get(mid2) == key) {
                System.out.println("Operation counter -> " + operationCounter);
                return mid2; //key is found
            }
            if (key < array.get(mid1)) //search the left subarray
                return ternarySearch(array, key, left, mid1 - 1, operationCounter);
            if (key > array.get(mid2)) //search the right subarray
                return ternarySearch(array, key, mid2 + 1, right, operationCounter);
            return ternarySearch(array, key, mid1 + 1, mid2 - 1, operationCounter); //search the middle subarray
        }
        System.out.println("Operation counter -> " + operationCounter);
        return -1; //key is not found
    }
    public static int randomKey(Random random) {
        return  random.nextInt((10000 - 10) + 1); //generates random key
    }
    public static ArrayList<Integer> createArray(Random random) {
        int arrayLength = random.nextInt((10000 - 10) + 1) + 10; //generates random length for our array
        ArrayList<Integer> array = new ArrayList<>(arrayLength);
        for (int index = 0; index < arrayLength;) {
            int number = random.nextInt((10000 - 10) + 1); //generates random integers for array
            if (!array.contains(number)) { //to avoid duplicates
                array.add(number);
                index++;
            }
        }
        return array;
    }
}