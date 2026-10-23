import java.util.Scanner;

public class DirectorySizeIterative {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] sizes = {100, 50, 10, 200, 20};
        int total = 0;

        for (int i = 0; i < sizes.length; i++) {
            total += sizes[i];
        }

        System.out.println("Total Directory Size: " + total + " KB");

        sc.close();
    }
}