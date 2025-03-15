package bullscows;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // generate the code
        System.out.println("Please, enter the secret code's length:");
        int codeLength = scanner.nextInt();
        scanner.nextLine();
        String code = Main.generateCode(codeLength);
        System.out.println("Okay, let's start a game!");

        // play the game
        int turn = 1;
        while (true) {
            System.out.println("Turn " + turn + ":");
            String guess = scanner.nextLine().trim();
            boolean gameOver = Main.evaluateGuess(code, guess);
            if (gameOver) {
                break;
            }
            turn++;
        }

        System.out.println("Congratulations! You guessed the secret code.");
    }

    private static String generateCode(int codeLength) {
        Random rand = new Random();
        if (codeLength > 10) {
            System.out.println("Error: can't generate a secret number with a length of 11 because there aren't enough unique digits.");
            return "";
        }

        // Generate a code of the given length with no repeating digits, that doesn't start with 0
        StringBuilder code = new StringBuilder(codeLength);
        StringBuilder digits = new StringBuilder("123456789");
        int index = rand.nextInt(digits.length());
        code.append(digits.charAt(index));
        digits.deleteCharAt(index);
        digits.append("0");
        for (int i = 1; i < codeLength; i++) {
            index = rand.nextInt(digits.length());
            code.append(digits.charAt(index));
            digits.deleteCharAt(index);
        }

        return code.toString();
    }

    private static boolean evaluateGuess(String code, String guess) {
        int bulls = 0;
        int cows = 0;

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
        return bulls == code.length();
    }
}
