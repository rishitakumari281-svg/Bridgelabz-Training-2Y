import java.util.*;

public class countingBits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] bits = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            bits[i] = bits[i / 2] + (i % 2);
        }

        for (int i = 0; i <= n; i++) {
            System.out.print(bits[i] + " ");
        }

        sc.close();
    }
}