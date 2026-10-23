import java.util.Scanner;

public class NQueensForbidden {

    static boolean isSafe(int[][] board, boolean[][] forbidden,
                          int row, int col, int n) {

        // Check whether the square is forbidden
        if (forbidden[row][col]) {
            return false;
        }

        // Check the same column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1) {
                return false;
            }
        }

        // Check upper-left diagonal
        for (int i = row - 1, j = col - 1;
             i >= 0 && j >= 0; i--, j--) {

            if (board[i][j] == 1) {
                return false;
            }
        }

        // Check upper-right diagonal
        for (int i = row - 1, j = col + 1;
             i >= 0 && j < n; i--, j++) {

            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    static boolean solve(int[][] board, boolean[][] forbidden,
                         int row, int n) {

        // All queens have been placed
        if (row == n) {
            return true;
        }

        // Try every column in the current row
        for (int col = 0; col < n; col++) {

            if (isSafe(board, forbidden, row, col, n)) {

                // Place the queen
                board[row][col] = 1;

                // Solve for the next row
                if (solve(board, forbidden, row + 1, n)) {
                    return true;
                }

                // Backtrack
                board[row][col] = 0;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter N: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("N must be positive.");
            sc.close();
            return;
        }

        int[][] board = new int[n][n];
        boolean[][] forbidden = new boolean[n][n];

        System.out.print("Enter number of forbidden squares: ");
        int k = sc.nextInt();

        System.out.println("Enter forbidden square coordinates (row column), 0-indexed:");

        for (int i = 0; i < k; i++) {
            int row = sc.nextInt();
            int col = sc.nextInt();

            if (row >= 0 && row < n && col >= 0 && col < n) {
                forbidden[row][col] = true;
            }
        }

        if (solve(board, forbidden, 0, n)) {

            System.out.println("Solution:");

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }

        } else {
            System.out.println("No solution exists.");
        }

        sc.close();
    }
}