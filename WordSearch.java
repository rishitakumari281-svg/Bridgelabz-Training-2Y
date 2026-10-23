import java.util.Scanner;

public class WordSearch {

    static boolean search(char[][] grid, String word, int row,
                          int col, int index, boolean[][] visited) {

        if (index == word.length()) {
            return true;
        }

        if (row < 0 || col < 0 || row >= grid.length
                || col >= grid[0].length
                || visited[row][col]
                || grid[row][col] != word.charAt(index)) {
            return false;
        }

        visited[row][col] = true;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                if (search(grid, word, row + i, col + j,
                        index + 1, visited)) {
                    return true;
                }
            }
        }

        visited[row][col] = false;
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char[][] grid = {
            {'C', 'A', 'T', 'S'},
            {'O', 'R', 'E', 'A'},
            {'D', 'E', 'A', 'M'},
            {'E', 'L', 'L', 'S'}
        };

        System.out.print("Enter word: ");
        String word = sc.next().toUpperCase();

        boolean found = false;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                boolean[][] visited = new boolean[4][4];

                if (search(grid, word, i, j, 0, visited)) {
                    found = true;
                    break;
                }
            }

            if (found) {
                break;
            }
        }

        if (found) {
            System.out.println("Word Found");
        } else {
            System.out.println("Word Not Found");
        }

        sc.close();
    }
}