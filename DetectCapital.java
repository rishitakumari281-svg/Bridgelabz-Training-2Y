import java.util.*;

public class DetectCapital {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String word = sc.next();

        int count = 0;

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) >= 'A' && word.charAt(i) <= 'Z') {
                count++;
            }
        }

        boolean ans = count == 0 || 
                      count == word.length() || 
                      (count == 1 && word.charAt(0) >= 'A' && word.charAt(0) <= 'Z');

        System.out.println(ans);
    }
}
