import java.util.Scanner;

public class NQueensFirst {

    static boolean isSafe(int[] board, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i] == col ||
                Math.abs(board[i] - col) == Math.abs(i - row)) {
                return false;
            }
        }

        return true;
    }

    static boolean solve(int[] board, int row, int n) {
        if (row == n) {
            return true;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col)) {
                board[row] = col;

                if (solve(board, row + 1, n)) {
                    return true;
                }

                board[row] = -1;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter N: ");
        int n = sc.nextInt();

        if (n < 1) {
            System.out.println("N must be positive.");
        } else {
            int[] board = new int[n];

            for (int i = 0; i < n; i++) {
                board[i] = -1;
            }

            if (solve(board, 0, n)) {
                for (int row = 0; row < n; row++) {
                    for (int col = 0; col < n; col++) {
                        System.out.print(
                            board[row] == col ? "Q " : ". "
                        );
                    }
                    System.out.println();
                }
            } else {
                System.out.println("No solution exists.");
            }
        }

        sc.close();
    }
}