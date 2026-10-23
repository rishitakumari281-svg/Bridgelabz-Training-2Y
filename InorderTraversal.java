import java.util.Scanner;
import java.util.Stack;

public class InorderTraversal {

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    static void recursiveInorder(Node root) {
        if (root == null) {
            return;
        }

        recursiveInorder(root.left);
        System.out.print(root.data + " ");
        recursiveInorder(root.right);
    }

    static void iterativeInorder(Node root) {
        Stack<Node> stack = new Stack<>();
        Node current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            System.out.print(current.data + " ");
            current = current.right;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        System.out.println("Recursive Inorder:");
        recursiveInorder(root);

        System.out.println("\nIterative Inorder:");
        iterativeInorder(root);

        sc.close();
    }
}