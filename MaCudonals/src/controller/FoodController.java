package controller;

import model.Food;
import model.ResponseSchema;
import repository.FoodRepository;

public class FoodController {
	public static Integer SUCCESS_ERROR_CODE = 1;
	public static Integer INVALID_DATA_ERROR_CODE = 999;
	
	public static String INSERT_SUCCESS_ERROR_MESSAGE = "Food inserted successfully";
	public static String UPDATE_SUCCESS_ERROR_MESSAGE = "Food updated successfully";
	public static String DELETE_SUCCESS_ERROR_MESSAGE = "Food deleted successfully";
	public static String INVALID_NAME_ERROR_MESSAGE = "Name must be between 5 - 30 characters";
	public static String INVALID_PRICE_ERROR_MESSAGE = "Price must more than 0";
	public static String INVALID_STOCK_ERROR_MESSAGE = "Stock must more than 0";
	public static String UNEXPECTED_ERROR_MESSAGE = "Enpected error encountered";
	
	public static ResponseSchema insert(Food food) {
		ResponseSchema response = new ResponseSchema(INVALID_DATA_ERROR_CODE, INSERT_SUCCESS_ERROR_MESSAGE);
		
		if(!Food.isNameValid(food.getName())) {
			response.setErrorMessage(INVALID_NAME_ERROR_MESSAGE);
			return response;
		}
		else if(!Food.isPriceValid(food.getPrice())) {
			response.setErrorMessage(INVALID_PRICE_ERROR_MESSAGE);
			return response;
		}
		else if(!Food.isStockValid(food.getStock())) {
			response.setErrorMessage(INVALID_STOCK_ERROR_MESSAGE);
			return response;
		}
		
		if(FoodRepository.save(food) == null) {
			response.setErrorMessage(UNEXPECTED_ERROR_MESSAGE);
			return response;
		}
		
		response.setErrorCode(SUCCESS_ERROR_CODE);
		return response;
	}
	
	public static ResponseSchema update(Food food) {
		ResponseSchema response = new ResponseSchema(INVALID_DATA_ERROR_CODE, UPDATE_SUCCESS_ERROR_MESSAGE);
		
		if(!Food.isNameValid(food.getName())) {
			response.setErrorMessage(INVALID_NAME_ERROR_MESSAGE);
			return response;
		}
		else if(!Food.isPriceValid(food.getPrice())) {
			response.setErrorMessage(INVALID_PRICE_ERROR_MESSAGE);
			return response;
		}
		else if(!Food.isStockValid(food.getStock())) {
			response.setErrorMessage(INVALID_STOCK_ERROR_MESSAGE);
			return response;
		}
		
		if(FoodRepository.update(food) == null) {
			response.setErrorMessage(UNEXPECTED_ERROR_MESSAGE);
			return response;
		}
		
		response.setErrorCode(SUCCESS_ERROR_CODE);
		return response;
	}
	
	public static ResponseSchema deleteById(Integer foodId) {
		ResponseSchema response = new ResponseSchema(INVALID_DATA_ERROR_CODE, DELETE_SUCCESS_ERROR_MESSAGE);
		
		if(FoodRepository.deleteById(foodId) == null) {
			response.setErrorMessage(UNEXPECTED_ERROR_MESSAGE);
			return response;
		}
		
		response.setErrorCode(SUCCESS_ERROR_CODE);
		return response;
	}
}
