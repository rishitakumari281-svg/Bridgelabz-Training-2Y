import java.util.Scanner;

public class GraphColoring {

    static boolean isSafe(int vertex, int color,
                          int[][] graph, int[] colors, int n) {

        for (int i = 0; i < n; i++) {
            if (graph[vertex][i] == 1 && colors[i] == color) {
                return false;
            }
        }

        return true;
    }

    static boolean solve(int vertex, int[][] graph,
                         int[] colors, int n, int m) {

        if (vertex == n) {
            return true;
        }

        for (int color = 1; color <= m; color++) {

            if (isSafe(vertex, color, graph, colors, n)) {
                colors[vertex] = color;

                if (solve(vertex + 1, graph, colors, n, m)) {
                    return true;
                }

                // Backtrack
                colors[vertex] = 0;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();

        int[][] graph = new int[n][n];

        System.out.println("Enter adjacency matrix:");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        int[] colors = new int[n];
        int minColors = -1;

        // Try increasing numbers of colors
        for (int m = 1; m <= n; m++) {
            colors = new int[n];

            if (solve(0, graph, colors, n, m)) {
                minColors = m;
                break;
            }
        }

        if (minColors == -1) {
            System.out.println("No coloring exists.");
        } else {
            System.out.println("Minimum colors required: " + minColors);

            for (int i = 0; i < n; i++) {
                System.out.println("Vertex " + i +
                                   " -> Color " + colors[i]);
            }
        }

        sc.close();
    }
}