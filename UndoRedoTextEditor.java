import java.util.Scanner;

public class UndoRedoTextEditor {

    static class Node {
        String text;
        Node prev, next;

        Node(String text) {
            this.text = text;
            prev = next = null;
        }
    }

    static Node head = null;
    static Node tail = null;
    static Node current = null;

    static int size = 0;
    static final int LIMIT = 10;

    // Add new text state
    static void addState(String text) {
        Node newNode = new Node(text);

        if (head == null) {
            head = tail = current = newNode;
            size++;
            System.out.println("Text saved successfully.");
            return;
        }

        // Remove future history after a new action
        Node temp = current.next;

        while (temp != null) {
            Node nextNode = temp.next;
            temp.prev = null;
            temp.next = null;
            temp = nextNode;
        }

        current.next = null;
        tail = current;

        newNode.prev = current;
        current.next = newNode;

        current = newNode;
        tail = newNode;
        size++;

        // Maintain maximum history limit
        if (size > LIMIT) {
            head = head.next;
            head.prev = null;
            size--;
        }

        System.out.println("Text saved successfully.");
    }

    // Undo operation
    static void undo() {
        if (current == null || current.prev == null) {
            System.out.println("Nothing to undo.");
            return;
        }

        current = current.prev;

        System.out.println("Undo successful.");
        displayCurrent();
    }

    // Redo operation
    static void redo() {
        if (current == null || current.next == null) {
            System.out.println("Nothing to redo.");
            return;
        }

        current = current.next;

        System.out.println("Redo successful.");
        displayCurrent();
    }

    // Display current text
    static void displayCurrent() {
        if (current == null) {
            System.out.println("Editor is empty.");
            return;
        }

        System.out.println("\nCurrent Text:");
        System.out.println(current.text);
    }

    // Display complete history
    static void displayHistory() {
        if (head == null) {
            System.out.println("No history available.");
            return;
        }

        Node temp = head;

        System.out.println("\n===== TEXT HISTORY =====");

        while (temp != null) {
            if (temp == current) {
                System.out.println(temp.text + " <-- Current State");
            } else {
                System.out.println(temp.text);
            }

            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\n===== UNDO/REDO TEXT EDITOR =====");
            System.out.println("1. Enter New Text");
            System.out.println("2. Undo");
            System.out.println("3. Redo");
            System.out.println("4. Display Current Text");
            System.out.println("5. Display Text History");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter Text: ");
                    String text = sc.nextLine();

                    addState(text);
                    break;

                case 2:
                    undo();
                    break;

                case 3:
                    redo();
                    break;

                case 4:
                    displayCurrent();
                    break;

                case 5:
                    displayHistory();
                    break;

                case 6:
                    System.out.println("Exiting Text Editor.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 6);

        sc.close();
    }
}