package view_controller;

import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.AccountRecords;
import model.Game;
import model.OurObserver;
import model.PlayerAccount;

/**
 * View for logging in and creating new accounts.
 * 
 * @author Priscilla Ware and Cameron Page
 */

public class LoginCreateAccountView extends GridPane implements OurObserver {

	private Scene scene;
	private BorderPane outerPane;
	private VBox boxContainer = new VBox();
	private Label wordleLabel = new Label("     Wordle");
	
	private HBox usernameBox = new HBox();
	private Label usernameLabel = new Label("Username:");
	private TextField usernameField = new TextField();
	
	private HBox passwordBox = new HBox();
	private Label passwordLabel = new Label("Password: ");
	private PasswordField passwordField = new PasswordField();
	
	private HBox buttonsBox = new HBox();
	private Button loginButton = new Button("Login");
	private Button createAccountButton = new Button("Create Account");

	private String username;
	private String password;
	private PlayerAccount player;
	private static AccountRecords accounts = new AccountRecords();
	
	public LoginCreateAccountView(BorderPane outerPane, Scene scene) {
		this.outerPane = outerPane;
		this.setHgap(110);
		this.setVgap(40);
		this.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
		this.scene = scene;
		createView();
		registerHandlers();
	}
	
	private void createView() {
		wordleLabel.setFont(Font.font("Serif", FontWeight.BOLD, 50));
		Font font = Font.font("Serif", 15);
		usernameLabel.setFont(font);
		passwordLabel.setFont(font);
		loginButton.setFont(font);
		createAccountButton.setFont(font);

		this.add(wordleLabel, 1, 3);
		this.add(boxContainer, 1, 4);
		this.add(buttonsBox, 1, 5);
		
		usernameBox.getChildren().addAll(usernameLabel, usernameField);
		usernameBox.setAlignment(Pos.CENTER);
		usernameBox.setSpacing(5);
		
		passwordBox.getChildren().addAll(passwordLabel, passwordField);
		passwordBox.setAlignment(Pos.CENTER);
		passwordBox.setSpacing(5);

		boxContainer.getChildren().addAll(usernameBox, passwordBox);
		boxContainer.setAlignment(Pos.CENTER);
		boxContainer.setSpacing(10);

		buttonsBox.getChildren().addAll(loginButton, createAccountButton);
		buttonsBox.setSpacing(15);
	}
	
	private void login() throws FileNotFoundException {
        WordleGUIDriver.showGameView();
	}

	private void registerHandlers() {
		loginButton.setOnAction((event) -> {
			player = accounts.login(usernameField.getText(), passwordField.getText());
			if (player == null) {
				Alert loginAlert = new Alert(AlertType.ERROR);
				loginAlert.setHeaderText("Invalid username or password.");
				loginAlert.setContentText("Try again");
				loginAlert.showAndWait();
				usernameField.setText("");
				passwordField.setText("");
			} else {
				WordleGUIDriver.currentPlayer = player;
				try {
					login();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});

		createAccountButton.setOnAction((event) -> {
			username = usernameField.getText();
			password = passwordField.getText();
			if (usernameField.getText().equals("") || passwordField.getText().equals("")) {
				Alert loginAlert = new Alert(AlertType.ERROR);
				loginAlert.setHeaderText("Invalid username or password.");
				loginAlert.setContentText("Try again");
				loginAlert.showAndWait();
			} else {
				if (accounts.contains(username)) {
					Alert usernameAlert = new Alert(AlertType.ERROR);
					usernameAlert.setHeaderText("This username is taken.");
					usernameAlert.setContentText("Try another username");
					usernameAlert.showAndWait();
				} else {
					player = new PlayerAccount(username, password);
					accounts.addAccount(player);
					WordleGUIDriver.currentPlayer = player;
					try {
						login();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

	@Override
	public void update(Object theObserved) {
	}
}
