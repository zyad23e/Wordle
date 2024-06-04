package model;

/**
 * Sets up the game by setting up the slots, picking the answer word, and populating
 * the list of acceptable words.
 * 
 * @author Valerie Militeeva
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Game {

	private String answer;
	private LinkedHashMap<Character, Integer> letters = new LinkedHashMap<>();
	private ArrayList<String> dictList;
	private ArrayList<String> answerList;
	private Slot[][] slots = new Slot[5][5];

	/**
	 * Constructor. Creates the slots, chooses a word, and initializes the
	 * dictionary.
	 * 
	 * @throws FileNotFoundException
	 */
	public Game() throws FileNotFoundException {
		createSlots();
		pickWord();
		createDict();
	}

	/**
	 * Getter for current answer
	 * 
	 * @return - String answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * Getter for correct letters and location
	 * 
	 * @return - HashMap mapping answer character to correct position
	 */
	public LinkedHashMap<Character, Integer> getLetters() {
		return letters;
	}

	/**
	 * Getter for dictionary
	 * 
	 * @return - ArrayList of acceptable words
	 */
	public ArrayList<String> getDictList() {
		return dictList;
	}
	
	public boolean isWord(String word) {
		if (dictList.contains(word)) {
			return true;
		}
		return false;
	}

	/**
	 * Set the char at a given Slot
	 * 
	 * @param i      - row
	 * @param j      - col
	 * @param letter - char to input
	 */
	public void setSlotChar(int i, int j, char letter) {
		slots[i][j].setChar(letter);
	}

	/**
	 * Getter for Slot object in 2D array of Slots
	 * 
	 * @param i - row
	 * @param j - column
	 * @return - Slot object at row, col
	 */
	public Slot getSlot(int i, int j) {
		return slots[i][j];
	}

	/**
	 * Create 2D array of Slot objects to show the user characters
	 */
	private void createSlots() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				Slot slot = new Slot();
				slots[i][j] = slot;
			}
		}
	}

	/**
	 * Pick a word to use as the answer for the current game
	 * 
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("resource")
	private void pickWord() throws FileNotFoundException {
		File answersTxt = new File("src/Words/Answers.txt");
		Scanner answersScanner = new Scanner(answersTxt);
		answerList = new ArrayList<>();
		while (answersScanner.hasNextLine()) {
			answerList.add(answersScanner.nextLine().toUpperCase());
		}
		answer = answerList.get((int) (Math.random() * answerList.size()));
		int k;
		for (int i = 0; i < 5; i++) {
			k = 1;
			for (int j = 0; j < 5; j++) {
				if (i != j && answer.charAt(i) == answer.charAt(j))
					k++;
			}
			letters.put(answer.charAt(i), k);
		}
	}

	/**
	 * Creates the dictionary of acceptable words
	 * 
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("resource")
	private void createDict() throws FileNotFoundException {
		File dictTxt = new File("src/Words/Dict.txt");
		Scanner dictScanner = new Scanner(dictTxt);
		dictList = new ArrayList<>();
		while (dictScanner.hasNextLine()) {
			dictList.add(dictScanner.nextLine().toUpperCase());
		}
	}

}
