package view.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import view.MainView;
import view.window.BuyFoodWindow;
import view.window.ManageFoodWindow;
import view.window.TransactionWindow;

public class ActionMenu extends Menu {
	private final String ACTION_MENU_TEXT = "Menu";
	private final String BUY_FOOD_MENU_ITEM_TEXT = "Buy Food";
	private final String MANAGE_FOOD_MENU_ITEM_TEXT = "Manage Food";
	private final String TRANSACTIONS_MENU_ITEM_TEXT = "Transactions";
	
	private MenuItem buyFoodMenuItem;
	private MenuItem manageFoodMenuItem;
	private MenuItem transactionsMenuItem;
	
	public ActionMenu(String role) {
		super();
		this.setText(ACTION_MENU_TEXT);
		
		this.buyFoodMenuItem = new MenuItem(BUY_FOOD_MENU_ITEM_TEXT);
		if(role.equalsIgnoreCase("USER")) {
			this.getItems().add(buyFoodMenuItem);
		}
		
		this.manageFoodMenuItem = new MenuItem(MANAGE_FOOD_MENU_ITEM_TEXT);
		this.getItems().add(manageFoodMenuItem);
		
		this.transactionsMenuItem = new MenuItem(TRANSACTIONS_MENU_ITEM_TEXT);
		this.getItems().add(transactionsMenuItem);
		
		setEventHandlers();
	}
	
	public void setEventHandlers() {
		this.buyFoodMenuItem.setOnAction(e -> {
			MainView.getInstance().setContent(new BuyFoodWindow());
		});
		
		this.manageFoodMenuItem.setOnAction(e -> {
			MainView.getInstance().setContent(new ManageFoodWindow());
		});
		
		this.transactionsMenuItem.setOnAction(e -> {
			MainView.getInstance().setContent(new TransactionWindow());
		});
	}
}
