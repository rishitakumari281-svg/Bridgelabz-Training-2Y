import java.util.*;

public class attendance {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        int absent = 0;
        int late = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == 'A') {
                absent++;
                late = 0;
            }
            else if (s.charAt(i) == 'L') {
                late++;
            }
            else {
                late = 0;
            }

            if (absent >= 2 || late >= 3) {
                System.out.println(false);
                return;
            }
        }

        System.out.println(true);
    }
}