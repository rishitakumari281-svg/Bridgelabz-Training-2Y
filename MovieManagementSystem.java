import java.util.Scanner;

public class MovieManagementSystem {

    static class Node {
        String title, director;
        int year;
        double rating;
        Node prev, next;

        Node(String title, String director, int year, double rating) {
            this.title = title;
            this.director = director;
            this.year = year;
            this.rating = rating;
            prev = next = null;
        }
    }

    static Node head = null;
    static Node tail = null;

    // Add at beginning
    static void addBeginning(String title, String director, int year, double rating) {
        Node newNode = new Node(title, director, year, rating);

        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }

        System.out.println("Movie added successfully.");
    }

    // Add at end
    static void addEnd(String title, String director, int year, double rating) {
        Node newNode = new Node(title, director, year, rating);

        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        System.out.println("Movie added successfully.");
    }

    // Add at specific position (1-based)
    static void addPosition(String title, String director, int year, double rating, int pos) {
        if (pos < 1) {
            System.out.println("Invalid position.");
            return;
        }

        if (pos == 1) {
            addBeginning(title, director, year, rating);
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
            addEnd(title, director, year, rating);
            return;
        }

        Node newNode = new Node(title, director, year, rating);

        newNode.next = temp.next;
        newNode.prev = temp;

        temp.next.prev = newNode;
        temp.next = newNode;

        System.out.println("Movie added successfully.");
    }

    // Remove movie by title
    static void removeMovie(String title) {
        Node temp = head;

        while (temp != null && !temp.title.equalsIgnoreCase(title)) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Movie not found.");
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

        System.out.println("Movie removed successfully.");
    }

    // Search by director
    static void searchDirector(String director) {
        Node temp = head;
        boolean found = false;

        while (temp != null) {
            if (temp.director.equalsIgnoreCase(director)) {
                displayMovie(temp);
                found = true;
            }
            temp = temp.next;
        }

        if (!found) {
            System.out.println("No movies found for this director.");
        }
    }

    // Search by rating
    static void searchRating(double rating) {
        Node temp = head;
        boolean found = false;

        while (temp != null) {
            if (temp.rating == rating) {
                displayMovie(temp);
                found = true;
            }
            temp = temp.next;
        }

        if (!found) {
            System.out.println("No movies found with this rating.");
        }
    }

    // Display movie details
    static void displayMovie(Node temp) {
        System.out.println("\nTitle: " + temp.title);
        System.out.println("Director: " + temp.director);
        System.out.println("Year: " + temp.year);
        System.out.println("Rating: " + temp.rating);
    }

    // Display forward
    static void displayForward() {
        if (head == null) {
            System.out.println("No movies available.");
            return;
        }

        Node temp = head;

        while (temp != null) {
            displayMovie(temp);
            temp = temp.next;
        }
    }

    // Display reverse
    static void displayReverse() {
        if (tail == null) {
            System.out.println("No movies available.");
            return;
        }

        Node temp = tail;

        while (temp != null) {
            displayMovie(temp);
            temp = temp.prev;
        }
    }

    // Update rating
    static void updateRating(String title, double rating) {
        Node temp = head;

        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                temp.rating = rating;
                System.out.println("Rating updated successfully.");
                return;
            }

            temp = temp.next;
        }

        System.out.println("Movie not found.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== MOVIE MANAGEMENT SYSTEM =====");
            System.out.println("1. Add at Beginning");
            System.out.println("2. Add at End");
            System.out.println("3. Add at Specific Position");
            System.out.println("4. Remove Movie");
            System.out.println("5. Search by Director");
            System.out.println("6. Search by Rating");
            System.out.println("7. Display Forward");
            System.out.println("8. Display Reverse");
            System.out.println("9. Update Rating");
            System.out.println("10. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                case 2:
                case 3:
                    System.out.print("Enter Movie Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Director: ");
                    String director = sc.nextLine();

                    System.out.print("Enter Release Year: ");
                    int year = sc.nextInt();

                    System.out.print("Enter Rating: ");
                    double rating = sc.nextDouble();

                    if (choice == 1) {
                        addBeginning(title, director, year, rating);
                    } else if (choice == 2) {
                        addEnd(title, director, year, rating);
                    } else {
                        System.out.print("Enter Position: ");
                        int pos = sc.nextInt();
                        addPosition(title, director, year, rating, pos);
                    }
                    break;

                case 4:
                    System.out.print("Enter Movie Title: ");
                    removeMovie(sc.nextLine());
                    break;

                case 5:
                    System.out.print("Enter Director Name: ");
                    searchDirector(sc.nextLine());
                    break;

                case 6:
                    System.out.print("Enter Rating: ");
                    searchRating(sc.nextDouble());
                    break;

                case 7:
                    displayForward();
                    break;

                case 8:
                    displayReverse();
                    break;

                case 9:
                    System.out.print("Enter Movie Title: ");
                    String t = sc.nextLine();

                    System.out.print("Enter New Rating: ");
                    double r = sc.nextDouble();

                    updateRating(t, r);
                    break;

                case 10:
                    System.out.println("Exiting program.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 10);

        sc.close();
    }
}