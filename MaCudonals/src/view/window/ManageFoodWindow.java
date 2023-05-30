package view.window;

import controller.FoodController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TableRow;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.Food;
import model.ResponseSchema;
import view.MainView;
import view.table.FoodTable;

public class ManageFoodWindow extends BaseWindow {
	public static final String TITLE = "Manage Food";
	
	private static ManageFoodWindow instance;
	
	public static ManageFoodWindow getInstance() {
		if(instance == null) {
			instance = new ManageFoodWindow();
		}
		return instance;
	}
	
	public static void destroy() {
		instance = null;
		MainView.getInstance().clearContent();
	}
	
	private final Double H_GAP = MainView.WIDTH / 40d;
	private final Double V_GAP = MainView.HEIGHT / 40d;
	
	private final String ID_TEXT = "ID";
	private final String NAME_TEXT = "Name";
	private final String TYPE_TEXT = "Type";
	private final String PRICE_TEXT = "Price";
	private final String STOCK_TEXT = "Stock";
	private final String INSERT_TEXT = "Insert";
	private final String UPDATE_TEXT = "Update";
	private final String DELETE_TEXT = "Delete";
	
	private BorderPane borderPane;
	
	private FoodTable foodTable;
	
	private GridPane itemGridPane;
	
	private Label idLabel;
	private Label idDataLabel;
	private Label nameLabel;
	private Label typeLabel;
	private Label priceLabel;
	private Label stockLabel;
	
	private TextField nameTextField;
	private TextField priceTextField;
	
	private ComboBox<String> typeComboBox;
	
	private Spinner<Integer> stockSpinner;
	
	private Button insertButton;
	private Button updateButton;
	private Button deleteButton;
	
	public ManageFoodWindow() {
		super(TITLE);
		
		this.borderPane = new BorderPane();
		this.setContentPane(borderPane);
		
		this.foodTable = new FoodTable();
		this.foodTable.setMaxHeight(MainView.HEIGHT * 0.4);
		this.borderPane.setTop(foodTable);
		BorderPane.setAlignment(foodTable, Pos.CENTER);
		
		this.itemGridPane = new GridPane();
		this.itemGridPane.setHgap(H_GAP);
		this.itemGridPane.setVgap(V_GAP);
		this.itemGridPane.setAlignment(Pos.TOP_CENTER);
		this.borderPane.setCenter(itemGridPane);
		BorderPane.setMargin(itemGridPane, new Insets(V_GAP));
		
		this.idLabel = new Label(ID_TEXT);
		this.itemGridPane.add(idLabel, 0, 1);
		
		this.idDataLabel = new Label();
		this.idDataLabel.setMinWidth(MainView.WIDTH * 0.3);
		this.itemGridPane.add(idDataLabel, 1, 1);
		
		this.nameLabel = new Label(NAME_TEXT);
		this.itemGridPane.add(nameLabel, 0, 2);
		
		this.nameTextField = new TextField();
		this.nameTextField.setMinWidth(MainView.WIDTH * 0.3);
		this.itemGridPane.add(nameTextField, 1, 2);
		
		this.typeLabel = new Label(TYPE_TEXT);
		this.itemGridPane.add(typeLabel, 0, 3);
		
		this.typeComboBox = new ComboBox<>();
		this.typeComboBox.setItems(FXCollections.observableArrayList(Food.TYPE_LIST));
		this.typeComboBox.setMinWidth(MainView.WIDTH * 0.3);
		this.itemGridPane.add(typeComboBox, 1, 3);
		
		this.priceLabel = new Label(PRICE_TEXT);
		this.itemGridPane.add(priceLabel, 0, 4);
		
		this.priceTextField = new TextField();
		this.priceTextField.setMinWidth(MainView.WIDTH * 0.3);
		this.itemGridPane.add(priceTextField, 1, 4);
		
		this.stockLabel = new Label(STOCK_TEXT);
		this.itemGridPane.add(stockLabel, 0, 5);
		
		this.stockSpinner = new Spinner<>();
		this.stockSpinner.setValueFactory(new IntegerSpinnerValueFactory(0, Integer.MAX_VALUE));
		this.stockSpinner.setMinWidth(MainView.WIDTH * 0.3);
		this.itemGridPane.add(stockSpinner, 1, 5);
		
		this.insertButton = new Button(INSERT_TEXT);
		this.insertButton.setMinWidth(MainView.WIDTH * 0.2);
		this.itemGridPane.add(insertButton, 2, 2);
		
		this.updateButton = new Button(UPDATE_TEXT);
		this.updateButton.setMinWidth(MainView.WIDTH * 0.2);
		this.itemGridPane.add(updateButton, 2, 3);
		
		this.deleteButton = new Button(DELETE_TEXT);
		this.deleteButton.setMinWidth(MainView.WIDTH * 0.2);
		this.itemGridPane.add(deleteButton, 2, 5);
		
		this.setEventHandlers();
	}

	@Override
	public void setEventHandlers() {
		this.closeIcon.setOnAction(e -> {
			ManageFoodWindow.destroy();
		});
		
		this.foodTable.setRowFactory(tv -> {
			TableRow<Food> row = new TableRow<>();
			row.setOnMouseClicked(e -> {
				loadFoodData(row.getItem());
			});
			return row;
		});
		
		this.insertButton.setOnAction(e -> {
			String name = this.nameTextField.getText();
			String type = this.typeComboBox.getSelectionModel().getSelectedItem();
			Integer price = 0;
			Integer stock = this.stockSpinner.getValue();
			try {
				price = Integer.valueOf(this.priceTextField.getText());
			}
			catch(NumberFormatException ex) {
				MainView.getInstance().showAlertDialog(AlertType.ERROR, "Price must be numeric");
				return;
			}
			
			Food food = new Food(null, name, type, price, stock);
			
			ResponseSchema response = FoodController.insert(food);
			if(response.getErrorCode() == FoodController.INVALID_DATA_ERROR_CODE) {
				MainView.getInstance().showAlertDialog(AlertType.ERROR, response.getErrorMessage());
				return;
			}
			
			foodTable.reset();
			MainView.getInstance().showAlertDialog(AlertType.INFORMATION, response.getErrorMessage());
		});
		
		this.updateButton.setOnAction(e -> {
			String idString = this.idDataLabel.getText();
			Integer id = -1;
			try {
				id = Integer.valueOf(idString);
			}
			catch(NumberFormatException ex) {
				MainView.getInstance().showAlertDialog(AlertType.ERROR, "Food hasn't been picked");
				return;
			}
			
			String name = this.nameTextField.getText();
			String type = this.typeComboBox.getSelectionModel().getSelectedItem();
			Integer price = 0;
			Integer stock = this.stockSpinner.getValue();
			try {
				price = Integer.valueOf(this.priceTextField.getText());
			}
			catch(NumberFormatException ex) {
				MainView.getInstance().showAlertDialog(AlertType.ERROR, "Price must be numeric");
				return;
			}
			
			Food food = new Food(id, name, type, price, stock);
			
			ResponseSchema response = FoodController.update(food);
			if(response.getErrorCode() == FoodController.INVALID_DATA_ERROR_CODE) {
				MainView.getInstance().showAlertDialog(AlertType.ERROR, response.getErrorMessage());
				return;
			}
			
			foodTable.reset();
			MainView.getInstance().showAlertDialog(AlertType.INFORMATION, response.getErrorMessage());
		});
		
		this.deleteButton.setOnAction(e -> {
			Integer id = -1;
			try {
				id = Integer.valueOf(this.idDataLabel.getText());
			}
			catch(NumberFormatException ex) {
				MainView.getInstance().showAlertDialog(AlertType.ERROR, "No food is selected");
				return;
			}
			
			ResponseSchema response = FoodController.deleteById(id);
			if(response.getErrorCode() == FoodController.INVALID_DATA_ERROR_CODE) {
				MainView.getInstance().showAlertDialog(AlertType.ERROR, response.getErrorMessage());
				return;
			}
			
			foodTable.reset();
			MainView.getInstance().showAlertDialog(AlertType.INFORMATION, response.getErrorMessage());
		});
	}
	
	public void loadFoodData(Food food) {
		if(food == null) return;
		this.idDataLabel.setText(food.getId().toString());
		this.nameTextField.setText(food.getName());
		this.typeComboBox.getSelectionModel().select(food.getType());
		this.priceTextField.setText(food.getPrice().toString());
		this.stockSpinner.getValueFactory().setValue(food.getStock());
	}
}
