import java.util.Scanner;

class Ticket {
    int ticketId;
    String customerName, movieName, seatNumber, bookingTime;
    Ticket next;

    Ticket(int id, String customer, String movie, String seat, String time) {
        ticketId = id;
        customerName = customer;
        movieName = movie;
        seatNumber = seat;
        bookingTime = time;
        next = null;
    }
}

public class OnlineTicketReservationSystem {
    static Ticket head = null, tail = null;
    static Scanner sc = new Scanner(System.in);

    static void addTicket() {
        System.out.print("Enter Ticket ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (searchTicket(id)) {
            System.out.println("Ticket ID already exists!");
            return;
        }

        System.out.print("Enter Customer Name: ");
        String customer = sc.nextLine();

        System.out.print("Enter Movie Name: ");
        String movie = sc.nextLine();

        System.out.print("Enter Seat Number: ");
        String seat = sc.nextLine();

        System.out.print("Enter Booking Time: ");
        String time = sc.nextLine();

        Ticket newTicket = new Ticket(id, customer, movie, seat, time);

        if (head == null) {
            head = tail = newTicket;
            tail.next = head;
        } else {
            tail.next = newTicket;
            tail = newTicket;
            tail.next = head;
        }

        System.out.println("Ticket booked successfully!");
    }

    static boolean searchTicket(int id) {
        if (head == null)
            return false;

        Ticket temp = head;

        do {
            if (temp.ticketId == id)
                return true;

            temp = temp.next;
        } while (temp != head);

        return false;
    }

    static void removeTicket() {
        if (head == null) {
            System.out.println("No tickets available.");
            return;
        }

        System.out.print("Enter Ticket ID to cancel: ");
        int id = sc.nextInt();

        Ticket current = head;
        Ticket previous = tail;

        do {
            if (current.ticketId == id) {
                if (head == tail) {
                    head = tail = null;
                } else if (current == head) {
                    head = head.next;
                    tail.next = head;
                } else {
                    previous.next = current.next;

                    if (current == tail)
                        tail = previous;

                    tail.next = head;
                }

                System.out.println("Ticket cancelled successfully!");
                return;
            }

            previous = current;
            current = current.next;

        } while (current != head);

        System.out.println("Ticket not found.");
    }

    static void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }

        Ticket temp = head;

        System.out.println("\n--- Booked Tickets ---");

        do {
            System.out.println("Ticket ID: " + temp.ticketId);
            System.out.println("Customer Name: " + temp.customerName);
            System.out.println("Movie Name: " + temp.movieName);
            System.out.println("Seat Number: " + temp.seatNumber);
            System.out.println("Booking Time: " + temp.bookingTime);
            System.out.println("----------------------");

            temp = temp.next;
        } while (temp != head);
    }

    static void searchByCustomer() {
        if (head == null) {
            System.out.println("No tickets available.");
            return;
        }

        sc.nextLine();
        System.out.print("Enter Customer Name: ");
        String name = sc.nextLine();

        Ticket temp = head;
        boolean found = false;

        do {
            if (temp.customerName.equalsIgnoreCase(name)) {
                System.out.println("Ticket ID: " + temp.ticketId);
                System.out.println("Movie: " + temp.movieName);
                System.out.println("Seat: " + temp.seatNumber);
                System.out.println("Booking Time: " + temp.bookingTime);
                found = true;
            }

            temp = temp.next;
        } while (temp != head);

        if (!found)
            System.out.println("No ticket found for this customer.");
    }

    static void searchByMovie() {
        if (head == null) {
            System.out.println("No tickets available.");
            return;
        }

        sc.nextLine();
        System.out.print("Enter Movie Name: ");
        String movie = sc.nextLine();

        Ticket temp = head;
        boolean found = false;

        do {
            if (temp.movieName.equalsIgnoreCase(movie)) {
                System.out.println("Ticket ID: " + temp.ticketId);
                System.out.println("Customer: " + temp.customerName);
                System.out.println("Seat: " + temp.seatNumber);
                System.out.println("Booking Time: " + temp.bookingTime);
                found = true;
            }

            temp = temp.next;
        } while (temp != head);

        if (!found)
            System.out.println("No tickets found for this movie.");
    }

    static void countTickets() {
        if (head == null) {
            System.out.println("Total booked tickets: 0");
            return;
        }

        int count = 0;
        Ticket temp = head;

        do {
            count++;
            temp = temp.next;
        } while (temp != head);

        System.out.println("Total booked tickets: " + count);
    }

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n--- Online Ticket Reservation System ---");
            System.out.println("1. Add Ticket");
            System.out.println("2. Remove Ticket");
            System.out.println("3. Display Tickets");
            System.out.println("4. Search by Customer Name");
            System.out.println("5. Search by Movie Name");
            System.out.println("6. Count Total Tickets");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addTicket();
                    break;

                case 2:
                    removeTicket();
                    break;

                case 3:
                    displayTickets();
                    break;

                case 4:
                    searchByCustomer();
                    break;

                case 5:
                    searchByMovie();
                    break;

                case 6:
                    countTickets();
                    break;

                case 7:
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 7);

        sc.close();
    }
}