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
        int index = binarySearch(array, key, 0, array.size() - 1, 0);
        if (index != -1) //key is found
            System.out.println("Element is found at index: " + index);
        else //key is not found
            System.out.println("Element is not found!");
    }
    public static int binarySearch(ArrayList<Integer> array, int key, int first, int last, int operationCounter) {
        if (last >= first) {
            int mid = first + (last - first) / 2;
            operationCounter++; //for comparing the key to mid
            if (array.get(mid) == key) {
                System.out.println("Operation counter -> " + operationCounter);
                return mid; //key is found
            }
            if (array.get(mid) > key)
                return binarySearch(array, key, first, mid-1, operationCounter);//search in left sub array
            else
                return binarySearch(array, key, mid + 1, last, operationCounter);//search in right sub array
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