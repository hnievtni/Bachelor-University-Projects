import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        ArrayList<Integer> array = createArray(random);
        int key = randomKey(random);
        System.out.println("Array -> " + array);
        System.out.println("Array length -> " + array.size());
        System.out.println("Key -> " +key);
        linearSearch(array, key);
    }
    public static void linearSearch(ArrayList<Integer> array, int key) {
        int operationCounter = 0;
        int index = -1;
        for (int i = 0; i < array.size(); i++) {
            operationCounter++;
            if (array.get(i) == key) {
                index = i; //key is found
                break;
            }
        }
        System.out.println("Operation counter -> " + operationCounter);
        if (index != -1) //key is found
            System.out.println("Element is found at index " + index);
        else //key is not found
            System.out.println("Element is not found!");
    }
    public static int randomKey(Random random) {
        return  random.nextInt(150 + 1); //generates random key
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