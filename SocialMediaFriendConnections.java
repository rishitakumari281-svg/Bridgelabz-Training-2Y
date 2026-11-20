import java.util.Scanner;
import java.util.ArrayList;

public class SocialMediaFriendConnections {

    static class Node {
        int userId, age;
        String name;
        ArrayList<Integer> friends;
        Node next;

        Node(int userId, String name, int age) {
            this.userId = userId;
            this.name = name;
            this.age = age;
            friends = new ArrayList<>();
            next = null;
        }
    }

    static Node head = null;

    // Add a new user
    static void addUser(int id, String name, int age) {
        if (findUser(id) != null) {
            System.out.println("User ID already exists.");
            return;
        }

        Node newNode = new Node(id, name, age);

        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;

            while (temp.next != null) {
                temp = temp.next;
            }

            temp.next = newNode;
        }

        System.out.println("User added successfully.");
    }

    // Find user by ID
    static Node findUser(int id) {
        Node temp = head;

        while (temp != null) {
            if (temp.userId == id) {
                return temp;
            }

            temp = temp.next;
        }

        return null;
    }

    // Add friend connection
    static void addFriend(int id1, int id2) {
        Node user1 = findUser(id1);
        Node user2 = findUser(id2);

        if (user1 == null || user2 == null) {
            System.out.println("User not found.");
            return;
        }

        if (id1 == id2) {
            System.out.println("A user cannot be their own friend.");
            return;
        }

        if (user1.friends.contains(id2)) {
            System.out.println("Friend connection already exists.");
            return;
        }

        user1.friends.add(id2);
        user2.friends.add(id1);

        System.out.println("Friend connection added successfully.");
    }

    // Remove friend connection
    static void removeFriend(int id1, int id2) {
        Node user1 = findUser(id1);
        Node user2 = findUser(id2);

        if (user1 == null || user2 == null) {
            System.out.println("User not found.");
            return;
        }

        if (user1.friends.remove(Integer.valueOf(id2))) {
            user2.friends.remove(Integer.valueOf(id1));
            System.out.println("Friend connection removed successfully.");
        } else {
            System.out.println("Friend connection does not exist.");
        }
    }

    // Find mutual friends
    static void mutualFriends(int id1, int id2) {
        Node user1 = findUser(id1);
        Node user2 = findUser(id2);

        if (user1 == null || user2 == null) {
            System.out.println("User not found.");
            return;
        }

        boolean found = false;

        System.out.println("Mutual Friends:");

        for (int friendId : user1.friends) {
            if (user2.friends.contains(friendId)) {
                Node friend = findUser(friendId);

                if (friend != null) {
                    System.out.println(friend.name + " (ID: " + friend.userId + ")");
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("No mutual friends found.");
        }
    }

    // Display all friends
    static void displayFriends(int id) {
        Node user = findUser(id);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        if (user.friends.isEmpty()) {
            System.out.println("No friends found.");
            return;
        }

        System.out.println("Friends of " + user.name + ":");

        for (int friendId : user.friends) {
            Node friend = findUser(friendId);

            if (friend != null) {
                System.out.println(friend.name + " (ID: " + friend.userId + ")");
            }
        }
    }

    // Search by user ID
    static void searchById(int id) {
        Node temp = findUser(id);

        if (temp == null) {
            System.out.println("User not found.");
            return;
        }

        displayUser(temp);
    }

    // Search by name
    static void searchByName(String name) {
        Node temp = head;
        boolean found = false;

        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name)) {
                displayUser(temp);
                found = true;
            }

            temp = temp.next;
        }

        if (!found) {
            System.out.println("User not found.");
        }
    }

    // Display user details
    static void displayUser(Node user) {
        System.out.println("\nUser ID: " + user.userId);
        System.out.println("Name: " + user.name);
        System.out.println("Age: " + user.age);
        System.out.println("Number of Friends: " + user.friends.size());
    }

    // Count friends of each user
    static void countFriends() {
        if (head == null) {
            System.out.println("No users available.");
            return;
        }

        Node temp = head;

        System.out.println("\n===== FRIEND COUNT =====");

        while (temp != null) {
            System.out.println(temp.name + " (ID: " + temp.userId
                    + ") - Friends: " + temp.friends.size());

            temp = temp.next;
        }
    }

    // Display all users
    static void displayAllUsers() {
        if (head == null) {
            System.out.println("No users available.");
            return;
        }

        Node temp = head;

        while (temp != null) {
            displayUser(temp);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\n===== SOCIAL MEDIA FRIEND CONNECTIONS =====");
            System.out.println("1. Add User");
            System.out.println("2. Add Friend Connection");
            System.out.println("3. Remove Friend Connection");
            System.out.println("4. Find Mutual Friends");
            System.out.println("5. Display All Friends");
            System.out.println("6. Search User by ID");
            System.out.println("7. Search User by Name");
            System.out.println("8. Count Friends of Each User");
            System.out.println("9. Display All Users");
            System.out.println("10. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Enter User ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();

                    addUser(id, name, age);
                    break;

                case 2:
                    System.out.print("Enter First User ID: ");
                    int id1 = sc.nextInt();

                    System.out.print("Enter Second User ID: ");
                    int id2 = sc.nextInt();

                    addFriend(id1, id2);
                    break;

                case 3:
                    System.out.print("Enter First User ID: ");
                    int user1 = sc.nextInt();

                    System.out.print("Enter Second User ID: ");
                    int user2 = sc.nextInt();

                    removeFriend(user1, user2);
                    break;

                case 4:
                    System.out.print("Enter First User ID: ");
                    int u1 = sc.nextInt();

                    System.out.print("Enter Second User ID: ");
                    int u2 = sc.nextInt();

                    mutualFriends(u1, u2);
                    break;

                case 5:
                    System.out.print("Enter User ID: ");
                    displayFriends(sc.nextInt());
                    break;

                case 6:
                    System.out.print("Enter User ID: ");
                    searchById(sc.nextInt());
                    break;

                case 7:
                    System.out.print("Enter Name: ");
                    searchByName(sc.nextLine());
                    break;

                case 8:
                    countFriends();
                    break;

                case 9:
                    displayAllUsers();
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