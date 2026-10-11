
import java.util.*;

public class getCommon {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] nums1 = new int[n];

        for (int i = 0; i < n; i++) {
            nums1[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] nums2 = new int[m];

        for (int i = 0; i < m; i++) {
            nums2[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            int l = 0;
            int r = m - 1;

            while (l <= r) {
                int mid = l + (r - l) / 2;

                if (nums2[mid] == nums1[i]) {
                    System.out.println(nums1[i]);
                    return;
                } else if (nums2[mid] < nums1[i]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }

        System.out.println(-1);
    }
}

