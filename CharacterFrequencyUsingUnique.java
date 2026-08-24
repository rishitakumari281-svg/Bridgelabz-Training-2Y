import java.util.Scanner;

class CharacterFrequencyUsingUnique {

    public static char[] findUniqueCharacters(String text) {

        char[] uniqueCharacters = new char[text.length()];
        int uniqueCount = 0;

        for (int i = 0; i < text.length(); i++) {

            boolean isUnique = true;

            for (int j = 0; j < i; j++) {
                if (text.charAt(i) == text.charAt(j)) {
                    isUnique = false;
                    break;
                }
            }

            if (isUnique) {
                uniqueCharacters[uniqueCount] = text.charAt(i);
                uniqueCount++;
            }
        }

        char[] result = new char[uniqueCount];

        for (int i = 0; i < uniqueCount; i++) {
            result[i] = uniqueCharacters[i];
        }

        return result;
    }

    public static String[][] findCharacterFrequency(String text) {

        int[] frequency = new int[256];

        for (int i = 0; i < text.length(); i++) {
            frequency[text.charAt(i)]++;
        }

        char[] uniqueCharacters = findUniqueCharacters(text);

        String[][] result = new String[uniqueCharacters.length][2];

        for (int i = 0; i < uniqueCharacters.length; i++) {
            char ch = uniqueCharacters[i];

            result[i][0] = String.valueOf(ch);
            result[i][1] = String.valueOf(frequency[ch]);
        }

        return result;
    }

    public static void displayFrequency(String[][] result) {

        System.out.println("Character\tFrequency");

        for (int i = 0; i < result.length; i++) {
            System.out.println(
                result[i][0] + "\t\t" + result[i][1]
            );
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a text: ");
        String text = scanner.nextLine();

        String[][] result = findCharacterFrequency(text);

        displayFrequency(result);

        scanner.close();
    }
}