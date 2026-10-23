import java.util.Scanner;
import java.util.ArrayList;

public class SubsetSum {

    static void findSubsets(int[] arr, int index,
                            int target, ArrayList<Integer> list) {

        if (target == 0) {
            System.out.println(list);
            return;
        }

        if (index == arr.length || target < 0) {
            return;
        }

        // Include the current element
        list.add(arr[index]);

        findSubsets(arr, index + 1,
                    target - arr[index], list);

        // Backtrack: remove the current element
        list.remove(list.size() - 1);

        // Exclude the current element
        findSubsets(arr, index + 1, target, list);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter array elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("Enter target sum: ");
        int target = sc.nextInt();

        findSubsets(arr, 0, target, new ArrayList<>());

        sc.close();
    }
}