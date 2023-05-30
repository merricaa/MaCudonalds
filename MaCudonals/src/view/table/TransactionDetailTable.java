package view.table;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Food;
import model.TransactionDetail;
import repository.FoodRepository;
import view.MainView;

public class TransactionDetailTable extends TableView<TransactionDetail> {
	private final String ID_TEXT = "ID";
	private final String FOOD_NAME_TEXT = "Food Name";
	private final String FOOD_TYPE_TEXT = "Food Type";
	private final String FOOD_PRICE_TEXT = "Food Price";
	private final String QUANTITY_TEXT = "Quantity";
	private final String TOTAL_PRICE_TEXT = "Total Price";
	
	private TableColumn<TransactionDetail, Integer> idColumn;
	private TableColumn<TransactionDetail, String> foodNameColumn;
	private TableColumn<TransactionDetail, String> foodTypeColumn;
	private TableColumn<TransactionDetail, Integer> foodPriceColumn;
	private TableColumn<TransactionDetail, Integer> quantityColumn;
	private TableColumn<TransactionDetail, Integer> totalPriceColumn;
	
	public TransactionDetailTable() {
		super();
		
		this.idColumn = new TableColumn<>(ID_TEXT);
		this.idColumn.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
		this.idColumn.setPrefWidth(MainView.WIDTH * 0.1);
		this.getColumns().add(idColumn);
		
		this.foodNameColumn = new TableColumn<>(FOOD_NAME_TEXT);
		this.foodNameColumn.setCellValueFactory(cellData -> {
			Integer foodId = cellData.getValue().getFoodId();
			
			Food food = FoodRepository.findById(foodId);
			String foodName = food.getName();
			
			return new SimpleStringProperty(foodName);
		});
		this.foodNameColumn.setPrefWidth(MainView.WIDTH * 0.2);
		this.getColumns().add(foodNameColumn);
		
		this.foodTypeColumn = new TableColumn<>(FOOD_TYPE_TEXT);
		this.foodTypeColumn.setCellValueFactory(cellData -> {
			Integer foodId = cellData.getValue().getFoodId();
			
			Food food = FoodRepository.findById(foodId);
			String foodType = food.getType();
			
			return new SimpleStringProperty(foodType);
		});
		this.foodTypeColumn.setPrefWidth(MainView.WIDTH * 0.2);
		this.getColumns().add(foodTypeColumn);
		
		this.foodPriceColumn = new TableColumn<>(FOOD_PRICE_TEXT);
		this.foodPriceColumn.setCellValueFactory(cellData -> {
			Integer foodId = cellData.getValue().getFoodId();
			
			Food food = FoodRepository.findById(foodId);
			Integer foodPrice = food.getPrice();
			
			return new SimpleIntegerProperty(foodPrice).asObject();
		});
		this.foodPriceColumn.setPrefWidth(MainView.WIDTH * 0.2);
		this.getColumns().add(foodPriceColumn);
		
		this.quantityColumn = new TableColumn<>(QUANTITY_TEXT);
		this.quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		this.quantityColumn.setPrefWidth(MainView.WIDTH * 0.1);
		this.getColumns().add(quantityColumn);
		
		this.totalPriceColumn = new TableColumn<>(TOTAL_PRICE_TEXT);
		this.totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
		this.totalPriceColumn.setPrefWidth(MainView.WIDTH * 0.2);
		this.getColumns().add(totalPriceColumn);
	}
}
