package scene;

import java.net.URL;
import java.util.ResourceBundle;

import alert.GameFinishedAlert;
import font.FONT_TYPE;
import font.Fonts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mode.GameMode;
import mode.GameModeImp;

public class TableController {
	private double x = 0;
	private double y = 0;
	private boolean isAI = false;
	private boolean isX = true;
	private boolean firstAI = true;
	Button[] buttons = new Button[9];
	private Stage mainStage;
	private Stage stage;
	private GameMode gameMode;
	private Fonts fonts;
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private GridPane gridPane;

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private Button btn00;

	@FXML
	private Button btn10;

	@FXML
	private Button btn01;

	@FXML
	private Button btn12;

	@FXML
	private Button btn11;

	@FXML
	private Button btn02;

	@FXML
	private Button btn20;

	@FXML
	private Button btn21;

	@FXML
	private Button btn22;

	@FXML
	private MenuBar menuBar;

	@FXML
	private MenuItem itemClearTable;

	@FXML
	private MenuItem itemChangeGameMode;

	@FXML
	private MenuItem itemAbout;
	
	@FXML
    private Menu parentMenuGame;
	
	@FXML
    private Menu parentMenuHelp;
	
	@FXML
	private Label lblTurnText;
	
	@FXML
    private VBox vBoxTurn;
	
	@FXML
    private VBox paneExit;
	
	@FXML
    private Label lblExit;
	
	@FXML
	void btnOnClick(MouseEvent event) {
		Button button = (Button) event.getSource();
		if (!(button.getText().length() > 0)) {
			char c;
			if (isX)
				c = 'X';
			else
				c = 'O';
			if (!(button.getText().length() > 0)) {
				button.setText(c + "");
				button.setStyle("-fx-background-color:#64b5f6");
				isX = isX ? false : true;
			}
			checkWinner();
		}
	}

	public TableController(boolean isAI) {
		this.isAI = isAI;
	}

	private void setLabelText() {
		if (isAI)
			if (isX)
				lblTurnText.setText("Player");
			else
				lblTurnText.setText("Computer");
		else if (isX)
			lblTurnText.setText("Player 1");
		else
			lblTurnText.setText("Player 2");
	}

	@FXML
	void itemAboutOnClick(ActionEvent event) {
		new GameFinishedAlert().finishAlert("github.com/cnkay");
	}

	@FXML
	void itemChangeGameModeOnClick(ActionEvent event) {
		stage.close();
		mainStage.show();
	}

	@FXML
	void itemClearTableOnClick(ActionEvent event) {
		for (Button btn : buttons) {
			btn.getStylesheets().removeAll();
			btn.setStyle("-fx-background-color:cyan;");
			btn.setText("");
			btn.setDisable(false);
			btn.getStylesheets().add(getClass().getResource("table.css").toExternalForm());
		}
		isX = true;
		if (isAI) {
			if (firstAI) {
				Sorry.setFirstRandom(true);
				checkWinner();
				Sorry.setFirstRandom(false);
				firstAI = false;
			} else {
				firstAI = true;
				Sorry.setFirstRandom(false);
			}
			lblTurnText.setText("Player");
		} else {
			lblTurnText.setText("Player 1");
		}

	}
	
	@FXML
    void paneExitOnClick(MouseEvent event) {
		javafx.application.Platform.exit();
    }


	@FXML
	void initialize() {
		assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'Table.fxml'.";
		assert gridPane != null : "fx:id=\"gridPane\" was not injected: check your FXML file 'Table.fxml'.";
		assert btn00 != null : "fx:id=\"btn00\" was not injected: check your FXML file 'Table.fxml'.";
		assert btn10 != null : "fx:id=\"btn10\" was not injected: check your FXML file 'Table.fxml'.";
		assert btn01 != null : "fx:id=\"btn01\" was not injected: check your FXML file 'Table.fxml'.";
		assert btn12 != null : "fx:id=\"btn12\" was not injected: check your FXML file 'Table.fxml'.";
		assert btn11 != null : "fx:id=\"btn11\" was not injected: check your FXML file 'Table.fxml'.";
		assert btn02 != null : "fx:id=\"btn02\" was not injected: check your FXML file 'Table.fxml'.";
		assert btn20 != null : "fx:id=\"btn20\" was not injected: check your FXML file 'Table.fxml'.";
		assert btn21 != null : "fx:id=\"btn21\" was not injected: check your FXML file 'Table.fxml'.";
		assert btn22 != null : "fx:id=\"btn22\" was not injected: check your FXML file 'Table.fxml'.";
		assert parentMenuGame != null : "fx:id=\"parentMenuGame\" was not injected: check your FXML file 'Table.fxml'.";
		assert parentMenuHelp != null : "fx:id=\"parentMenuHelp\" was not injected: check your FXML file 'Table.fxml'.";
		assert menuBar != null : "fx:id=\"menuBar\" was not injected: check your FXML file 'Table.fxml'.";
		assert itemClearTable != null : "fx:id=\"itemClearTable\" was not injected: check your FXML file 'Table.fxml'.";
		assert itemChangeGameMode != null : "fx:id=\"itemChangeGameMode\" was not injected: check your FXML file 'Table.fxml'.";
		assert itemAbout != null : "fx:id=\"itemAbout\" was not injected: check your FXML file 'Table.fxml'.";
		assert lblTurnText != null : "fx:id=\"lblTurnText\" was not injected: check your FXML file 'Table.fxml'.";
		assert lblExit != null : "fx:id=\"lblExit\" was not injected: check your FXML file 'Table.fxml'.";
		assert vBoxTurn != null : "fx:id=\"vBoxTurn\" was not injected: check your FXML file 'Table.fxml'.";
		assert paneExit != null : "fx:id=\"paneExit\" was not injected: check your FXML file 'Table.fxml'.";
		fonts=new Fonts();
		lblTurnText.setText("");
		lblTurnText.setFont(fonts.getFont(FONT_TYPE.BOLD, 20));
		lblExit.setFont(fonts.getFont(FONT_TYPE.EXTRA_BOLD, 16));
		lblExit.setTextFill(Color.WHITE);
		setLabelText();
		buttons = new Button[] { btn00, btn01, btn02, btn10, btn11, btn12, btn20, btn21, btn22 };
		for (Button btn : buttons)
			btn.setFont(fonts.getFont(FONT_TYPE.EXTRA_BOLD, 42));
		itemClearTableOnClick(new ActionEvent());
		itemClearTable.getStyleClass().add("-fx-text-fill:#ef5350");
		rootPaneMouseListener();
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}

	public void setIsAI(boolean isAI) {
		this.isAI = isAI;
		Sorry.setAI(isAI);
	}

	private boolean isFull() {
		for (Button btn : buttons) {
			if (!(btn.getText().length() > 0))
				return false;
		}
		return true;
	}
	private void checkWinner() {
		gameMode = new GameModeImp(buttons);
		if (isFull()) {
			if (!gameMode.checkWinnerP1VSP2()) {
				new GameFinishedAlert().finishAlert("Round Draw!", buttons);
				for (Button button2 : buttons)
					button2.setDisable(true);
			}
		} else {
			if (isAI) {
				gameMode.turnForAI();
				isX = true;
			}
			if (!gameMode.checkWinnerP1VSP2()) {
				if (isFull()) {
					new GameFinishedAlert().finishAlert("Round Draw!", buttons);
					for (Button button2 : buttons)
						button2.setDisable(true);
				}
			}
		}
		setLabelText();
	}

	private void rootPaneMouseListener() {
		menuBar.setOnMousePressed((event) -> {
			x = event.getSceneX();
			y = event.getSceneY();
		});
		menuBar.setOnMouseDragged(((event) -> {
			stage = (Stage) (((Node) event.getSource())).getScene().getWindow();
			stage.setX(event.getScreenX() - x);
			stage.setY(event.getScreenY() - y);
		}));
	}
}