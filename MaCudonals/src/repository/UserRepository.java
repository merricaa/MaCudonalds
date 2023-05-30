package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Database;
import model.User;

public class UserRepository {
	public static List<User> findAll() {
		List<User> userList = new ArrayList<>();
		
		try {
			PreparedStatement s = Database.getConnection().prepareStatement(
					"SELECT * FROM `user`");
			
			ResultSet rs = s.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String phoneNumber = rs.getString("phone_number");
				String address = rs.getString("address");
				String gender = rs.getString("gender");
				String role = rs.getString("role");
				String nationality = rs.getString("nationality");
				
				User u = new User(id, username, password, email, phoneNumber, address, gender, role, nationality);
				userList.add(u);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userList;
	}
	
	public static User findById(Integer id) {
		try {
			PreparedStatement s = Database.getConnection().prepareStatement(
					"SELECT * FROM `user` WHERE `id` = ?");
			s.setInt(1, id);
			
			ResultSet rs = s.executeQuery();
			if(rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String phoneNumber = rs.getString("phone_number");
				String address = rs.getString("address");
				String gender = rs.getString("gender");
				String role = rs.getString("role");
				String nationality = rs.getString("nationality");
				
				return new User(id, username, password, email, phoneNumber, address, gender, role, nationality);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static User findByEmail(String email) {
		try {
			PreparedStatement s = Database.getConnection().prepareStatement(
					"SELECT * FROM `user` WHERE `email` = ?");
			s.setString(1, email);
			
			ResultSet rs = s.executeQuery();
			if(rs.next()) {
				Integer id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String phoneNumber = rs.getString("phone_number");
				String address = rs.getString("address");
				String gender = rs.getString("gender");
				String role = rs.getString("role");
				String nationality = rs.getString("nationality");
				
				return new User(id, username, password, email, phoneNumber, address, gender, role, nationality);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static User insert(User user) {
		try {
			PreparedStatement s = Database.getConnection().prepareStatement(
					"INSERT INTO `user` (`username`, `password`, `email`, `phone_number`, `address`, `gender`, `role`, `nationality`) " +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			s.setString(1, user.getUsername());
			s.setString(2, user.getPassword());
			s.setString(3, user.getEmail());
			s.setString(4, user.getPhoneNumber());
			s.setString(5, user.getAddress());
			s.setString(6, user.getGender());
			s.setString(7, user.getRole());
			s.setString(8, user.getNationality());
			
			if(s.executeUpdate() != 0) {
				return user;
			};
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
