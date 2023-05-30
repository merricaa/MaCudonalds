package view;


import app.MacuDonals;
import controller.UserController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.ResponseSchema;
import model.User;
import repository.UserRepository;
import session.Session;

public class LoginView extends BaseView {
	public static final Double WIDTH = 400d;
	public static final Double HEIGHT = 250d;
	
	private static LoginView instance;
	
	public static LoginView getInstance() {
		if(instance == null) {
			instance = new LoginView(WIDTH, HEIGHT);
		}
		return instance;
	}
	
	private final Double H_GAP = WIDTH / 15d;
	private final Double V_GAP = HEIGHT / 15d;
	
	private final String TITLE_LABEL_TEXT = "LOGIN";
	private final String EMAIL_LABEL_TEXT = "Email";
	private final String PASSWORD_LABEL_TEXT = "Password";
	private final String REGISTER_BUTTON_TEXT = "Don't have account? Register here";
	private final String LOGIN_BUTTON_TEXT = "Login";

	
	private GridPane gridPane;
	
	private Label titleLabel;
	private Label emailLabel;
	private Label passwordLabel;
	
	private TextField emailTextField;
	
	private PasswordField passwordField;
	
	private VBox buttonContainer;
	
	private Hyperlink registerButton;
	private Button loginButton;

	
	public LoginView(Double width, Double height) {
		super(width, height);
		
		this.borderPane.setPadding(new Insets(V_GAP));
		this.borderPane.setStyle("-fx-background-color: #F9D923;");
		this.gridPane = new GridPane();
		this.gridPane.setHgap(H_GAP);
		this.gridPane.setVgap(V_GAP);
		this.gridPane.setAlignment(Pos.CENTER);
		this.borderPane.setCenter(gridPane);
		
		this.titleLabel = new Label(TITLE_LABEL_TEXT);
		this.titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		this.borderPane.setTop(titleLabel);
		BorderPane.setAlignment(titleLabel, Pos.CENTER);
		
		this.emailLabel = new Label(EMAIL_LABEL_TEXT);
		this.gridPane.add(emailLabel, 0, 0);
		
		this.passwordLabel = new Label(PASSWORD_LABEL_TEXT);
		this.gridPane.add(passwordLabel, 0, 1);
		
		this.emailTextField = new TextField();
		this.gridPane.add(emailTextField, 1, 0);

		this.passwordField = new PasswordField();
		this.gridPane.add(passwordField, 1, 1);
		
		this.buttonContainer = new VBox();
		this.buttonContainer.setAlignment(Pos.CENTER);
		this.buttonContainer.setSpacing(H_GAP);
		this.borderPane.setBottom(buttonContainer);
		BorderPane.setAlignment(buttonContainer, Pos.CENTER);
		
		
		this.loginButton = new Button(LOGIN_BUTTON_TEXT);
		this.buttonContainer.getChildren().add(this.loginButton);
		this.loginButton.setStyle("-fx-background-color: #ff0000;-fx-text-fill: #ffffff ;");

		
		this.registerButton = new Hyperlink(REGISTER_BUTTON_TEXT);
		this.buttonContainer.getChildren().add(this.registerButton);
		
		
		setEventHandlers();
	}



	@Override
	public void setEventHandlers() {
		this.registerButton.setOnAction(e -> {
			MacuDonals.getInstance().changeScene(RegisterView.getInstance());
		});
		
		this.loginButton.setOnAction(e -> {
			String email = emailTextField.getText();
			String password = passwordField.getText();
			
			ResponseSchema response = UserController.login(email, password);
			
			if(response.getErrorCode() == UserController.INVALID_DATA_ERROR_CODE) {
				showAlertDialog(AlertType.ERROR, response.getErrorMessage());
				return;
			}
			
			User user = UserRepository.findByEmail(email);
			Session.createSession(user);;
			
			MacuDonals.getInstance().changeScene(MainView.getInstance());
		});
	}
}
