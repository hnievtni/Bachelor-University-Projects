import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] rows = new String[9];
        for (int i = 0; i < 9; i++)
            rows[i] = scanner.nextLine();

        int[][] solvedSudoku = buildingInput(rows);
        if (solveSudoku(solvedSudoku))
            display(solvedSudoku);
        else
            System.out.println("No solution exists");
    }
    public static int[][] buildingInput(String[] rows) {
        int[][] sudoku = new int[9][9];
        for (int row = 0; row < 9; row++) {
            int charIndex = 0;
            for (int column = 0; column < 9; column++) {
                sudoku[row][column] = rows[row].charAt(charIndex) - '0';
                charIndex += 2;
            }
        }
        return sudoku;
    } //function for building the sudoku
    public static boolean solveSudoku(int[][] output) {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                if (output[row][column] == 0) {
                    for (int number = 1; number <= 9; number++) {
                        if (isSafe(output, row, column, number)) {
                            output[row][column] = number;
                            if (solveSudoku(output))
                                return true;
                            output[row][column] = 0; //backtrack
                        }
                    }
                    return false; //needed for backtracking
                }
            }
        }
        return true; //puzzle is solved
    } //function to solve the Sudoku puzzle using backtracking
    public static boolean isSafe(int[][] input, int row, int column, int number) {
        //check the column
        for (int i = 0; i < 9; i++) {
            if (input[i][column] == number)
                return false;
        }
        //check the row
        for (int j = 0; j < 9; j++) {
            if (input[row][j] == number)
                return false;
        }
        //check the 3×3 subgrid
        int startRow = row - row % 3;
        int startCol = column - column % 3;
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (input[i][j] == number)
                    return false;
            }
        }
        return true;
    } //function to check if it's safe to place a number in a cell
    public static void display(int[][] sudoku) {
        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                System.out.print(sudoku[row][column]);
                if (column < 8)
                    System.out.print(" ");
            }
            System.out.println();
        }
    } //function to display the solved sudoku
}