class MyClass {
    int int_var;
    int int_var2 = 10;

    void some_function(int x, float y, double z) {

    }
}

class Main {
    void free_function(int a1, int a2) {

    }

    public static void main(String[] args) {
        float float_var = 10.0f;
        int j = 20 + 2 * 3 - 10 + 15 / 5; // = 19

        Main mainObject = new Main();

        mainObject.free_function(5, 10);

        for (int i = 0; i < 10; i++) {
            mainObject.free_function(10, j);
        }

        int i = 0;
        while (i < 10) {
            System.out.println("Hello world!");
            i++;
        }

        if (i < 5) {
            System.out.println("If !");
        } else if (j < 5) // Without braces
            System.out.println("Else if!");
        else {
            System.out.println("Else!");
        }
    }
}