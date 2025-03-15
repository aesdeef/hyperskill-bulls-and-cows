package bullscows;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // generate the code
        System.out.println("Input the length of the secret code:");
        int codeLength = Integer.parseInt(scanner.nextLine());
        System.out.println("Input the number of possible symbols in the code:");
        int numberOfCharacters = Integer.parseInt(scanner.nextLine());

        String characterRange = Main.getCharacterRange(numberOfCharacters);
        String code = Main.generateCode(codeLength, characterRange);
        System.out.println("Okay, let's start a game!");

        // play the game
        int turn = 1;
        while (true) {
            System.out.println("Turn " + turn + ":");
            String guess = scanner.nextLine().trim();
            boolean gameOver = Main.evaluateGuess(code, guess, characterRange);
            if (gameOver) {
                break;
            }
            turn++;
        }

        System.out.println("Congratulations! You guessed the secret code.");
    }

    private static String getCharacterRange(int numberOfCharacters) {
        return "0123456789abcdefghijklmnopqrstuvwxyz".substring(0, numberOfCharacters);
    }

    private static String generateCode(int codeLength, String characterRange) {
        Random rand = new Random();

        // Generate a code of the given length with no repeating digits, that doesn't start with 0
        StringBuilder code = new StringBuilder(codeLength);
        StringBuilder characters = new StringBuilder(characterRange);
        char lastCharacter = characters.charAt(characters.length() - 1);
        for (int i = 0; i < codeLength; i++) {
            int index = rand.nextInt(characters.length());
            code.append(characters.charAt(index));
            characters.deleteCharAt(index);
        }

        StringBuilder message = new StringBuilder();
        message.append("The secret is prepared: ");
        message.append("*".repeat(codeLength));
        if (characterRange.length() > 10) {
            message.append("(0-9, a-");
        } else {
            message.append("(0-");
        }
        message.append(lastCharacter);
        message.append(").");
        System.out.println(message);
        return code.toString();
    }

    private static boolean evaluateGuess(String code, String guess, String characterRange) {
        int bulls = 0;
        int cows = 0;

        for (char character : characterRange.toCharArray()) {
            int occurrencesInCode = 0;
            int occurrencesInGuess = 0;
            for (int i = 0; i < code.length(); i++) {
                if (code.charAt(i) == character && guess.charAt(i) == character) {
                    bulls++;
                } else if (code.charAt(i) == character) {
                    occurrencesInCode++;
                } else if (guess.charAt(i) == character) {
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
