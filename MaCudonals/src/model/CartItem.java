package model;

public class CartItem {
	private Integer foodId;
	private String foodName;
	private Integer foodPrice;
	private Integer quantity;
	private Integer totalPrice;
	
	public CartItem(Integer foodId, String foodName, Integer quantity, Integer foodPrice, Integer totalPrice) {
		super();
		this.foodId = foodId;
		this.foodName = foodName;
		this.quantity = quantity;
		this.foodPrice = foodPrice;
		this.totalPrice = totalPrice;
	}

	public Integer getFoodId() {
		return foodId;
	}

	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(Integer foodPrice) {
		this.foodPrice = foodPrice;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
}
