import java.util.ArrayList;

class WordSearchAllPaths {

    static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};

    static void search(char[][] grid, String word,
                       int row, int col, int index,
                       boolean[][] visited,
                       ArrayList<String> path) {

        if (row < 0 || col < 0 ||
            row >= grid.length || col >= grid[0].length ||
            visited[row][col] ||
            grid[row][col] != word.charAt(index)) {
            return;
        }

        visited[row][col] = true;
        path.add("(" + row + "," + col + ")");

        if (index == word.length() - 1) {
            System.out.println(path);
        } else {
            for (int i = 0; i < 8; i++) {
                search(grid, word,
                       row + dr[i], col + dc[i], index + 1,
                       visited, path);
            }
        }

        // Backtrack
        path.remove(path.size() - 1);
        visited[row][col] = false;
    }
}