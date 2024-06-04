package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * PlayerAccount class for representing a player and their account/game data
 * 
 * @author Zyad Elmaghraby
 */

public class PlayerAccount implements Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private HashMap<Integer, ArrayList<Integer>> stats;
	private int incorrectGuesses;
	private int correctGuesses;
	private int gamesPlayed;
	private int streak;
	private Game currentGame;
	private Player currentPlayer;

	/**
	 * Constructs a new PlayerAccount object with the given username and password.
	 * Also sets the player's guess stats, number of games played, and win streak to
	 * 0. Creates a new empty HashMap for storing the player's stats
	 */
	public PlayerAccount(String username, String password) {
		this.username = username;
		this.password = password;
		incorrectGuesses = correctGuesses = gamesPlayed = streak = 0;
		stats = new HashMap<>();
	}

	/**
	 * Getter for the current player's username
	 * 
	 * @return - String username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Getter for the current player's password
	 * 
	 * @return - String password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Getter for the current game in progress
	 * 
	 * @return - Current game
	 */
	public Game getCurrentGame() {
		return currentGame;
	}

	/**
	 * Getter for the current player
	 * 
	 * @return - Current player
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Manually resets the currentGame and currentPlayer to null
	 */
	public void clearCurrentGame() {
		currentGame = null;
		currentPlayer = null;
	}

	/**
	 * Resets game, updates the PlayerAccount's number of gamesPlayed, and checks if
	 * the word was guessed before the game ended. If word was guessed, updates the
	 * PlayerAccount's number of correctGuesses, win streak, and stats. If not
	 * guessed, resets win streak and updates incorrectGuesses
	 * 
	 * @param guessedWord - true if word guessed, false otherwise
	 * @param numGuessed  - number of guesses made before game ended
	 * @param wordSize    - length of the word being guessed
	 */
	public void finishGame(boolean guessedWord, int numGuesses, int wordSize) {
		clearCurrentGame();
		gamesPlayed++;

		if (guessedWord) {
			correctGuesses++;
			streak++;
			if (stats.containsKey(wordSize)) {
				ArrayList<Integer> guessesList = stats.get(wordSize);
				int winsWithGuesses = guessesList.get(numGuesses - 1);
				guessesList.set(numGuesses - 1, winsWithGuesses + 1);
				stats.put(wordSize, guessesList);
			} else {
				ArrayList<Integer> guessesList = new ArrayList<>();
				for (int i = 0; i < wordSize; i++) {
					guessesList.add(0);
				}
				guessesList.set(numGuesses - 1, 1);
				stats.put(wordSize, guessesList);
			}

		} else {
			incorrectGuesses++;
			streak = 0;
		}
	}

	/**
	 * Gets a string representation of the PlayerAccount's game stats
	 * 
	 * @return a string of game stats
	 */
	public String getStats() {
		String str = "Statistics:\n";
		str += "\nGames Played: " + gamesPlayed;
		str += "\nWins: " + correctGuesses;
		str += "\nLosses: " + incorrectGuesses;
		str += "\nWin Streak: " + streak;
		str += "\n\n";

		// TODO: change this for loop to use max word length instead of 10 when we allow
		// different word sizes
		for (int i = 1; i <= 10; i++) {
			if (stats.containsKey(i)) {
				str += "Guess amounts for words with " + i + " letters:";
				ArrayList<Integer> guesses = stats.get(i);
				for (int j = 0; j < guesses.size(); j++) {
					str += "\n" + (j + 1) + ". " + guesses.get(j);
				}
				str += "\n";
			}
		}
		return str;
	}
	
	public int getGamesPlayed() {
		return gamesPlayed;
	}
	
	public int getCorrectGuesses() {
		return correctGuesses;
	}
	
	public int getIncorrectGuesses() {
		return incorrectGuesses;
	}
	
	public int getStreak() {
		return streak;
	}
	

	/**
	 * Updates the PlayerAccount's game progress to be most recent
	 * 
	 * @param game   - the game to save
	 * @param player - the player and their updated game stats
	 */
	public void saveCurrentGame(Game game, Player player) {
		currentGame = game;
		currentPlayer = player;
	}

}
