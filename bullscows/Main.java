package bullscows;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String code = "9305";
        int bulls = 0;
        int cows = 0;

        String guess = scanner.nextLine().trim();

        for (char digit : "0123456789".toCharArray()) {
            int occurrencesInCode = 0;
            int occurrencesInGuess = 0;
            for (int i = 0; i < code.length(); i++) {
                if (code.charAt(i) == digit && guess.charAt(i) == digit) {
                    bulls++;
                } else if (code.charAt(i) == digit) {
                    occurrencesInCode++;
                } else if (guess.charAt(i) == digit) {
                    occurrencesInGuess++;
                }
            }
            cows += Math.min(occurrencesInCode, occurrencesInGuess);
        }

        System.out.print("Grade: ");
        if (bulls == 0 && cows == 0) {
            System.out.println("None");
        } else if (bulls == 0) {
            String plural = cows > 1 ? "s" : "";
            System.out.println(cows + " cow" + plural + ".");
        } else if (cows == 0) {
            String plural = bulls > 1 ? "s" : "";
            System.out.println(bulls + " bull" + plural + ".");
        } else {
            String pluralBulls = bulls > 1 ? "s" : "";
            String pluralCows = cows > 1 ? "s" : "";
            System.out.println(bulls + " bulls" + pluralBulls + " and " + cows + " cow" + pluralCows + ".");
        }
        System.out.println("The secret code is " + code);
        /*
        System.out.println("The secret code is prepared: ****.");
        System.out.println();
        System.out.println("Turn 1. Answer:");
        System.out.println("1234");
        System.out.println("Grade: 1 cow.");
        System.out.println();
        System.out.println("Turn 2. Answer:");
        System.out.println("5678");
        System.out.println("Grade: 1 cow.");
        System.out.println();
        System.out.println("Turn 3. Answer:");
        System.out.println("9012");
        System.out.println("Grade: 1 bull and 1 cow.");
        System.out.println();
        System.out.println("Turn 4. Answer:");
        System.out.println("9087");
        System.out.println("Grade: 1 bull and 1 cow.");
        System.out.println();
        System.out.println("Turn 5. Answer:");
        System.out.println("1087");
        System.out.println("Grade: 1 cow.");
        System.out.println();
        System.out.println("Turn 6. Answer:");
        System.out.println("9205");
        System.out.println("Grade: 3 bulls.");
        System.out.println();
        System.out.println("Turn 7. Answer:");
        System.out.println("9305");
        System.out.println("Grade: 4 bulls.");
        System.out.println("Congrats! The secret code is 9305.");
         */
    }
}
