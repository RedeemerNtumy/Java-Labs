import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MinMaxMatrix {
    public static void main(String[] args) {
        int[][] matrix = askForMatrix("Matrix A: ");
        if (matrix != null) {
            calculatePeak(matrix);
        }
    }

    //Ask for user input
    public static int[][] askForMatrix(String userInput) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(userInput);

        try {
            String input = scanner.nextLine().trim();
            String[] parts = input.split(",");
            if (parts.length != 2) {
                System.out.println("Error: Dimensions must be entered in the format 'row,column'.");
                return null;
            }
            int rows = Integer.parseInt(parts[0].trim());
            int columns = Integer.parseInt(parts[1].trim());
            int[][] matrix = new int[rows][columns];

            for (int i = 0; i < rows; i++) {
                int[] rowPointer = new int[columns];
                for (int j = 0; j < columns; j++) {
                    if (scanner.hasNextInt()) {
                        rowPointer[j] = scanner.nextInt();
                    }
                }
                matrix[i] = rowPointer;
                scanner.nextLine();
            }
            return matrix;
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid format for matrix dimensions. Kindly enter integers");
        } catch (InputMismatchException e) {
            System.out.println("Error: Non-integer input. Kindly enter integers");
        } catch (NoSuchElementException e) {
            System.out.println("Error: Insufficient input. Kindly complete input.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
        return null;
    }

    //Calculates the peak points
    public static void calculatePeak(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            System.out.println("Matrix is empty");
            return;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        // Finds the maximums of each row
        int[] rowMax = new int[rows];
        for (int i = 0; i < rows; i++) {
            int max = matrix[i][0];
            for (int j = 1; j < cols; j++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
            rowMax[i] = max;
        }

        // Finds the minimums of each column
        int[] colMin = new int[cols];
        for (int j = 0; j < cols; j++) {
            int min = matrix[0][j];
            for (int i = 1; i < rows; i++) {
                if (matrix[i][j] < min) {
                    min = matrix[i][j];
                }
            }
            colMin[j] = min;
        }

        // Checks for peak-columns
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == rowMax[i] && matrix[i][j] == colMin[j]) {
                    System.out.printf("(%d,%d) = %d\n", i + 1, j + 1, matrix[i][j]);
                }
            }
        }
    }
}
