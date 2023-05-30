package model;

public class TransactionDetail {
	private Integer transactionId;
	private Integer foodId;
	private Integer quantity;
	private Integer totalPrice;
	
	public TransactionDetail(Integer transactionId, Integer foodId, Integer quantity, Integer totalPrice) {
		super();
		this.transactionId = transactionId;
		this.foodId = foodId;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getFoodId() {
		return foodId;
	}

	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
}
