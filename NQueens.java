import java.util.Scanner;

public class NQueens {

    static int[] board;
    static boolean[] columns;
    static boolean[] diagonal1;
    static boolean[] diagonal2;
    static int n;

    static boolean solve(int row) {
        if (row == n) {
            return true;
        }

        for (int col = 0; col < n; col++) {
            int d1 = row - col + n - 1;
            int d2 = row + col;

            if (!columns[col] && !diagonal1[d1]
                    && !diagonal2[d2]) {

                board[row] = col;
                columns[col] = true;
                diagonal1[d1] = true;
                diagonal2[d2] = true;

                if (solve(row + 1)) {
                    return true;
                }

                board[row] = -1;
                columns[col] = false;
                diagonal1[d1] = false;
                diagonal2[d2] = false;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter N: ");
        n = sc.nextInt();

        board = new int[n];
        columns = new boolean[n];
        diagonal1 = new boolean[2 * n - 1];
        diagonal2 = new boolean[2 * n - 1];

        for (int i = 0; i < n; i++) {
            board[i] = -1;
        }

        if (solve(0)) {
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < n; col++) {
                    if (board[row] == col) {
                        System.out.print("Q ");
                    } else {
                        System.out.print(". ");
                    }
                }
                System.out.println();
            }
        } else {
            System.out.println("No solution exists");
        }

        sc.close();
    }
}