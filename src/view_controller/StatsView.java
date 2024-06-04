package view_controller;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.OurObserver;
import model.PlayerAccount;

public class StatsView extends BorderPane implements OurObserver {

	private PlayerAccount player;
	GridPane grid;
	private Label stats = new Label("STATS:");
	private Label gamesWon = new Label("Total Games Won:    ");
	private Label gamesLost = new Label("Total Games Lost:  ");
	private Label wrongGuesses = new Label("Total Incorrect Guesses:    ");
	private Label correctGuesses = new Label("Total Correct Guesses:  ");
	private Label percentWon = new Label("Percentage Games Won:   ");
	private Label bestGameDate = new Label("Best Game Played On: ");

	public StatsView(PlayerAccount player) {
		grid = new GridPane();
		createView();
		this.setCenter(grid);
		this.player = player;
	}

	public void createView() {
		Font font = Font.font("Serif", FontWeight.BOLD, 50);
		stats.setFont(font);
		gamesWon.setFont(font);
		gamesLost.setFont(font);
		wrongGuesses.setFont(font);
		correctGuesses.setFont(font);
		percentWon.setFont(font);
		bestGameDate.setFont(font);
		
		stats = new Label("STATS:");
		correctGuesses = new Label("Total Correct Guesses:  " + player.getCorrectGuesses());
		wrongGuesses = new Label("Total Incorrect Guesses:    " + player.getIncorrectGuesses());
		percentWon = new Label("Win Ratio:   " + (player.getCorrectGuesses() / player.getGamesPlayed()));
		bestGameDate = new Label("Best Game Played On: ");
		
		grid.add(stats, 0, 0);
		grid.add(gamesWon, 0, 1);
		grid.add(gamesLost, 0, 2);
		grid.add(wrongGuesses, 0, 3);
		grid.add(correctGuesses, 0, 4);
		grid.add(percentWon, 0, 5);
		grid.add(bestGameDate, 0, 6);
	}

	@Override
	public void update(Object theObserved) {
		// TODO Auto-generated method stub
	}

}
