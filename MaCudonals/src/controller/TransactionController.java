package controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import model.CartItem;
import model.Food;
import model.ResponseSchema;
import model.TransactionDetail;
import model.TransactionHeader;
import repository.FoodRepository;
import repository.TransactionDetailRepository;
import repository.TransactionHeaderRepository;
import session.Session;

public class TransactionController {
	public static final Integer SUCCESS_ERROR_CODE = 1;
	public static final Integer INVALID_DATA_ERROR_CODE = 999;
	
	public static final String SUCCESS_CHECKOUT_ERROR_MESSAGE = "Checkout successful"; 
	public static final String EMPTY_CART_ERROR_MESSAGE = "Cart is empty";
	
	public static ResponseSchema checkout(List<Food> foodList, List<CartItem> cartItemList) {
		if(cartItemList.isEmpty()) {
			return new ResponseSchema(INVALID_DATA_ERROR_CODE, EMPTY_CART_ERROR_MESSAGE);
		}
		
		Integer userId = Session.getInstance().getUser().getId();
		Timestamp date = Timestamp.from(Instant.now());
		TransactionHeader transactionHeader = new TransactionHeader(null, userId, date);
		TransactionHeaderRepository.save(transactionHeader);
		
		Integer transactionId = TransactionHeaderRepository.findAll().size();
		for(CartItem ci: cartItemList) {
			Integer foodId = ci.getFoodId();
			Integer quantity = ci.getQuantity();
			Integer totalPrice = ci.getTotalPrice();
			TransactionDetail transactionDetail = new TransactionDetail(transactionId, foodId, quantity, totalPrice);
			
			TransactionDetailRepository.save(transactionDetail);
		}
		
		FoodRepository.updateAll(foodList);
		
		return new ResponseSchema(SUCCESS_ERROR_CODE, SUCCESS_CHECKOUT_ERROR_MESSAGE);
	}
}
