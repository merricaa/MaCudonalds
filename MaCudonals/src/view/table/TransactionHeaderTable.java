package view.table;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.TransactionHeader;
import model.User;
import repository.TransactionHeaderRepository;
import repository.UserRepository;
import session.Session;
import view.MainView;

public class TransactionHeaderTable extends TableView<TransactionHeader> {
	private final String ID_COLUMN_TEXT = "ID";
	private final String USER_ID_COLUMN_TEXT = "User ID";
	private final String USER_NAME_COLUMN_TEXT = "User name";
	private final String USER_ROLE_COLUMN_TEXT = "User role";
	private final String DATE_COLUMN_TEXT = "Date";

	private TableColumn<TransactionHeader, Integer> idColumn;
	private TableColumn<TransactionHeader, Integer> userIdColumn;
	private TableColumn<TransactionHeader, String> userNameColumn;
	private TableColumn<TransactionHeader, String> userRoleColumn;
	private TableColumn<TransactionHeader, String> dateColumn;
	
	private List<TransactionHeader> transactionHeaderList;

	public TransactionHeaderTable() {
		this.idColumn = new TableColumn<>(ID_COLUMN_TEXT);
		this.idColumn.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
		this.idColumn.setPrefWidth(MainView.WIDTH * 0.1);
		this.getColumns().add(idColumn);

		this.userIdColumn = new TableColumn<>(USER_ID_COLUMN_TEXT);
		this.userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
		this.userIdColumn.setPrefWidth(MainView.WIDTH * 0.2);
		this.getColumns().add(userIdColumn);

		this.userNameColumn = new TableColumn<>(USER_NAME_COLUMN_TEXT);
		this.userNameColumn.setCellValueFactory(cellData -> {
			Integer userId = cellData.getValue().getUserId();

			User user = UserRepository.findById(userId);
			String userName = user.getUsername();

			return new SimpleStringProperty(userName);
		});
		this.userNameColumn.setPrefWidth(MainView.WIDTH * 0.3);
		this.getColumns().add(userNameColumn);

		this.userRoleColumn = new TableColumn<>(USER_ROLE_COLUMN_TEXT);
		this.userRoleColumn.setCellValueFactory(cellData -> {
			Integer userId = cellData.getValue().getUserId();

			User user = UserRepository.findById(userId);
			String userRole = user.getRole();

			return new SimpleStringProperty(userRole);
		});
		this.userRoleColumn.setPrefWidth(MainView.WIDTH * 0.2);
		this.getColumns().add(userRoleColumn);

		this.dateColumn = new TableColumn<>(DATE_COLUMN_TEXT);
		this.dateColumn.setCellValueFactory(cellData -> {
			Timestamp dateTimestamp = cellData.getValue().getDate();
			String dateTimeString = dateTimestamp.toLocalDateTime().format(DateTimeFormatter.ISO_DATE);

			return new SimpleStringProperty(dateTimeString);
		});
		this.dateColumn.setPrefWidth(MainView.WIDTH * 0.2);
		this.getColumns().add(dateColumn);
		
		loadData();
	}
	
	public void loadData() {
		if(transactionHeaderList == null) {
			if(Session.getInstance().getUser().getRole().equalsIgnoreCase(User.USER_ROLE)) {
				Integer userId = Session.getInstance().getUser().getId();
				this.transactionHeaderList = TransactionHeaderRepository.findByUserId(userId);
			}
			else {
				this.transactionHeaderList = TransactionHeaderRepository.findAll();
			}
		}
		this.setItems(FXCollections.observableArrayList(transactionHeaderList));
	}
}
