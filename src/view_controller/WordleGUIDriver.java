package view_controller;

import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Game;
import model.OurObserver;
import model.PlayerAccount;

/**
 * GUI driver for the Wordle game
 * 
 * @author Priscilla Ware and Cameron Page
 */

public class WordleGUIDriver extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private static BorderPane outerPane;
    private static Scene scene;
    private static Game wordleGame;
    public static PlayerAccount currentPlayer;

    private static OurObserver currentView;
    private static OurObserver gameView;
    private OurObserver statsView;
    private OurObserver settingsView;

    private MenuBar menuBar;
    private static Menu options;
    
    public void start(Stage stage) {
        stage.setTitle("Wordle Game");
        outerPane = new BorderPane();
        scene = new Scene(outerPane, 500, 700);
        stage.setResizable(false);
        
        createMenus();
        outerPane.setTop(menuBar);
    	options.setDisable(true);

        showLoginView();
        stage.setScene(scene);
        stage.show();
    }

    public void createMenus() {
    	MenuItem newGame = new MenuItem("New Game");

    	Menu account = new Menu("Account");
    	MenuItem stats = new MenuItem("Statistics & Achievements");
    	MenuItem settings = new MenuItem("Settings");
    	account.getItems().addAll(stats, settings);
    	
    	MenuItem logOut = new MenuItem("Log Out");

    	EventHandler<ActionEvent> handler = new MenuHandler();
    	newGame.setOnAction(handler);
    	stats.setOnAction(handler);
    	settings.setOnAction(handler);
    	logOut.setOnAction(handler);

    	options = new Menu("Options");
    	options.getItems().addAll(newGame, account, logOut);
    	menuBar = new MenuBar();
    	menuBar.getMenus().addAll(options);
    }

    private static void setViewTo(OurObserver newView) {
        outerPane.setCenter(null);
        currentView = newView;
        outerPane.setCenter((Node) currentView);
    }

    private void showLoginView() {
        setViewTo(new LoginCreateAccountView(outerPane, scene));
    }

    public static void showGameView() throws FileNotFoundException {
    	options.setDisable(false);
        wordleGame = new Game();
        gameView = new GameView(currentPlayer, wordleGame, scene);
        setViewTo(gameView);
    }
    
    private void showStatsView() {
        setViewTo(new StatsView(currentPlayer));
    }
    
	private class MenuHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent arg0) {
			String menuItemClicked = ((MenuItem) arg0.getSource()).getText();

			if (menuItemClicked.equals("New Game"))
				try {
					showGameView();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			if (menuItemClicked.equals("Statistics & Achievements"))
				showStatsView();
			if (menuItemClicked.equals("Settings"))
				setViewTo(settingsView);
			if (menuItemClicked.equals("Log Out")) {
                currentPlayer = null;
                showLoginView();
			}
		}
	}
}
