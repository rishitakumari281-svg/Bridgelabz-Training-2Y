import java.util.Scanner;

public class NQueensCount {

    static int count = 0;

    static boolean isSafe(int[] board, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i] == col ||
                Math.abs(board[i] - col) == Math.abs(i - row)) {
                return false;
            }
        }

        return true;
    }

    static void solve(int[] board, int row, int n) {
        if (row == n) {
            count++;
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col)) {
                board[row] = col;

                solve(board, row + 1, n);

                board[row] = -1;
            }
        }
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

            solve(board, 0, n);

            System.out.println("Total solutions: " + count);
        }

        sc.close();
    }
}