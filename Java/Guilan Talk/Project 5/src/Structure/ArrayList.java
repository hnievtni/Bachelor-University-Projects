package Structure;

import java.util.Objects;

public class ArrayList {
    Object[] objects;

    int length;
    int last;

    public ArrayList(int capacity) {
        this.length = capacity;
        this.objects = new Object[capacity];
        this.last = -1;
    }


    public void add(Object object) {
        this.last++;
        this.objects[last] = object;
        if (last == length - 1)
            addCapacity();
    }
    public void remove(Object object) {
        this.objects = swap(object, objects[last]);
        this.objects[last] = null;
        this.last--;
    }
    public boolean contains(Object object) {
        for (int i = 0; i < last + 1; i++) {
            if (Objects.equals(object, objects[i]))
                return true;
        }
        return false;
    }
    public int getIndex(Object object) {
        for (int index = 0; index < last + 1; index++) {
            if (Objects.equals(object, objects[index]))
                return index;
        }
        return -1; //objects doesn't contain object
    }
    public Object get(int index) {
        return objects[index];
    }
    private Object[] swap(Object element1, Object element2){
        int index1 = getIndex(element1);
        int index2 = getIndex(element2);
        this.objects[index1] = element2;
        this.objects[index2] = element1;
        return objects;
    }


    public int getLast() {
        return last;
    }

    private void addCapacity() {
        Object[] temp = objects;
        this.objects = new Object[length * 2]; //new array with new capacity
        int index = 0;
        for (Object object : temp) {
            this.objects[index] = object;
            index++;
        }
    }
}
