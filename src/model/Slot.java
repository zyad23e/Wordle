package model;

/**
 * Slot object to represent the characters guessed with the correct color.
 * 
 * @author Valerie Militeeva, Priscilla Ware, Zyad Elmaghraby
 */

public class Slot {

	private char character;
	private Colors color;

	/**
	 * Constructor, creates new empty slot
	 */
	public Slot() {
		character = ' ';
		color = Colors.GRAY;
	}

	/**
	 * Sets the char of the Slot
	 * 
	 * @param newChar - char to be in Slot
	 */
	public void setChar(char newChar) {
		character = newChar;
	}

	/**
	 * Getter for the char of the Slot
	 * 
	 * @return - char in the Slot
	 */
	public char getChar() {
		return character;
	}

	/**
	 * Sets the color of the Slot. Green = right char, right place. Yellow = right
	 * char, wrong place. Gray = wrong char.
	 * 
	 * @param newColor - Colors constant
	 */
	public void setColor(Colors newColor) {
		color = newColor;
	}

	/**
	 * Getter for the Slot's color
	 * 
	 * @return - Colors constant
	 */
	public Colors getColor() {
		return color;
	}

}