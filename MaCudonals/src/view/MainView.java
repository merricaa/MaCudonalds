package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import session.Session;
import view.menu.AccountMenu;
import view.menu.ActionMenu;

public class MainView extends BaseView {
	public static final Double WIDTH = 1000d;
	public static final Double HEIGHT = 625d;
	
	private static MainView instance;
	
	public static MainView getInstance() {
		if(instance == null) {
			instance = new MainView(WIDTH, HEIGHT);
		}
		return instance;
	}
	
	public static void destroy() {
		instance = null;
	}
	
	private MenuBar menuBar;
	
	private ActionMenu actionMenu;
	private AccountMenu accountMenu;
	private Image image;
	private FileInputStream src;
	

	public MainView(Double width, Double height) {
		super(width, height);
		
		this.menuBar = new MenuBar();
		this.borderPane.setTop(menuBar);
		this.borderPane.setStyle("-fx-background-color: #DA1212;");
		String sessionRole = "";
		if(Session.getInstance().getUser() != null) {
			sessionRole = Session.getInstance().getUser().getRole();
		}
		
		this.actionMenu = new ActionMenu(sessionRole);
		this.menuBar.getMenus().add(actionMenu);
		
		this.accountMenu = new AccountMenu();
		this.menuBar.getMenus().add(accountMenu);
//		try {
//			this.src = new FileInputStream("D:\\01.1ASLAB 22-1\\03. CASE MAKING\\BAD\\!Upload\\Hasil Case Making\\Answer");
//		} 
//		catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		this.image = new Image(src);
//		this.setContent(new ImageView(image));
//		
		setEventHandlers();
	}
	
	public void setContent(Node content) {
		this.borderPane.setCenter(content);
	}
	
	public void clearContent() {
		this.setContent(new ImageView(image));
	}

	@Override
	public void setEventHandlers() { }
}
