package view.table;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.CartItem;
import view.MainView;

public class CartItemTable extends TableView<CartItem> {
	private final String FOOD_ID_TEXT = "Food ID";
	private final String FOOD_NAME_TEXT = "Food Name";
	private final String FOOD_PRICE_TEXT = "Food Price";
	private final String QUANTITY_TEXT = "Quantity";
	private final String TOTAL_PRICE_TEXT = "Total Price";
	
	private TableColumn<CartItem, Integer> foodIdColumn;
	private TableColumn<CartItem, String> foodNameColumn;
	private TableColumn<CartItem, Integer> foodPriceColumn;
	private TableColumn<CartItem, Integer> quantityColumn;
	private TableColumn<CartItem, Integer> totalPriceColumn;
	
	public CartItemTable() {
		this.foodIdColumn = new TableColumn<CartItem, Integer>(FOOD_ID_TEXT);
		this.foodIdColumn.setCellValueFactory(new PropertyValueFactory<>("foodId"));
		this.foodIdColumn.setPrefWidth(MainView.WIDTH * 0.53 * 0.15);
		this.getColumns().add(foodIdColumn);
		
		this.foodNameColumn = new TableColumn<CartItem, String>(FOOD_NAME_TEXT);
		this.foodNameColumn.setCellValueFactory(new PropertyValueFactory<>("foodName"));
		this.foodNameColumn.setPrefWidth(MainView.WIDTH * 0.53 * 0.3);
		this.getColumns().add(foodNameColumn);
		
		this.foodPriceColumn = new TableColumn<CartItem, Integer>(FOOD_PRICE_TEXT);
		this.foodPriceColumn.setCellValueFactory(new PropertyValueFactory<>("foodPrice"));
		this.foodPriceColumn.setPrefWidth(MainView.WIDTH * 0.53 * 0.2);
		this.getColumns().add(foodPriceColumn);
		
		this.quantityColumn = new TableColumn<CartItem, Integer>(QUANTITY_TEXT);
		this.quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		this.quantityColumn.setPrefWidth(MainView.WIDTH * 0.53 * 0.15);
		this.getColumns().add(quantityColumn);
		
		this.totalPriceColumn = new TableColumn<CartItem, Integer>(TOTAL_PRICE_TEXT);
		this.totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
		this.totalPriceColumn.setPrefWidth(MainView.WIDTH * 0.53 * 0.2);
		this.getColumns().add(totalPriceColumn);
		
		this.setItems(FXCollections.observableArrayList(new ArrayList<>()));
	}
	
	public void reset() {
		this.setItems(FXCollections.observableArrayList(new ArrayList<>()));
		this.refresh();
	};
	
	public void updateCartItemQuantity(Integer index, Integer newQuantity) {
		this.getItems().get(index).setQuantity(newQuantity);
		Integer newTotalPrice = this.getItems().get(index).getFoodPrice() * newQuantity;
		this.getItems().get(index).setTotalPrice(newTotalPrice);
		this.refresh();
	}
}
