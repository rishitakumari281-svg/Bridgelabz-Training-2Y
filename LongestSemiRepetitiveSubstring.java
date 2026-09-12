import java.util.*;

public class LongestSemiRepetitiveSubstring {
    public static int longestSemiRepetitiveSubstring(String s) {
        int l = 0;
        int r = 0;
        int max = 1;

        for (int i = 1; i < s.length(); i++) {

            if (s.charAt(i - 1) == s.charAt(i)) {
                r++;
            }

            while (r > 1) {
                if (s.charAt(l) == s.charAt(l + 1)) {
                    r--;
                }
                l++;
            }

            max = Math.max(max, i - l + 1);
        }

        return max;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();

        System.out.println(longestSemiRepetitiveSubstring(s));

        sc.close();
    }
}