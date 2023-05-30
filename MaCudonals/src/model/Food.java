package model;

import java.util.Arrays;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Food {
	public static List<String> TYPE_LIST = Arrays.asList(
			"Appetizer", "Main dish", "Desert", "Side dish", "Drink");
	
	private Integer id;
	private String name;
	private String type;
	private Integer price;
	private Integer stock;
	
	public Food(Integer id, String name, String type, Integer price, Integer stock) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
		this.stock = stock;
	}
	
	public static Boolean isNameValid(String name) {
		return (name.length() >=5 && name.length() <= 30);
	}
	
	public static Boolean isStockValid(Integer stock) {
		return (stock > 0);
	}
	
	public static Boolean isPriceValid(Integer price) {
		return (price > 0);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public IntegerProperty stockProperty() {
		return new SimpleIntegerProperty(stock);
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
}
