import java.util.Random;
import java.util.Scanner;

public class GuessingGame {
    private static final int MAX_ATTEMPTS = 7; // Maximum number of attempts allowed

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int score = 0;
        int round = 1;

        while (true) {
            System.out.println("\nRound " + round);

            int randomNumber = random.nextInt(100) + 1; // Generate a random number between 1 and 100
            int attempts = 0;

            while (attempts < MAX_ATTEMPTS) {
                System.out.print("Guess a number between 1 and 100: ");
                int guess = scanner.nextInt();

                if (guess == randomNumber) {
                    System.out.println("Correct! You guessed the number in " + (attempts + 1) + " attempts.");
                    score += (MAX_ATTEMPTS - attempts) * 10; // Award points based on the number of attempts
                    break;
                } else if (guess < randomNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }

                attempts++;
            }

            if (attempts == MAX_ATTEMPTS) {
                System.out.println("Sorry, you ran out of attempts. The number was " + randomNumber + ".");
            }

            System.out.print("Play again? (y/n): ");
            String playAgain = scanner.next();

            if (!playAgain.equalsIgnoreCase("y")) {
                break;
            }

            round++;
        }

        System.out.println("\nFinal Score: " + score);
        scanner.close();
    }
}