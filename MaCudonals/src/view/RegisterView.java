package view;

import app.MacuDonals;
import controller.UserController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.ResponseSchema;
import model.User;
import session.Session;

public class RegisterView extends BaseView {
	public static final Double WIDTH = 1000d;
	public static final Double HEIGHT = 625d;

	private static RegisterView instance;
	
	public static RegisterView getInstance() {
		if(instance == null) {
			instance = new RegisterView(WIDTH, HEIGHT);
		}
		return instance;
	}
	
	private final Double H_GAP = WIDTH / 25d;
	private final Double V_GAP = HEIGHT / 25d;
	
	private GridPane gridPane;
	
	private final String TITLE_LABEL_TEXT = "REGISTER";
	private final String NAME_LABEL_TEXT = "Name";
	private final String EMAIL_LABEL_TEXT = "Email";
	private final String PASSWORD_LABEL_TEXT = "Password";
	private final String CONFIRM_PASSWORD_LABEL_TEXT = "Confirm Password";
	private final String GENDER_LABEL_TEXT = "Gender";
	private final String MALE_RADIO_BUTTON_TEXT = "Male";
	private final String FEMALE_RADIO_BUTTON_TEXT = "Female";
	private final String PHONE_NUMBER_LABEL_TEXT = "Phone Number";
	private final String ADDRESS_LABEL_TEXT = "Address";
	private final String NATIONALITY_LABEL_TEXT = "Nationality";
	private final String REGISTER_BUTTON_TEXT = "Register";
	private final String LOGIN_BUTTON_TEXT = "Already have account? Login here!";
	
	private Label titleLabel;
	private Label nameLabel;
	private Label emailLabel;
	private Label passwordLabel;
	private Label confirmPasswordLabel;
	private Label genderLabel;
	private Label phoneNumberLabel;
	private Label addressLabel;
	private Label nationalityLabel;
	
	private TextField nameTextField;
	private TextField emailTextField;
	private TextField phoneNumberTextField;
	
	private TextArea addressTextArea;
	
	private PasswordField passwordField;
	private PasswordField confirmPasswordField;
	
	private HBox genderButtonContainer;
	
	private ToggleGroup genderButtonGroup;	
	
	private RadioButton maleRadioButton;
	private RadioButton femaleRadioButton;
	
	private ComboBox<String> nationalityComboBox;
	
	private VBox buttonContainer;
	
	private Button registerButton;
	private Hyperlink loginButton;
	
	public RegisterView(Double width, Double height) {
		super(width, height);
		
		this.borderPane.setPadding(new Insets(V_GAP));
		this.borderPane.setStyle("-fx-background-color: #FFD93D;");
		
		this.gridPane = new GridPane();
		this.gridPane.setHgap(H_GAP);
		this.gridPane.setVgap(V_GAP);
		this.gridPane.setAlignment(Pos.CENTER);
		this.borderPane.setCenter(gridPane);
		
		this.titleLabel = new Label(TITLE_LABEL_TEXT);
		this.titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		this.borderPane.setTop(titleLabel);
		BorderPane.setMargin(titleLabel, new Insets(V_GAP));
		BorderPane.setAlignment(titleLabel, Pos.CENTER);
		
		this.nameLabel = new Label(NAME_LABEL_TEXT);
		this.gridPane.add(nameLabel, 0, 0);
		
		this.emailLabel = new Label(EMAIL_LABEL_TEXT);
		this.gridPane.add(emailLabel, 0, 1);
		
		this.passwordLabel = new Label(PASSWORD_LABEL_TEXT);
		this.gridPane.add(passwordLabel, 0, 2);
		
		this.confirmPasswordLabel = new Label(CONFIRM_PASSWORD_LABEL_TEXT);
		this.gridPane.add(confirmPasswordLabel, 0, 3);
		
		this.genderLabel = new Label(GENDER_LABEL_TEXT);
		this.gridPane.add(genderLabel, 0, 4);
		
		this.phoneNumberLabel = new Label(PHONE_NUMBER_LABEL_TEXT);
		this.gridPane.add(phoneNumberLabel, 0, 5);
		
		this.addressLabel = new Label(ADDRESS_LABEL_TEXT);
		this.gridPane.add(addressLabel, 0, 6);
		
		this.nationalityLabel = new Label(NATIONALITY_LABEL_TEXT);
		this.gridPane.add(nationalityLabel, 0, 7);
		
		this.nameTextField = new TextField();
		this.nameTextField.setPrefWidth(WIDTH * 0.4);
		this.gridPane.add(nameTextField, 1, 0);
		
		this.emailTextField = new TextField();
		this.emailTextField.setPrefWidth(WIDTH * 0.4);
		this.gridPane.add(emailTextField, 1, 1);
		
		this.passwordField = new PasswordField();
		this.passwordField.setPrefWidth(WIDTH * 0.4);
		this.gridPane.add(passwordField, 1, 2);
		
		this.confirmPasswordField = new PasswordField();
		this.confirmPasswordField.setPrefWidth(WIDTH * 0.4);
		this.gridPane.add(confirmPasswordField, 1, 3);
		
		this.genderButtonContainer = new HBox();
		this.genderButtonContainer.setAlignment(Pos.CENTER);
		this.genderButtonContainer.setSpacing(H_GAP);
		this.gridPane.add(genderButtonContainer, 1, 4);
		BorderPane.setAlignment(genderButtonContainer, Pos.CENTER);
		
		this.genderButtonGroup = new ToggleGroup();
		
		this.maleRadioButton = new RadioButton(MALE_RADIO_BUTTON_TEXT);
		this.maleRadioButton.setToggleGroup(genderButtonGroup);
		this.genderButtonContainer.getChildren().add(maleRadioButton);
		
		this.femaleRadioButton = new RadioButton(FEMALE_RADIO_BUTTON_TEXT);
		this.femaleRadioButton.setToggleGroup(genderButtonGroup);
		this.genderButtonContainer.getChildren().add(femaleRadioButton);
		
		this.phoneNumberTextField = new TextField();
		this.phoneNumberTextField.setPrefWidth(WIDTH * 0.4);
		this.gridPane.add(phoneNumberTextField, 1, 5);
		
		this.addressTextArea = new TextArea();
		this.addressTextArea.setPrefWidth(WIDTH * 0.4);
		this.gridPane.add(addressTextArea, 1, 6);
		
		this.nationalityComboBox = new ComboBox<>();
		User.NATIONALITY_LIST.sort((s1, s2) -> {
			return s1.compareTo(s2);
		});
		this.nationalityComboBox.setItems(FXCollections.observableArrayList(User.NATIONALITY_LIST));
		this.nationalityComboBox.setPrefWidth(WIDTH * 0.4);
		this.gridPane.add(nationalityComboBox, 1, 7);
		
		this.buttonContainer = new VBox();
		this.buttonContainer.setAlignment(Pos.CENTER);
		this.buttonContainer.setSpacing(H_GAP);
		this.borderPane.setBottom(buttonContainer);
		BorderPane.setMargin(buttonContainer, new Insets(V_GAP));
		BorderPane.setAlignment(buttonContainer, Pos.CENTER);
	
		
		this.registerButton = new Button(REGISTER_BUTTON_TEXT);
		this.buttonContainer.getChildren().add(this.registerButton);
		this.registerButton.setStyle("-fx-background-color: #ff0000;-fx-text-fill: #ffffff ;");
		this.registerButton.setMaxHeight(100);
		this.registerButton.setMaxWidth(100);
		
		this.loginButton = new Hyperlink(LOGIN_BUTTON_TEXT);
		this.buttonContainer.getChildren().add(this.loginButton);
		
		setEventHandlers();
	}

	@Override
	public void setEventHandlers() {
		this.loginButton.setOnAction(e -> {
			MacuDonals.getInstance().changeScene(LoginView.getInstance());
		});
		
		this.registerButton.setOnAction(e -> {
			String name = nameTextField.getText();
			String email = emailTextField.getText();
			String password = passwordField.getText();
			String confirmPassword = confirmPasswordField.getText();
			String phoneNumber = phoneNumberTextField.getText();
			String address = addressTextArea.getText();
			String nationality = nationalityComboBox.getSelectionModel().getSelectedItem();
			
			RadioButton selectedGender = ((RadioButton) genderButtonGroup.getSelectedToggle());
			String gender = (selectedGender == null) ? "" : selectedGender.getText();
			
			if(!password.equals(confirmPassword)) {
				showAlertDialog(AlertType.ERROR, "Confirm password is not the same as password");
				return;
			}
			if(nationality == null) {
				showAlertDialog(AlertType.ERROR, "Your nationality hasn't been chosen");
				return;
			}
			
			User newUser = new User(null, name, password, email, phoneNumber, address, gender, User.USER_ROLE, nationality);
			ResponseSchema response = UserController.register(newUser);
			if(response.getErrorCode() == UserController.INVALID_DATA_ERROR_CODE) {
				showAlertDialog(AlertType.ERROR, response.getErrorMessage());
				return;
			}
			
			Session.createSession(newUser);
			MacuDonals.getInstance().changeScene(MainView.getInstance());
		});
	}
}
