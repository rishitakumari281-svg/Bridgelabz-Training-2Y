import java.util.Scanner;

public class RatInMaze {

    static boolean solve(int[][] maze, int[][] path,
                         int row, int col, int n) {

        if (row < 0 || col < 0 ||
            row >= n || col >= n ||
            maze[row][col] == 0 ||
            path[row][col] == 1) {
            return false;
        }

        path[row][col] = 1;

        if (row == n - 1 && col == n - 1) {
            return true;
        }

        // Down
        if (solve(maze, path, row + 1, col, n)) {
            return true;
        }

        // Right
        if (solve(maze, path, row, col + 1, n)) {
            return true;
        }

        // Up
        if (solve(maze, path, row - 1, col, n)) {
            return true;
        }

        // Left
        if (solve(maze, path, row, col - 1, n)) {
            return true;
        }

        // Backtrack
        path[row][col] = 0;

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter maze size: ");
        int n = sc.nextInt();

        int[][] maze = new int[n][n];
        int[][] path = new int[n][n];

        System.out.println("Enter maze (1=open, 0=blocked):");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maze[i][j] = sc.nextInt();
            }
        }

        if (solve(maze, path, 0, 0, n)) {
            System.out.println("Path found:");

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(path[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("No path exists.");
        }

        sc.close();
    }
}