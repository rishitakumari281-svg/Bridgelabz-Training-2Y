import java.util.Scanner;

public class LibraryManagementSystem {

    static class Node {
        String title, author, genre;
        int bookId;
        boolean available;
        Node prev, next;

        Node(String title, String author, String genre, int bookId, boolean available) {
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.bookId = bookId;
            this.available = available;
            prev = next = null;
        }
    }

    static Node head = null;
    static Node tail = null;

    // Add at beginning
    static void addBeginning(String title, String author, String genre, int id, boolean available) {
        Node newNode = new Node(title, author, genre, id, available);

        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }

        System.out.println("Book added successfully.");
    }

    // Add at end
    static void addEnd(String title, String author, String genre, int id, boolean available) {
        Node newNode = new Node(title, author, genre, id, available);

        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        System.out.println("Book added successfully.");
    }

    // Add at specific position (1-based)
    static void addPosition(String title, String author, String genre, int id, boolean available, int pos) {
        if (pos < 1) {
            System.out.println("Invalid position.");
            return;
        }

        if (pos == 1) {
            addBeginning(title, author, genre, id, available);
            return;
        }

        Node temp = head;

        for (int i = 1; i < pos - 1 && temp != null; i++) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Invalid position.");
            return;
        }

        if (temp == tail) {
            addEnd(title, author, genre, id, available);
            return;
        }

        Node newNode = new Node(title, author, genre, id, available);

        newNode.next = temp.next;
        newNode.prev = temp;

        temp.next.prev = newNode;
        temp.next = newNode;

        System.out.println("Book added successfully.");
    }

    // Remove book by ID
    static void removeBook(int id) {
        Node temp = head;

        while (temp != null && temp.bookId != id) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Book not found.");
            return;
        }

        if (temp == head) {
            head = temp.next;
        }

        if (temp == tail) {
            tail = temp.prev;
        }

        if (temp.prev != null) {
            temp.prev.next = temp.next;
        }

        if (temp.next != null) {
            temp.next.prev = temp.prev;
        }

        System.out.println("Book removed successfully.");
    }

    // Display book details
    static void displayBook(Node temp) {
        System.out.println("\nBook ID: " + temp.bookId);
        System.out.println("Title: " + temp.title);
        System.out.println("Author: " + temp.author);
        System.out.println("Genre: " + temp.genre);
        System.out.println("Availability: " + (temp.available ? "Available" : "Not Available"));
    }

    // Search by title
    static void searchByTitle(String title) {
        Node temp = head;
        boolean found = false;

        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                displayBook(temp);
                found = true;
            }

            temp = temp.next;
        }

        if (!found) {
            System.out.println("Book not found.");
        }
    }

    // Search by author
    static void searchByAuthor(String author) {
        Node temp = head;
        boolean found = false;

        while (temp != null) {
            if (temp.author.equalsIgnoreCase(author)) {
                displayBook(temp);
                found = true;
            }

            temp = temp.next;
        }

        if (!found) {
            System.out.println("No books found by this author.");
        }
    }

    // Update availability
    static void updateAvailability(int id, boolean status) {
        Node temp = head;

        while (temp != null) {
            if (temp.bookId == id) {
                temp.available = status;
                System.out.println("Availability updated successfully.");
                return;
            }

            temp = temp.next;
        }

        System.out.println("Book not found.");
    }

    // Display forward
    static void displayForward() {
        if (head == null) {
            System.out.println("Library is empty.");
            return;
        }

        Node temp = head;

        while (temp != null) {
            displayBook(temp);
            temp = temp.next;
        }
    }

    // Display reverse
    static void displayReverse() {
        if (tail == null) {
            System.out.println("Library is empty.");
            return;
        }

        Node temp = tail;

        while (temp != null) {
            displayBook(temp);
            temp = temp.prev;
        }
    }

    // Count total books
    static void countBooks() {
        int count = 0;
        Node temp = head;

        while (temp != null) {
            count++;
            temp = temp.next;
        }

        System.out.println("Total number of books: " + count);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add at Beginning");
            System.out.println("2. Add at End");
            System.out.println("3. Add at Specific Position");
            System.out.println("4. Remove Book");
            System.out.println("5. Search by Title");
            System.out.println("6. Search by Author");
            System.out.println("7. Update Availability");
            System.out.println("8. Display Forward");
            System.out.println("9. Display Reverse");
            System.out.println("10. Count Total Books");
            System.out.println("11. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                case 2:
                case 3:
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Book Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();

                    System.out.print("Enter Genre: ");
                    String genre = sc.nextLine();

                    System.out.print("Is Book Available? (true/false): ");
                    boolean available = sc.nextBoolean();

                    if (choice == 1) {
                        addBeginning(title, author, genre, id, available);
                    } else if (choice == 2) {
                        addEnd(title, author, genre, id, available);
                    } else {
                        System.out.print("Enter Position: ");
                        int pos = sc.nextInt();

                        addPosition(title, author, genre, id, available, pos);
                    }
                    break;

                case 4:
                    System.out.print("Enter Book ID to remove: ");
                    removeBook(sc.nextInt());
                    break;

                case 5:
                    System.out.print("Enter Book Title: ");
                    searchByTitle(sc.nextLine());
                    break;

                case 6:
                    System.out.print("Enter Author Name: ");
                    searchByAuthor(sc.nextLine());
                    break;

                case 7:
                    System.out.print("Enter Book ID: ");
                    int bookId = sc.nextInt();

                    System.out.print("Enter Availability (true/false): ");
                    boolean status = sc.nextBoolean();

                    updateAvailability(bookId, status);
                    break;

                case 8:
                    displayForward();
                    break;

                case 9:
                    displayReverse();
                    break;

                case 10:
                    countBooks();
                    break;

                case 11:
                    System.out.println("Exiting program.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 11);

        sc.close();
    }
}