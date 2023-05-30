package view.table;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Food;
import repository.FoodRepository;
import view.MainView;

public class FoodTable extends TableView<Food> {
	private final String ID_TEXT = "ID";
	private final String NAME_TEXT = "Name";
	private final String TYPE_TEXT = "Type";
	private final String PRICE_TEXT = "Price";
	private final String STOCK_TEXT = "Stock";
	
	private TableColumn<Food, Integer> idColumn;
	private TableColumn<Food, String> nameColumn;
	private TableColumn<Food, String> typeColumn;
	private TableColumn<Food, Integer> priceColumn;
	private TableColumn<Food, Integer> stockColumn;
	
	private List<Food> foodList;
	
	public FoodTable() {
		this.idColumn = new TableColumn<>(ID_TEXT);
		this.idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		this.idColumn.setPrefWidth(MainView.WIDTH * 0.1);
		this.getColumns().add(idColumn);
		
		this.nameColumn = new TableColumn<>(NAME_TEXT);
		this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		this.nameColumn.setPrefWidth(MainView.WIDTH * 0.3);
		this.getColumns().add(nameColumn);
		
		this.typeColumn = new TableColumn<>(TYPE_TEXT);
		this.typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
		this.typeColumn.setPrefWidth(MainView.WIDTH * 0.2);
		this.getColumns().add(typeColumn);
		
		this.priceColumn = new TableColumn<>(PRICE_TEXT);
		this.priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		this.priceColumn.setPrefWidth(MainView.WIDTH * 0.2);
		this.getColumns().add(priceColumn);
		
		this.stockColumn = new TableColumn<>(STOCK_TEXT);
		this.stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
		this.stockColumn.setPrefWidth(MainView.WIDTH * 0.2);
		this.getColumns().add(stockColumn);
		
		loadData();
	}
	
	public void reset() {
		this.foodList = null;
		loadData();
		this.refresh();
	}
	
	public void loadData() {
		if(this.foodList == null) {
			this.foodList = FoodRepository.findAll();
		}
		this.setItems(FXCollections.observableArrayList(this.foodList));
	};
	
	public List<Food> getFoodList() {
		return foodList;
	}
	
	public void updateFoodStock(Integer index, Integer newStock) {
		this.foodList.get(index).setStock(newStock);
		this.refresh();
	}
}
