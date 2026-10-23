import java.util.Scanner;

public class OptimizedPower {

    static long power(long x, int n) {
        if (n == 0) {
            return 1;
        }

        long half = power(x, n / 2);

        if (n % 2 == 0) {
            return half * half;
        }

        return x * half * half;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter base: ");
        long x = sc.nextLong();

        System.out.print("Enter exponent: ");
        int n = sc.nextInt();

        if (n < 0) {
            System.out.println("Enter a non-negative exponent.");
        } else {
            System.out.println("Result: " + power(x, n));
        }

        sc.close();
    }
}