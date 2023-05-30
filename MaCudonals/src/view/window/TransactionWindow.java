package view.window;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.TableRow;
import javafx.scene.layout.BorderPane;
import model.TransactionDetail;
import model.TransactionHeader;
import repository.TransactionDetailRepository;
import view.MainView;
import view.table.TransactionDetailTable;
import view.table.TransactionHeaderTable;

public class TransactionWindow extends BaseWindow {
	public static final String TITLE = "Transactions";
	
	private static TransactionWindow instance;
	
	public static TransactionWindow getInstance() {
		if(instance == null) {
			instance = new TransactionWindow();
		}
		return instance;
	}
	
	public static void destroy() {
		instance = null;
		MainView.getInstance().clearContent();
	}
	
	private BorderPane borderPane;
	
	private TransactionHeaderTable transactionHeaderTable;
	
	private TransactionDetailTable transactionDetailTable;
		
	public TransactionWindow() {
		super(TITLE);
		
		this.borderPane = new BorderPane();
		this.setContentPane(borderPane);
		
		this.transactionHeaderTable = new TransactionHeaderTable();
		this.transactionHeaderTable.setMaxHeight(MainView.HEIGHT * 0.45);
		this.borderPane.setTop(transactionHeaderTable);
		BorderPane.setAlignment(transactionHeaderTable, Pos.CENTER);
		
		this.transactionDetailTable = new TransactionDetailTable();
		this.transactionDetailTable.setMaxHeight(MainView.HEIGHT * 0.45);
		this.borderPane.setBottom(transactionDetailTable);
		BorderPane.setAlignment(transactionDetailTable, Pos.CENTER);
		
		this.setEventHandlers();
	}

	@Override
	public void setEventHandlers() {
		this.closeIcon.setOnAction(e -> {
			TransactionWindow.destroy();
		});
		
		this.transactionHeaderTable.setRowFactory(tv -> {
			TableRow<TransactionHeader> row = new TableRow<>();
			row.setOnMouseClicked(e -> {
				loadTransactionDetails(row.getItem().getTransactionId());
			});
			return row;
		});
	}
	
	public void loadTransactionDetails(Integer transactionId) {
		List<TransactionDetail> transactionDetailList = TransactionDetailRepository.findByTransactionId(transactionId);
		transactionDetailTable.setItems(FXCollections.observableArrayList(transactionDetailList));
	}
}
