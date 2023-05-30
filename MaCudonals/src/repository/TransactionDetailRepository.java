package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Database;
import model.TransactionDetail;

public class TransactionDetailRepository {
	public static List<TransactionDetail> findByTransactionId(Integer transactionId) {
		List<TransactionDetail> transactionDetailList = new ArrayList<>();
		
		PreparedStatement s;
		try {
			s = Database.getConnection().prepareStatement(
					"SELECT * FROM `transaction_detail` WHERE `transaction_id` = ?");
			s.setInt(1, transactionId);
			
			ResultSet rs = s.executeQuery();
			while(rs.next()) {
				Integer foodId = rs.getInt("food_id");
				Integer quantity = rs.getInt("quantity");
				Integer totalPrice = rs.getInt("total_price");
				
				TransactionDetail td = new TransactionDetail(transactionId, foodId, quantity, totalPrice);
				transactionDetailList.add(td);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return transactionDetailList;
	}
	
	public static TransactionDetail save(TransactionDetail transactionDetail) {
		try {
			PreparedStatement s = Database.getConnection().prepareStatement(
					"INSERT INTO `transaction_detail`(`transaction_id`, `food_id`, `quantity`, `total_price`) " + 
					"VALUES (?, ?, ?, ?)");
			s.setInt(1, transactionDetail.getTransactionId());
			s.setInt(2, transactionDetail.getFoodId());
			s.setInt(3, transactionDetail.getQuantity());
			s.setInt(4, transactionDetail.getTotalPrice());
			
			if(s.executeUpdate() == 1) {
				return transactionDetail;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static List<TransactionDetail> saveAll(List<TransactionDetail> transactionDetailList) {
		try {
			for(TransactionDetail td: transactionDetailList) {
				PreparedStatement s = Database.getConnection().prepareStatement(
						"INSERT INTO `transaction_detail`(`transaction_id`, `food_id`, `quantity`, `total_price`) " + 
						"VALUES (?, ?, ?, ?)");
				s.setInt(1, td.getTransactionId());
				s.setInt(2, td.getFoodId());
				s.setInt(3, td.getQuantity());
				s.setInt(4, td.getTotalPrice());
				
				s.executeUpdate();
			}
			
			return transactionDetailList;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
