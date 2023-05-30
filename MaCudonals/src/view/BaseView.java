package view;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;

public abstract class BaseView {
	protected Double width;
	protected Double height;
	
	protected BorderPane borderPane;
	protected Scene scene;
	
	public BaseView(Double width, Double height) {
		this.width = width;
		this.height = height;
		this.borderPane = new BorderPane();
		this.scene = new Scene(this.borderPane, width, height);
	}
	
	public abstract void setEventHandlers();
	
	public void showAlertDialog(AlertType alertType, String contentText) {
		Alert a = new Alert(alertType, contentText);
		a.show();
	}
	
	public Double getWidth() {
		return width;
	}
	
	public void setWidth(Double width) {
		this.width = width;
	}
	
	public Double getHeight() {
		return height;
	}
	
	public void setHeight(Double height) {
		this.height = height;
	}
	
	public BorderPane getPane() {
		return borderPane;
	}
	
	public void setPane(BorderPane pane) {
		this.borderPane = pane;
	}
	
	public Scene getScene() {
		return scene;
	}
	
	public void setScene(Scene scene) {
		this.scene = scene;
	}
}
