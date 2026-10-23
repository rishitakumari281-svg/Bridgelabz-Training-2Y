import java.util.Scanner;

public class PowerFunction {

    static int power(int x, int n) {
        if (n == 0) {
            return 1;
        }

        return x * power(x, n - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter base: ");
        int x = sc.nextInt();

        System.out.print("Enter exponent: ");
        int n = sc.nextInt();

        System.out.println("Result: " + power(x, n));

        sc.close();
    }
}