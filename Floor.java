import java.util.*;

public class Floor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int x = sc.nextInt();

        int l = 0;
        int r = n - 1;

        int floor = -1;
        int ceil = -1;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (nums[mid] == x) {
                floor = x;
                ceil = x;
                break;
            } 
            else if (nums[mid] < x) {
                floor = nums[mid];
                l = mid + 1;
            } 
            else {
                ceil = nums[mid];
                r = mid - 1;
            }
        }

        System.out.println(floor + " " + ceil);
    }
}