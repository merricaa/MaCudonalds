package view.menu;

import app.MacuDonals;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import view.LoginView;
import view.MainView;

public class AccountMenu extends Menu {
	private final String ACCOUNT_MENU_TEXT = "Account";
	private final String LOG_OUT_MENU_ITEM_TEXT = "Log out";
	
	private MenuItem logOutMenuItem;
	
	public AccountMenu() {
		super();
		this.setText(ACCOUNT_MENU_TEXT);
		
		this.logOutMenuItem = new MenuItem(LOG_OUT_MENU_ITEM_TEXT);
		this.getItems().add(logOutMenuItem);
		
		setEventHandlers();
	}
	
	public void setEventHandlers() {
		this.logOutMenuItem.setOnAction(e -> {
			MacuDonals.getInstance().changeScene(LoginView.getInstance());
			MainView.destroy();
		});
	}
}
