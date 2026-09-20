import java.util.*;

public class ReverseBits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long n = sc.nextLong();
        long ans = 0;

        for (int i = 0; i < 32; i++) {
            ans = (ans << 1) | (n & 1);
            n = n >>> 1;
        }

        System.out.println(ans);
    }
}
