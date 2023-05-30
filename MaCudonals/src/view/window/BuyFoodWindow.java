package view.window;

import java.util.List;

import controller.TransactionController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TableRow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import model.CartItem;
import model.Food;
import model.ResponseSchema;
import view.MainView;
import view.table.CartItemTable;
import view.table.FoodTable;

public class BuyFoodWindow extends BaseWindow {
	public static final String TITLE = "Buy Food";

	private static BuyFoodWindow instance;

	public static BuyFoodWindow getInstance() {
		if (instance == null) {
			instance = new BuyFoodWindow();
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
	private final String ADD_TO_CART_TEXT = "Add to cart";
	private final String REMOVE_FROM_CART_TEXT = "Remove from cart";
	private final String CHECKOUT_TEXT = "Checkout";
	private final String QUANTITY_TEXT = "Quantity:";

	private BorderPane borderPane;

	private FoodTable foodTable;

	private CartItemTable cartItemTable;

	private GridPane itemGridPane;

	private Label idLabel;
	private Label nameLabel;
	private Label typeLabel;
	private Label priceLabel;
	private Label stockLabel;
	private Label idDataLabel;
	private Label nameDataLabel;
	private Label typeDataLabel;
	private Label priceDataLabel;
	private Label stockDataLabel;
	private Label qtyLabel;

	private Spinner<Integer> amountSpinner;

	private Button addToCartButton;
	private Button removeFromCartButton;
	private Button checkoutButton;

	public BuyFoodWindow() {
		super(TITLE);

		this.borderPane = new BorderPane();
		this.setContentPane(borderPane);

		this.foodTable = new FoodTable();
		this.foodTable.setMaxHeight(MainView.HEIGHT * 0.4);
		this.borderPane.setTop(foodTable);
		BorderPane.setAlignment(foodTable, Pos.CENTER);

		this.cartItemTable = new CartItemTable();
		this.borderPane.setCenter(cartItemTable);
		BorderPane.setAlignment(cartItemTable, Pos.CENTER);
		BorderPane.setMargin(cartItemTable, new Insets(V_GAP));

		this.itemGridPane = new GridPane();
		this.itemGridPane.setHgap(H_GAP);
		this.itemGridPane.setVgap(V_GAP);
		this.itemGridPane.setAlignment(Pos.TOP_CENTER);
		this.borderPane.setLeft(itemGridPane);
		BorderPane.setMargin(itemGridPane, new Insets(V_GAP));

		this.idLabel = new Label(ID_TEXT);
		this.itemGridPane.add(idLabel, 0, 1);

		this.idDataLabel = new Label();
		this.itemGridPane.add(idDataLabel, 1, 1);

		this.nameLabel = new Label(NAME_TEXT);
		this.itemGridPane.add(nameLabel, 0, 2);

		this.nameDataLabel = new Label();
		this.nameDataLabel.setPrefWidth(MainView.WIDTH * 0.15);
		this.itemGridPane.add(nameDataLabel, 1, 2);

		this.typeLabel = new Label(TYPE_TEXT);
		this.itemGridPane.add(typeLabel, 0, 3);

		this.typeDataLabel = new Label();
		this.typeDataLabel.setPrefWidth(MainView.WIDTH * 0.15);
		this.itemGridPane.add(typeDataLabel, 1, 3);

		this.priceLabel = new Label(PRICE_TEXT);
		this.itemGridPane.add(priceLabel, 0, 4);

		this.priceDataLabel = new Label();
		this.priceDataLabel.setPrefWidth(MainView.WIDTH * 0.15);
		this.itemGridPane.add(priceDataLabel, 1, 4);

		this.stockLabel = new Label(STOCK_TEXT);
		this.itemGridPane.add(stockLabel, 0, 5);

		this.stockDataLabel = new Label();
		this.stockDataLabel.setPrefWidth(MainView.WIDTH * 0.15);
		this.itemGridPane.add(stockDataLabel, 1, 5);
		
		this.qtyLabel = new Label(QUANTITY_TEXT);
		this.itemGridPane.add(qtyLabel, 2, 1);

		this.amountSpinner = new Spinner<>();
		this.amountSpinner.setPrefWidth(MainView.WIDTH * 0.15);
		this.itemGridPane.add(amountSpinner, 2, 2);

		this.addToCartButton = new Button(ADD_TO_CART_TEXT);
		this.addToCartButton.setPrefWidth(MainView.WIDTH * 0.15);
		this.itemGridPane.add(addToCartButton, 2, 3);

		this.removeFromCartButton = new Button(REMOVE_FROM_CART_TEXT);
		this.removeFromCartButton.setPrefWidth(MainView.WIDTH * 0.15);
		this.itemGridPane.add(removeFromCartButton, 2, 4);

		this.checkoutButton = new Button(CHECKOUT_TEXT);
		this.checkoutButton.setPrefWidth(MainView.WIDTH * 0.15);
		this.itemGridPane.add(checkoutButton, 2, 5);
		
		this.setEventHandlers();
	}

	@Override
	public void setEventHandlers() {
		this.closeIcon.setOnAction(e -> {
			BuyFoodWindow.destroy();
		});
		
		this.foodTable.setRowFactory(tv -> {
			TableRow<Food> row = new TableRow<>();
			row.setOnMouseClicked(e -> {
				loadFoodData(row.getItem());
			});
			return row;
		});
		
		this.addToCartButton.setOnAction(e -> {
			Integer selectedIndex = this.foodTable.getSelectionModel().getSelectedIndex();
			if(selectedIndex == -1) return;
			
			// Update food table
			Food selectedFood = this.foodTable.getSelectionModel().getSelectedItem();
			
			Integer selectedStock = selectedFood.getStock();
			Integer selectedQuantity = amountSpinner.getValue();
			Integer newStock = selectedStock - selectedQuantity;
			
			this.foodTable.updateFoodStock(selectedIndex, newStock);
			setAmountSpinnerLimit(newStock);
			
			// Update cart item table
			Boolean inCart = false;
			
			for(Integer i = 0; i < cartItemTable.getItems().size(); i++) {
				CartItem cartItem = cartItemTable.getItems().get(i);
				
				if(cartItem.getFoodId().equals(selectedFood.getId())) {
					inCart = true;
					
					Integer newQuantity = cartItem.getQuantity() + selectedQuantity;
					cartItemTable.updateCartItemQuantity(i, newQuantity);
					
					break;
				}
			}
			
			if(!inCart) {
				Integer foodId = selectedFood.getId();
				String foodName = selectedFood.getName();
				Integer foodPrice = selectedFood.getPrice();
				Integer totalPrice = selectedQuantity * foodPrice;
				
				CartItem newCartItem = new CartItem(foodId, foodName, selectedQuantity, foodPrice, totalPrice);
				
				cartItemTable.getItems().add(newCartItem);
				cartItemTable.refresh();
			}
			
			// To update spinner limit
			loadFoodData(selectedFood);
		});
		
		this.removeFromCartButton.setOnAction(e -> {
			CartItem selectedCartItem = cartItemTable.getSelectionModel().getSelectedItem();
			if(selectedCartItem == null) return;
			
			for(Integer i = 0; i < foodTable.getItems().size(); i++) {
				Food currentFood = foodTable.getItems().get(i);
				
				if(currentFood.getId() == selectedCartItem.getFoodId()) {
					Integer newStock = currentFood.getStock() + selectedCartItem.getQuantity();
					foodTable.updateFoodStock(i, newStock);
					
					loadFoodData(currentFood);
					break;
				}
			}
			
			cartItemTable.getItems().remove(selectedCartItem);
		});
		
		this.checkoutButton.setOnAction(e -> {
			List<Food> foodList = foodTable.getItems();
			List<CartItem> cartItemList = cartItemTable.getItems();
			
			ResponseSchema response = TransactionController.checkout(foodList, cartItemList);
			reset();
			
			AlertType alertType = (response.getErrorCode() == TransactionController.SUCCESS_ERROR_CODE) ? AlertType.INFORMATION : AlertType.ERROR;
			MainView.getInstance().showAlertDialog(alertType, response.getErrorMessage());
		});
	}
	
	public void loadFoodData(Food food) {
		if(food == null) return;
		
		this.idDataLabel.setText(food.getId().toString());
		this.nameDataLabel.setText(food.getName());
		this.typeDataLabel.setText(food.getType());
		this.priceDataLabel.setText(food.getPrice().toString());
		this.stockDataLabel.setText(food.getStock().toString());
		
		setAmountSpinnerLimit(food.getStock());
	}
	
	public void setAmountSpinnerLimit(Integer limit) {
		if(limit <= 0) {
			this.amountSpinner.setValueFactory(new IntegerSpinnerValueFactory(0, limit));
			this.amountSpinner.getValueFactory().setValue(0);
			return;
		}
		this.amountSpinner.setValueFactory(new IntegerSpinnerValueFactory(1, limit));
		this.amountSpinner.getValueFactory().setValue(1);
	}
	
	public void reset() {
		this.foodTable.reset();
		this.cartItemTable.reset();
	}
}
