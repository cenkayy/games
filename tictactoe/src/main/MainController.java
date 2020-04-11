package main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import font.FONT_TYPE;
import font.Fonts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import scene.TableController;

public class MainController {
	private Stage mainStage;
	private boolean isAI = false;
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button btnPlayer;

	@FXML
	private Button btnComputer;

	@FXML
	void btnComputerOnClick(MouseEvent event) throws IOException {
		startGame(true);
	}

	@FXML
	void btnPlayerOnClick(MouseEvent event) throws IOException {
		startGame(false);
	}

	@FXML
	void initialize() {
		assert btnPlayer != null : "fx:id=\"btnPlayer\" was not injected: check your FXML file 'Main.fxml'.";
		assert btnComputer != null : "fx:id=\"btnComputer\" was not injected: check your FXML file 'Main.fxml'.";
		btnPlayer.setFont(new Fonts().getFont(FONT_TYPE.MEDIUM, 16));
		btnComputer.setFont(new Fonts().getFont(FONT_TYPE.MEDIUM, 16));
	}

	private void startGame(boolean isAI) throws IOException {
		this.isAI = isAI;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/scene/Table.fxml"));
		loader.setControllerFactory(type -> {
			if (type == TableController.class) {
				return new TableController(isAI);
			}
			// default behavior: need this in case there are <fx:include> in the FXML
			try {
				return type.getConstructor().newInstance();
			} catch (Exception exc) {
				// fatal...
				throw new RuntimeException(exc);
			}
		});
		Parent root;
		try {
			root = (Parent) loader.load();
			root.setStyle("-fx-background-color:transparent");
			TableController controller = loader.getController();
			controller.setIsAI(isAI);
			Stage stage = new Stage();
			stage.initStyle(StageStyle.TRANSPARENT);
			Scene scene = new Scene(root, 300, 363);
			scene.getStylesheets().add(getClass().getResource("/scene/table.css").toExternalForm());
			scene.setFill(Color.TRANSPARENT); // Pane transparent background
			stage.setScene(scene);
			stage.setResizable(false);
			controller.setStage(stage);
			controller.setMainStage(mainStage);
			stage.show();
			mainStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void setStage(Stage mainStage) {
		this.mainStage = mainStage;
	}

	public boolean isAI() {
		return isAI;
	}
}
