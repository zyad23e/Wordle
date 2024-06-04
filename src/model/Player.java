package model;

/**
 * Current player of the game. Saves the row (guess #) and the current attempts.
 * 
 * @author Valerie Militeeva, Cameron Page
 */

public class Player {

	private int row = 0;
	private String attempt;
	private boolean correct = false;

	/**
	 * Getter for current row being guessed
	 * 
	 * @return - integer value of row being played
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Increments the row, called when user needs another guess
	 */
	public void incrementRow() {
		row++;
	}

	/**
	 * Get the user's attempt at the word
	 * 
	 * @return - String attempt
	 */
	public String getAttempt() {
		return attempt;
	}

	/**
	 * Setter for the user's attempt at the word
	 * 
	 * @param newAnswer - String to be saved as the user's current attempt
	 */
	public void setAttempt(String newAttempt) {
		attempt = newAttempt;
	}

	/**
	 * Getter for user's status. False = continue guessing, true = terminate
	 * 
	 * @return - false if word has not been guessed, true otherwise
	 */
	public boolean isCorrect() {
		return correct;
	}

	/**
	 * Sets the user's current status
	 * 
	 * @param isCorrect - true if word guessed, false otherwise
	 */
	public void setCorrect(boolean isCorrect) {
		correct = isCorrect;
	}

}
