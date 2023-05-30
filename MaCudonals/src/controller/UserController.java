package controller;

import model.ResponseSchema;
import model.User;
import repository.UserRepository;

public class UserController {
	public static final Integer SUCCESS_ERROR_CODE = 1;
	public static final Integer INVALID_DATA_ERROR_CODE = 999;
	
	public static final String LOGIN_SUCCESS_ERROR_MESSAGE = "Logged in!";
	public static final String LOGIN_FAIL_ERROR_MESSAGE = "Invalid email/password!";
	public static final String REGISTRATION_SUCCESS_ERROR_MESSAGE = "Registration successful!";
	public static final String INVALID_USERNAME_ERROR_MESSAGE = "Username must be between 5 - 20 characers!";
	public static final String INVALID_PASSWORD_ERROR_MESSAGE = "Password must be between 5 - 20 characers!";
	public static final String INVALID_EMAIL_FORMAT_ERROR_MESSAGE = "Email format is not valid!";
	public static final String EMAIL_NOT_UNIQUE_ERROR_MESSAGE = "Email has already been used!";
	public static final String INVALID_PHONE_NUMBER_ERROR_MESSAGE = "Phone number must be numeric!";
	public static final String INVALID_ADDRESS_ERROR_MESSAGE = "Address must ends with \'street\'";
	public static final String INVALID_GENDER_ERROR_MESSAGE = "Must select gender!";
	public static final String UNEXPECTED_ERROR_MESSAGE = "Unexpected error encountered!";
	
	public static ResponseSchema register(User user) {
		ResponseSchema response = new ResponseSchema(INVALID_DATA_ERROR_CODE, REGISTRATION_SUCCESS_ERROR_MESSAGE);
		
		if(!User.isUsernameValid(user.getUsername())) {
			response.setErrorMessage(INVALID_USERNAME_ERROR_MESSAGE);
		}
		else if(!User.isEmailValid(user.getEmail())) {
			response.setErrorMessage(INVALID_EMAIL_FORMAT_ERROR_MESSAGE);
		}
		else if(!User.isEmailUnique(user.getEmail())) {
			response.setErrorMessage(EMAIL_NOT_UNIQUE_ERROR_MESSAGE);
		}
		else if(!User.isPasswordValid(user.getPassword())) {
			response.setErrorMessage(INVALID_PASSWORD_ERROR_MESSAGE);
		}
		else if(!User.isGenderValid(user.getGender())) {
			response.setErrorMessage(INVALID_GENDER_ERROR_MESSAGE);
		}
		else if(!User.isPhoneNumberValid(user.getPhoneNumber())) {
			response.setErrorMessage(INVALID_PHONE_NUMBER_ERROR_MESSAGE);
		}
		else if(!User.isAddressValid(user.getAddress())) {
			response.setErrorMessage(INVALID_ADDRESS_ERROR_MESSAGE);
		}
		
		if(response.getErrorMessage().equals(REGISTRATION_SUCCESS_ERROR_MESSAGE)) {
			response.setErrorCode(SUCCESS_ERROR_CODE);
			
			if(UserRepository.insert(user) == null) {
				response.setErrorCode(INVALID_DATA_ERROR_CODE);
				response.setErrorMessage(UNEXPECTED_ERROR_MESSAGE);
			}
		}
		
		return response;
	}
	
	public static ResponseSchema login(String email, String password) {
		ResponseSchema response = new ResponseSchema(INVALID_DATA_ERROR_CODE, LOGIN_FAIL_ERROR_MESSAGE);
		
		for(User u: UserRepository.findAll()) {
			if(u.getEmail().equals(email) && u.getPassword().equals(password)) {
				response.setErrorCode(SUCCESS_ERROR_CODE);
				response.setErrorMessage(LOGIN_SUCCESS_ERROR_MESSAGE);
				break;
			}
		}
		
		return response;
	}
}
