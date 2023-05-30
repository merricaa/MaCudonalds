package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import database.Database;
import model.TransactionHeader;

public class TransactionHeaderRepository {
	public static List<TransactionHeader> findAll() {
		List<TransactionHeader> transactionHeaderList = new ArrayList<>();
		
		try {
			PreparedStatement s = Database.getConnection().prepareStatement(
					"SELECT * FROM `transaction_header`");
			
			ResultSet rs = s.executeQuery();
			while(rs.next()) {
				Integer transactionId = rs.getInt("transaction_id");
				Integer userId = rs.getInt("user_id");
				Timestamp date = rs.getTimestamp("date");
				
				TransactionHeader th = new TransactionHeader(transactionId, userId, date);
				transactionHeaderList.add(th);
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return transactionHeaderList;
	}
	
	public static TransactionHeader findByTransactionId(Integer transactionId) {
		try {
			PreparedStatement s = Database.getConnection().prepareStatement(
					"SELECT * FROM `transaction_header` WHERE `transaction_id` = ?");
			s.setInt(1, transactionId);
			
			ResultSet rs = s.executeQuery();
			if(rs.next()) {
				Integer userId = rs.getInt("user_id");
				Timestamp date = rs.getTimestamp("date");
				
				return new TransactionHeader(transactionId, userId, date);
			}
			
			return null;
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static List<TransactionHeader> findByUserId(Integer userId) {
		List<TransactionHeader> transactionHeaderList = new ArrayList<>();
		
		try {
			PreparedStatement s = Database.getConnection().prepareStatement(
					"SELECT * FROM `transaction_header` WHERE `user_id` = ?");
			s.setInt(1, userId);
			
			ResultSet rs = s.executeQuery();
			while(rs.next()) {
				Integer transactionId = rs.getInt("transaction_id");
				Timestamp date = rs.getTimestamp("date");
				
				TransactionHeader th = new TransactionHeader(transactionId, userId, date);
				transactionHeaderList.add(th);
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return transactionHeaderList;
	}
	
	public static TransactionHeader save(TransactionHeader transactionHeader) {
		try {
			PreparedStatement s = Database.getConnection().prepareStatement(
					"INSERT INTO `transaction_header`(`user_id`, `date`) " + 
					"VALUES (?, ?)");
			s.setInt(1, transactionHeader.getUserId());
			s.setTimestamp(2, transactionHeader.getDate());
			
			if(s.executeUpdate() == 1) {
				return transactionHeader;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
