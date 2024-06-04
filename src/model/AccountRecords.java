package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 * Collections class for serializing the PlayerAccounts and their data
 * 
 * @author Priscilla Ware, Zyad Elmaghraby, Cameron Page
 */

public class AccountRecords {

	private HashMap<String, PlayerAccount> allAccounts;
	private static final String fileName = "file.ser";

	public AccountRecords() {
		try {
			readFromFile();
		} catch (IOException | ClassNotFoundException e) {
			clearFile();
			allAccounts = new HashMap<>();
		}
	}

	private void clearFile() {
		// TODO Auto-generated method stub
	}

	/**
	 * Reads the serialized file of PlayerAccounts into the allAccounts HashMap
	 * 
	 * @throws ClassNotFoundException, IOException
	 */
	@SuppressWarnings("unchecked")
	private void readFromFile() throws ClassNotFoundException, IOException {
		FileInputStream rawBytes = new FileInputStream(fileName);
		ObjectInputStream inFile = new ObjectInputStream(rawBytes);
		HashMap<String, PlayerAccount> temp = (HashMap<String, PlayerAccount>) inFile.readObject();
		inFile.close();
		allAccounts = temp;
	}

	/**
	 * Writes the current allAccounts HashMap to update the .ser file
	 */
	private void serialize() {
		try (FileOutputStream bytesToDisk = new FileOutputStream(fileName);
				ObjectOutputStream outFile = new ObjectOutputStream(bytesToDisk);) {
			outFile.writeObject(allAccounts);
			outFile.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Adds account to the HashMap of PlayerAccounts
	 * 
	 * @param account - a PlayerAccount being added to the allAccounts HashMap
	 */
	public void addAccount(PlayerAccount account) {
		allAccounts.put(account.getUsername(), account);
		serialize();
	}

	/**
	 * Checks the allAccounts HashMap for an account with the given username
	 * 
	 * @param username - a username being searched for in allAccounts
	 * @return true if an account has the given username, false otherwise
	 */
	public boolean contains(String username) {
		return allAccounts.containsKey(username);
	}

	/**
	 * Attempts to log in with the given username and password
	 * 
	 * @param username
	 * @param password
	 * @return the PlayerAccount with the given credentials, null otherwise
	 */
	public PlayerAccount login(String username, String password) {
		if (contains(username)) {
			PlayerAccount account = allAccounts.get(username);
			if (account.getPassword().equals(password)) {
				return account;
			}
		}
		return null;
	}

	/**
	 * Saves a player's game in progress by updating the serialized file after
	 * updating allAccounts
	 * 
	 * @param account - a PlayerAccount which stores the updated game progress
	 */
	public void savePlayerProgress(PlayerAccount account) {
		allAccounts.put(account.getUsername(), account);
		serialize();
	}

}
