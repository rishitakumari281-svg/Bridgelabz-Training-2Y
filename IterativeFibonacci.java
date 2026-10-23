import java.util.Scanner;

public class IterativeFibonacci {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter n: ");
        int n = sc.nextInt();

        if (n < 0) {
            System.out.println("Enter a non-negative number.");
        } else if (n <= 1) {
            System.out.println(n);
        } else {
            int prev = 0;
            int curr = 1;

            for (int i = 2; i <= n; i++) {
                int next = prev + curr;
                prev = curr;
                curr = next;
            }

            System.out.println("Fibonacci number: " + curr);
        }

        sc.close();
    }
}