package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Database;
import model.Food;

public class FoodRepository {
	public static List<Food> findAll() {
		List<Food> foodList = new ArrayList<>();
		
		try {
			PreparedStatement s = Database.getConnection().prepareStatement(
					"SELECT * FROM `food`");

			ResultSet rs = s.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String type = rs.getString("type");
				Integer price = rs.getInt("price");
				Integer stock = rs.getInt("stock");
				
				Food f = new Food(id, name, type, price, stock);
				foodList.add(f);
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return foodList;
	}
	
	public static Food findById(Integer id) {
		try {
			PreparedStatement s = Database.getConnection().prepareStatement(
					"SELECT * FROM `food` WHERE `id` = ?");
			s.setInt(1, id);
			
			ResultSet rs = s.executeQuery();
			if(rs.next()) {
				String name = rs.getString("name");
				String type = rs.getString("type");
				Integer price = rs.getInt("price");
				Integer stock = rs.getInt("stock");
				
				return new Food(id, name, type, price, stock);
			}
			
			return null;
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static Food save(Food food) {
		PreparedStatement s;
		try {
			s = Database.getConnection().prepareStatement(
					"INSERT INTO `food`(`name`, `type`, `price`, `stock`) " + 
					"VALUES (?, ?, ?, ?)");
			s.setString(1, food.getName());
			s.setString(2, food.getType());
			s.setInt(3, food.getPrice());
			s.setInt(4, food.getStock());
			
			if(s.executeUpdate() == 1) {
				return food;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static Food update(Food food) {
		PreparedStatement s;
		try {
			s = Database.getConnection().prepareStatement(
					"UPDATE `food` SET `name`=?, `type`=?, `price`=?, `stock`=? " + 
					"WHERE `id`=?");
			s.setString(1, food.getName());
			s.setString(2, food.getType());
			s.setInt(3, food.getPrice());
			s.setInt(4, food.getStock());
			s.setInt(5, food.getId());
			
			if(s.executeUpdate() == 1) {
				return food;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static List<Food> updateAll(List<Food> foodList) {
		try {
			for(Food f: foodList) {
				PreparedStatement s = Database.getConnection().prepareStatement(
						"UPDATE `food` SET `name`=?, `type`=?, `price`=?, `stock`=? " + 
						"WHERE `id`=?");
				s.setString(1, f.getName());
				s.setString(2, f.getType());
				s.setInt(3, f.getPrice());
				s.setInt(4, f.getStock());
				s.setInt(5, f.getId());
				
				s.executeUpdate();
			}
			
			return foodList;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static Integer deleteById(Integer id) {
		PreparedStatement s;
		try {
			s = Database.getConnection().prepareStatement(
					"DELETE FROM `food` WHERE `id`=?");
			s.setInt(1, id);
			
			if(s.executeUpdate() == 1) {
				return id;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
