package app;

import javafx.application.Application;
import javafx.stage.Stage;
import view.BaseView;
import view.LoginView;

public class MacuDonals extends Application {
	private static MacuDonals instance;
	
	public static MacuDonals getInstance() {
		return instance;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private Stage stage;
	
	public MacuDonals() {
		instance = this;
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("MacuDonals");
		stage.setScene(LoginView.getInstance().getScene());	
		stage.show();
		
		this.stage = stage;
	}
	
	public void changeScene(BaseView view) {
		this.stage.setScene(view.getScene());
		this.stage.centerOnScreen();
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
