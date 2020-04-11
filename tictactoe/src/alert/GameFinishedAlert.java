package alert;

import font.FONT_TYPE;
import font.Fonts;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GameFinishedAlert {
	public void finishAlert(String message, Button... arr) {
		if (arr.length > 0)
			disableButtons(arr);
		Stage dialogStage = new Stage();
		// dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initModality(Modality.APPLICATION_MODAL);
		dialogStage.initStyle(StageStyle.TRANSPARENT);
		Label label = new Label(message);
		label.setStyle("-fx-text-fill:white");
		label.setFont(new Fonts().getFont(FONT_TYPE.REGULAR, 15));

		Button btnOk = new Button("Okay");
		btnOk.setFont(new Fonts().getFont(FONT_TYPE.MEDIUM, 14));
		btnOk.setAlignment(Pos.CENTER_LEFT);
		btnOk.getStylesheets().add(getClass().getResource("alert.css").toExternalForm());
		btnOk.setOnAction(event -> {
			dialogStage.close();
		});
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.TOP_LEFT);
		vbox.getChildren().add(label);
		vbox.setPrefSize(200, 120);
		vbox.setMargin(label, new Insets(5));
		vbox.setPadding(new Insets(10));

		VBox vbox1 = new VBox();
		vbox1.getChildren().add(btnOk);
		vbox1.setAlignment(Pos.BOTTOM_RIGHT);
		vbox1.setMargin(btnOk, new Insets(5, 15, 15, 5));
		vbox1.setPrefSize(200, 120);

		HBox box = new HBox();
		box.setAlignment(Pos.CENTER);
		box.setPrefHeight(95);
		box.setPrefWidth(350);
		box.getChildren().add(vbox);
		box.getChildren().add(vbox1);
		box.setStyle("-fx-background-color:#2196f3;");
		dialogStage.setScene(new Scene(box));
		dialogStage.show();
	}

	private void disableButtons(Button... arr) {
		for (Button button : arr) {
			button.setDisable(true);
		}
	}
}
