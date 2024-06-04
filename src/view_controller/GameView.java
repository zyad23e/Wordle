package view_controller;

import java.util.ArrayList;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import model.Game;
import model.OurObserver;
import model.PlayerAccount;

/*
 * @author Priscilla Ware, Zyad Elmaghraby
 * 
 */

public class GameView extends BorderPane implements OurObserver {

	PlayerAccount player;
	Game theGame;

	Canvas canvas;
	String answer = "CRANE"; // HARDCODED FOR TESTING PURPOSES

	int currX = 131;
	int currY = 143;
	int noLetters = 0;
	int noGuesses = 0;
	String currGuess = "";
	boolean isWon = false;
	ArrayList<String> allGuesses = new ArrayList<>();

	Color Letters = Color.BLACK;
	Color Slots = Color.WHITE;
	Color canvasColor = Color.WHITE;
	boolean isDarkMode = false;
	boolean settingsOpen = false;

	Group root;

	Rectangle rect00 = new Rectangle(115, 110, 50, 50);
	Rectangle rect01 = new Rectangle(170, 110, 50, 50);
	Rectangle rect02 = new Rectangle(225, 110, 50, 50);
	Rectangle rect03 = new Rectangle(280, 110, 50, 50);
	Rectangle rect04 = new Rectangle(335, 110, 50, 50);

	Rectangle rect10 = new Rectangle(115, 165, 50, 50);
	Rectangle rect11 = new Rectangle(170, 165, 50, 50);
	Rectangle rect12 = new Rectangle(225, 165, 50, 50);
	Rectangle rect13 = new Rectangle(280, 165, 50, 50);
	Rectangle rect14 = new Rectangle(335, 165, 50, 50);

	Rectangle rect20 = new Rectangle(115, 220, 50, 50);
	Rectangle rect21 = new Rectangle(170, 220, 50, 50);
	Rectangle rect22 = new Rectangle(225, 220, 50, 50);
	Rectangle rect23 = new Rectangle(280, 220, 50, 50);
	Rectangle rect24 = new Rectangle(335, 220, 50, 50);

	Rectangle rect30 = new Rectangle(115, 275, 50, 50);
	Rectangle rect31 = new Rectangle(170, 275, 50, 50);
	Rectangle rect32 = new Rectangle(225, 275, 50, 50);
	Rectangle rect33 = new Rectangle(280, 275, 50, 50);
	Rectangle rect34 = new Rectangle(335, 275, 50, 50);

	Rectangle rect40 = new Rectangle(115, 330, 50, 50);
	Rectangle rect41 = new Rectangle(170, 330, 50, 50);
	Rectangle rect42 = new Rectangle(225, 330, 50, 50);
	Rectangle rect43 = new Rectangle(280, 330, 50, 50);
	Rectangle rect44 = new Rectangle(335, 330, 50, 50);

	Rectangle rect50 = new Rectangle(115, 385, 50, 50);
	Rectangle rect51 = new Rectangle(170, 385, 50, 50);
	Rectangle rect52 = new Rectangle(225, 385, 50, 50);
	Rectangle rect53 = new Rectangle(280, 385, 50, 50);
	Rectangle rect54 = new Rectangle(335, 385, 50, 50);

	public GameView(PlayerAccount player, Game game, Scene scene) {
		this.player = player;
		theGame = game;
		createView();
		registerKeys(scene);
	}

	private void createView() {
		root = drawSlots();
		canvas = new Canvas();
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Paint.valueOf("ffffff"));
		canvas.setWidth(500);
		canvas.setHeight(650);

		Scene scene = new Scene(this, 500, 650);
		setUp();

		registerKeys(scene);
		Font font = Font.font("Verdana", FontWeight.BOLD, 22);
		gc.setFont(font);
		gc.setFill(Color.BLACK);

		this.getChildren().add(canvas);

		setUp();
		registerKeys(scene);

		this.setOnMousePressed(new MousePressed());
	}

	public Group drawSlots() {
		Group root = new Group();

		rect00.setFill(Slots);
		rect00.setStroke(Color.GRAY);
		rect01.setFill(Slots);
		rect01.setStroke(Color.GRAY);
		rect02.setFill(Slots);
		rect02.setStroke(Color.GRAY);
		rect03.setFill(Slots);
		rect03.setStroke(Color.GRAY);
		rect04.setFill(Slots);
		rect04.setStroke(Color.GRAY);
		root.getChildren().addAll(rect00, rect01, rect02, rect03, rect04);

		rect10.setFill(Slots);
		rect10.setStroke(Color.GRAY);
		rect11.setFill(Slots);
		rect11.setStroke(Color.GRAY);
		rect12.setFill(Slots);
		rect12.setStroke(Color.GRAY);
		rect13.setFill(Slots);
		rect13.setStroke(Color.GRAY);
		rect14.setFill(Slots);
		rect14.setStroke(Color.GRAY);
		root.getChildren().addAll(rect10, rect11, rect12, rect13, rect14);

		rect20.setFill(Slots);
		rect20.setStroke(Color.GRAY);
		rect21.setFill(Slots);
		rect21.setStroke(Color.GRAY);
		rect22.setFill(Slots);
		rect22.setStroke(Color.GRAY);
		rect23.setFill(Slots);
		rect23.setStroke(Color.GRAY);
		rect24.setFill(Slots);
		rect24.setStroke(Color.GRAY);
		root.getChildren().addAll(rect20, rect21, rect22, rect23, rect24);

		rect30.setFill(Slots);
		rect30.setStroke(Color.GRAY);
		rect31.setFill(Slots);
		rect31.setStroke(Color.GRAY);
		rect32.setFill(Slots);
		rect32.setStroke(Color.GRAY);
		rect33.setFill(Slots);
		rect33.setStroke(Color.GRAY);
		rect34.setFill(Slots);
		rect34.setStroke(Color.GRAY);
		root.getChildren().addAll(rect30, rect31, rect32, rect33, rect34);

		rect40.setFill(Slots);
		rect40.setStroke(Color.GRAY);
		rect41.setFill(Slots);
		rect41.setStroke(Color.GRAY);
		rect42.setFill(Slots);
		rect42.setStroke(Color.GRAY);
		rect43.setFill(Slots);
		rect43.setStroke(Color.GRAY);
		rect44.setFill(Slots);
		rect44.setStroke(Color.GRAY);
		root.getChildren().addAll(rect40, rect41, rect42, rect43, rect44);

		rect50.setFill(Slots);
		rect50.setStroke(Color.GRAY);
		rect51.setFill(Slots);
		rect51.setStroke(Color.GRAY);
		rect52.setFill(Slots);
		rect52.setStroke(Color.GRAY);
		rect53.setFill(Slots);
		rect53.setStroke(Color.GRAY);
		rect54.setFill(Slots);
		rect54.setStroke(Color.GRAY);
		root.getChildren().addAll(rect50, rect51, rect52, rect53, rect54);
		this.getChildren().add(root);
		return root;
	}

	public void registerKeys(Scene scene) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (!isWon && !settingsOpen) {
					gc.setFill(Letters);
					Font font = Font.font("Verdana", FontWeight.MEDIUM, 18);
					gc.setFont(font);
					if (event.getCode() == KeyCode.A && noLetters != 5 && noGuesses < 6) {
						gc.fillText("A", currX + noLetters * 55, currY + noGuesses * 55);
						noLetters++;
						currGuess = currGuess + "A";
					} else if (event.getCode() == KeyCode.B && noLetters != 5 && noGuesses < 6) {
						gc.fillText("B", currX + noLetters * 55, currY + noGuesses * 55);
						noLetters++;
						currGuess = currGuess + "B";
					} else if (event.getCode() == KeyCode.C && noLetters != 5 && noGuesses < 6) {
						gc.fillText("C", currX + noLetters * 55, currY + noGuesses * 55);
						noLetters++;
						currGuess = currGuess + "C";
					} else if (event.getCode() == KeyCode.D && noLetters != 5 && noGuesses < 6) {
						gc.fillText("D", currX + noLetters * 55, currY + noGuesses * 55);
						noLetters++;
						currGuess = currGuess + "D";
					} else if (event.getCode() == KeyCode.E && noLetters != 5 && noGuesses < 6) {
						gc.fillText("E", currX + noLetters * 55, currY + noGuesses * 55);
						noLetters++;
						currGuess = currGuess + "E";
					} else if (event.getCode() == KeyCode.F && noLetters != 5 && noGuesses < 6) {
						gc.fillText("F", currX + noLetters * 55, currY + noGuesses * 55);
						noLetters++;
						currGuess = currGuess + "F";
					} else if (event.getCode() == KeyCode.G && noLetters != 5 && noGuesses < 6) {
						gc.fillText("G", currX + noLetters * 55, currY + noGuesses * 55);
						noLetters++;
						currGuess = currGuess + "G";
					} else if (event.getCode() == KeyCode.H && noLetters != 5 && noGuesses < 6) {
						gc.fillText("H", currX + noLetters * 55, currY + noGuesses * 55);
						noLetters++;
						currGuess = currGuess + "H";
					} else if (event.getCode() == KeyCode.I && noLetters != 5 && noGuesses < 6) {
						gc.fillText("I", currX + noLetters * 55, currY + noGuesses * 55);
						noLetters++;
						currGuess = currGuess + "I";
					} else if (event.getCode() == KeyCode.J && noLetters != 5 && noGuesses < 6) {
						gc.fillText("J", currX + noLetters * 55, currY + noGuesses * 55);
						noLetters++;
						currGuess = currGuess + "J";
					} else if (event.getCode() == KeyCode.K && noLetters != 5 && noGuesses < 6) {
						gc.fillText("K", currX + noLetters * 55, currY + noGuesses * 55);
						noLetters++;
						currGuess = currGuess + "K";
					} else if (event.getCode() == KeyCode.L && noLetters != 5 && noGuesses < 6) {
						gc.fillText("L", currX + noLetters * 55, currY + noGuesses * 55);
						noLetters++;
						currGuess = currGuess + "L";
					} else if (event.getCode() == KeyCode.M && noLetters != 5 && noGuesses < 6) {
						gc.fillText("M", currX + noLetters * 55, currY + noGuesses * 55);
						noLetters++;
						currGuess = currGuess + "M";
					} else if (event.getCode() == KeyCode.N && noLetters != 5 && noGuesses < 6) {
						gc.fillText("N", currX + noLetters * 55, currY + noGuesses * 55);
						noLetters++;
						currGuess = currGuess + "N";
					} else if (event.getCode() == KeyCode.O && noLetters != 5 && noGuesses < 6) {
						gc.fillText("O", currX + noLetters * 55, currY + noGuesses * 55);
						noLetters++;
						currGuess = currGuess + "O";
					} else if (event.getCode() == KeyCode.P && noLetters != 5 && noGuesses < 6) {
						gc.fillText("P", currX + noLetters * 55, currY + noGuesses * 55);
						noLetters++;
						currGuess = currGuess + "P";
					} else if (event.getCode() == KeyCode.Q && noLetters != 5 && noGuesses < 6) {
						gc.fillText("Q", currX + noLetters * 55, currY + noGuesses * 55);
						noLetters++;
						currGuess = currGuess + "Q";
					} else if (event.getCode() == KeyCode.R && noLetters != 5 && noGuesses < 6) {
						gc.fillText("R", currX + noLetters * 55, currY + noGuesses * 55);
						noLetters++;
						currGuess = currGuess + "R";
					} else if (event.getCode() == KeyCode.S && noLetters != 5 && noGuesses < 6) {
						gc.fillText("S", currX + noLetters * 55, currY + noGuesses * 55);
						noLetters++;
						currGuess = currGuess + "S";
					} else if (event.getCode() == KeyCode.T && noLetters != 5 && noGuesses < 6) {
						gc.fillText("T", currX + noLetters * 55, currY + noGuesses * 55);
						noLetters++;
						currGuess = currGuess + "T";
					} else if (event.getCode() == KeyCode.U && noLetters != 5 && noGuesses < 6) {
						gc.fillText("U", currX + noLetters * 55, currY + noGuesses * 55);
						noLetters++;
						currGuess = currGuess + "U";
					} else if (event.getCode() == KeyCode.V && noLetters != 5 && noGuesses < 6) {
						gc.fillText("V", currX + noLetters * 55, currY + noGuesses * 55);
						noLetters++;
						currGuess = currGuess + "V";
					} else if (event.getCode() == KeyCode.W && noLetters != 5 && noGuesses < 6) {
						gc.fillText("W", currX + noLetters * 55, currY + noGuesses * 55);
						noLetters++;
						currGuess = currGuess + "W";
					} else if (event.getCode() == KeyCode.X && noLetters != 5 && noGuesses < 6) {
						gc.fillText("X", currX + noLetters * 55, currY + noGuesses * 55);
						noLetters++;
						currGuess = currGuess + "X";
					} else if (event.getCode() == KeyCode.Y && noLetters != 5 && noGuesses < 6) {
						gc.fillText("Y", currX + noLetters * 55, currY + noGuesses * 55);
						noLetters++;
						currGuess = currGuess + "Y";
					} else if (event.getCode() == KeyCode.Z && noLetters != 5 && noGuesses < 6) {
						gc.fillText("Z", currX + noLetters * 55, currY + noGuesses * 55);
						noLetters++;
						currGuess = currGuess + "Z";
					} else if (event.getCode() == KeyCode.ENTER && noLetters == 5 && noGuesses < 6) {
						if (4 > 3) { // 911!!!!
							if (noGuesses == 0) {
								shake(rect00);
								shake(rect01);
								shake(rect02);
								shake(rect03);
								shake(rect04);
							} else if (noGuesses == 1) {
								shake(rect10);
								shake(rect11);
								shake(rect12);
								shake(rect13);
								shake(rect14);
							} else if (noGuesses == 2) {
								shake(rect20);
								shake(rect21);
								shake(rect22);
								shake(rect23);
								shake(rect24);
							} else if (noGuesses == 3) {
								shake(rect30);
								shake(rect31);
								shake(rect32);
								shake(rect33);
								shake(rect34);
							} else if (noGuesses == 4) {
								shake(rect40);
								shake(rect41);
								shake(rect42);
								shake(rect43);
								shake(rect44);
							} else if (noGuesses == 5) {
								shake(rect50);
								shake(rect51);
								shake(rect52);
								shake(rect53);
								shake(rect54);
							}
						}
						// else {
						guess();
						allGuesses.add(currGuess);
						noGuesses++;
						noLetters = 0;
						currGuess = "";
					// }
					}
					else if (event.getCode() == KeyCode.BACK_SPACE && noLetters > 0) {
						if (isDarkMode) {
							gc.setFill(Color.BLACK);
							gc.clearRect((currX + noLetters * 55) - 100, (currY + noGuesses * 55) - 100, 100, 100);
							gc.fillRect((currX + noLetters * 55) - 100, (currY + noGuesses * 55) - 100, 100, 100);
						} else {
							gc.clearRect((currX + noLetters * 55) - 55, (currY + noGuesses * 55) - 20, 50, 50);
						}
						noLetters--;
						if (currGuess.length() > 0)
							currGuess = currGuess.substring(0, currGuess.length() - 1);
					}
				}
			}
		});
	}

	private void shake(Node rect) {
		TranslateTransition translateTransition = new TranslateTransition();
		translateTransition.setNode(rect);
		translateTransition.setDuration(Duration.millis(50));
		translateTransition.setByX(8);
		translateTransition.setCycleCount(10);
		translateTransition.setAutoReverse(true);
		translateTransition.play();
	}

	private void clear() {
		this.getChildren().removeAll(root, canvas);
		root = drawSlots();
		// pane.getChildren().add(root);

		canvas = new Canvas();
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Paint.valueOf("ffffff"));
		canvas.setWidth(500);
		canvas.setHeight(650);
		this.getChildren().add(canvas);
	}

	public void setUp() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.LIGHTGRAY);
		if (!isDarkMode) {
			Image image = new Image("file:images/settings.png", 60, 50, false, false);
			gc.drawImage(image, 440, 8);
		}
		int xPos = 25;
		for (int i = 0; i < 10; i++) {
			gc.fillRoundRect(xPos, 480, 40, 50, 5, 5);
			// gc.strokeRoundRect(xPos, 480, 40, 50, 5, 5);
			xPos = xPos + 45;
		}
		xPos = 50;
		for (int j = 0; j < 9; j++) {
			gc.fillRoundRect(xPos, 535, 40, 50, 5, 5);
			// gc.strokeRoundRect(xPos, 535, 40, 50, 5, 5);
			xPos = xPos + 45;
		}
		xPos = 95;
		for (int j = 0; j < 7; j++) {
			gc.fillRoundRect(xPos, 590, 40, 50, 5, 5);
			// gc.strokeRoundRect(xPos, 590, 40, 50, 5, 5);
			xPos = xPos + 45;
		}
		gc.fillRoundRect(20, 590, 68, 50, 5, 5);
		gc.fillRoundRect(413, 590, 68, 50, 5, 5);

		Font font = Font.font("Verdana", FontWeight.BOLD, 19);
		gc.setFont(font);
		gc.setFill(Letters);
		gc.fillText("Q", 36, 512);
		gc.fillText("W", 79, 512);
		gc.fillText("E", 128, 512);
		gc.fillText("R", 172, 512);
		gc.fillText("T", 217, 512);
		gc.fillText("Y", 262, 512);
		gc.fillText("U", 307, 512);
		gc.fillText("I", 352, 512);
		gc.fillText("O", 397, 512);
		gc.fillText("P", 442, 512);

		gc.fillText("A", 62, 567);
		gc.fillText("S", 107, 567);
		gc.fillText("D", 152, 567);
		gc.fillText("F", 197, 567);
		gc.fillText("G", 242, 567);
		gc.fillText("H", 287, 567);
		gc.fillText("J", 333, 567);
		gc.fillText("K", 377, 567);
		gc.fillText("L", 422, 567);

		gc.fillText("Z", 108, 622);
		gc.fillText("X", 153, 622);
		gc.fillText("C", 198, 622);
		gc.fillText("V", 243, 622);
		gc.fillText("B", 288, 622);
		gc.fillText("N", 331, 622);
		gc.fillText("M", 376, 622);

		font = Font.font("Verdana", FontWeight.MEDIUM, 12);
		gc.setFont(font);
		gc.fillText("ENTER", 35, 619);
		font = Font.font("Verdana", FontWeight.MEDIUM, 18);
		gc.setFont(font);
		gc.fillText("⌫", 434, 621);
		// everything above is drawing the key board

		// gc.setFill(Color.LIGHTGRAY);

		gc.setStroke(Color.LIGHTGRAY);
		xPos = 115;
		int yPos = 110;
		// for (int k = 0; k < 6; k++) {
		// for (int l = 0; l < 5; l++) {
		// gc.fillRect(xPos, yPos, 50, 50);
		// Rectangle rect = new Rectangle(xPos, yPos, 50, 50);

		// gc.strokeRect(xPos, yPos, 50, 50);
		// xPos = xPos + 55;
		// }
		// xPos = 115;
		// yPos = yPos + 55;
		// }

		Font font2 = Font.font("Verdana", FontWeight.EXTRA_BOLD, 50);

		gc.setFill(Color.BLACK);
		gc.setFont(font2);
		gc.fillText("Wordle", 150, 60);
	}

	private RotateTransition createRotator(Node letter) {
		RotateTransition rotator = new RotateTransition(Duration.millis(750), letter);
		rotator.setAxis(Rotate.X_AXIS);
		rotator.setFromAngle(0);
		rotator.setToAngle(180);
		rotator.setInterpolator(Interpolator.LINEAR);
		rotator.setCycleCount(1);
		rotator.play();
		return rotator;
	}

	private Rectangle getRect(int i) {
		if (noGuesses == 0) {
			if (i == 0) {
				return rect00;
			} else if (i == 1) {
				return rect01;
			} else if (i == 2) {
				return rect02;
			} else if (i == 3) {
				return rect03;
			} else if (i == 4) {
				return rect04;
			}
		} else if (noGuesses == 1) {
			if (i == 0) {
				return rect10;
			} else if (i == 1) {
				return rect11;
			} else if (i == 2) {
				return rect12;
			} else if (i == 3) {
				return rect13;
			} else if (i == 4) {
				return rect14;
			}
		} else if (noGuesses == 2) {
			if (i == 0) {
				return rect20;
			} else if (i == 1) {
				return rect21;
			} else if (i == 2) {
				return rect22;
			} else if (i == 3) {
				return rect23;
			} else if (i == 4) {
				return rect24;
			}
		} else if (noGuesses == 3) {
			if (i == 0) {
				return rect30;
			} else if (i == 1) {
				return rect31;
			} else if (i == 2) {
				return rect32;
			} else if (i == 3) {
				return rect33;
			} else if (i == 4) {
				return rect34;
			}
		} else if (noGuesses == 4) {
			if (i == 0) {
				return rect40;
			} else if (i == 1) {
				return rect41;
			} else if (i == 2) {
				return rect42;
			} else if (i == 3) {
				return rect43;
			} else if (i == 4) {
				return rect44;
			}
		} else if (noGuesses == 5) {
			if (i == 0) {
				return rect50;
			} else if (i == 1) {
				return rect51;
			} else if (i == 2) {
				return rect52;
			} else if (i == 3) {
				return rect53;
			} else if (i == 4) {
				return rect54;
			}
		}
		return null;
	}

	public void guess(String guess) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Font font = Font.font("Verdana", FontWeight.BOLD, 19);
		gc.setFont(font);
		// Game game = player.getCurrentGame();
		// String answer = game.getAnswer();
		int correctCount = 0;
		int yPos = 110;
		boolean skip = false;
		for (int i = 0; i < 5; i++) {
			skip = false;
			String inAnswer = "" + String.valueOf(guess.charAt(i));
			if (guess.charAt(i) == answer.charAt(i)) {
				correctCount++;
				gc.clearRect(115 + i * 55, yPos + noGuesses * 55, 50, 50);
				gc.setFill(Color.LIGHTGREEN);
				gc.fillRect(115 + i * 55, yPos + noGuesses * 55, 50, 50);
				skip = true;
			} else if (answer.contains(inAnswer) && skip == false) {
				gc.clearRect(115 + i * 55, yPos + noGuesses * 55, 50, 50);
				gc.setFill(Color.LIGHTYELLOW);
				gc.fillRect(115 + i * 55, yPos + noGuesses * 55, 50, 50);
			} else {
				gc.clearRect(115 + i * 55, yPos + noGuesses * 55, 50, 50);
				gc.setFill(Color.GRAY);
				gc.fillRect(115 + i * 55, yPos + noGuesses * 55, 50, 50);
			}
			gc.setFill(Letters);
		}
		int xPos = 131;
		for (int j = 0; j < 5; j++) {
			String letter = String.valueOf(guess.charAt(j));
			gc.fillText(letter, xPos, currY + noGuesses * 55);
			xPos = xPos + 55;
		}
		if (correctCount == 5) {
			isWon = true;
			Alert win = new Alert(AlertType.INFORMATION);
			win.setHeaderText("You win!");
			win.showAndWait();
		} else {
			if (noGuesses == 5) {
				Alert loser = new Alert(AlertType.INFORMATION);
				loser.setHeaderText("You lose! Try again");
				loser.showAndWait();
			}
		}
	}

	public void guess() {
		// I need the views to be connected to write this code. I gave it my best guess
		// though
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Font font = Font.font("Verdana", FontWeight.BOLD, 19);
		gc.setFont(font);
		// Game game = player.getCurrentGame();
		// String answer = game.getAnswer();
		int correctCount = 0;
		int yPos = 110;
		boolean skip = false;
		for (int i = 0; i < 5; i++) {
			skip = false;
			String inAnswer = "" + String.valueOf(currGuess.charAt(i));
			if (currGuess.charAt(i) == answer.charAt(i)) {
				correctCount++;
				gc.clearRect(115 + i * 55, yPos + noGuesses * 55, 50, 50);
				gc.setFill(Color.LIGHTGREEN);
				createRotator(getRect(i));
				gc.fillRect(115 + i * 55, yPos + noGuesses * 55, 50, 50);
				skip = true;
			} else if (answer.contains(inAnswer) && skip == false) {
				gc.clearRect(115 + i * 55, yPos + noGuesses * 55, 50, 50);
				gc.setFill(Color.LIGHTYELLOW);
				createRotator(getRect(i));
				gc.fillRect(115 + i * 55, yPos + noGuesses * 55, 50, 50);
			} else {
				gc.clearRect(115 + i * 55, yPos + noGuesses * 55, 50, 50);
				gc.setFill(Color.GRAY);
				createRotator(getRect(i));
				gc.fillRect(115 + i * 55, yPos + noGuesses * 55, 50, 50);
			}
			gc.setFill(Color.BLACK);
		}
		int xPos = 131;
		for (int j = 0; j < 5; j++) {
			String letter = String.valueOf(currGuess.charAt(j));
			gc.fillText(letter, xPos, currY + noGuesses * 55);
			xPos = xPos + 55;
		}
		if (correctCount == 5) {
			isWon = true;
			Alert win = new Alert(AlertType.INFORMATION);
			win.setHeaderText("You win!");
			win.showAndWait();
		} else {
			if (noGuesses == 5) {
				Alert loser = new Alert(AlertType.INFORMATION);
				loser.setHeaderText("You lose! Try again");
				loser.showAndWait();
			}
		}
	}

	public void darkModeLightMode() {
		settingsOpen = false;
		GraphicsContext gc = canvas.getGraphicsContext2D();
		// if (isDarkMode) {
		Letters = Color.WHITE;
		Slots = Color.BLACK;

		gc.clearRect(0, 0, 500, 650);
		gc.fillRect(0, 0, 500, 650);

		Font font2 = Font.font("Verdana", FontWeight.EXTRA_BOLD, 50);
		gc.setFill(Color.WHITE);
		gc.setFont(font2);
		gc.fillText("Wordle", 150, 60);
		gc.setFill(Color.WHITE);
		// gc.setFont(font2);
		setUp();

		gc.setStroke(Color.LIGHTGRAY);
		int xPos = 115;
		int yPos = 110;
		for (int k = 0; k < 6; k++) {
			for (int l = 0; l < 5; l++) {
				gc.fillRect(xPos, yPos, 50, 50);
				// Rectangle rect = new Rectangle(xPos, yPos, 50, 50);

				gc.setStroke(Color.LIGHTGRAY);
				gc.strokeRect(xPos, yPos, 50, 50);
				xPos = xPos + 55;
			}
			xPos = 115;
			yPos = yPos + 55;
		}

		noGuesses = 0;
		for (int i = 0; i < allGuesses.size(); i++) {
			guess(allGuesses.get(i));
			noGuesses++;
		}
		for (int i = 0; i < noLetters; i++) {
			char character = currGuess.charAt(i);
			gc.fillText(Character.toString(character), 131 + i * 55, 143 + noGuesses * 55);
		}
		// }
		Letters = Color.WHITE;
		Slots = Color.BLACK;
	}

	public void lightModeDarkMode() {
		settingsOpen = false;
		GraphicsContext gc = canvas.getGraphicsContext2D();

		gc.clearRect(0, 0, 500, 650);

		Font font2 = Font.font("Verdana", FontWeight.EXTRA_BOLD, 50);
		gc.setFill(Color.BLACK);
		gc.setFont(font2);
		gc.fillText("Wordle", 150, 60);

		setUp();

	}

	private class MousePressed implements EventHandler<MouseEvent> {

		@Override
		public void handle(MouseEvent event) {

			GraphicsContext gc = canvas.getGraphicsContext2D();

			int xCoord = (int) event.getSceneX();
			int yCoord = (int) event.getSceneY();

			settingsOpen = true;

			if (xCoord > 435 && yCoord < 40) {
				gc.setFill(Color.LIGHTGRAY);
				gc.fillRoundRect(100, 100, 300, 350, 5, 5);
				Font font = Font.font("Verdana", FontWeight.MEDIUM, 18);
				gc.setFont(font);
				gc.setFill(Letters);
				gc.fillText("SETTINGS", 200, 125);
				gc.fillText("DARK MODE:", 155, 220);
				gc.setFill(Color.BLACK);
				gc.fillText("OFF/ON", 295, 220);
				gc.setFill(Letters);
				gc.fillText("NEW GAME", 200, 320);
				gc.fillText("SAVE AND QUIT", 180, 420);
				gc.setFill(Color.BLACK);
				gc.setStroke(Letters);
				gc.strokeLine(370, 105, 390, 125);
				gc.setStroke(Letters);
				gc.strokeLine(390, 105, 370, 125);
			}
			if (xCoord > 290 && xCoord < 365 && settingsOpen && yCoord < 230 && yCoord > 200) {
				Letters = Color.WHITE;
				Slots = Color.BLACK;
			}
			if (xCoord > 260 && xCoord < 355 && settingsOpen && yCoord < 230 && yCoord > 200) {
				isDarkMode = !isDarkMode;
				if (isDarkMode) {
					Letters = Color.WHITE;
					Slots = Color.BLACK;
					darkModeLightMode();
					settingsOpen = false;
					noGuesses = 0;
					for (int i = 0; i < allGuesses.size(); i++) {
						guess(allGuesses.get(i));
						noGuesses++;
					}
					for (int i = 0; i < noLetters; i++) {
						char character = currGuess.charAt(i);
						gc.fillText(Character.toString(character), 131 + i * 55, 143 + noGuesses * 55);
					}
				} else {
					Letters = Color.BLACK;
					Slots = Color.WHITE;
					lightModeDarkMode();
				}
				settingsOpen = false;
				noGuesses = 0;
				for (int i = 0; i < allGuesses.size(); i++) {
					guess(allGuesses.get(i));
					noGuesses++;
				}
				for (int i = 0; i < noLetters; i++) {
					char character = currGuess.charAt(i);
					gc.fillText(Character.toString(character), 131 + i * 55, 143 + noGuesses * 55);
				}
			}
			if (xCoord > 370 && xCoord < 390 && settingsOpen && yCoord < 125 && yCoord > 105) {
				gc.clearRect(100, 100, 300, 350);
				settingsOpen = false;
				noGuesses = 0;
				for (int i = 0; i < allGuesses.size(); i++) {
					guess(allGuesses.get(i));
					noGuesses++;
				}
				for (int i = 0; i < noLetters; i++) {
					char character = currGuess.charAt(i);
					gc.fillText(Character.toString(character), 131 + i * 55, 143 + noGuesses * 55);
				}
			}
		}

	}

	public void update(Object theObserved) {
		// TODO Auto-generated method stub

	}
}
