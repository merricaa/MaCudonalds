package session;

import model.User;

public class Session {
	private static Session instance;
	
	public static Session getInstance() {
		if(instance == null) {
			instance = new Session();
		}
		return instance;
	}
	
	public static Session createSession(User user) {
		Session.getInstance().setUser(user);
		return Session.getInstance();
	}
	
	private User user;
	
	public Session() { }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
