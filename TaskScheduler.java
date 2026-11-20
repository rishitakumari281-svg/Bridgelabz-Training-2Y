import java.util.Scanner;

public class TaskScheduler {

    static class Node {
        int taskId, priority;
        String taskName, dueDate;
        Node next;

        Node(int taskId, String taskName, int priority, String dueDate) {
            this.taskId = taskId;
            this.taskName = taskName;
            this.priority = priority;
            this.dueDate = dueDate;
            next = null;
        }
    }

    static Node head = null;
    static Node current = null;

    // Add at beginning
    static void addBeginning(int id, String name, int priority, String date) {
        Node newNode = new Node(id, name, priority, date);

        if (head == null) {
            head = newNode;
            newNode.next = head;
        } else {
            Node temp = head;

            while (temp.next != head) {
                temp = temp.next;
            }

            newNode.next = head;
            temp.next = newNode;
            head = newNode;
        }

        if (current == null) {
            current = head;
        }

        System.out.println("Task added successfully.");
    }

    // Add at end
    static void addEnd(int id, String name, int priority, String date) {
        Node newNode = new Node(id, name, priority, date);

        if (head == null) {
            head = newNode;
            newNode.next = head;
        } else {
            Node temp = head;

            while (temp.next != head) {
                temp = temp.next;
            }

            temp.next = newNode;
            newNode.next = head;
        }

        if (current == null) {
            current = head;
        }

        System.out.println("Task added successfully.");
    }

    // Add at specific position (1-based)
    static void addPosition(int id, String name, int priority, String date, int pos) {
        if (pos < 1) {
            System.out.println("Invalid position.");
            return;
        }

        if (pos == 1) {
            addBeginning(id, name, priority, date);
            return;
        }

        if (head == null) {
            System.out.println("Invalid position.");
            return;
        }

        Node temp = head;

        for (int i = 1; i < pos - 1 && temp.next != head; i++) {
            temp = temp.next;
        }

        if (temp.next == head && pos > 2) {
            System.out.println("Invalid position.");
            return;
        }

        Node newNode = new Node(id, name, priority, date);

        newNode.next = temp.next;
        temp.next = newNode;

        System.out.println("Task added successfully.");
    }

    // Remove task by ID
    static void removeTask(int id) {
        if (head == null) {
            System.out.println("Task list is empty.");
            return;
        }

        Node temp = head;
        Node prev = null;

        do {
            if (temp.taskId == id) {
                if (temp == head && temp.next == head) {
                    head = null;
                    current = null;
                } else {
                    if (prev == null) {
                        Node last = head;

                        while (last.next != head) {
                            last = last.next;
                        }

                        head = head.next;
                        last.next = head;
                    } else {
                        prev.next = temp.next;
                    }

                    if (current == temp) {
                        current = temp.next == temp ? null : temp.next;
                    }
                }

                System.out.println("Task removed successfully.");
                return;
            }

            prev = temp;
            temp = temp.next;

        } while (temp != head);

        System.out.println("Task not found.");
    }

    // View current task
    static void viewCurrentTask() {
        if (current == null) {
            System.out.println("No tasks available.");
            return;
        }

        System.out.println("\nCurrent Task:");
        displayTask(current);
    }

    // Move to next task
    static void nextTask() {
        if (current == null) {
            System.out.println("No tasks available.");
            return;
        }

        current = current.next;
        viewCurrentTask();
    }

    // Search by priority
    static void searchPriority(int priority) {
        if (head == null) {
            System.out.println("Task list is empty.");
            return;
        }

        Node temp = head;
        boolean found = false;

        do {
            if (temp.priority == priority) {
                displayTask(temp);
                found = true;
            }

            temp = temp.next;

        } while (temp != head);

        if (!found) {
            System.out.println("No tasks found with this priority.");
        }
    }

    // Display task details
    static void displayTask(Node temp) {
        System.out.println("Task ID: " + temp.taskId);
        System.out.println("Task Name: " + temp.taskName);
        System.out.println("Priority: " + temp.priority);
        System.out.println("Due Date: " + temp.dueDate);
    }

    // Display all tasks
    static void displayAll() {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }

        Node temp = head;

        System.out.println("\n===== ALL TASKS =====");

        do {
            displayTask(temp);
            System.out.println();
            temp = temp.next;

        } while (temp != head);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== TASK SCHEDULER =====");
            System.out.println("1. Add at Beginning");
            System.out.println("2. Add at End");
            System.out.println("3. Add at Specific Position");
            System.out.println("4. Remove Task");
            System.out.println("5. View Current Task");
            System.out.println("6. Move to Next Task");
            System.out.println("7. Display All Tasks");
            System.out.println("8. Search by Priority");
            System.out.println("9. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                case 2:
                case 3:
                    System.out.print("Enter Task ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Task Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Priority: ");
                    int priority = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Due Date: ");
                    String date = sc.nextLine();

                    if (choice == 1) {
                        addBeginning(id, name, priority, date);
                    } else if (choice == 2) {
                        addEnd(id, name, priority, date);
                    } else {
                        System.out.print("Enter Position: ");
                        int pos = sc.nextInt();

                        addPosition(id, name, priority, date, pos);
                    }
                    break;

                case 4:
                    System.out.print("Enter Task ID to remove: ");
                    removeTask(sc.nextInt());
                    break;

                case 5:
                    viewCurrentTask();
                    break;

                case 6:
                    nextTask();
                    break;

                case 7:
                    displayAll();
                    break;

                case 8:
                    System.out.print("Enter Priority: ");
                    searchPriority(sc.nextInt());
                    break;

                case 9:
                    System.out.println("Exiting program.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 9);

        sc.close();
    }
}