package util;

public class Utility {
	public static Boolean isAlphanumeric(String s) {
		for(Character c: s.toCharArray()) {
			if(!Character.isAlphabetic(c) && !Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}
	
	public static Boolean isNumeric(String s) {
		for(Character c: s.toCharArray()) {
			if(!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}
}
