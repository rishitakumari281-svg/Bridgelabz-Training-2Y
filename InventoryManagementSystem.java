import java.util.Scanner;

public class InventoryManagementSystem {

    static class Node {
        String itemName;
        int itemId, quantity;
        double price;
        Node next;

        Node(String itemName, int itemId, int quantity, double price) {
            this.itemName = itemName;
            this.itemId = itemId;
            this.quantity = quantity;
            this.price = price;
            next = null;
        }
    }

    static Node head = null;

    // Add at beginning
    static void addBeginning(String name, int id, int quantity, double price) {
        Node newNode = new Node(name, id, quantity, price);
        newNode.next = head;
        head = newNode;

        System.out.println("Item added successfully.");
    }

    // Add at end
    static void addEnd(String name, int id, int quantity, double price) {
        Node newNode = new Node(name, id, quantity, price);

        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;

            while (temp.next != null) {
                temp = temp.next;
            }

            temp.next = newNode;
        }

        System.out.println("Item added successfully.");
    }

    // Add at specific position (1-based)
    static void addPosition(String name, int id, int quantity, double price, int pos) {
        if (pos < 1) {
            System.out.println("Invalid position.");
            return;
        }

        if (pos == 1) {
            addBeginning(name, id, quantity, price);
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

        Node newNode = new Node(name, id, quantity, price);
        newNode.next = temp.next;
        temp.next = newNode;

        System.out.println("Item added successfully.");
    }

    // Remove item by ID
    static void removeItem(int id) {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }

        if (head.itemId == id) {
            head = head.next;
            System.out.println("Item removed successfully.");
            return;
        }

        Node temp = head;

        while (temp.next != null && temp.next.itemId != id) {
            temp = temp.next;
        }

        if (temp.next == null) {
            System.out.println("Item not found.");
        } else {
            temp.next = temp.next.next;
            System.out.println("Item removed successfully.");
        }
    }

    // Update quantity
    static void updateQuantity(int id, int quantity) {
        Node temp = head;

        while (temp != null) {
            if (temp.itemId == id) {
                temp.quantity = quantity;
                System.out.println("Quantity updated successfully.");
                return;
            }

            temp = temp.next;
        }

        System.out.println("Item not found.");
    }

    // Search by ID
    static void searchById(int id) {
        Node temp = head;

        while (temp != null) {
            if (temp.itemId == id) {
                displayItem(temp);
                return;
            }

            temp = temp.next;
        }

        System.out.println("Item not found.");
    }

    // Search by name
    static void searchByName(String name) {
        Node temp = head;
        boolean found = false;

        while (temp != null) {
            if (temp.itemName.equalsIgnoreCase(name)) {
                displayItem(temp);
                found = true;
            }

            temp = temp.next;
        }

        if (!found) {
            System.out.println("Item not found.");
        }
    }

    // Display item details
    static void displayItem(Node temp) {
        System.out.println("\nItem Name: " + temp.itemName);
        System.out.println("Item ID: " + temp.itemId);
        System.out.println("Quantity: " + temp.quantity);
        System.out.println("Price: " + temp.price);
    }

    // Display all items
    static void display() {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }

        Node temp = head;

        while (temp != null) {
            displayItem(temp);
            temp = temp.next;
        }
    }

    // Calculate total inventory value
    static void totalValue() {
        double total = 0;
        Node temp = head;

        while (temp != null) {
            total += temp.price * temp.quantity;
            temp = temp.next;
        }

        System.out.println("Total Inventory Value: " + total);
    }

    // Sort by name or price
    static void sort(int choice, boolean ascending) {
        if (head == null || head.next == null) {
            System.out.println("Inventory sorted successfully.");
            return;
        }

        for (Node i = head; i.next != null; i = i.next) {
            for (Node j = i.next; j != null; j = j.next) {

                boolean swap = false;

                if (choice == 1) {
                    int result = i.itemName.compareToIgnoreCase(j.itemName);
                    swap = ascending ? result > 0 : result < 0;
                } else {
                    swap = ascending
                            ? i.price > j.price
                            : i.price < j.price;
                }

                if (swap) {
                    String name = i.itemName;
                    int id = i.itemId;
                    int quantity = i.quantity;
                    double price = i.price;

                    i.itemName = j.itemName;
                    i.itemId = j.itemId;
                    i.quantity = j.quantity;
                    i.price = j.price;

                    j.itemName = name;
                    j.itemId = id;
                    j.quantity = quantity;
                    j.price = price;
                }
            }
        }

        System.out.println("Inventory sorted successfully.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== INVENTORY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add at Beginning");
            System.out.println("2. Add at End");
            System.out.println("3. Add at Specific Position");
            System.out.println("4. Remove Item");
            System.out.println("5. Update Quantity");
            System.out.println("6. Search by Item ID");
            System.out.println("7. Search by Item Name");
            System.out.println("8. Display All Items");
            System.out.println("9. Calculate Total Inventory Value");
            System.out.println("10. Sort Inventory");
            System.out.println("11. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                case 2:
                case 3:
                    System.out.print("Enter Item Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Item ID: ");
                    int id = sc.nextInt();

                    System.out.print("Enter Quantity: ");
                    int quantity = sc.nextInt();

                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();

                    if (choice == 1) {
                        addBeginning(name, id, quantity, price);
                    } else if (choice == 2) {
                        addEnd(name, id, quantity, price);
                    } else {
                        System.out.print("Enter Position: ");
                        int pos = sc.nextInt();
                        addPosition(name, id, quantity, price, pos);
                    }
                    break;

                case 4:
                    System.out.print("Enter Item ID: ");
                    removeItem(sc.nextInt());
                    break;

                case 5:
                    System.out.print("Enter Item ID: ");
                    int searchId = sc.nextInt();

                    System.out.print("Enter New Quantity: ");
                    int newQuantity = sc.nextInt();

                    updateQuantity(searchId, newQuantity);
                    break;

                case 6:
                    System.out.print("Enter Item ID: ");
                    searchById(sc.nextInt());
                    break;

                case 7:
                    System.out.print("Enter Item Name: ");
                    searchByName(sc.nextLine());
                    break;

                case 8:
                    display();
                    break;

                case 9:
                    totalValue();
                    break;

                case 10:
                    System.out.println("1. Sort by Name");
                    System.out.println("2. Sort by Price");
                    int sortChoice = sc.nextInt();

                    System.out.println("1. Ascending");
                    System.out.println("2. Descending");
                    int order = sc.nextInt();

                    if (sortChoice == 1 || sortChoice == 2) {
                        sort(sortChoice, order == 1);
                        display();
                    } else {
                        System.out.println("Invalid sorting choice.");
                    }
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