import java.util.Scanner;

class StringCharacters {

    // Method to return all characters of a string without using toCharArray()
    public static char[] getCharacters(String text) {

        // Create a character array with the same length as the string
        char[] characters = new char[text.length()];

        // Store each character in the array
        for (int i = 0; i < text.length(); i++) {
            characters[i] = text.charAt(i);
        }

        return characters;
    }

    // Method to compare two character arrays
    public static boolean compareArrays(char[] firstArray, char[] secondArray) {

        // Check if array lengths are different
        if (firstArray.length != secondArray.length) {
            return false;
        }

        // Compare each character
        for (int i = 0; i < firstArray.length; i++) {
            if (firstArray[i] != secondArray[i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        // Create Scanner object
        Scanner input = new Scanner(System.in);

        // Take input for the string
        System.out.print("Enter a string: ");
        String text = input.next();

        // Get characters using the user-defined method
        char[] userDefinedCharacters = getCharacters(text);

        // Get characters using the built-in toCharArray() method
        char[] builtInCharacters = text.toCharArray();

        // Compare both character arrays
        boolean result =
                compareArrays(userDefinedCharacters, builtInCharacters);

        // Display the characters
        System.out.println("Characters using user-defined method:");

        for (int i = 0; i < userDefinedCharacters.length; i++) {
            System.out.print(userDefinedCharacters[i] + " ");
        }

        System.out.println();

        // Display the characters using built-in method
        System.out.println("Characters using toCharArray():");

        for (int i = 0; i < builtInCharacters.length; i++) {
            System.out.print(builtInCharacters[i] + " ");
        }

        System.out.println();

        // Display comparison result
        System.out.println("Both character arrays are same: " + result);

        // Close Scanner object
        input.close();
    }
}