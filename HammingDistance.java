import java.util.*;

public class HammingDistance {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int x = sc.nextInt();
        int y = sc.nextInt();

        int count = 0;

        while (x != 0 || y != 0) {

            int a = x % 2;
            int b = y % 2;

            if (a != b)
                count++;

            x = x / 2;
            y = y / 2;
        }

        System.out.println(count);
    }
}