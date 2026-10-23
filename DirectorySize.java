import java.util.Scanner;

public class DirectorySize {

    static int calculateSize(int[] sizes, int n) {
        if (n == 0) {
            return 0;
        }

        return sizes[n - 1] + calculateSize(sizes, n - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] sizes = {100, 50, 10, 200, 20};

        System.out.println("Total Directory Size: "
                + calculateSize(sizes, sizes.length) + " KB");

        sc.close();
    }
}