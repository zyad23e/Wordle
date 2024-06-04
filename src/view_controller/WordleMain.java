package view_controller;

/**
 * Main driver for the console wordle game. Allows appropriate amount of guesses, 
 * checking for correctness and updating Slot colors with each attempt.
 * 
 * @author Valerie Militeeva, Zyad Elmaghraby
 */

import java.io.FileNotFoundException;
import java.util.*;

import model.*;

public class WordleMain {

	private static AccountRecords accounts = new AccountRecords();

	/**
	 * Driver for the console wordle game
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Player player = new Player();
		PlayerAccount playerAccount = new PlayerAccount("", "");
		Game game = new Game();
		String gameAns = game.getAnswer();
		writeSlots(game);

		for (int i = 0; i < 5; i++) {
			boolean isCorrect;
			do {
				isCorrect = getUserAttempt(player, game);
			} while (!isCorrect);
			findColors(player, game);
			writeSlots(game);
			player.incrementRow();
			if (player.getAttempt().equals(gameAns)) {
				player.setCorrect(true);
				break;
			}
		}
		if (player.isCorrect()) {
			System.out.println("\nCongratulations!\n");
			playerAccount.finishGame(true, player.getRow(), 5);
		} else {
			System.out.printf("The correct answer is %S.%n", gameAns);
			System.out.println("\nBetter luck next time :(\n");
			playerAccount.finishGame(false, 0, 0);
		}
		
		System.out.println(playerAccount.getStats());
		// you can also call this function after adding game and player to playerAccount
		// to save game in progress
		accounts.savePlayerProgress(playerAccount);
	}

	/**
	 * Output the correct letter and color
	 * 
	 * @param game - current instance of the Game
	 */
	static void writeSlots(Game game) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				Slot currentSlot = game.getSlot(i, j);
				switch (currentSlot.getColor()) {
				case GREEN -> System.out.printf("\u001b[42;1m %C \033[0m ", currentSlot.getChar());
				case YELLOW -> System.out.printf("\u001b[43;1m %C \033[0m ", currentSlot.getChar());
				default -> System.out.printf("\u001b[40;1m %C \033[0m ", currentSlot.getChar());
				}
			}
			System.out.println("\n");
		}
	}

	/**
	 * Get the user's attempt at the word
	 * 
	 * @param player - current instance of Player
	 * @param game   - current instance of the Game
	 * @return - true if word is valid, false otherwise (aka 5 letters, real word,
	 *         etc)
	 */
	@SuppressWarnings("resource")
	static boolean getUserAttempt(Player player, Game game) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter your answer: ");
		String attempt = scanner.next().toUpperCase();
		player.setAttempt(attempt);

		if (game.getDictList().contains(player.getAttempt())) {
			for (int i = 0; i < 5; i++) {
				game.setSlotChar(player.getRow(), i, player.getAttempt().charAt(i));
			}
			return true;
		} else {
			System.out.println("You entered an invalid word!");
			return false;
		}
	}

	/**
	 * Find the right color for each character (Slot). Green if correct letter in
	 * correct location, yellow if correct letter in wrong location, no change if
	 * wrong letter.
	 * 
	 * @param player - current instance of Player
	 * @param game   - current instance of the Game
	 */
	static void findColors(Player player, Game game) {
		LinkedHashMap<Character, Integer> correctLetters = new LinkedHashMap<>(game.getLetters());
		String answer = game.getAnswer();

		for (int i = 0; i < 5; i++) {
			Slot currentSlot = game.getSlot(player.getRow(), i);
			char currentChar = currentSlot.getChar();

			if (currentChar == answer.charAt(i) && correctLetters.get(currentChar) >= 1) {
				currentSlot.setColor(Colors.GREEN);
				correctLetters.put(currentChar, correctLetters.get(currentChar) - 1);
			} else if (answer.contains(String.valueOf(currentChar)) && correctLetters.get(currentChar) >= 1) {
				currentSlot.setColor(Colors.YELLOW);
				correctLetters.put(currentChar, correctLetters.get(currentChar) - 1);
			}
		}
	}

}
