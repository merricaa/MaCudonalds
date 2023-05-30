 package model;

import java.util.Arrays;
import java.util.List;

import repository.UserRepository;
import util.Utility;

public class User {
	public static final List<String> NATIONALITY_LIST = Arrays.asList(
			"Indonesia", "Malaysia", "Korea", "Japan", "USA", "United Kingdom",
			"Singapore", "China", "Mexico", "South Africa", "Morrocco", "Italy");
	
	public static final String USER_ROLE = "USER";
	
	private Integer id;
	private String username;
	private String password;
	private String email;
	private String phoneNumber;
	private String address;
	private String gender;
	private String role;
	private String nationality;
	
	public User(Integer id, String username, String password, String email, String phoneNumber, String address,
			String gender, String role, String nationality) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.gender = gender;
		this.role = role;
		this.nationality = nationality;
	}

	public static Boolean isUsernameValid(String username) {
		return (username.length() >= 5 && username.length() <= 20);
	}
	
	public static Boolean isPasswordValid(String password) {
		return (password.length() >= 5 && password.length() <= 20) 
				&& Utility.isAlphanumeric(password);
	}
	
	public static Boolean isEmailValid(String email) {
		if(email.split("@").length > 2) {
			return false;
		}
		
		return email.endsWith(".com");
	}
	
	public static Boolean isEmailUnique(String email) {
		for(User u: UserRepository.findAll()) {
			if(u.getEmail().equals(email)) {
				return false;
			}
		}
		return true;
	}
	
	public static Boolean isPhoneNumberValid(String phoneNumber) {
		return (!phoneNumber.isEmpty() && Utility.isNumeric(phoneNumber));
	}
	
	public static Boolean isAddressValid(String address) {
		return address.endsWith("street");
	}
	
	public static Boolean isGenderValid(String gender) {
		return (gender.equalsIgnoreCase("MALE") || gender.equalsIgnoreCase("FEMALE"));
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
}