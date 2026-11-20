import java.util.Scanner;

public class RoundRobinScheduling {

    static class Node {
        int processId, burstTime, priority;
        int remainingTime;
        Node next;

        Node(int processId, int burstTime, int priority) {
            this.processId = processId;
            this.burstTime = burstTime;
            this.priority = priority;
            this.remainingTime = burstTime;
            next = null;
        }
    }

    static Node head = null;

    // Add process at end
    static void addProcess(int id, int burstTime, int priority) {
        Node newNode = new Node(id, burstTime, priority);

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

        System.out.println("Process added successfully.");
    }

    // Display processes
    static void display() {
        if (head == null) {
            System.out.println("No processes available.");
            return;
        }

        Node temp = head;

        System.out.println("\nProcess ID\tBurst Time\tPriority\tRemaining Time");

        do {
            System.out.println(temp.processId + "\t\t"
                    + temp.burstTime + "\t\t"
                    + temp.priority + "\t\t"
                    + temp.remainingTime);

            temp = temp.next;

        } while (temp != head);
    }

    // Remove process by ID
    static void removeProcess(int id) {
        if (head == null) {
            return;
        }

        Node temp = head;
        Node prev = null;

        do {
            if (temp.processId == id) {

                if (temp.next == temp) {
                    head = null;
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
                }

                return;
            }

            prev = temp;
            temp = temp.next;

        } while (temp != head);
    }

    // Round Robin Scheduling
    static void roundRobin(int quantum) {
        if (head == null) {
            System.out.println("No processes to execute.");
            return;
        }

        if (quantum <= 0) {
            System.out.println("Time quantum must be positive.");
            return;
        }

        int totalProcesses = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        Node temp = head;

        do {
            totalProcesses++;
            temp = temp.next;
        } while (temp != head);

        int[] ids = new int[totalProcesses];
        int[] burst = new int[totalProcesses];
        int[] waiting = new int[totalProcesses];
        int[] turnaround = new int[totalProcesses];

        temp = head;

        for (int i = 0; i < totalProcesses; i++) {
            ids[i] = temp.processId;
            burst[i] = temp.burstTime;
            temp = temp.next;
        }

        int completed = 0;
        int currentTime = 0;

        System.out.println("\n===== ROUND ROBIN EXECUTION =====");

        while (completed < totalProcesses) {

            temp = head;

            if (temp == null) {
                break;
            }

            do {
                if (temp.remainingTime > 0) {

                    int executionTime = Math.min(quantum, temp.remainingTime);

                    System.out.println("Process P" + temp.processId
                            + " executes for " + executionTime + " units.");

                    temp.remainingTime -= executionTime;
                    currentTime += executionTime;

                    if (temp.remainingTime == 0) {

                        for (int i = 0; i < totalProcesses; i++) {
                            if (ids[i] == temp.processId) {
                                turnaround[i] = currentTime;
                                waiting[i] = turnaround[i] - burst[i];
                                totalWaitingTime += waiting[i];
                                totalTurnaroundTime += turnaround[i];
                                break;
                            }
                        }

                        System.out.println("Process P" + temp.processId + " completed.");

                        removeProcess(temp.processId);
                        completed++;

                        break;
                    }
                }

                temp = temp.next;

            } while (temp != head);

            System.out.println("\nProcesses after this round:");
            display();
        }

        System.out.println("\n===== PROCESS DETAILS =====");
        System.out.println("Process\tBurst Time\tWaiting Time\tTurnaround Time");

        for (int i = 0; i < totalProcesses; i++) {
            System.out.println("P" + ids[i] + "\t"
                    + burst[i] + "\t\t"
                    + waiting[i] + "\t\t"
                    + turnaround[i]);
        }

        System.out.println("\nAverage Waiting Time: "
                + (double) totalWaitingTime / totalProcesses);

        System.out.println("Average Turnaround Time: "
                + (double) totalTurnaroundTime / totalProcesses);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\n===== ROUND ROBIN SCHEDULING =====");
            System.out.println("1. Add Process");
            System.out.println("2. Display Processes");
            System.out.println("3. Execute Round Robin Scheduling");
            System.out.println("4. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Process ID: ");
                    int id = sc.nextInt();

                    System.out.print("Enter Burst Time: ");
                    int burst = sc.nextInt();

                    System.out.print("Enter Priority: ");
                    int priority = sc.nextInt();

                    if (burst <= 0) {
                        System.out.println("Burst time must be positive.");
                    } else {
                        addProcess(id, burst, priority);
                    }
                    break;

                case 2:
                    display();
                    break;

                case 3:
                    System.out.print("Enter Time Quantum: ");
                    int quantum = sc.nextInt();

                    roundRobin(quantum);
                    break;

                case 4:
                    System.out.println("Exiting program.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 4);

        sc.close();
    }
}