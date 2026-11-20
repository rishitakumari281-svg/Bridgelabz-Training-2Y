import java.util.Scanner;

public class StudentRecordManagement {

    static class Node {
        int rollNo, age;
        String name, grade;
        Node next;

        Node(int rollNo, String name, int age, String grade) {
            this.rollNo = rollNo;
            this.name = name;
            this.age = age;
            this.grade = grade;
            this.next = null;
        }
    }

    static Node head = null;

    // Add student at beginning
    static void addBeginning(int rollNo, String name, int age, String grade) {
        Node newNode = new Node(rollNo, name, age, grade);
        newNode.next = head;
        head = newNode;
        System.out.println("Student added successfully.");
    }

    // Add student at end
    static void addEnd(int rollNo, String name, int age, String grade) {
        Node newNode = new Node(rollNo, name, age, grade);

        if (head == null) {
            head = newNode;
            return;
        }

        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = newNode;
        System.out.println("Student added successfully.");
    }

    // Add student at specific position (1-based)
    static void addPosition(int rollNo, String name, int age, String grade, int pos) {
        if (pos < 1) {
            System.out.println("Invalid position.");
            return;
        }

        if (pos == 1) {
            addBeginning(rollNo, name, age, grade);
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

        Node newNode = new Node(rollNo, name, age, grade);
        newNode.next = temp.next;
        temp.next = newNode;

        System.out.println("Student added successfully.");
    }

    // Delete student by roll number
    static void deleteStudent(int rollNo) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        if (head.rollNo == rollNo) {
            head = head.next;
            System.out.println("Student deleted successfully.");
            return;
        }

        Node temp = head;

        while (temp.next != null && temp.next.rollNo != rollNo) {
            temp = temp.next;
        }

        if (temp.next == null) {
            System.out.println("Student not found.");
        } else {
            temp.next = temp.next.next;
            System.out.println("Student deleted successfully.");
        }
    }

    // Search student
    static void searchStudent(int rollNo) {
        Node temp = head;

        while (temp != null) {
            if (temp.rollNo == rollNo) {
                System.out.println("Student Found!");
                System.out.println("Roll No: " + temp.rollNo);
                System.out.println("Name: " + temp.name);
                System.out.println("Age: " + temp.age);
                System.out.println("Grade: " + temp.grade);
                return;
            }

            temp = temp.next;
        }

        System.out.println("Student not found.");
    }

    // Display all students
    static void display() {
        if (head == null) {
            System.out.println("No student records available.");
            return;
        }

        Node temp = head;

        while (temp != null) {
            System.out.println("\nRoll No: " + temp.rollNo);
            System.out.println("Name: " + temp.name);
            System.out.println("Age: " + temp.age);
            System.out.println("Grade: " + temp.grade);

            temp = temp.next;
        }
    }

    // Update student grade
    static void updateGrade(int rollNo, String grade) {
        Node temp = head;

        while (temp != null) {
            if (temp.rollNo == rollNo) {
                temp.grade = grade;
                System.out.println("Grade updated successfully.");
                return;
            }

            temp = temp.next;
        }

        System.out.println("Student not found.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\n===== STUDENT RECORD MANAGEMENT =====");
            System.out.println("1. Add at Beginning");
            System.out.println("2. Add at End");
            System.out.println("3. Add at Specific Position");
            System.out.println("4. Delete Student");
            System.out.println("5. Search Student");
            System.out.println("6. Display Students");
            System.out.println("7. Update Grade");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                case 2:
                case 3:
                    System.out.print("Enter Roll Number: ");
                    int roll = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Enter Grade: ");
                    String grade = sc.nextLine();

                    if (choice == 1) {
                        addBeginning(roll, name, age, grade);
                    } else if (choice == 2) {
                        addEnd(roll, name, age, grade);
                    } else {
                        System.out.print("Enter Position: ");
                        int pos = sc.nextInt();
                        addPosition(roll, name, age, grade, pos);
                    }
                    break;

                case 4:
                    System.out.print("Enter Roll Number to delete: ");
                    deleteStudent(sc.nextInt());
                    break;

                case 5:
                    System.out.print("Enter Roll Number to search: ");
                    searchStudent(sc.nextInt());
                    break;

                case 6:
                    display();
                    break;

                case 7:
                    System.out.print("Enter Roll Number: ");
                    int r = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Enter New Grade: ");
                    String g = sc.nextLine();

                    updateGrade(r, g);
                    break;

                case 8:
                    System.out.println("Exiting program.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 8);

        sc.close();
    }
}